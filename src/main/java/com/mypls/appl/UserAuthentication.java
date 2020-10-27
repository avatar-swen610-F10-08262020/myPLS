package com.mypls.appl;

import com.mypls.model.User;
import com.mypls.ui.LoginController;
import com.mypls.util.SessionUtil;
import spark.ModelAndView;
import spark.Request;

public class UserAuthentication {
    SessionUtil sessionUtil = new SessionUtil();
    LoginController loginController = new LoginController();
    public enum MethodName {
        doNothing,
        athenticateUser
    }
    public Object invokeMethod(MethodName methodName, Object argument){
        switch (methodName){
            case doNothing:
                return argument;
            case athenticateUser:
                return athenticateUser((Request) argument);

            default:
                return null;
        }
    }

    public ModelAndView athenticateUser(Request req){
        User user = sessionUtil.getAuthenticatedUser(req);

        return loginController.login(req);
    }


}
