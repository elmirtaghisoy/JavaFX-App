package sample;

import java.sql.*;

public class DatabaseHandler extends Config {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useTimezone=true&serverTimezone=UTC";
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE +
                "(" + Const.USERS_FIRSTNAME + "," + Const.USERS_LASTNAME +
                "," + Const.USERS_USERNAME + "," + Const.USERS_PASSWORD +
                "," + Const.USERS_LOCATION + "," + Const.USERS_GENDER + ")" +
                "VALUES(?,?,?,?,?,?)";

        PreparedStatement prstr = null;
        try {
            prstr = getDbConnection().prepareStatement(insert);
            prstr.setString(1, user.getFirstName());
            prstr.setString(2, user.getLastName());
            prstr.setString(3, user.getUserName());
            prstr.setString(4, user.getPassword());
            prstr.setString(5, user.getLocation());
            prstr.setString(6, user.getGender());
            prstr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user) {
        ResultSet rs = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?";
        PreparedStatement prstr = null;
        try {
            prstr = getDbConnection().prepareStatement(select);
            prstr.setString(1, user.getUserName());
            prstr.setString(2, user.getPassword());
            rs = prstr.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
