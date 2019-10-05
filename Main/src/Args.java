/*
 * Copyright (c) 2019. By Mehmet Shams
 * Contact me: m.shamsj[at]yahoo.com
 */

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.Switch;

public class Args {
    public static final String SW_HELP = "help";
    public static final String SW_SERVER = "server";
    public static final String SW_USER = "user";
    public static final String SW_LIST = "list";
    public static final String SW_DELETE = "del";
    public static final String SW_FIND = "find";
    public static final String SW_WRITE = "write";
    public static final String SW_READ = "read";

    public static final char SW_HELP_SHORT = 'h';
    public static final char SW_SERVER_SHORT = 's';
    public static final char SW_USER_SHORT = 'u';
    public static final char SW_LIST_SHORT = 'l';
    public static final char SW_DELETE_SHORT = 'd';
    public static final char SW_FIND_SHORT = 'f';
    public static final char SW_WRITE_SHORT = 'w';
    public static final char SW_READ_SHORT = 'r';

    public static final String SW_HELP_HELP = "Get help information.";

    public static final String SW_SERVER_HELP = "Set server IP or hostname and database name.\n" +
            "Ex: -" + SW_SERVER_SHORT + " localhost:dbname";

    public static final String SW_USER_HELP = "Set username and password of 3dPassport database.\n" +
            "Ex: -" + SW_USER_SHORT + " dbuser:dbpassword";

    public static final String SW_LIST_HELP = "Get list of users.";

    public static final String SW_DELETE_HELP = "Delete specific user.\n" +
            "Ex: -" + SW_DELETE_SHORT + " id:101\n" +
            "Ex: -" + SW_DELETE_SHORT + " mail:myname@domain.com\n" +
            "Ex: -" + SW_DELETE_SHORT + " mail:%john@dom%";

    public static final String SW_FIND_HELP = "Find user by keyword.\n" +
            "Ex: -" + SW_FIND_SHORT + " keyword";

    public static final String SW_WRITE_HELP = "Save sessin (hostname, dbname, user, pass) to jps file.\n" +
            "Ex: -" + SW_WRITE_SHORT + " session1.conf";

    public static final String SW_READ_HELP = "Load sessin (hostname, dbname, user, pass) from jps file.\n" +
            "Ex: -" + SW_READ_SHORT + " session1.conf";

    public static final java.lang.String SW_DELIMITER = ":";
    public static final CharSequence SW_SUB_ID = "id";
    public static final CharSequence SW_SUB_MAIL = "mail";

    public Args() {
    }

    public static JSAP getInstance() throws JSAPException {
        JSAP jsap = new JSAP();
        //Switch: A single-argument parameter whose presence alone.
        //  Ex: "-version"

        //Option: A parameter whose content conveys meaning.
        //  Ex: "-cp myjar.jar"

        //Flagged Option: An option that is preceded by a "flag" to indicate which option is being set.
        //  Ex: "-cp myjar.jar" is flagged by "-cp"

        //Unflagged Option: An option that is not preceded by a flag, and whose meaning is conveyed by its
        // position in the command line relative to other Unflagged Options.
        //  Ex: "java -cp myjar.jar MyClass", "MyClass" is an unflagged option

        //QualifiedSwitch: A combination of Switch and FlaggedOption. It serves the same function as a Switch
        // on a command line, but it may be "qualified" with additional data following a colon.
        //  EX: "java -verbose MyClass" and "java -verbose:class MyClass", "verbose" is a QualifiedSwitch
        //      qualified by the "class" qualifier in the second example.


        jsap.registerParameter(new Switch(Args.SW_HELP)
                .setShortFlag(Args.SW_HELP_SHORT)
                .setLongFlag(Args.SW_HELP)
                .setHelp(Args.SW_HELP_HELP));

        jsap.registerParameter(new Switch(Args.SW_LIST)
                .setShortFlag(Args.SW_LIST_SHORT)
                .setLongFlag(Args.SW_LIST)
                .setHelp(Args.SW_LIST_HELP));

        jsap.registerParameter(new FlaggedOption(Args.SW_SERVER)
                .setShortFlag(Args.SW_SERVER_SHORT)
                .setLongFlag(Args.SW_SERVER)
                .setRequired(false)
                .setAllowMultipleDeclarations(false)
                .setHelp(Args.SW_SERVER_HELP));

        jsap.registerParameter(new FlaggedOption(Args.SW_USER)
                .setShortFlag(Args.SW_USER_SHORT)
                .setLongFlag(Args.SW_USER)
                .setRequired(false)
                .setAllowMultipleDeclarations(false)
                .setHelp(Args.SW_USER_HELP));

        jsap.registerParameter(new FlaggedOption(Args.SW_DELETE)
                .setShortFlag(Args.SW_DELETE_SHORT)
                .setLongFlag(Args.SW_DELETE)
                .setRequired(false)
                .setAllowMultipleDeclarations(false)
                .setHelp(Args.SW_DELETE_HELP));

        jsap.registerParameter(new FlaggedOption(Args.SW_FIND)
                .setShortFlag(Args.SW_FIND_SHORT)
                .setLongFlag(Args.SW_FIND)
                .setRequired(false)
                .setAllowMultipleDeclarations(false)
                .setHelp(Args.SW_FIND_HELP));

        jsap.registerParameter(new FlaggedOption(Args.SW_WRITE)
                .setShortFlag(Args.SW_WRITE_SHORT)
                .setLongFlag(Args.SW_WRITE)
                .setRequired(false)
                .setAllowMultipleDeclarations(false)
                .setHelp(Args.SW_WRITE_HELP));

        jsap.registerParameter(new FlaggedOption(Args.SW_READ)
                .setShortFlag(Args.SW_READ_SHORT)
                .setLongFlag(Args.SW_READ)
                .setRequired(false)
                .setAllowMultipleDeclarations(false)
                .setHelp(Args.SW_READ_HELP));

        return jsap;
    }
}
