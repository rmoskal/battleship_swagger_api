(ns my-api.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [rob-learns.game-server :refer :all]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
            [schema.core :as s]))




(def app
  (api
    {:swagger
     {:ui   "/"
      :spec "/swagger.json"
      :data {:info {:title       "Who Sunk My Battleship!?"
                    :description "Alloyed Test Api"}
             :tags [{:name "api", :description "Battleship game for two!"}]}}}

    (context "/api" []
      :tags ["api"]

      (GET "/plus" []
        :return {:result Long}
        :query-params [x :- Long, y :- Long]
        :summary "adds two numbers together"
        (ok {:result (+ x y)}))

      (POST "/init" []
        (initialize-game)
        :summary "Initializes the game!"
        (ok {:player1 @player_1 :player2 @player_2})
        )
      (POST "/place/:player/:piece/:direction" []
        :summary "Places a piece on the board"
        :path-params [player :- (s/enum :player1 :player2),
                      piece :- (s/enum "submarine" "destroyer" "cruiser" "battleship" "carrier"),
                      direction :- (s/enum :v :h)]
        :query-params [x :- Long, y :- Long]
        (let [player (get-atom player)]
          (ok {:player (place-move player piece (direction directionfn) x y)})))
      (GET "/:player" []
        :summary "Gets the state for a player"
        :path-params [player :- (s/enum :player1 :player2)]
        (let [player (get-atom player)]
          (ok {:player @player})))
      (POST "/attack/:attacker/:attacked" []
        :summary "First player attacks the second"
        :path-params [attacker :- (s/enum :player1 :player2),
                      attacked :- (s/enum :player1 :player2)]
        :query-params [x :- Long, y :- Long]

        (let [[attacker attacked] [(get-atom attacker) (get-atom attacked)]]
          (ok {:player (attack-move attacker attacked x y)})
          )
        ))))
(defn -main [& [port]]
  (print  (read-string (env :port)))
   (jetty/run-jetty app {:port (or (read-string(env :port))
                                    9303)})
  )