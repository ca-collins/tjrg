(ns tjrg.home.core
  (:require
   [reagent.core :as r]
   [soda-ash.core :as sa]
   [goog.string :as g]
   [tjrg.core :refer [path-for]]
   [tjrg.home.hero :refer [hero]]
   [tjrg.home.contribute :refer [contribute]]))

(defn homepage []
  (fn []
    [sa/Container
     [hero]
     [contribute]
     ;; ADVERTISE ==============================
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
                  "Contact us"]]]

     ;; Donate ==============================
     [sa/Container {:text-align "center" :text true}
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
