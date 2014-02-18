(ns wattage.core
  (:require [ring.util.response :refer [file-response]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.edn :refer [wrap-edn-params]]
            [compojure.core :refer [defroutes GET PUT]]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [wat.core :as w]))

(defn index []
  (file-response "public/html/index.html" {:root "resources"}))

(defn generate-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

(defn tags []
  (let [fur-elise (w/wat-vals (w/read-audiofile "resources/data/Beethoven - Fur Elise.mp3"))]
    (generate-response fur-elise)))

(defroutes routes
  (GET "/" [] (index))
  (GET "/tags" [] (tags))
;  (PUT "/tag/:id/update"
;    {params :params edn-params :edn-params}
;    (update-class (:id params) edn-params))
  (route/files "/" {:root "resources/public"}))

(def app
  (-> routes
      wrap-edn-params))

(defonce server
  (run-jetty #'app {:port 8083 :join? false}))
