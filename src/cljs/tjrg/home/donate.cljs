(ns tjrg.home.donate
  (:require
   [reagent.core :as r]))

(defn donate []
 (fn []
   [:div
    [:br]
    [:h1 {:size "large"} "Support the JRG"]
    [:p "Please consider supporting the JRG with a small donation.
         Proceeds help us cover our printing and website costs
         and any surplus is donated to the "
         [:a {:href "#"} "Jame River Foundation."]]
    [:a {:href "https://www.paypal.me/jrgazette"
         :target "_blank"}
        "Donate"]]))
