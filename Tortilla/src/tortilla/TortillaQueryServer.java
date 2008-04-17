package tortilla;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/**
 * Query an actual game server instead of the master server.
 * Not using a file to cache the data this time.
 * @author david
 */
public class TortillaQueryServer extends TortillaParseQstat {

    private Process qstatProcess;
    private String ip;
    private File qstat;

    /**
     * Use qstat to query this server.     *
     */
    public void qstat()
    {
        String cmd = null;
        String osName = new String (System.getProperty("os.name"));
        String userDir = System.getProperty("user.dir");

        if (osName.contains("Windows"))
        {
            qstat = new File(userDir + "\\qstat\\qstat.exe");
            cmd = new String(qstat.toString() + " -nexuizs "
                    + getIp() + " -raw ___");
        }
        else if (osName.contains("Linux") || osName.contains("Solaris") ||
                osName.contains("FreeBSD"))
        {
            qstat = new File(qstatUnixLocation());
            cmd = new String("quakestat -nexuizs " + getIp() +
                    " -raw ___");
        }

        if (qstat.exists() && cmd != null)
        {
            try
            {
                Runtime runtime = Runtime.getRuntime();
                setQstatProcess(runtime.exec(cmd));
                parseInputStream();
            }
            catch (Exception err)
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
     * Parse output of qstat process.
     */
    public void parseInputStream()
    {
        String input;
        try
        {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(getQstatProcess().getInputStream()));
            while ( (input = in.readLine()) != null )
            {
                processLine( input );
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public Process getQstatProcess()
    {
        return qstatProcess;
    }

    public void setQstatProcess(Process qstatProcess)
    {
        this.qstatProcess = qstatProcess;
    }

}
