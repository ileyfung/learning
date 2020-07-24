<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false"
%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Starter</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${root}/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${root}/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${root}/bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${root}/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect. -->
    <link rel="stylesheet" href="${root}/dist/css/skins/skin-blue.min.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!-- Main Header 包含子页面 -->
    <c:import url="/WEB-INF/include/header.jsp"/>

    <!-- Left side column. contains the logo and sidebar -->
    <c:import url="/WEB-INF/include/left-side.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Page Header
                <small>Optional description</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
                <li class="active">Here</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">

            <!--------------------------
              | Your Page Content Here |
              -------------------------->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">员工信息
                        <a href="${root}/emp/add.do" class="btn btn-success btn-xs">
                            <i class="fa fa-fw  fa-plus"></i>
                        </a>
                    </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table class="table table-bordered" style="text-align: center">
                        <tr>
                            <th style="width: 10px">工号</th>
                            <th>姓名</th>
                            <th>上级领导工号</th>
                            <th>入职日期</th>
                            <th>部门编号</th>
                            <th>薪资</th>
                            <th>提成</th>
                            <th>删除</th>
                            <th>修改</th>
                        </tr>
                        <c:forEach items="${list}" var="emp">
                            <tr>
                                <td>${emp.empno}</td>
                                <td><c:out value="${emp.ename}"/></td>
                                <td><c:out value="${names[emp.mgr]}"></c:out></td>
                                <%--<td>${controller.mgr}</td>--%>
                                <td>${emp.hiredate}</td>
                                <td>${emp.deptno}</td>
                                <td>${emp.salary}</td>
                                <td>${emp.comm}</td>
                                    <%--onclick中出现“return false" 则会阻止事件-点击事件的响应--%>
                                <td><a onclick="return confirm('真的删除${controller.ename}吗?')"
                                       href="${root}/controller/delete.do?empno=${emp.empno}"
                                       class="btn btn-danger btn-xs"
                                ><i class="fa fa-fw fa-remove"></i></a></td>
                                <td><a href="${root}/controller/edit.do?empno=${emp.empno}"
                                       class="btn btn-success btn-xs"><i
                                        class="fa fa-fw fa-edit"></i></a></td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
                <!-- /.box-body -->
                <div class="box-footer clearfix">
                    <ul class="pagination pagination-sm no-margin pull-right">
                        <li><a href="#">&laquo;</a></li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">&raquo;</a></li>
                    </ul>
                </div>
            </div>
            <!-- /.box -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <c:import url="/WEB-INF/include/footer.jsp"/>

    <!-- Control Sidebar -->
    <c:import url="/WEB-INF/include/sidebar.jsp"/>

</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="${root}/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${root}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${root}/dist/js/adminlte.min.js"></script>

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>