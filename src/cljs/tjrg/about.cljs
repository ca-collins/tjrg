(ns tjrg.about
  (:require
   [reagent.core :as r]
   [soda-ash.core :as sa]))

(defn about-page []
 (fn []
   [sa/Container {:text-align "center" :text true}
    [:h1 "About the Gazette"]
    [:p "[to include more info as to the mission / purpose / goal of the JRG]"]]))
