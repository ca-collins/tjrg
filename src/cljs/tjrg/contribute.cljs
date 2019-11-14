(ns tjrg.contribute
  (:require
   [reagent.core :as r]
   [cljss.core :refer-macros [defstyles]]
   [tjrg.components.buttons :refer [primary-button secondary-button link-button]]))

(defstyles container []
 {:text-align "center"
  :padding "2em 2em 2em 2em"})

(defn contribute-page []
 (fn []
   [:div {:class (container)}
    [:h1 "Contribute to the Gazette"]
    [:p "[to include various ways folks can contribute (including donate button) and instructions to contact via email]"]
    [:p "[Could eventually have various contribution forms]"]]))
