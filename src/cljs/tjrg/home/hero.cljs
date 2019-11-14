(ns tjrg.home.hero
  (:require
   [reagent.core :as r]
   [goog.string :as g]
   [cljss.core :refer-macros [defstyles]]
   [tjrg.components.buttons :refer [primary-button secondary-button link-button]]))

(defstyles container []
 {:text-align "center"
  :padding "2em 2em 2em 2em"})
(defstyles button-container []
  {:display "flex"
   :flex-wrap "wrap"
   :justify-content "center"})

(defn hero []
 (fn []
   [:div {:class (container)}
    [:h1 (str "The James River"
              (g/unescapeEntities "&nbsp;")
              "Gazette")]
    [:p (str "A community-driven newspaper dedicated to connecting the people of the "
             "Middle" (g/unescapeEntities "&nbsp;")
             "James"  (g/unescapeEntities "&nbsp;")
             "River"  (g/unescapeEntities "&nbsp;")
             "Basin"  (g/unescapeEntities "&nbsp;")
             "around the common goal of protecting our local waterways.")]
    [:div {:class (button-container)}
     [link-button {:href "/about"}
                  "Learn more"]
     [secondary-button {:href "#"}
                       "Subscribe"]
     [primary-button {:href "/issues/0_2020_01_07_James_River_Gazette_EXAMPLE.pdf"
                      :download true}
                     "Download the Latest Issue"]]]))
