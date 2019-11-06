(ns tjrg.contribute
  (:require
   [reagent.core :as r]))

(defn contribute-page []
 (fn []
   [:div
    [:h1 "Contribute to the Gazette"]
    [:p "[to include various ways folks can contribute (including donate button) and instructions to contact via email]"]
    [:p "[Could eventually have various contribution forms]"]]))
