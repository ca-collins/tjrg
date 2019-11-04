(ns tjrg.home.contribute
  (:require
   [reagent.core :as r]
   [soda-ash.core :as sa]
   [tjrg.core :refer [path-for]]))

(defn contribute []
 (fn []
   [sa/Container {:text-align "center" :text true}
    [:br]
    [sa/Header {:size "large"} "Get Involved"]
    [:p "Write an article, submit a joke or comic, give us feedback"]
    [sa/Container
     [sa/Button {:type "button"
                 :size "medium"
                 :basic true
                 :href (path-for :contribute)}
                "Learn how"]]]))