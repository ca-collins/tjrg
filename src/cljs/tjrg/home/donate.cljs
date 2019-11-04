(ns tjrg.home.donate
  (:require
   [reagent.core :as r]
   [soda-ash.core :as sa]))

(defn donate []
 (fn []
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
               "Donate"]]))
