package mvc;
import javax.servlet.http.HttpServletRequest;

/**
 * 控制器类，用于封装业务功能
 * @Data 2020/7/16 0016
 */
public class BizController {

    /**
     * 第一个业务功能，hello world!
     * @return 目标页面名称
     */
    public String execute(HttpServletRequest request) {
        System.out.println("Hello world!");
        request.setAttribute("msg","hello");
        return "hello";
    }

    /**
     * 负责处理/emp/list.do 显示员工列表
     * 在web程序运行期间，将/emp/list.do请求映射到List()方法上，请求/emp/list.do时候，
     * 执行list()方法
     */
    @RequestMapping("/test/list.do")
    public String list(HttpServletRequest request) {
        return "emp2";
    }

    /**
     * 负责处理/emp/add.do 显示添加员工界面
     */
    @RequestMapping("/test/add.do")
    public String add(HttpServletRequest request) {
        return "add-emp";
    }

   @RequestMapping("/test/hello.do")
    public String hello(HttpServletRequest request) {
        request.setAttribute("msg","HI");
        return "hello";
   }
    //...
    @RequestMapping("/test/test.do")
    public String test(HttpServletRequest request) {
        //测试重定向功能
        String path = request.getContextPath() + "/controller/list.do";
        return "redirect:"+path;
    }
}
