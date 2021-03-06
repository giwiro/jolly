package app.utils;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class Result<U> {

    @Setter @Getter private boolean success;
    @Setter @Getter private U result;
    @Setter @Getter private String message;

    public Result(boolean success, U result, String message) {
        this.success = success;
        this.result = result;
        this.message = message;
    }

    public Result(boolean success, U result) {
        this.success = success;
        this.result = result;
    }
}