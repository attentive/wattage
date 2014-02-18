(ns wattage.data
  (:require [cljs.reader :as reader]
            [goog.events :as events]
            [om.core :as om :include-macros true]
            [clojure.string :as string])
  (:import [goog.net XhrIo]
           goog.net.EventType
           [goog.events EventType]))

; App data
(def APP
  {:tags {:MADEUP "Made up tag", :ALSOMADEUP "Also made up"}})

; Single tag for testing with tag-view
(def TAG-TEST {:MADEUP "Madeup"})

; XHR helper
(def ^:private meths
  {:get "GET"
   :put "PUT"
   :post "POST"
   :delete "DELETE"})

(defn edn-xhr [{:keys [method url data on-complete]}]
  (let [xhr (XhrIo.)]
    (events/listen xhr goog.net.EventType.COMPLETE
      (fn [e]
        (on-complete (reader/read-string (.getResponseText xhr)))))
    (. xhr
      (send url (meths method) (when data (pr-str data))
        #js {"Content-Type" "application/edn"}))))

(defn refresh-tags [app]
  (edn-xhr
   {:method :get
    :url "tags"
    :on-complete #(om/transact! app :tags (fn [_] %))}))

; Helper function
(defn- non-blank-tags [app]
  "Filter non blank ID3 tags from the list of all tags in the target music file."
  (filter (fn [[k v]] (not (string/blank? v))) (:tags app)))

(defn tag-data [app]
  "Restructure tag data for ease of view rendering."
  (map (fn [p] {(first p) (second p)}) (non-blank-tags app)))
