(ns tjrg.advertise1 ;; TODO: Figure out why the app breaks if I name this ns 'advertise'
  (:require
   [reagent.core :as r]
   [soda-ash.core :as sa]))

(defn advertise-page []
 (fn []
   [sa/Container {:text-align "center" :text true}
    [:h1 "ADVERTISE JRG PAGE"]]))