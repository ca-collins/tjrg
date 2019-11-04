(ns tjrg.home.core
  (:require
   [reagent.core :as r]
   [soda-ash.core :as sa]
   [goog.string :as g]
   [tjrg.home.hero :refer [hero]]
   [tjrg.home.contribute :refer [contribute]]
   [tjrg.home.advertise1 :refer [advertise]]
   [tjrg.home.donate :refer [donate]]))

(defn homepage []
  (fn []
    [sa/Container
     [hero]
     [contribute]
     [advertise]
     [donate]]))
