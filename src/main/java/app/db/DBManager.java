package app.db;

import app.Conf;
import org.neo4j.driver.v1.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    public Iterator<Map<String, Object>> query(String query, Map<String, Object> params) {
        try (Session session = driver.session()) {
            List<Map<String, Object>> list = session.run(query, params)
                    .list( r -> r.asMap(DBManager::convert));
            return list.iterator();
        }
    }

    static Object convert(Value value) {
        switch (value.type().name()) {
            case "PATH":
                return value.asList(DBManager::convert);
            case "NODE":
            case "RELATIONSHIP":
                return value.asMap();
        }
        return value.asObject();
    }

    private static void initConstraint(Driver driver) {
        Session session = driver.session();
        final String q1 = "CREATE CONSTRAINT ON (producto:Producto) ASSERT producto.id IS UNIQUE";
        final String q2 = "CREATE CONSTRAINT ON (categoria:Categoria) ASSERT categoria.id IS UNIQUE";

        session.run(q1);
        session.run(q2);

    }

    public Driver getDriver () {
        return driver;
    }
}
