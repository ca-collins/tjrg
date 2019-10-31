(ns tjrg.core
  (:require
   [reagent.core :as r]
   [reagent.session :as session]
   [reitit.frontend :as reitit]
   [clerk.core :as clerk]
   [accountant.core :as accountant]
   [soda-ash.core :as sa]))

;; -------------------------
;; Routes

(def router
  (reitit/router
   [["/" :index]
    ; ["/items"
    ;  ["" :items]
    ;  ["/:item-id" :item]]
    ["/about" :about]]))

(defn path-for [route & [params]]
  (if params
    (:path (reitit/match-by-name router route params))
    (:path (reitit/match-by-name router route))))

;; -------------------------
;; Page components

(defn home-page [{:keys [id]}]
  (fn [{:keys [id]}]
    [sa/Container {:id id :text-align "center" :text true}
     [sa/Header {:size "huge"} "The James River Gazette"]
     [:p "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."]
     [:div {:class "ui vertical buttons"}
      [:a {:href "https://www.paypal.me/jrgazette" :target "_blank"}
       [sa/Button {:type "button"
                   :primary true
                   :icon true
                   :label-position "left"}
        "Donate to the JRG"
        [sa/Icon {:class "like"}]]]
      [:a {:href "/issues/0_2020_01_07_James_River_Gazette_EXAMPLE.pdf"
           :download true}
       [sa/Button {:type "button"
                   :secondary true
                   :icon true
                   :label-position "left"}
        "Download the Latest Issue"
        [sa/Icon {:class "download"}]]]]]))


; (defn items-page []
;   (fn []
;     [:span.main
;      [:h1 "The items of tjrg"]
;      [:ul (map (fn [item-id]
;                  [:li {:name (str "item-" item-id) :key (str "item-" item-id)}
;                   [:a {:href (path-for :item {:item-id item-id})} "Item: " item-id]])
;                (range 1 60))]]))


; (defn item-page []
;   (fn []
;     (let [routing-data (session/get :route)
;           item (get-in routing-data [:route-params :item-id])]
;       [:span.main
;        [:h1 (str "Item " item " of tjrg")]
;        [:p [:a {:href (path-for :items)} "Back to the list of items"]]])))


(defn about-page []
  (fn [] [sa/Container {:text-align "center" :text true}
          [:h1 "About the Gazette"]]))


;; -------------------------
;; Translate routes -> page components

(defn page-for [route]
  (case route
    :index #'home-page
    :about #'about-page))
    ;:items #'items-page
    ;:item #'item-page))


;; -------------------------
;; Page mounting component
(defn navbar []
  (let [active-item (r/atom nil)
        handle-click (fn [event props]
                       (let [name (-> props
                                      (js->clj :keywordize-keys true)
                                      (:name))]
                         (reset! active-item name)))]
    (fn []
      [sa/Menu
         [sa/MenuItem {:header true
                       :href (path-for :index)
                       :on-click handle-click}
           "The James River Gazette"]
         [sa/MenuItem {:name "about"
                       :href (path-for :about)
                       :active (= @active-item "about")
                       :on-click handle-click}]
         [sa/MenuItem {:name "contribute"
                       :href (path-for :issues)
                       :active (= @active-item "contribute")
                       :on-click handle-click}]
         [sa/MenuItem {:name "advertise"
                       :href (path-for :issues)
                       :active (= @active-item "advertise")
                       :on-click handle-click}]])))

(defn current-page []
  (fn []
    (let [page (:current-page (session/get :route))]
      [:<>
       [navbar]
       [page {:id "content"}]])))
;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (clerk/initialize!)
  (accountant/configure-navigation!
   {:nav-handler
    (fn [path]
      (let [match (reitit/match-by-path router path)
            current-page (:name (:data  match))
            route-params (:path-params match)]
        (r/after-render clerk/after-render!)
        (session/put! :route {:current-page (page-for current-page)
                              :route-params route-params})
        (clerk/navigate-page! path)))

    :path-exists?
    (fn [path]
      (boolean (reitit/match-by-path router path)))})
  (accountant/dispatch-current!)
  (mount-root))
