/*
 * Copyright (c) 2019. By Mehmet Shams
 * Contact me: m.shamsj[at]yahoo.com
 */

package db.dto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Locale;

@DatabaseTable(tableName = "x3dpassport_schema.users")
public class User {
    @DatabaseField(canBeNull = false, id = true)
    private long ds_id;

    @DatabaseField(canBeNull = true)
    private String ds_EMAIL;

    @DatabaseField(canBeNull = true)
    private String ds_FIRSTNAME;

    @DatabaseField(canBeNull = true)
    private String ds_LASTNAME;

    @DatabaseField(canBeNull = true)
    private String ds_USERNAME;

    @DatabaseField(canBeNull = false)
    private long ds_tenant_id;

    @DatabaseField(canBeNull = false)
    private int ds_active;

    @DatabaseField(canBeNull = true)
    private String ds_lastsynchronized;

    @DatabaseField(canBeNull = true)
    private long ds_pwd_crea_ts;

    @DatabaseField(canBeNull = true)
    private int ds_email_confirmed;

    @DatabaseField(canBeNull = false)
    private int ds_social;

    public static String FIELD_EMAIL = "ds_EMAIL";

    public User() {
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH,
                "%d:\t%s, %s, %s, %s", ds_id, ds_EMAIL, ds_FIRSTNAME, ds_LASTNAME, ds_USERNAME);
    }

    public static String header() {
        return "[Id:\tMail, FirstName, LastName, UserName]";
    }

    public String getDs_EMAIL() {
        return ds_EMAIL;
    }

    public String getDs_FIRSTNAME() {
        return ds_FIRSTNAME;
    }

    public String getDs_LASTNAME() {
        return ds_LASTNAME;
    }

    public String getDs_USERNAME() {
        return ds_USERNAME;
    }
}
