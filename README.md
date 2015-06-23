# Guess-Client

This project is part of a multi-thread/web-socket guessing game app. The guess-server (which can be found [here](https://github.com/marcio704/guess-server)) is the server-side application which is responsible for generate a randomized number and inform the players (clients) whether the number they are guessing is right or not.

The players are generated by this app: Guess-Client which is a multi-thread app. Each player is represented by a thread which recursively generates a random number and send the guess via web-socket to the server app. The moment a player find the right number, the server stops the game and all the other players stop playing.

