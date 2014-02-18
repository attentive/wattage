(defproject wattage "0.1.0-SNAPSHOT"
  :description "Wattage is an ID3 tag viewer based on David Nolen's Om (Facebook React)."
  :url "http://github.com/attentive/wattage"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :jvm-opts ^:replace ["-Xmx1g" "-server"]

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2156"]
                 [ring/ring "1.2.1"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [om "0.4.0"]
                 [compojure "1.1.6"]
                 [fogus/ring-edn "0.2.0"]
                 ; Note: this dependency is not currently distributed centrally.
                 [wat/wat "0.2.0"]]

  :plugins [[lein-cljsbuild "1.0.2"]]

  :source-paths ["src/clj" "src/cljs"]
  :resource-paths ["resources"]

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src/clj" "src/cljs"]
              :compiler {
                :output-to "resources/public/js/main.js"
                :output-dir "resources/public/js/out"
                :optimizations :none
                :source-map true}}]})
