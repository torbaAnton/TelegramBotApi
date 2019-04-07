/*
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
    public static void main(String[] args) {

        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/myProgram", "postgres",
                    "keufycr1997");/**
             lemon_school - это имя базы данных, вы подставляете свою
             user:postgres - это имя пользователя вашей базыданных
             password - собственно пароль ваше базы
             */

/*
            if (connection == null) {
                System.out.println("Failed to make connection!");
            } else {
                System.out.println("You made it, take control your database now!");
                List<User> users = new ArrayList<User>();
                try {
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT *  FROM public.users ");
                    while (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("id_user"));
                        user.setLogin(rs.getString("login"));
                        user.setPassword(rs.getString("password"));
                        users.add(user);
                    }
                    rs.close();
                    statement.close();
                } catch (SQLException se) {
                    System.err.println(se.getMessage());
                }
                User[] usersArray = new User[users.size()];
                users.toArray(usersArray);
                System.out.println(users);
                connection.close();
            }
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }




    }

}

 */