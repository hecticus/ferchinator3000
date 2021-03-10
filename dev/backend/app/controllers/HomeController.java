package controllers;

import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * Manage a database of computers
 */
public class HomeController extends Controller {

    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;
    private final MessagesApi messagesApi;

    @Inject
    public HomeController(FormFactory formFactory,
                          HttpExecutionContext httpExecutionContext,
                          MessagesApi messagesApi) {
        this.formFactory = formFactory;
        this.httpExecutionContext = httpExecutionContext;
        this.messagesApi = messagesApi;
    }
    /**
     * Handle default path requests, redirect to computers list
     */
    public Result index() {
        return ok();
    }
}
            
