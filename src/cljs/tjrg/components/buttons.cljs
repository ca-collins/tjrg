(ns tjrg.components.buttons
  (:require
   [reagent.core :as r]
   [cljss.core :refer-macros [defstyles]]))

(defstyles button []
 {:box-sizing "border-box"
  :color "rgba(0, 0, 0, 0.7)"
  :text-shadow "0 0.04em 0.04em rgba(0,0,0,0.35)"
  :font-size "1em"
  :padding "0.46em 1.6em"
  :margin "0.5em 0.2em"
  :border-radius "0.12em"
  :box-box-shadow "1px solid rgba(0, 0, 0, 0.2)"
  :border "0.1em solid rgba(0, 0, 0, 0.7)"
  :text-decoration "none"
  :&:hover {:text-shadow "0 0 2em rgba(255,255,255,1)"
            :color "#FFFFFF"}})

(defstyles primary []
 {:background-color "#FFFFFF"
  :&:hover {:background-color "rgba(0, 0, 0, 0.7)"
            :border-color "#FFFFFF"}})

(defstyles secondary []
  {:background-color "none"
   :&:hover {:border-color "#FFFFFF"
             :color "#FFFFFF"
             :background-color "none"}})

(defstyles link []
 {:box-box-shadow "none"
  :padding-right "1em"
  :padding-left "1em"
  :margin-right "0.1em"
  :margin-left "0.1em"
  :border "none"
  :background "none"})

(defn primary-button [{:keys [href download target]} children]
  [:a {:href href
       :download download
       :target target
       :class [(button)
               (primary)]}
   children])

(defn secondary-button [{:keys [href download]} children]
  [:a {:href href
       :download download
       :class [(button)
               (secondary)]}
   children])

(defn link-button [{:keys [href download]} children]
  [:a {:href href
       :download download
       :class [(button)
               (link)]}
   children])
