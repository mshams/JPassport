/*
 * Copyright (c) 2021. By Muhammad Shams
 * @author Muhammad Shams - mshamsj@gmail.com
 * @version 1.0
 */

package security;


public class Session {
    public static String HOST = "host";
    public static String DBNAME = "dbname";
    public static String USER = "user";
    public static String PASSWORD = "passsword";

    private String host;
    private String dbname;
    private String user;
    private String password;

    public Session(String host, String dbname, String user, String password) {
        this.host = host;
        this.dbname = dbname;
        this.user = user;
        this.password = password;
    }

    public Session() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
