package app.utils;

import app.Routes;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Map;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class View {
    private static VelocityTemplateEngine engine;

    public static String render(Request request, Map<String, Object> model, String templatePath) {

        model.put("currentUser", SessionUtil.getSessionCurrentUser(request));
        model.put("isLogged", SessionUtil.isLogged(request));
        model.put("WebPath", Routes.Web.class); // Access application URLs from templates

        if (model.get("titulo") == null) {
            model.put("titulo", "Sistema de Control Sanitario");
        }
        return getRenderer().render(new ModelAndView(model, templatePath));
    }

    private static VelocityTemplateEngine getRenderer() {
        if (engine == null) {
            engine = new VelocityTemplateEngine();
        }
        return engine;
    }
}
