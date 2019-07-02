/*
 * Copyright (c) 2019. By Mehmet Shams
 * Contact me: m.shamsj[at]yahoo.com
 */

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.table.DatabaseTableConfig;
import dto.Table;
import dto.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class DataAccess {
    private String connectionString = "jdbc:sqlserver://%s; databaseName=%s; user=%s; password=%s;";
    private JdbcConnectionSource cs;
    private DatabaseTableConfig<User> tbUserConfig;
    private Dao<User, Long> daoUser;

    private String csHost;
    private String csDatabase;
    private String csUser;
    private String csPassword;

    public DataAccess(String csHost, String csDatabase, String csUser, String csPassword) throws SQLException {
        this.csHost = csHost;
        this.csDatabase = csDatabase;
        this.csUser = csUser;
        this.csPassword = csPassword;

        connectionString = String.format(Locale.ENGLISH, connectionString,
                this.csHost,
                this.csDatabase,
                this.csUser,
                this.csPassword);

        initDatabase();
    }


    @Override
    protected void finalize() throws Throwable {
        cs.close();
        super.finalize();
    }

    public void close() throws IOException {
        if (cs != null)
            cs.close();
    }

    private void initDatabase() throws SQLException {
        //connect to database
        cs = new JdbcConnectionSource(connectionString);

        //get Users table exact name from SQL Server schema
        Dao<Table, String> tableDao = DaoManager.createDao(cs, Table.class);
        Where<Table, String> tableQuery = tableDao.queryBuilder().where()
                .eq(Table.QUERY_NAME1, Table.QUERY_VALUE1).and()
                .eq(Table.QUERY_NAME2, Table.QUERY_VALUE2);

        List<Table> listTables = tableQuery.query();
        if (listTables.size() > 0) {
            String usersTableName = listTables.get(0).toString();

            //set found name for Users table
            tbUserConfig = DatabaseTableConfig.fromClass(cs, User.class);
            tbUserConfig.setTableName(usersTableName);

            daoUser = DaoManager.createDao(cs, tbUserConfig);


        } else {
            throw new SQLException("Error, Users table not found!");
        }
    }

    public List<User> getAllUsers() throws SQLException {
        return daoUser.queryForAll();
    }

    public void deleteById(long id) throws SQLException {
        daoUser.deleteById(id);
    }

    public void deleteByMail(String mail) throws SQLException {
        DeleteBuilder<User, Long> deleteQuery = daoUser.deleteBuilder();
        deleteQuery.where().like(User.FIELD_EMAIL, mail);
        daoUser.delete(deleteQuery.prepare());
    }
}
