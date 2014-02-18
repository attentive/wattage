(ns wattage.core
  (:require [goog.dom :as gdom]
            [om.core :as om :include-macros true]
            [wattage.data :as data]
            [wattage.views :as views]))

(enable-console-print!)

;Test rendering a single tag
;(om/root views/tag-view data/TAG-TEST {:target (gdom/getElement "tags")})

(om/root views/tags-view data/APP
  {:target (gdom/getElement "tags")})

;(om/root views/tags-view data/APP
;  {:target (gdom/getElement "second-tags")})
