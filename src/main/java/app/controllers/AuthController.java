package app.controllers;

import app.Routes;
import app.dao.UsuarioDAO;
import app.model.Usuario;
import app.utils.Result;
import app.utils.SessionUtil;
import app.utils.View;
import spark.Filter;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class AuthController {

    final static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public static Filter canAccess = (request, response) -> {
        if (!SessionUtil.isLogged(request)) {
            response.redirect("/");
        }
    };

    public static Filter alreadyLogged = (request, response) -> {
        if (SessionUtil.isLogged(request)) {
            response.redirect("/");
        }
    };

    public static Route serveLoginPage = (request, response) -> {
        return View.render(request, new HashMap<>(), Routes.Template.LOGIN);
    };

    public static Route handleLoginPost = (request, response) -> {
        Map<String, Object> model = new HashMap<>();

        Result<Usuario> result
                = usuarioDAO.authUsuario(request.queryParams("email"),request.queryParams("password"));

        if (result.isSuccess()) {
            SessionUtil.initSession(request, (Usuario)result.getResult());
            response.redirect(Routes.Web.INDEX);
        }

        model.put("result", result);

        //System.out.println("Login: " + result.isSuccess());

        return View.render(request, model, Routes.Template.LOGIN);
    };
}
