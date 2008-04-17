package tortilla;

import java.awt.Frame;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class TortillaParseQstat {
    
    private TortillaServer server;
    private TortillaPlayer player;
    
    /**
     * Parse this line, updating server info.
     * @param aLine String of input being processed.
    */
    protected void processLine(String aLine){
      Scanner scanner = new Scanner(aLine);
      scanner.useDelimiter("___");
      if ( scanner.hasNext() )
      {
          String name = new String(scanner.next());
          if ( name.contains("NEXUIZS") )
          {
              String ip = new String(scanner.next());
              String hostname = new String(scanner.next());
              if (!hostname.contains("TIMEOUT") && !hostname.contains("DOWN"))
              {
                  try
                  {
                      String map = new String(scanner.next());                  
                      int maxplayers = Integer.parseInt(new String(scanner.next()));
                      int players = Integer.parseInt(new String(scanner.next()));
                      int ping = Integer.parseInt(new String(scanner.next()));
                        setServer(new TortillaServer(ip, hostname, map, 
                                maxplayers, players, ping));  
                  }
                  catch(NoSuchElementException e)
                  {
                      JOptionPane.showMessageDialog(new Frame(),
                              "Received bad server data");
                  }
              }
          }
          else if (!name.contains("NEXUIZM"))
          {
              int score = Integer.parseInt(new String(scanner.next()));
              int ping = Integer.parseInt(new String(scanner.next()));
              player = new TortillaPlayer(name, score, ping);
              server.addPlayer(name, player);
          }
      }
      scanner.close();
    }
    
    /**
     * Returns server being queried.
     * @return queriedServer TortillaServer which was queried.
     */
    public TortillaServer getServer()
    {
        return server;
    }

    public void setServer(TortillaServer server)
    {
        this.server = server;
    }

}
