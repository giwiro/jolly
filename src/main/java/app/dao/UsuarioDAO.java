package app.dao;

import app.db.DBManager;
import app.model.Usuario;
import app.utils.Hasher;
import app.utils.Result;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class UsuarioDAO {

    private static String addQuery = "CREATE ( usuario:Usuario {nombre: {nombre}, apellido: {apellido}, dni: {dni}, email: {email}, password: {password}} ) -[:TIENE]-> (carrito:Carrito {activo: true})";
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
        Node user_node = record.get("usuario").asNode();
        Usuario usuario = new Usuario();

        //System.out.println("nombre: " + user_node.get("nombre").asString());
        usuario.setNombre(user_node.get("nombre").asString());
        usuario.setApellido(user_node.get("apellido").asString());
        usuario.setDni(user_node.get("dni").asString());
        usuario.setPassword(user_node.get("password").asString());
        usuario.setEmail(user_node.get("email").asString());

        result = new Result(true, usuario, "Se encontro al usuario");
        session.close();
        return result;
    };

    public Result authUsuario (String email, String password) {

        Result result_find = this.findUsuario_byEmail(email);
        Result result
                = new Result(false, null, "El email y la contraseña no coinciden con la base de datos");

        if (result_find.isSuccess()) {

            String hpassword = Hasher.MD5(password);
            Usuario usuario = (Usuario)result_find.getResult();
            if (hpassword.equals(usuario.getPassword())) {
                //usuario.setPassword(null);
                result = new Result(true, usuario, "Se autenticó al usuario correctamente");
            }
        }

        return result;
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


        if (nodesCreated > 1) {
            return new Result(true, usuario, "Se creó el usuario satisfactoriamente");
        }else {
            return new Result(false, null, "No se pudo crear al usuario");
        }
    }
}
