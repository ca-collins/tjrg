(ns tjrg.home.contribute
  (:require
   [reagent.core :as r]
   [cljss.core :refer-macros [defstyles]]
   [tjrg.components.buttons :refer [link-button]]))

(defstyles container []
 {:text-align "center"
  :padding "2em 0"})

(defn contribute []
 (fn []
   [:div {:class (container)}
    [:h1 "Get Involved"]
    [:p "Write an article, submit a joke or comic, give us feedback"]
    [:div
     [link-button {:href "/contribute"}
                  "Learn how"]]]))
