package mvc;

import entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserFilter", urlPatterns = "/user/*")
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //转换类型
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("admin");
        if (admin == null) {
            System.out.println("没有登录，拒绝访问");
            //没有登录，重定向到登录页面，不执行后续链节
            String login = request.getContextPath() +
                    "/start.do";
            response.sendRedirect(login);
            return; //不执行后续链节
        }
        //执行后续链节，就是执行后续的Servlet
        System.out.println("登录成功，继续执行");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
