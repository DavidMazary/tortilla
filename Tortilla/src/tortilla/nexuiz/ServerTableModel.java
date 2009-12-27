package tortilla.nexuiz;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 * Models a table displaying Nexuiz servers.
 * @author dmaz
 */
@SuppressWarnings("serial")
public class ServerTableModel extends AbstractTableModel {

    public static final int PING = 0;
    public static final int HOSTNAME = 1;
    public static final int PLAYERS = 2;
    public static final int MAX = 3;
    public static final int MAP = 4;
    public static final int TYPE = 5;
    private static final String[] COLUMN_NAMES = {"Ping", "Server", "Players", "Max", "Map", "Type"};
    private Vector<Server> dataVector = null;

    public ServerTableModel() {
        dataVector = new Vector<Server>();
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case PING:
            case PLAYERS:
            case MAX:
                return Integer.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        Server server = getDataVector().get(row);
        switch (column) {
            case PING:
                return server.getPing();
            case PLAYERS:
                return server.getPlayerCount();
            case MAX:
                return server.getMaxPlayers();
            case HOSTNAME:
                return server.getHostname();
            case MAP:
                return server.getMap();
            case TYPE:
                return server.getType();
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        Server server = getDataVector().get(row);
        switch (column) {
            case PING:
                server.setPing((Integer) value);
                break;
            case PLAYERS:
                server.setPlayerCount((Integer) value);
                break;
            case MAX:
                server.setMaxPlayers((Integer) value);
                break;
            case HOSTNAME:
                server.setHostname((String) value);
                break;
            case MAP:
                server.setMap((String) value);
                break;
            case TYPE:
                server.setType((String) value);
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
        fireTableCellUpdated(row, column);
    }

    @Override
    public int getRowCount() {
        return getDataVector().size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    /**
     * @return the dataVector
     */
    public Vector<Server> getDataVector() {
        return dataVector;
    }
}
