package controllers;

import play.*;
import play.mvc.*;

import play.twirl.api.Html;
import views.html.*;

/**
 * The primary application controller.
 */
public class Application extends Controller {

    public Result index() {
        return ok(home.render());
    }

}
