package app.db;

import app.Conf;
import org.neo4j.driver.v1.*;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class DBManager {

    private static DBManager dbManager;
    private Driver driver;

    public DBManager () {

        String neo_url = Conf.getConf("NEO_URL");
        String username = Conf.getConf("NEO_USER");
        String password = Conf.getConf("NEO_PASSWORD");

        driver = GraphDatabase.driver( neo_url, AuthTokens.basic( username, password ) );

        initConstraint(driver);


    }

    public static DBManager getInstance () {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    private static void initConstraint(Driver driver) {
        Session session = driver.session();
        final String q = "CREATE CONSTRAINT ON (producto:Producto) ASSERT producto.id IS UNIQUE";



        session.close();
    }

    public Driver getDriver () {
        return driver;
    }
}
