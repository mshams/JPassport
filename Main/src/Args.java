/*
 * Copyright (c) 2019. By Mehmet Shams
 * Contact me: m.shamsj[at]yahoo.com
 */

import com.martiansoftware.jsap.*;

public class Args {
    public static final String SW_HELP = "help";
    public static final String SW_SERVER = "server";
    public static final String SW_USER = "user";
    public static final String SW_LIST = "list";
    public static final String SW_DELETE = "del";

    public static final char SW_HELP_SHORT = 'h';
    public static final char SW_SERVER_SHORT = 's';
    public static final char SW_USER_SHORT = 'u';
    public static final char SW_LIST_SHORT = 'l';
    public static final char SW_DELETE_SHORT = 'd';

    public static final String SW_HELP_HELP = "Get help information.";

    public static final String SW_SERVER_HELP = "Set server IP and database name.\n" +
            "Ex: -" + SW_SERVER_SHORT + " localhost:3dsdb";

    public static final String SW_USER_HELP = "Set username and password of 3dPassport database.\n" +
            "Ex: -" + SW_USER_SHORT + " x3d:myPassword";

    public static final String SW_LIST_HELP = "Get list of users.";

    public static final String SW_DELETE_HELP = "Delete specific user.\n" +
            "Ex: -" + SW_DELETE_SHORT + " id:101\n" +
            "Ex: -" + SW_DELETE_SHORT + " mail:myname@domain.com\n" +
            "Ex: -" + SW_DELETE_SHORT + " mail:%john@dom%";

    public static final java.lang.String SW_DELIMITER = ":";
    public static final CharSequence SW_SUB_ID = "id";
    public static final CharSequence SW_SUB_MAIL = "mail";

    public Args() {
    }

    public static JSAP getInstance() throws JSAPException {
        JSAP jsap = new JSAP();

        jsap.registerParameter(new Switch(Args.SW_HELP).setShortFlag(Args.SW_HELP_SHORT)
                .setLongFlag(Args.SW_HELP)
                .setHelp(Args.SW_HELP_HELP));

        jsap.registerParameter(new FlaggedOption(Args.SW_SERVER).setShortFlag(Args.SW_SERVER_SHORT)
                .setLongFlag(Args.SW_SERVER)
                .setRequired(true)
                .setHelp(Args.SW_SERVER_HELP));

        jsap.registerParameter(new FlaggedOption(Args.SW_USER).setShortFlag(Args.SW_USER_SHORT)
                .setLongFlag(Args.SW_USER)
                .setRequired(true)
                .setHelp(Args.SW_USER_HELP));

        jsap.registerParameter(new Switch(Args.SW_LIST).setShortFlag(Args.SW_LIST_SHORT)
                .setLongFlag(Args.SW_LIST)
                .setHelp(Args.SW_LIST_HELP));

        jsap.registerParameter(new FlaggedOption(Args.SW_DELETE).setShortFlag(Args.SW_DELETE_SHORT)
                .setLongFlag(Args.SW_DELETE)
                .setHelp(Args.SW_DELETE_HELP));

        return jsap;
    }
}
