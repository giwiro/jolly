package app.dao;

import app.db.DBManager;
import app.model.Usuario;
import app.utils.Hasher;
import app.utils.Result;
import org.neo4j.driver.v1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class UsuarioDAO {

    private static String addQuery = "CREATE ( usuario:Usuario {nombre: {nombre}, apellido: {apellido}, dni: {dni}, email: {email}, password: {password}} )";
    private static String findQuery = "MATCH (usuario:Usuario) WHERE usuario.email = {email} RETURN usuario";

    //private static Session db = DBManager.getInstance().getSession();
    private Driver db;

    public UsuarioDAO () {
        db = DBManager.getInstance().getDriver();
    }

    public Result findUsuario_byEmail(String email) {
        Session session = db.session();
        Result result;

        StatementResult stmt
                = session.run( findQuery, Values.parameters(
                        "email", email) );

        if (!stmt.hasNext()) {
            return new Result(false, null, "No existe el usuario");
        }

        Record record = stmt.next();

        Usuario usuario = new Usuario();
        usuario.setNombre(record.get("s").toString());

        result = new Result(true, usuario, "Se encontro al usuario");
        session.close();
        return result;
    };

    public Result authUsuario (String email, String password) {

        Result result;
        String hpassword = Hasher.MD5(password);

        return null;
    }

    public Result addUsuario (Usuario usuario) {
        Session session = db.session();

        StatementResult result =
                session.run(addQuery, Values.parameters(
                        "nombre", usuario.getNombre(),
                        "apellido", usuario.getApellido(),
                        "dni", usuario.getDni(),
                        "email", usuario.getEmail(),
                        "password", usuario.getPassword()));

        int nodesCreated = result.consume().counters().nodesCreated();
        System.out.format("nodesCreated: %d", nodesCreated);

        session.close();


        if (nodesCreated == 1) {
            return new Result(true, usuario);
        }else {
            return new Result(false, null, "No se pudo crear al usuario");
        }
    }
}
