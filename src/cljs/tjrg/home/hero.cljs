(ns tjrg.home.hero
  (:require
   [reagent.core :as r]
   [goog.string :as g]))

(defn hero []
 (fn []
   [:div
    [:h1 "The James River Gazette"]
    [:p (str "A community-driven newspaper dedicated to connecting the people of the "
             "Middle" (g/unescapeEntities "&nbsp;")
             "James"  (g/unescapeEntities "&nbsp;")
             "River"  (g/unescapeEntities "&nbsp;")
             "Basin"  (g/unescapeEntities "&nbsp;")
             "around the common goal of protecting our local waterways.")]
    [:div
     [:a {:href "/about"}
         "Learn more"]
     [:a {:href "#"}
         "Subscribe"]
     [:a {:href "/issues/0_2020_01_07_James_River_Gazette_EXAMPLE.pdf"
          :download true}
         "Download the Latest Issue"]]]))
