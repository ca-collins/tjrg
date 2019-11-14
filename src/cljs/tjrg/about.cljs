(ns tjrg.about
  (:require
   [reagent.core :as r]
   [cljss.core :refer-macros [defstyles]]
   [tjrg.components.buttons :refer [primary-button secondary-button link-button]]))

(defstyles container []
 {:text-align "center"
  :padding "2em 2em 2em 2em"})

(defn about-page []
 (fn []
   [:div {:class (container)}
    [:h1 "About the Gazette"]
    [:p "[to include more info as to the mission / purpose / goal of the JRG]"]]))
