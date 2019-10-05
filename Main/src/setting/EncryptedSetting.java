/*
 * Copyright (c) 2019. By Mehmet Shams
 * Contact me: m.shamsj[at]yahoo.com
 */

package setting;

import security.CryptoUtil;
import security.Session;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EncryptedSetting {
    private static Properties prop;

    public static Properties getProp() {
        if (prop == null)
            prop = new Properties();

        return prop;
    }

    public static void writeSession(String filename, Session s) throws IOException {
        //set key-values
        getProp().setProperty(Session.HOST,
                CryptoUtil.encrypt(s.getHost()));

        getProp().setProperty(Session.DBNAME,
                CryptoUtil.encrypt(s.getDbname()));

        getProp().setProperty(Session.USER,
                CryptoUtil.encrypt(s.getUser()));

        getProp().setProperty(Session.PASSWORD,
                CryptoUtil.encrypt(s.getPassword()));

        //save to file
        FileOutputStream fs = new FileOutputStream(filename);
        getProp().store(fs, "JPassport");
    }

    public static Session readSession(String filename) throws IOException {
        Session result = new Session();

        FileInputStream fs = new FileInputStream(filename);
        getProp().load(fs);

        //System.out.println(getProp().toString());

        result.setHost(CryptoUtil.decrypt(
                getProp().getProperty(Session.HOST)));

        result.setDbname(CryptoUtil.decrypt(
                getProp().getProperty(Session.DBNAME)));

        result.setUser(CryptoUtil.decrypt(
                getProp().getProperty(Session.USER)));

        result.setPassword(CryptoUtil.decrypt(
                getProp().getProperty(Session.PASSWORD)));

        return result;
    }

}
