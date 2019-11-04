(ns tjrg.home.contribute
  (:require
   [reagent.core :as r]
   [soda-ash.core :as sa]))

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
                 :href "/contribute"}
                "Learn how/"]]]))
