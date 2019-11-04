(ns tjrg.contribute
  (:require
   [reagent.core :as r]
   [soda-ash.core :as sa]))

(defn contribute-page []
 (fn []
   [sa/Container {:text-align "center" :text true}
    [:h1 "Contribute to the Gazette"]
    [:p "[to include various ways folks can contribute (including donate button) and instructions to contact via email]"]
    [:p "[Could eventually have various contribution forms]"]]))
