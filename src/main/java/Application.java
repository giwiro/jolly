import app.Routes;
import app.controllers.AuthController;
import app.controllers.UsuarioController;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class Application {

    public static void main(String[] args) {
        port(getAssignedPort());
        initRoutes();
        enableDebugScreen();
    }

    static void initRoutes() {
        get("/", (req, res) -> "holi");

        before(Routes.Web.LOGIN, AuthController.alreadyLogged);
        get(Routes.Web.LOGIN, AuthController.serveLoginPage);
        post(Routes.Web.LOGIN, AuthController.handleLoginPost);

        get(Routes.Web.ADD_USUARIO, UsuarioController.serveRegisterUsuario);
        post(Routes.Web.ADD_USUARIO, UsuarioController.registerUsuario);

        before(Routes.Web.GET_USUARIO, AuthController.canAccess);
        get(Routes.Web.GET_USUARIO, UsuarioController.findUsuario);
    }


    static int getAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8080;
    }

}
