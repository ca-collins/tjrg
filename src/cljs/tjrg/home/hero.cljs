(ns tjrg.home.hero
  (:require
   [reagent.core :as r]
   [soda-ash.core :as sa]
   [goog.string :as g]
   [tjrg.core :refer [path-for]]))

(defn hero []
 (fn []
   [sa/Container {:text-align "center" :text true}
    [sa/Header {:size "huge"} "The James River Gazette"]
    [:p (str "A community-driven newspaper dedicated to connecting the people of the "
             "Middle" (g/unescapeEntities "&nbsp;")
             "James"  (g/unescapeEntities "&nbsp;")
             "River"  (g/unescapeEntities "&nbsp;")
             "Basin"  (g/unescapeEntities "&nbsp;")
             "around the common goal of protecting our local waterways.")]
    [sa/Container
     [sa/Button {:type "button"
                 :size "medium"
                 :basic true
                 :color "large"
                 :href (path-for :about)}
                "Learn more"]
     [sa/Button {:type "button"
                 :size "large"
                 :primary true}
                "Subscribe"]]]))
      ; [:br]
      ; [sa/Button {:href "/issues/0_2020_01_07_James_River_Gazette_EXAMPLE.pdf"
      ;             :download true
      ;             :type "button"
      ;             :secondary true
      ;             :icon true
      ;             :label-position "left"}
      ;            "Download the Latest Issue"
      ;            [sa/Icon {:class "download"}]]]
