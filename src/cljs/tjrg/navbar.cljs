(ns tjrg.navbar
  (:require
   [reagent.core :as r]
   [soda-ash.core :as sa]))

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
                     :href "/"
                     :active (= @active-item "home")
                     :on-click handle-link-click}]
       [sa/MenuItem {:as "a"
                     :name "about"
                     :href "/about"
                     :active (= @active-item "about")
                     :on-click handle-link-click}]
       [sa/MenuItem {:as "a"
                     :name "contribute"
                     :href "/contribute"
                     :active (= @active-item "contribute")
                     :on-click handle-link-click}]
       [sa/MenuItem {:as "a"
                     :name "advertise"
                     :href "/advertise"
                     :active (= @active-item "advertise")
                     :on-click handle-link-click}]]
      [sa/SidebarPusher {:dimmed @visible}
       [sa/Menu
        [sa/MenuItem {:header true
                      :href "/"
                      :on-click handle-link-click}
          "The James River Gazette"]
        [sa/MenuItem {:icon "bars"
                      :position "right"
                      :on-click #(reset! visible true)}]]
       children]])))
