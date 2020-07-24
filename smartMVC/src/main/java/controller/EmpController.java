package controller;

import dao.EmpDao;
import entity.Emp;
import mvc.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Describe 员工列表控制器
 * @Data 2020/7/22 0022
 */
public class EmpController {
    //创建dao对象
    EmpDao dao = new EmpDao();

    //员工列表
    @RequestMapping("/controller/list.do")
    public String list(HttpServletRequest request) {
        //得到所有员工
        List<Emp> list = dao.findAll();
        //将员工号作为key，和员工姓名value存入map
        Map<Integer, String> names = new HashMap<>();
        //遍历员工
        for (Emp emp : list) {
            names.put(emp.getEmpno(), emp.getEname());
        }
        //利用request对象将数据共享到jsp页面
        request.setAttribute("list", list);
        request.setAttribute("names", names);
        return "emp2";
    }

    //添加员工
    @RequestMapping("/controller/add.do")
    public String add(HttpServletRequest request) {
        List<Emp> mgrs = dao.findMgrs();
        request.setAttribute("mgrs", mgrs);
        return "add-emp";
    }

    //保存添加员工的信息
    @RequestMapping("/controller/save.do")
    public String save(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            //获取表单提交的数据
            String ename = request.getParameter("ename");
            String mgrId = request.getParameter("mgr");
            String date = request.getParameter("hiredate");
            String deptId = request.getParameter("deptno");
            String salarys = request.getParameter("salary");
            String comms = request.getParameter("comm");

            //将 数据转换成 可以存入数据库的类型
            int mgr = Integer.parseInt(mgrId);
            int deptno = Integer.parseInt(deptId);
            /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            Date hiredate = new Date(sdf.parse(date).getTime());*/
            Date hiredate = Date.valueOf(date);
            double salary = Double.parseDouble(salarys);
            double comm = Double.parseDouble(comms);


            //创建Emp对象
            Emp emp = new Emp(0, ename, mgr, hiredate, deptno, salary, comm);
            //将emp对象的数据传给dao
            int i = dao.save(emp);
            //用i进行判断，如果返回1则重定向到list页面，否则转发至消息提醒页面
            if (i == 1) {
                String path = request.getContextPath() + "/controller/list.do";
                return "redirect:" + path;
            } else {
                request.setAttribute("message", "保存失败");
                return "message";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @RequestMapping("/controller/delete.do")
    public String delete(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            //拿到要删除的员工号
            int empno = Integer.parseInt(request.getParameter("empno"));
            //dao执行删除操作
            int i = dao.delete(empno);
            //根据结果做出判断
            if (i == 1) {
                String path = request.getContextPath() + "/controller/list.do";
                return "redirect:" + path;
            } else {
                request.setAttribute("message", "删除失败");
                return "message";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @RequestMapping("/controller/edit.do")
    public String edit(HttpServletRequest request) {
        //拿到要修改的员工
        int empno = Integer.parseInt(request.getParameter("empno"));
        //使用dao查询该员工信息操作
        Emp emp = dao.findEmp(empno);
        //获取所有上级领导
        List<Emp> mgrs = dao.findMgrs();
        //使用request共享数据到jsp页面
        request.setAttribute("emp", emp);
        request.setAttribute("mgrs", mgrs);
        return "edit-emp";
    }

    @RequestMapping("/controller/update.do")
    public String update(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            String no = request.getParameter("empno");
            String ename=request.getParameter("ename");
            String mgrno=request.getParameter("mgr");
            String hire=request.getParameter("hiredate");
            String dept=request.getParameter("deptno");
            String sly =request.getParameter("salary");
            String com =request.getParameter("comm");
            int empno = Integer.parseInt(no);
            int mgr = Integer.parseInt(mgrno);
            //java.sql.Date 提供了将字符串转换为日期的方法
            Date hiredate = Date.valueOf(hire);
            int deptno = Integer.parseInt(dept);
            double salary = Double.parseDouble(sly);
            double comm = Double.parseDouble(com);

            /*System.out.println(ename);
            System.out.println(mgr);
            System.out.println(hiredate);
            System.out.println(deptno);
            System.out.println(salary);
            System.out.println(comm);*/

            //将数据传入emp对象
            Emp emp = new Emp(empno, ename, mgr, hiredate, deptno, salary, comm);
            //将emp传入dao中执行更新操作
            int i = dao.updateEmp(emp);
            //根据结果做出判断
            if (i == 1) {
                String path = request.getContextPath() + "/controller/list.do";
                return "redirect:" + path;
            } else {
                request.setAttribute("message", "修改失败");
                return "message";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
