package com.mypls.util;

import com.mypls.model.User;
import spark.Request;

public class SessionUtil {
    private static final String USER_SESSION_ID = "user";
    public void addAuthenticatedUser(Request request, User u) {
        request.session().attribute(USER_SESSION_ID, u);
//        request.session().attribute(ACL_SESSION_ID, ACL);

    }



    public void removeAuthenticatedUser(Request request) {
        request.session().removeAttribute(USER_SESSION_ID);
    }

    public User getAuthenticatedUser(Request request) {
        return request.session().attribute(USER_SESSION_ID);
    }
}
