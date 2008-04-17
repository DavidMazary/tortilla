package tortilla;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.swing.JOptionPane;

/**
 * Queries the master server for the list of servers.
 * Creates TortillaServer objects.
 * Puts those into a ConcurrentHashMap accessible with getServerConcurrentHashMap();
 *
 * @author David
 */
public class TortillaQueryMaster extends TortillaParseQstat {

    private Process qstatProcess;
    private ConcurrentMap < String, TortillaServer > serverList =
                new ConcurrentHashMap < String, TortillaServer >();
    private File qstat;

    /**
     * Run qstat. Cache serverlist.
     */
    public void qstat()
    {
        String cmd = null;
        String osName = new String (System.getProperty("os.name"));
        String userDir = System.getProperty("user.dir");

        if (osName.contains("Windows"))
        {
            qstat = new File(userDir + "\\qstat\\qstat.exe");
            cmd = new String(qstat.toString() + " -nexuizm "
                    + getMaster() + " -raw ___ -retry 1 -maxsim 25");
        }
        else if (osName.contains("Linux") || osName.contains("Solaris") ||
                osName.contains("FreeBSD"))
        {
            qstat = new File(qstatUnixLocation());
            cmd = new String(qstat.toString() + " -nexuizm " + getMaster() +
                    " -raw ___ -retry 1 -maxsim 25");
        }

        if (qstat.exists() && cmd != null)
        {
            try
            {
                Runtime runtime = Runtime.getRuntime();
                setQstatProcess(runtime.exec(cmd));
                parseInputStream();
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(new Frame(), 
                    "Error running qstat");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(new Frame(), 
                    "Qstat not found.\nBe sure that qstat is in your $PATH");
        }
    }
    
    /**
     * Used to check if qstat is installed on Unix systems.
     * @return File path to quakestat executable on unix systems.
     */
    public String qstatUnixLocation()
    {
        String path = null;
        try
        {
            Runtime runtime = Runtime.getRuntime();
            setQstatProcess(runtime.exec("which quakestat"));
            BufferedReader in = new BufferedReader(
                new InputStreamReader(getQstatProcess().getInputStream()));
            path = in.readLine();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * Choose a random master server to query, for load-balancing.
     * @return URL of master server.
     */
    public String getMaster()
    {
        String[] master = {"ghdigital.com", "dpmaster.deathmask.net",
            "excalibur.nvg.ntnu.no"};
        Random generator = new Random();
        int randomIndex = generator.nextInt(master.length);
        return master[randomIndex];
    }

    /**
     * Parse output of qstat process.
     */
    public void parseInputStream()
    {        
        String input;
        try
        {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(getQstatProcess().getInputStream()));
            in.readLine();
            while ( (input = in.readLine()) != null )
            {
                processLine( input );
                if (getServer() != null)
                {
                    serverList.put(getServer().getIp(), getServer());
                }
            }
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Process getQstatProcess()
    {
        return qstatProcess;
    }

    public void setQstatProcess(Process qstatProcess)
    {
        this.qstatProcess = qstatProcess;
    }

    /**
     * The serverList ConcurrentHashMap.
     * @return ConcurrentHashMap of serverList.
     */
    public ConcurrentMap<String, TortillaServer> getServerList()
    {
        return serverList;
    }

    public void setServerList(ConcurrentMap<String, TortillaServer> serverList)
    {
        this.serverList = serverList;
    }

}
