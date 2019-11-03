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
    ["/about" :about]
    ["/contribute" :contribute]
    ["/advertise" :advertise]]))


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
      ; [:br]
      ; [sa/Button {:href "/issues/0_2020_01_07_James_River_Gazette_EXAMPLE.pdf"
      ;             :download true
      ;             :type "button"
      ;             :secondary true
      ;             :icon true
      ;             :label-position "left"}
      ;            "Download the Latest Issue"
      ;            [sa/Icon {:class "download"}]]]


     ;; GET INVOLVED ==============================
     [sa/Container {:id id :text-align "center" :text true}
      [:br]
      [sa/Header {:size "large"} "Get Involved"]
      [:p "Write an article, submit a joke or comic, give us feedback"]
      [sa/Container
       [sa/Button {:type "button"
                   :size "medium"
                   :basic true
                   :href (path-for :contribute)}
                  "Learn how"]]]


     ;; ADVERTISE ==============================
     [sa/Container {:id id :text-align "center" :text true}
      [:br]
      [sa/Header {:size "large"} "Advertise with Us"]
      [:p "Odio duis pharetra hendrerit ullamcorper. Consequat urna per odio cum etiam luctus nisl etiam? Dapibus fusce tempus eget fusce consectetur rutrum gravida sapien posuere. "]
      [sa/Container
       [sa/Button {:type "button"
                   :size "medium"
                   :basic true
                   :href (path-for :advertise)}
                  "Learn more"]
       [sa/Button {:type "button"
                   :primary true
                   :href (path-for :advertise)}
                  "Contact us"]]]

     ;; Donate ==============================
     [sa/Container {:id id :text-align "center" :text true}
      [:br]
      [sa/Header {:size "large"} "Support the JRG"]
      [:p "Please consider supporting the JRG with a small donation.
           Proceeds help us cover our printing and website costs
           and any surplus is donated to the "
           [:a {:href "#"} "Jame River Foundation."]]
      [sa/Button {:type "button"
                  :primary true
                  :href "https://www.paypal.me/jrgazette"
                  :target "_blank"}
                 "Donate"]]]))

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
          [:h1 "About the Gazette"]
          [:p "[to include more info as to the mission / purpose / goal of the JRG]"]]))

(defn contribute-page []
  (fn [] [sa/Container {:text-align "center" :text true}
          [:h1 "Contribute to the Gazette"]
          [:p "[to include various ways folks can contribute (including donate button) and instructions to contact via email]"]
          [:p "[Could eventually have various contribution forms]"]]))

(defn advertise-page []
  (fn [] [sa/Container {:text-align "center" :text true}
          [:h1 "Advertise with the Gazette"]
          [:p "[to include info anticipated distribution range and online availability and instructions to contact through email]"]
          [:p "[Could eventually have actual numbers of distribution locations & number of subscribers as well as a contact form option]"]]))


;; -------------------------
;; Translate routes -> page components

(defn page-for [route]
  (case route
    :index #'home-page
    :about #'about-page
    :contribute #'contribute-page
    :advertise #'advertise-page))
    ;:items #'items-page
    ;:item #'item-page))


(defn nav-menu [children]
  (let [active-item (r/atom nil)
        visible (r/atom false)
        handle-menu-hide (fn [] (reset! visible false))
        handle-link-click (fn [event props]
                            (let [name (-> props
                                           (js->clj :keywordize-keys true)
                                           (:name))]
                              (do (reset! active-item name)
                                  (handle-menu-hide))))]
    (fn [children]
     [sa/SidebarPushable {:as (.-Segment js/semanticUIReact)}
      [sa/Sidebar {:as (.-Menu js/semanticUIReact)
                   :animation "overlay"
                   :align "right"
                   :floated "right"
                   :visible @visible
                   :vertical true
                   :width "thin"
                   :on-hide handle-menu-hide}
       [sa/MenuItem {:as "a"
                     :name "home"
                     :href (path-for :index)
                     :active (= @active-item "home")
                     :on-click handle-link-click}]
       [sa/MenuItem {:as "a"
                     :name "about"
                     :href (path-for :about)
                     :active (= @active-item "about")
                     :on-click handle-link-click}]
       [sa/MenuItem {:as "a"
                     :name "contribute"
                     :href (path-for :contribute)
                     :active (= @active-item "contribute")
                     :on-click handle-link-click}]
       [sa/MenuItem {:as "a"
                     :name "advertise"
                     :href (path-for :advertise)
                     :active (= @active-item "advertise")
                     :on-click handle-link-click}]]
      [sa/SidebarPusher {:dimmed @visible}
       [sa/Menu
        [sa/MenuItem {:header true
                      :href (path-for :index)
                      :on-click handle-link-click}
          "The James River Gazette"]
        [sa/MenuItem {:icon "bars"
                      :position "right"
                      :on-click #(reset! visible true)}]]

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
