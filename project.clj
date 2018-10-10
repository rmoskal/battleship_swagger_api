

(defproject rob_learns "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [failjure "1.2.0"]
                 [metosin/compojure-api "1.1.11"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [environ "1.0.0"]]
  :ring {:handler my-api.handler/app}
  :uberjar-name "server.jar"
  :plugins [[environ/environ.lein "0.3.1"]]
  :hooks [environ.leiningen.hooks]
  :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]
                                  [cheshire "5.5.0"]
                                  [ring/ring-mock "0.3.0"]]
                   :plugins [[lein-ring "0.12.0"]]}})




