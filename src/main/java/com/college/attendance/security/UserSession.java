package com.college.attendance.security;

public class UserSession {

    private static Long loggedInUserId;

    public static void setLoggedInUserId(Long id) {
        loggedInUserId = id;
    }

    public static Long getLoggedInUserId() {
        return loggedInUserId;
    }
}
