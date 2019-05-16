(defproject nearhe.re "0.1.0-SNAPSHOT"
  :description "nearhe.re/ local calendar, products, services"
  :url "http://nearhe.re/"
  :license {:description "All Rights Reserved."}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [http-kit "2.3.0"]
                 [compojure "1.6.1"]]
  :main ^:skip-aot nearhe.re
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
