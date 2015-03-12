# Design Goals #

The important factors in the design of Tortilla are speed, simplicity, and multi-platform support.

  * Tortilla is multi-threaded for speed, it will query hundreds of servers in milliseconds.

  * Tortilla is simple in design in the code and the UI, because I was put off by the cruft of XQF and ST2. To stay simple, Tortilla depends on the appframework Java library.

  * Tortilla is multi-platform because although I use Linux, most people use Windows, and I hope that others use this too. I chose Java for this because Swing has better Windows theming than GTK but looks like GTK under Linux.