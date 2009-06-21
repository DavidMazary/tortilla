package tortilla;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dmaz
 */
public class ServerTableModel extends AbstractTableModel {

    public static final int PING = 0;
    public static final int SERVER = 1;
    public static final int PLAYERS = 2;
    public static final int MAX = 3;
    public static final int MAP = 4;
    private static final String[] COLUMN_NAMES = {"Ping", "Server", "Players", "Max", "Map"};
    private static final long serialVersionUID = 2187967572701857442L;
    private Vector<Server> dataVector;

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
            case SERVER:
            case MAP:
                return String.class;
            default:
                return Integer.class;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        Server server = dataVector.get(row);
        switch (column) {
            case PING:
                return server.getPing();
            case PLAYERS:
                return server.getPlayerCount();
            case MAX:
                return server.getMaxPlayers();
            case SERVER:
                return server.getHostname();
            case MAP:
                return server.getMap();
            default:
                return new Object();
        }
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        Server server = dataVector.get(row);
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
            case SERVER:
                server.setHostname((String) value);
                break;
            case MAP:
                server.setMap((String) value);
                break;
            default:
                System.err.println("invalid index");
        }
        fireTableCellUpdated(row, column);
    }

    @Override
    public int getRowCount() {
        return dataVector.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    public void addRow(Server newServer) {
            dataVector.add(newServer);
            fireTableRowsInserted(
                    dataVector.size() - 1,
                    dataVector.size() - 1);
    }

    public void deleteRow(Server newServer) {
            dataVector.remove(newServer);
            fireTableRowsDeleted(
                    dataVector.size() - 1,
                    dataVector.size() - 1);
    }

    protected Vector<Server> getDataVector() {
        return dataVector;
    }
}