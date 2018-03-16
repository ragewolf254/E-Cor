package com.ragew.creativesolutions.e_cor.Config;

/**
 * Created by User on 01/10.
 */

public final class TaskConfig {
    private static final boolean PRODUCTION = false;
    public static final String BASE_URL = PRODUCTION ? "" : "http://192.168.1.5/e-cor/Android/";
    //"http://192.168.1.4/xforge/public/ci/"
    //192.168.43.192
    //172.16.46.56
    //192.168.1.18
    public static final String LOGIN_URL = BASE_URL + "validate_user";
    public static final String CLIENT_URL = BASE_URL + "getJson_client";
}
