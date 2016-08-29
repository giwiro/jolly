package app;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class Conf {
    private static Map<String, String> c = new HashMap();

    static {
        c.put("PORT", "8080");
        c.put("IP", "127.0.0.1");

        // Neo4j
        c.put("NEO_URL", "bolt://localhost");
        c.put("NEO_USER", "neo4j");
        c.put("NEO_PASSWORD", "neo4j");
    }

    public static void setConf (String k, String v) {
        c.put(k, v);
    }

    public static String getConf (String k) {
        return c.get(k);
    }

}
