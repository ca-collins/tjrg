(ns tjrg.home.advertise1
  (:require
   [reagent.core :as r]))

(defn advertise []
 (fn []
   [:div
    [:br]
    [:h1 "Advertise with Us!"]
    [:p "Odio duis pharetra hendrerit ullamcorper. Consequat urna per odio cum etiam luctus nisl etiam? Dapibus fusce tempus eget fusce consectetur rutrum gravida sapien posuere. "]
    [:div
     [:a {:href "/advertise"}
         "Learn more"]
     [:a {:href "/advertise"}
        "Contact us"]]]))
