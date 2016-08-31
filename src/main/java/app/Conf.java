package app;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class Conf {
    private static Map<String, String> c = new HashMap();

    static {
        String port = System.getenv("PORT") != null
                ? System.getenv("PORT") : "8080";

        String neo_url = System.getenv("NEO_URL") != null
                ? System.getenv("NEO_URL") : "bolt://localhost";

        String neo_user = System.getenv("NEO_USER") != null
                ? System.getenv("NEO_USER") : "neo4j";

        String neo_password = System.getenv("NEO_PASSWORD") != null
                ? System.getenv("NEO_PASSWORD") : "neo4j";

        c.put("PORT", port);
        System.out.println("PORT: " + port);
        c.put("NEO_URL", neo_url);
        c.put("NEO_USER", neo_user);
        c.put("NEO_PASSWORD", neo_password);
    }

    public static void setConf (String k, String v) {
        c.put(k, v);
    }

    public static String getConf (String k) {
        return c.get(k);
    }

}
