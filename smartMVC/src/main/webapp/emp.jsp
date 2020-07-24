<%@ page import="java.util.List" %>
<%@ page import="entity.Emp" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/6/1 0001
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <h1>
            emp员工表
        </h1>
        <table border="1">
            <tr>
                <td>编号</td>
                <td>姓名</td>
                <td>领导</td>
                <td>入职日期</td>
                <td>部门号</td>
                <td>薪资</td>
                <td>提成</td>
            </tr>
            <%
                List<Emp> list = (List<Emp>)request.getAttribute("list");
            %>

            <%
                for (Emp controller:list){
            %>
            <tr>
                <td><%=emp.getEmpno()%></td>
                <td><%=emp.getEname()%></td>
                <td><%=emp.getMgr()%></td>
                <td><%=emp.getHiredate()%></td>
                <td><%=emp.getDeptno()%></td>
                <td><%=emp.getSalary()%></td>
                <td><%=emp.getComm()%></td>
            </tr>
            <%
                }
            %>

        </table>
</body>
</html>
