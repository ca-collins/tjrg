(ns tjrg.components.svg
  (:require
   [reagent.core :as r]))

(defn menu-icon []
 [:svg {:xmlns "http://www.w3.org/2000/svg", :width "24", :height "24", :viewBox "0 0 24 24"}
  [:path {:d "M0 0h24v24H0z", :fill "none"}]
  [:path {:d "M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z"}]])

(defn close-icon []
  [:svg {:xmlns "http://www.w3.org/2000/svg", :width "24", :height "24", :viewBox "0 0 24 24"}
   [:path {:d "M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"}]
   [:path {:d "M0 0h24v24H0z", :fill "none"}]])

(defn outside-link []
 [:svg {:xmlns "http://www.w3.org/2000/svg", :width "24", :height "24", :viewbox "0 0 24 24"}
  [:path {:d "M0 0h24v24H0z", :fill "none"}]
  [:path {:d "M10.09 15.59L11.5 17l5-5-5-5-1.41 1.41L12.67 11H3v2h9.67l-2.58 2.59zM19 3H5c-1.11 0-2 .9-2 2v4h2V5h14v14H5v-4H3v4c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2z"}]])
