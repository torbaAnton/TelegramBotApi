import java.sql.*;
import java.util.ArrayList;

public class Login {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public Login() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/myProgram\", \"postgres\",\n" +
                    "                    \"keufycr1997");
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public String add(String name, String chat_id) {
        String sql = "INSERT INTO users(name, chat_id)VALUES (?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, chat_id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error" + e);
            return "Информацию не удалось добавить";
        }
        return "Информация добавлена!";
    }
    public void change(String newName, String id_chat){
        try{
            PreparedStatement st = con.prepareStatement("UPDATE users SET name = ? WHERE chat_id=?");
            st.setString(1, newName);
            st.setString(2, id_chat);
            st.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public ArrayList getChatID(){
        ArrayList<String> list = null;
        try{
            list = new ArrayList<>();
            PreparedStatement st = null;
            String query = "select * FROM users";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()){
                list.add(rs.getString("chat_id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public void remove (String name){
        PreparedStatement st = null;
        try{
            st = con.prepareStatement("DELETE FROM user WHERE name = ?");
            st.setString(1,name);
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
