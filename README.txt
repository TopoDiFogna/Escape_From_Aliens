README

The server is started from the server class in network.entrypoint package and doesn’t need any configuration. 

The Command Line Interface Client is start from the class client in network.entrypoint package. 
After entering some data required by client the available commands are: 
- gamelist : retrieves the list of available games
- join “mapname” : join a match with specified map
After joining a game the following commands become available:
- move “sector letter” “sector number” : to move the player in selected sector
- moveattack “sector letter” “sector number”  : to move and attack in selected sector
- use “cardname” “[letter] [number]” : to use card ([letter] [number] are used only for spotlight card)
- noise “letter” “number” : to make noise after drawing a noise in any sector card
- discard “cardname” : to discard selected card
- getcards : to retreive the player cards in his hand
- endturn : to end turn
- chat “message” : to chat with other players

The GUI client is started from the clientGUI class in gui package. 

To play is needed to start two client (both CLI and GUI).