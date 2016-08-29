import static spark.Spark.*;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class Application {

    public static void main(String[] args) {
        port(getAssignedPort());
        get("/hello", (req, res) -> "Hello Heroku World");
    }


    static int getAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8080; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
