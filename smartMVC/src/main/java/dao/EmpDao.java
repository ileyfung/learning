package dao;

import entity.Emp;
import sun.security.pkcs11.Secmod;
import util.DButil;

import java.awt.image.DataBufferUShort;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Data 2020/6/1 0001
 */
public class EmpDao {

    /*
    获取某一个员工的信息
     */
    public Emp findEmp(int empno) {
        String sql = "select * from t_emp where empno=" + empno;
        try (Connection conn = DButil.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Emp emp = null;
            while (resultSet.next()) {
                emp = rowToEmp(resultSet);
            }
            return emp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /*
    更新某个员工的信息
     */
    public int updateEmp(Emp emp) {
        String sql = "update t_emp set ename=?,mgr=?,hiredate=?,deptno=?,salary=?,comm=? where empno=?";
        try (Connection conn = DButil.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, emp.getEname());
            preparedStatement.setInt(2, emp.getMgr());
            preparedStatement.setDate(3, emp.getHiredate());
            preparedStatement.setInt(4, emp.getDeptno());
            preparedStatement.setDouble(5, emp.getSalary());
            preparedStatement.setDouble(6, emp.getComm());
            preparedStatement.setInt(7, emp.getEmpno());
            int i = preparedStatement.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /*
    获取员工中的全部领导
     */
    public List<Emp> findMgrs() {
        String sql = "select * from t_emp where mgr = 0 or mgr = 1";
        try (Connection conn = DButil.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Emp> list = new ArrayList<>();
            while (resultSet.next()) {
                Emp emp = rowToEmp(resultSet);
                list.add(emp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /*
    按照员工号删除员工信息
     */
    public int delete(int empno) {
        String sql = "delete from t_emp where empno=?";
        try (Connection conn = DButil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, empno);
            int n = ps.executeUpdate();
            return n;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
    添加员工
    返回添加的行数
     */
    public int save(Emp emp) {
        //将emp对象中的数据保存到数据库
        String sql = "insert into t_emp " +
                "(empno,ename,mgr,hiredate,deptno,salary,comm) " +
                "values(null,?,?,?,?,?,?)";
        try (Connection conn = DButil.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, emp.getEname());
            preparedStatement.setInt(2, emp.getMgr());
            preparedStatement.setDate(3, emp.getHiredate());
            preparedStatement.setInt(4, emp.getDeptno());
            preparedStatement.setDouble(5, emp.getSalary());
            preparedStatement.setDouble(6, emp.getComm());
            int i = preparedStatement.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public List<Emp> findAll() {
        String sql = "select empno,ename,mgr,hiredate,deptno," +
                "salary,comm from t_emp";
        Connection conn = null;
        try {
            conn = DButil.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Emp> list = new ArrayList<>();
            while (resultSet.next()) {
                Emp emp = rowToEmp(resultSet);
                list.add(emp);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        } finally {
            DButil.close(conn);
        }
    }

    /**
     * 重构
     *
     * @param resultSet
     * @return Emp
     * @throws SQLException
     */
    private Emp rowToEmp(ResultSet resultSet) throws SQLException {
        int empno = resultSet.getInt("empno");
        String ename = resultSet.getString("ename");
        int mgr = resultSet.getInt("mgr");
        Date hiredate = resultSet.getDate("hiredate");
        int deptno = resultSet.getInt("deptno");
        double salary = resultSet.getDouble("salary");
        double comm = resultSet.getDouble("comm");
        return new Emp(empno, ename, mgr, hiredate, deptno, salary, comm);
    }

}
