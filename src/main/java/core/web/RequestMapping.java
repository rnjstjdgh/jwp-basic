package core.web;

import next.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {

    private static final Map<String, Controller> requestMapping = new HashMap<>();

    static {
        Controller controller = new CreateUserController();
        requestMapping.put("/users/create",controller);
        requestMapping.put("/users/form",controller);

        requestMapping.put("/",new HomeController());
        requestMapping.put("/users/",new ListUserController());

        controller = new LoginController();
        requestMapping.put("/users/login",controller);
        requestMapping.put("/users/loginForm",controller);

        requestMapping.put("/users/logout",new LogoutController());
        requestMapping.put("/users/profile",new ProfileController());

        controller = new UpdateUserController();
        requestMapping.put("/users/update",controller);
        requestMapping.put("/users/updateForm",controller);
    }

    public static Controller getController(String url){
        return requestMapping.get(url);
    }
}
