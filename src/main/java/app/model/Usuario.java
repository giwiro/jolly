package app.model;

import app.utils.Hasher;
import lombok.Getter;
import lombok.Setter;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class Usuario implements Model{

    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private String apellido;

    @Getter
    @Setter
    private String dni;

    @Getter
    @Setter
    private String fbid;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Override
    public boolean isValid() {
        if (nombre == null || nombre.length() == 0) {
            return false;
        }
        if (apellido == null || apellido.length() == 0) {
            return false;
        }
        if (dni == null || dni.length() == 0) {
            return false;
        }
        if (email == null || email.length() == 0) {
            return false;
        }
        if (password == null || password.length() == 0) {
            return false;
        }
        return true;
    }

    public void set_s_Password(String _s_Password) {
        this.password = Hasher.MD5(_s_Password);
    }
}
