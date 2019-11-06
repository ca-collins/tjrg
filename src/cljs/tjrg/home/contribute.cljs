(ns tjrg.home.contribute
  (:require
   [reagent.core :as r]))

(defn contribute []
 (fn []
   [:div
    [:br]
    [:h1 "Get Involved"]
    [:p "Write an article, submit a joke or comic, give us feedback"]
    [:div
     [:a {:href "/contribute"}
         "Learn how"]]]))
