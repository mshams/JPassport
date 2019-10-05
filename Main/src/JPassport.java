/*
 * Copyright (c) 2019. By Mehmet Shams
 * Contact me: m.shamsj[at]yahoo.com
 */

import com.j256.ormlite.logger.LocalLog;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import db.DataAccess;
import db.dto.User;
import security.Session;
import setting.EncryptedSetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class JPassport {
    private static final String VERSION = "1.1";
    private static final String ABOUT_ME = "JPassport, 3DPassport user manager By M.Shams v" + VERSION;

    public static void main(String[] args) {
        //decrease log level for ormlite
        System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");

        try {
            //parse arguments
            JSAP jsap = Args.getInstance();
            JSAPResult config = jsap.parse(args);

            if (config.getBoolean(Args.SW_HELP)) {
                //help switch
                System.out.println(ABOUT_ME);
                System.out.print("Usage:" + jsap.getUsage() + "\n" + jsap.getHelp());

            } else if (!config.success()) {
                //show wrong arguments
                for (java.util.Iterator errs = config.getErrorMessageIterator(); errs.hasNext(); ) {
                    System.out.println("Error: " + errs.next());
                }

            } else {
                //process command switches
                System.out.println(ABOUT_ME);

                //check for switches

                //session to store credentials
                Session ses;

                //check session parameters
                if (config.contains(Args.SW_READ)) {
                    //load session from file
                    String configFile = config.getString(Args.SW_READ);
                    ses = EncryptedSetting.readSession(configFile);

                } else if (config.contains(Args.SW_SERVER) && config.contains(Args.SW_USER)) {
                    //extract session credentials from arguments
                    String host = config.getString(Args.SW_SERVER).split(Args.SW_DELIMITER)[0];
                    String database = config.getString(Args.SW_SERVER).split(Args.SW_DELIMITER)[1];
                    String user = config.getString(Args.SW_USER).split(Args.SW_DELIMITER)[0];
                    String password = config.getString(Args.SW_USER).split(Args.SW_DELIMITER)[1];

                    ses = new Session(host, database, user, password);
                } else {
                    System.out.println("Not enough parameters. Try \"JPassport -h\"");
                    return;
                }

                if (config.contains(Args.SW_WRITE)) {
                    //save session to file
                    String configFile = config.getString(Args.SW_WRITE);
                    EncryptedSetting.writeSession(configFile, ses);
                }


                //initialize data access layer
                DataAccess dal = new DataAccess(
                        ses.getHost(),
                        ses.getDbname(),
                        ses.getUser(),
                        ses.getPassword());


                //process rest of parameters
                if (config.getBoolean(Args.SW_LIST)) {
                    listPrint(dal.getAllUsers());

                } else if (config.contains(Args.SW_FIND)) {
                    String keyword = config.getString(Args.SW_FIND).toLowerCase();

                    List<User> users = dal.getAllUsers();
                    List<User> foundUsers = new ArrayList<User>();
                    for (User user : users) {
                        if (user.getDs_USERNAME().toLowerCase().contains(keyword) ||
                                user.getDs_LASTNAME().toLowerCase().contains(keyword) ||
                                user.getDs_FIRSTNAME().toLowerCase().contains(keyword) ||
                                user.getDs_EMAIL().toLowerCase().contains(keyword))
                            foundUsers.add(user);
                    }

                    if (foundUsers.size() > 0) {
                        listPrint(foundUsers);
                    } else {
                        System.out.println("No match found.");
                    }

                } else if (config.contains(Args.SW_DELETE)) {
                    //run delete command
                    String deleteStr = config.getString(Args.SW_DELETE);

                    if (deleteStr.contains(Args.SW_SUB_ID)) {
                        //delete by id
                        long id = Long.parseLong(deleteStr.split(Args.SW_DELIMITER)[1]);
                        dal.deleteById(id);

                    } else if (deleteStr.contains(Args.SW_SUB_MAIL)) {
                        //delete by email
                        String mail = deleteStr.split(Args.SW_DELIMITER)[1];
                        dal.deleteByMail(mail);
                    }

                    System.out.println("Delete successful.");
                }

                dal.close();
            }

        } catch (JSAPException e) {
            System.out.println("Error in parameters. Try \"JPassport -h\"");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listPrint(List<User> users) {
        System.out.println(String.format(Locale.ENGLISH, "Found %d users:", users.size()));
        System.out.println(User.header());

        for (User user : users) {
            System.out.println(user);
        }
    }
}
