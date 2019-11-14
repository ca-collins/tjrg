(ns tjrg.advertise1 ;; TODO: Figure out why the app breaks if I name this ns 'advertise'
  (:require
   [reagent.core :as r]
   [cljss.core :refer-macros [defstyles]]
   [tjrg.components.buttons :refer [primary-button secondary-button link-button]]))

(defstyles container []
 {:text-align "center"
  :padding "2em 2em 2em 2em"})

(defn advertise-page []
 (fn []
   [:div {:class (container)}
    [:h1 "ADVERTISE JRG PAGE"]]))
