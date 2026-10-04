package service;

import dao.WaterConnectionDAO;
import model.WaterConnection;

import java.util.List;

public class WaterConnectionService {

    private WaterConnectionDAO dao = new WaterConnectionDAO();

    public boolean addConnection(WaterConnection connection) {
        return dao.addConnection(connection);
    }

    public List<WaterConnection> getAllConnections() {
        return dao.getAllConnections();
    }

    public WaterConnection getConnectionById(int id) {
        return dao.getConnectionById(id);
    }

    public boolean updateConnection(WaterConnection connection) {
        return dao.updateConnection(connection);
    }

    public boolean deleteConnection(int id) {
        return dao.deleteConnection(id);
    }
}