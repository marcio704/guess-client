# Guess-Client

This project is part of a multi-thread/web-socket guessing game app. The guess-server (which can be found [here](https://github.com/marcio704/guess-server)) is the server-side application which is responsible for generating a randomized number and inform the players (clients) whether the number they are guessing is right or not, if the client's guess is wrong, the server sends a tip to the client, informing whether the number used on the guess is below or above the secret number.

The players are generated by this app: Guess-Client which is a multi-thread app. Each player is represented by a thread which recursively generates a random number (based on the tips sent by the server-side app) and send the guess via web-socket to the server app. The moment a player find the right number, the server stops the game and all the other players stop playing.

