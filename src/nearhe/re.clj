(ns nearhe.re
 ; (:gen-class)
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :refer :all]))

(defn show-landing-pg [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (slurp "resources/public/index.html")})

(defn general-handler [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "All hail General Zod!"})

(defroutes app-routes
  (GET "/" [] show-landing-pg)
  (GET "/hi" [] general-handler)
  (resources "/")
  (resources "/css/") ;; static file url prefix /static, in `public` folder
  (not-found "le 404"))


(defn -main
  "This is our app's entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))]
  (server/run-server #'app-routes {:port port})
  (println (str "Server running 127.0.0.1:" port "/"))))
