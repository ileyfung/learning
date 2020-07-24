import dao.EmpDao;
import dao.UserDao;
import entity.Emp;
import entity.User;
import org.junit.Test;

import java.sql.Date;
import java.util.List;


/**
 * @Data 2020/6/4 0004
 */
public class TestCase {
    @Test
    public void testSaveEmp() {
        Date date = new Date(System.currentTimeMillis());
        Emp emp = new Emp(0,"范传奇",1,date,1,1000,20);
        EmpDao empDao = new EmpDao();
        int n = empDao.save(emp);
        System.out.println(n);
    }

    @Test
    public void testDeleteEmp() {
        EmpDao empDao = new EmpDao();
        int delete = empDao.delete(6);
        System.out.println(delete+"line changed");
    }

    @Test
    public void testFindMgrs() {
        EmpDao dao = new EmpDao();
        List<Emp> list = dao.findMgrs();
        for (Emp emp:list) {
            System.out.println(emp);
        }
    }

    @Test
    public void testFindUserByName() {
        UserDao userDao = new UserDao();
        User tom = userDao.findUserByName("Tom");
        System.out.println(tom);
    }

    @Test
    public void testFindAll() {
        UserDao userDao = new UserDao();
        List<User> userList = userDao.allUser();
        for (User user1: userList) {
            System.out.println(user1.getName());
        }
    }
}
