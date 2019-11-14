(ns tjrg.home.donate
  (:require
   [reagent.core :as r]
   [cljss.core :refer-macros [defstyles]]
   [tjrg.components.buttons :refer [primary-button]]))

(defstyles container []
 {:text-align "center"
  :padding "2em 0"})

(defn donate []
 (fn []
   [:div {:class (container)}
    [:h1 {:size "large"} "Support the JRG"]
    [:p "Please consider supporting the JRG with a small donation.
         Proceeds help us cover our printing and website costs
         and any surplus is donated to the "
         [:a {:href "#"} "Jame River Foundation."]]
    [primary-button {:href "https://www.paypal.me/jrgazette"
                     :target "_blank"}
                    "Donate"]]))
