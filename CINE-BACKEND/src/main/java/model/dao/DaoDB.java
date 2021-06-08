package model.dao;

import cr.ac.una.db.Database;
import java.io.IOException;
import java.io.InputStream;

public class DaoDB extends Database {
    
    public DaoDB() throws IOException {
        InputStream in = getClass().getResourceAsStream(DAO_CONFIGURATION_PATH);
        loadConfiguration(in);
    }
    
    protected static final String DAO_CONFIGURATION_PATH = "/model/dao/dao.properties";
    
}
