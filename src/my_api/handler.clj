(ns my-api.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [rob-learns.game-server :refer :all]
            [schema.core :as s]))




(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Who Sunk My Battleship!?"
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
                      piece :- String, direction :- (s/enum :v :h)]
        :query-params [x :- Long, y :- Long]

        (let [player  (cond (= player :player1  ) player_1
                       (= player :player2  ) player_2)
              ]
          (ok {:player (place-move player piece (direction directionfn) x y)})
      )

        (POST "/attack/:attacker/:attacked" []
          :summary "Places a piece on the board"
          :path-params [attacker :- (s/enum :player1 :player2),
                        attacked :- (s/enum :player1 :player2)]
          :query-params [x :- Long, y :- Long]

          (let [player  (cond (= player :player1  ) player_1
                              (= player :player2  ) player_2)
                ]
            (ok {:player (place-move player piece (direction directionfn) x y)})
            )
        ))))
