

(defproject rob_learns "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [failjure "1.2.0"]
                 [metosin/compojure-api "1.1.11"]
                 [ring-sse-middleware "0.1.2"]]
  :ring {:handler my-api.handler/app}
  :min-lein-version "2.0.0"
  :uberjar-name "server.jar"
  :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]
                                  [cheshire "5.5.0"]
                                  [ring/ring-mock "0.3.0"]]
                   :plugins [[lein-ring "0.12.0"]]}})




