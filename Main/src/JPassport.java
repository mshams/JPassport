/*
 * Copyright (c) 2019. By Mehmet Shams
 * Contact me: m.shamsj[at]yahoo.com
 */

import com.j256.ormlite.logger.LocalLog;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import dto.User;

import java.util.List;
import java.util.Locale;



public class JPassport {
    private static final String VERSION = "1.0";
    private static final String ABOUT_ME = "JPassport, 3DPassport user manager By M.Shams v" + VERSION;

    public static void main(String[] args) {
        //decrease log level for ormlite
        System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");

        try {
            //parse arguments
            JSAP jsap = Args.getInstance();
            JSAPResult config = jsap.parse(args);

            if (config.getBoolean(Args.SW_HELP)) {
                System.out.println(ABOUT_ME);
                System.out.print("Usage:" + jsap.getUsage() + "\n" + jsap.getHelp());

            } else if (!config.success()) {
                //show wrong arguments
                for (java.util.Iterator errs = config.getErrorMessageIterator(); errs.hasNext(); ) {
                    System.out.println("Error: " + errs.next());
                }

            } else {
                System.out.println(ABOUT_ME);

                //extract credentials from arguments
                String host = config.getString(Args.SW_SERVER).split(Args.SW_DELIMITER)[0];
                String database = config.getString(Args.SW_SERVER).split(Args.SW_DELIMITER)[1];
                String user = config.getString(Args.SW_USER).split(Args.SW_DELIMITER)[0];
                String password = config.getString(Args.SW_USER).split(Args.SW_DELIMITER)[1];

                DataAccess dal = new DataAccess(host, database, user, password);

                if (config.getBoolean(Args.SW_LIST)) {
                    listPrint(dal.getAllUsers());
                } else {
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
