package app.model;

import app.utils.Hasher;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class Usuario implements Model{
    private String nombre;
    private String apellido;
    private String dni;
    private String fbid;
    private String email;
    private String password;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    /*
        OJO: el parametro de entrada del método
        es en texto plano.
     */
    public void setPassword(String password) {
        this.password = password;
    }

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
