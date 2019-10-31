(ns tjrg.core
  (:require
   [reagent.core :as r]
   [reagent.session :as session]
   [reitit.frontend :as reitit]
   [clerk.core :as clerk]
   [accountant.core :as accountant]
   [soda-ash.core :as sa]
   [goog.string :as g]))
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
    [sa/Container
     ;; HERO ==============================
     [sa/Container {:id id :text-align "center" :text true}
      [sa/Header {:size "huge"} "The James River Gazette"]
      [:p (str "A community-driven newspaper dedicated to connecting the people of the "
               "Middle" (g/unescapeEntities "&nbsp;")
               "James"  (g/unescapeEntities "&nbsp;")
               "River"  (g/unescapeEntities "&nbsp;")
               "Basin"  (g/unescapeEntities "&nbsp;")
               "around the common goal of protecting our local waterways.")]
      [sa/Container
       [sa/Button {:type "button"
                   :size "medium"
                   :basic true
                   :color "large"
                   :href (path-for :about)}
                  "Learn more"]
       [sa/Button {:type "button"
                   :size "large"
                   :primary true}
                  "Subscribe"]]]
     ;; CONTRIBUTE ==============================
     [sa/Container {:id id :text-align "center" :text true}
      [sa/Header {:size "large"} "Contribute"]
      [:p "Odio duis pharetra hendrerit ullamcorper. Consequat urna per odio cum etiam luctus nisl etiam? Dapibus fusce tempus eget fusce consectetur rutrum gravida sapien posuere. "]
      [sa/Container
       [sa/Button {:type "button"
                   :size "medium"
                   :basic true
                   :href (path-for :about)}
                  "Learn how"]]]

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


(defn nav-menu [children]
  (let [active-item (r/atom nil)
        visible (r/atom false)
        handle-click (fn [event props]
                       (let [name (-> props
                                      (js->clj :keywordize-keys true)
                                      (:name))]
                         (reset! active-item name)))]
    (fn [children]
     [sa/SidebarPushable {:as (.-Segment js/semanticUIReact)}
      [sa/Sidebar {:as (.-Menu js/semanticUIReact)
                   :animation "overlay"
                   :align "right"
                   :floated "right"
                   :visible @visible
                   :vertical true
                   :width "thin"
                   :on-hide #(reset! visible (not @visible))}
       [sa/MenuItem {:as "a"
                     :name "home"
                     :href (path-for :index)
                     :active (= @active-item "home")
                     :on-click handle-click}]
       [sa/MenuItem {:as "a"
                     :name "about"
                     :href (path-for :about)
                     :active (= @active-item "about")
                     :on-click handle-click}]
       [sa/MenuItem {:as "a"
                     :name "contribute"
                     :href (path-for :issues)
                     :active (= @active-item "contribute")
                     :on-click handle-click}]
       [sa/MenuItem {:as "a"
                     :name "advertise"
                     :href (path-for :issues)
                     :active (= @active-item "advertise")
                     :on-click handle-click}]]
      [sa/SidebarPusher {:dimmed @visible}
       [sa/Menu
        [sa/MenuItem {:header true
                      :href (path-for :index)
                      :on-click handle-click}
          "The James River Gazette"]
        [sa/MenuItem {:icon "bars"
                      :position "right"
                      :on-click #(reset! visible (not @visible))}]]

       children]])))

(defn current-page []
  (fn []
    (let [page (:current-page (session/get :route))]
      [nav-menu
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
