/*
 * Copyright (c) 2021. By Muhammad Shams
 * @author Muhammad Shams - mshamsj@gmail.com
 * @version 1.0
 */

package db.dto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "INFORMATION_SCHEMA.TABLES")
public class Table {
    public static String QUERY_NAME1 = "Table_Type";
    public static String QUERY_VALUE1 = "BASE TABLE";

    public static String QUERY_NAME2 = "Table_Name";
    public static String QUERY_VALUE2 = "users";

    @DatabaseField(canBeNull = false)
    private String Table_Catalog;

    @DatabaseField(canBeNull = false)
    private String Table_Schema;

    @DatabaseField(canBeNull = false)
    private String Table_Name;

    @DatabaseField(canBeNull = false)
    private String Table_Type;

    @Override
    public String toString() {
        return Table_Schema + "." + Table_Name;
    }
}
