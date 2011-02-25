package tortilla.xonotic.tablemodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import tortilla.xonotic.Server;

/**
 * Models a table displaying xonotic servers.
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
    private List<Server> dataVector = null;

    public ServerTableModel() {
        super();
        dataVector = Collections.synchronizedList(new ArrayList<Server>());
    }

    @Override
    public String getColumnName(final int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public boolean isCellEditable(final int row, final int column) {
        return false;
    }

    @Override
    public Class getColumnClass(final int column) {
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
    public Object getValueAt(final int row, final int column) {
        final Server server = getDataVector().get(row);
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
    public void setValueAt(final Object value, final int row, final int column) {
        final Server server = getDataVector().get(row);
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
    public List<Server> getDataVector() {
        return dataVector;
    }

    /**
     * Clear the datavector and update table rows
     */
    public void clear() {
        if (!dataVector.isEmpty()) {
            int size = dataVector.size();  // Cache value since data being deleted.
            dataVector.clear();
            fireTableRowsDeleted(0, size - 1);
        }
    }
}
