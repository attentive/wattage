(ns wattage.views
  (:require [cljs.reader :as reader]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [wattage.data :as data]
            [wattage.widgets :as w]))

(enable-console-print!)

(defn on-edit [key val]
  (println "Key: " (str key) " Value: " (str val)))

(defn tag-view [tag owner]
  (reify
    om/IRender
    (render [_]
            (let [k (first (keys tag))
                  v (first (vals tag))]
              (dom/div #js {:className "row"} nil
                (dom/div #js {:className "span3"} (str k))
                (dom/div #js {:className "span3"}
                         (om/build w/editable tag
                                {:opts {:edit-key k
                                        :on-edit #(on-edit k %)}})))))))

(defn tags-view [app owner]
  (reify
    om/IWillMount
    (will-mount [_]
      (data/refresh-tags app))
    om/IRender
    (render [_]
      (dom/div #js {:id "tags"} ; NB hard-coded ID here—would need to parameterise to have multiple?
        ;(apply dom/ul nil
        (apply dom/div nil (om/build-all tag-view (data/tag-data app)))))))
