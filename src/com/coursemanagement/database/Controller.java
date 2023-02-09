package com.coursemanagement.database;

import com.coursemanagement.user.User;

public class Controller {
    public static User current = null;

    public static User getCurrentUser() {
        return current;
    }

    public static void setCurrentUser(User user) {
        current = user;
    }

    public static void logout() {
        current = null;
    }

}
