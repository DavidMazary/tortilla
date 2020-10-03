Tortilla is a fast, parallel, server-browsing desktop application for the free first-person shooter [Xonotic](http://xonotic.org/).

Tortilla lets you view the status of Xonotic servers and connect to them.

### Getting Started ###
Unzip Tortilla into your Xonotic directory and run Tortilla.jar or Tortilla.sh
### Screenshot ###
![http://i.imgur.com/eR4lI.png](http://i.imgur.com/eR4lI.png)

# Introduction #
Explanation of the features of Tortilla.

## Connecting ##
Double-click on a server, or click the Nexuiz icon once you've selected a server.
## Filtering ##
On the filter bar you can filter the displayed servers by gametype, exclude empty, full or high-ping servers. The search box instantly searches server names, map names, game types and player names.
## Refreshing ##
Click the refresh button
## Setting preferences ##
Click the settings menu in the top-right.
## Viewing server info ##
Mouse over a server to view player names.
## Adding private servers ##
Click the Heart-Plus icon and enter a server in the address:port format.

# Design Goals #

The important factors in the design of Tortilla are speed, simplicity, and multi-platform support.

  * Tortilla is multi-threaded for speed, it will query hundreds of servers in milliseconds.

  * Tortilla is simple in design in the code and the UI, because I was put off by the cruft of XQF and ST2. To stay simple, Tortilla depends on the appframework Java library.

  * Tortilla is multi-platform because although I use Linux, most people use Windows, and I hope that others use this too. I chose Java for this because Swing has better Windows theming than GTK but looks like GTK under Linux.
