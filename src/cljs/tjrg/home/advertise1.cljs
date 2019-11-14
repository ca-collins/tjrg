(ns tjrg.home.advertise1
  (:require
   [reagent.core :as r]
   [cljss.core :refer-macros [defstyles]]
   [tjrg.components.buttons :refer [link-button primary-button]]))

(defstyles container []
 {:text-align "center"
  :padding "2em 0"})

(defn advertise []
 (fn []
   [:div {:class (container)}
    [:h1 "Advertise with Us!"]
    [:p "Odio duis pharetra hendrerit ullamcorper. Consequat urna per odio cum etiam luctus nisl etiam? Dapibus fusce tempus eget fusce consectetur rutrum gravida sapien posuere. "]
    [:div
     [link-button {:href "/advertise"}
                  "Learn more"]
     [primary-button {:href "/advertise"}
                     "Contact us"]]]))
