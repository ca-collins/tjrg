(ns tjrg.home.advertise1
  (:require
   [reagent.core :as r]
   [soda-ash.core :as sa]
   [tjrg.core :refer [path-for]]))

(defn advertise []
 (fn []
   [sa/Container {:text-align "center" :text true}
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
                "Contact us"]]]))