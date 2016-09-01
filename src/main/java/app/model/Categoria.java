package app.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Gi Wah Davalos on 31/08/2016.
 */
public class Categoria implements Model{

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String nombre;

    @Override
    public boolean isValid() {
        return true;
    }
}
