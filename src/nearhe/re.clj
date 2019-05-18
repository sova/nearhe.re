(ns nearhe.re
 ; (:gen-class)
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :refer :all]
            [ring.middleware.defaults :refer :all]

            [duratom.core :refer :all]))

(def email-invites (duratom :local-file
                            :file-path "email-invites.sova"
                            :init []))

(defn show-landing-pg [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (slurp "resources/public/index.html")})

(defn show-email-recvd-pg [email zip]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body    (str "Thanks your email " email " has been recorded, we'll notify you when nearhe.re is available.")})

(defroutes app-routes
  (GET "/" [] show-landing-pg)
  (POST "/notify-by-email-please" [email zip :as req]
        ;(println req)
        (swap! email-invites  conj {:email email :zip zip})
        (show-email-recvd-pg email zip))

        ;{body :body} (slurp body))

        ;[email zip] (show-email-recvd-pg email zip))

  (resources "/")
  (resources "/css/") ;; static file url prefix /static, in `public` folder
  (not-found "le 404"))


(defn -main
  "This is our app's entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))]
  (server/run-server
    (wrap-defaults  #'app-routes
                    (assoc-in site-defaults [:security :anti-forgery] false))
    {:port port})
  (println (str "Server running 127.0.0.1:" port "/"))))
