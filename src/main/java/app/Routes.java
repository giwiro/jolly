package app;

import lombok.Getter;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class Routes {

    public static class Web {
        @Getter public static final String INDEX = "/";
        @Getter public static final String LOGIN = "/login";
        @Getter public static final String LOGOUT = "/logout";
    }

    public static class Template {
        public final static String INDEX = "/templates/index/index.vm";
        public final static String LOGIN = "/templates/login/login.vm";
    }
}