package app.controllers;

import app.Routes;
import app.dao.UsuarioDAO;
import app.model.Usuario;
import app.utils.Result;
import app.utils.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class UsuarioController {

    final static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public static Route serveRegisterUsuario = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        return View.render(request, model, Routes.Template.ADD_USUARIO);
    };

    public static Route findUsuario = (request, response) -> {
        String email = request.params(":email");

        if (email == null || email.length() == 0) {
            return "Mal input del email";
        }

        Result result = usuarioDAO.findUsuario_byEmail(email);

        return "isSuccess: " + result.isSuccess() + "\n Message:" + result.getMessage();
    };

    public static Route registerUsuario = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        Result result;
        String email = request.queryParams("email");

        Usuario usuario = new Usuario();
        usuario.setNombre(request.queryParams("nombre"));
        usuario.setApellido(request.queryParams("apellido"));
        usuario.setDni(request.queryParams("dni"));
        usuario.setEmail(email);
        usuario.setPassword(request.queryParams("password"));

        if (!usuario.isValid()) {
            result = new Result(false, null, "Datos no válidos");
        }else if (usuarioDAO.findUsuario_byEmail(email).isSuccess()) {
            result = new Result(false, null, "Ya existe un usuario con ese email");
        }else{
            result = usuarioDAO.addUsuario(usuario);
        }

        model.put("result", result);

        return View.render(request, model, Routes.Template.ADD_USUARIO);
    };
}
