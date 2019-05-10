package login;

import enteties.Program;
import enteties.User;

import java.sql.*;

public class DBManager {
    private static final String ADD_USER_SQL = "INSERT INTO users(chat_id, name, real_name)VALUES (?,?,?)";
    private static final String UPDATE_SEX_SQL = "UPDATE users SET sex = ? WHERE chat_id = ?";
    private static final String UPDATE_AGE_SQL = "UPDATE users SET age = ? WHERE chat_id = ?";
    private static final String UPDATE_HEIGHT_SQL = "UPDATE users SET height = ? WHERE chat_id = ?" ;
    private static final String UPDATE_WEIGHT_SQL = "UPDATE users SET weight = ? WHERE chat_id = ?" ;
    private static final String UPDATE_ACTIVITY_SQL ="UPDATE users SET activity = ? WHERE chat_id = ?";
    private static final String GET_PROGRAM_BY_AGE_AND_SEX_SQL = "SELECT * FROM train_programs WHERE age = ? AND sex = ?";
    private static final String GET_USER_SQL = "SELECT * FROM users WHERE chat_id = ?";
    private static Connection con;
    private Statement st;
    private ResultSet rs;
    private static DBManager dbManager;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/myProgram", "postgres","");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static DBManager getInstance(){
        if (dbManager == null){
            dbManager = new DBManager();
        }
        return dbManager;
    }


    public String addUser(String name, int chat_id, String real_name) {
        try {
            PreparedStatement pstm = con.prepareStatement(ADD_USER_SQL);
            pstm.setInt(1, chat_id);
            pstm.setString(2, name);
            pstm.setString(3, real_name);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error" + e);
            return "Failure";
        }
        return "It's OK!";
    }

    public User getUser(int chatId){
        User user = null;
        try {
            PreparedStatement pstm = con.prepareStatement(GET_USER_SQL);
            pstm.setInt(1, chatId);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setAge(resultSet.getString("age"));
                user.setChatId(resultSet.getInt("chat_id"));
                user.setName(resultSet.getString("name"));
                user.setRealName(resultSet.getString("real_name"));
                user.setSex(resultSet.getString("sex"));
                user.setHeight(resultSet.getString("height"));
                user.setWeight(resultSet.getString("weight"));
                user.setActivity(resultSet.getString("activity"));
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
        return user;
    }

    public String updateSex(String sex, int chat_id) {
        return setStringAndExecute(sex, chat_id, UPDATE_SEX_SQL);
    }

    public String updateAge(String age, int chat_id) {
        return setStringAndExecute(age, chat_id, UPDATE_AGE_SQL);
    }

    public String updateHeight(String height, int chat_id) { return setStringAndExecute(height, chat_id, UPDATE_HEIGHT_SQL);}

    public String updateWeight(String weight, int chat_id) { return setStringAndExecute(weight, chat_id, UPDATE_WEIGHT_SQL);}

    public String updateActivity(String activity, int chat_id) { return setStringAndExecute(activity, chat_id, UPDATE_ACTIVITY_SQL); }

    public Program getProgramByAgeAndSex(String age, String sex){
        Program program = null;
        try {
            PreparedStatement pstm = con.prepareStatement(GET_PROGRAM_BY_AGE_AND_SEX_SQL);
            pstm.setString(1, age);
            pstm.setString(2, sex);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                program = new Program();
                program.setBulk(resultSet.getString("bulk"));
                program.setFatLoss(resultSet.getString("fat_loss"));
                program.setFit(resultSet.getString("fit"));
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
        return program;
    }


    private String setStringAndExecute(String firstParam, int chat_id, String updateSql) {
        try {
            PreparedStatement pstm = con.prepareStatement(updateSql);
            pstm.setString(1, firstParam);
            pstm.setInt(2, chat_id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error" + e);
            return "Failure";
        }
        return "It's OK!";
    }
}
