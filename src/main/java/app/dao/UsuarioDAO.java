package app.dao;

import app.db.DBManager;
import app.model.Usuario;
import app.utils.Hasher;
import app.utils.Result;
import org.neo4j.driver.v1.Session;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class UsuarioDAO {

    private Session db = DBManager.getInstance().getSession();

    public static Result authUsuario (String email, String password) {

        Result result;

        try {
            String hpassword = Hasher.MD5(password);

            return null;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new Result(false, null, "Error al autenticar usuario");
        }




    }
}
