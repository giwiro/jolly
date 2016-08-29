package app.utils;

import app.model.Usuario;
import spark.Request;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class SessionUtil {
    public static Usuario getSessionCurrentUser(Request request) {
        return request.session().attribute("currentUser");
    }

    public static boolean isLogged(Request request) {
        boolean isLogged
                = request.session().attribute("isLogged") == null ? false : request.session().attribute("isLogged");
        return isLogged;
    }

    public static void initSession(Request request, Usuario usuario) {
        request.session().attribute("currentUser", usuario);
        request.session().attribute("isLogged", true);
        request.session().attribute("userType", "regular");
    }

    public static void removeSession(Request request) {
        request.session().removeAttribute("currentUser");
        request.session().attribute("isLogged", false);
        request.session().attribute("loggedOut", true);
        request.session().removeAttribute("userType");
    }
}
