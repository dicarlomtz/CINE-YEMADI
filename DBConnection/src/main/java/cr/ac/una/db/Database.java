package cr.ac.una.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Georges Alfaro S.
 * @version 2.0.0
 */
public abstract class Database extends MysqlDataSource {

    public Database(InputStream in) throws IOException {
        this.configuration = new Properties();
        loadConfiguration(in);
    }

    public Database() throws IOException {
        this.configuration = new Properties();
        InputStream in = getClass().getResourceAsStream(CONFIGURATION_PATH);
        loadConfiguration(in);
    }

    public final void loadConfiguration(InputStream in) throws IOException {
        configuration.loadFromXML(in);

        setURL(String.format("%s//%s/%s",
                this.configuration.getProperty("protocol"),
                this.configuration.getProperty("server_url"),
                this.configuration.getProperty("database")
        ));
        setUser(this.configuration.getProperty("user"));
        setPassword(this.configuration.getProperty("password"));
    }

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    public String getConfigurationData() {
        StringBuilder r = new StringBuilder("{\n");
        for (String key : configuration.stringPropertyNames()) {
            r.append(String.format("\t%s: '%s', %n",
                    key, configuration.getProperty(key)));
        }
        r.append("\n}");
        return r.toString();
    }

    @Override
    public String toString() {
        return String.format("%s: %s",
                getClass().getCanonicalName(),
                getConfigurationData());
    }

    protected static final String CONFIGURATION_PATH = "/cr/ac/una/db/db.properties";
    private Properties configuration = null;
}
