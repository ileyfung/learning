package controller;

import dao.UserDao;
import entity.Emp;
import entity.User;
import mvc.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * @Describe 用户控制器
 * @Data 2020/7/23 0023
 */
public class UserController {

    UserDao userDao = new UserDao();

    @RequestMapping("/start.do")
    public String start(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            //遍历cookie 看有没有符合的cookie 有则使用request将name共享到jsp页面
            //从而实现记住用户
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("savename".equals(cookie.getName())) {
                        //获取对应的cookie值
                        String name = cookie.getValue();
                        //转字符集
                        name = URLDecoder.decode(name, "UTF-8");
                        request.setAttribute("name", name);
                    }
                }
            }
            //转发到登录页面
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //检验用户登录
    @RequestMapping("/login.do")
    public String login(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();
            String save = request.getParameter("save");

            System.out.println("用户名"+username);
            System.out.println("密码"+password);
            System.out.println(save);
            //把用户输入的用户名传入数据库中进行查询
            User user = userDao.findUserByName(username);

            //如果用户为空，则没有找到该用户，或者用户名为空，对用户做出提示
            if (user == null) {
                request.setAttribute("message", "用户名错误/用户名不能为空");
                return "login";
            }
            //核对用户名密码
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("loginuser", user);

                if ("true".equals(save)) {
                    Cookie cookie = new Cookie("savename", URLEncoder.encode(username, "UTF-8"));
                    cookie.setPath("/");
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                }
                //用户ID为1是管理员登陆成功，显示用户列表
                if (user.getId() == 1) {
                    request.setAttribute("admin", "admin");
                    //存入session，用于判断是否管理员
                    session.setAttribute("admin",user);
                }

                //转发到消息页面显示登录成功消息
                request.setAttribute("message", "登录成功");
                return "message";
            }
            //登录失败，密码错误或为空
            request.setAttribute("message", "密码错误/密码不能空");
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //注册
    @RequestMapping("/register.do")
    public String register(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            return "register";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //保存注册信息
    @RequestMapping("/user/save.do")
    public String save(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String password1 = request.getParameter("password1");


            List<User> userList = userDao.allUser();
            for (User user1 : userList) {
                if (user1.getName().equals(name)) {
                    request.setAttribute("message", "用户已存在");
                    return "register";
                }

            }
            if (password != null && password1 != null && password.equals(password1)) {
                User user = new User(0, name, password, email);
                int i = userDao.addUser(user);

                if (i == 1) {
                    String path = request.getContextPath() + "/user/start-login";
                    return "redirect:" + path;
                }
            }
            request.setAttribute("message", "密码不能为空，两次密码不一致");
            return "register";

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    @RequestMapping("/user/list.do")
    public String list(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            List<User> userList = userDao.allUser();
            request.setAttribute("userList",userList);
            return "UserList";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @RequestMapping("/user/delete.do")
    public String delete(HttpServletRequest request) {
        try{
            request.setCharacterEncoding("UTF-8");
            int id = Integer.parseInt(request.getParameter("id"));
            int i = userDao.delete(id);
            if (i == 1) {
                //删除成功返回列表
                String path = request.getContextPath() + "/user/list.do";
                return "redirect:" + path;
            }
            request.setAttribute("message","删除失败");
            return "message";
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @RequestMapping("/user/edit.do")
    public String edit(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userDao.findUser(id);
            request.setAttribute("user",user);
            return "edit-user";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @RequestMapping("/user/update.do")
    public String update(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String email = request.getParameter("email");

            //将数据传入user 对象
            User user = new User(Integer.parseInt(id),name,password,email);
            //将emp传入dao中执行更新操作
            int i = userDao.update(user);
            //根据结果做出判断
            if (i == 1) {
                String path = request.getContextPath() + "/user/list.do";
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
