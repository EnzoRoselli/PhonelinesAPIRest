package com.utn.UTNphones.Controllers.Webs.URLconstants;

public class UserRouter {
    public static final String BASE_PATH="/users";
    public static final String USER_ID=BASE_PATH +"/{userId}";
    public static final String USER_IDENTIFICATION=BASE_PATH +"/{identification}";
    public static final String BASE_PATH_CALLS="/calls";
    public static final String DESTINATION_PHONE=BASE_PATH_CALLS + "/{destinationPhone}";
}
