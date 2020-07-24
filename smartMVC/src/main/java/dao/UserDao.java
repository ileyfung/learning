package dao;

import entity.Emp;
import entity.User;
import util.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Data 2020/6/28 0028
 */
public class UserDao {

    public User findUserByName(String name) {
        String sql = "select id,name,password,email from t_user where name=?";
        try (Connection conn = DButil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = getUser(resultSet);
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    
    public int addUser(User user) {
        String sql = "insert into t_user values(null,?,?,?)";
        try(Connection conn = DButil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getEmail());
            int i = ps.executeUpdate();
            return i;

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<User> allUser() {
        String sql = "select * from t_user";
        try(Connection conn = DButil.getConnection()){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> userList = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                User user = new User(id,name,password,email);
                System.out.println(user);
                userList.add(user);
            }
            return userList;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //删除用户
    public int delete(int id) {
        String sql = "delete from t_user where id=?";
        try (Connection conn = DButil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int n = ps.executeUpdate();
            return n;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //更新用户信息
    public int update(User user) {
        String sql = "update t_user set name=?,password=?,email=? where id=?";
        try (Connection conn = DButil.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setInt(4,user.getId());
            int i = preparedStatement.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /*
    获取某一个用户的信息
     */
    public User findUser(int id) {
        String sql = "select * from t_user where id=" + id;
        try (Connection conn = DButil.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            User user = null;
            while (resultSet.next()) {
                user = getUser(resultSet);
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        return new User(id,name,password,email);
    }
}
