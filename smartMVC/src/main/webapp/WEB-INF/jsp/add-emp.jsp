<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

  <!-- Main Header -->
 <jsp:include page="/WEB-INF/include/header.jsp"></jsp:include>
  <!-- Left side column. contains the logo and sidebar -->
  <jsp:include page="/WEB-INF/include/left-side.jsp"></jsp:include>

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
      <!-- Horizontal Form -->
      <div class="box box-info">
        <div class="box-header with-border">
          <h3 class="box-title">添加新员工信息</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <form method="post" action="${root}/emp/save.do" class="form-horizontal">
          <div class="box-body">
            <div class="form-group">
              <label for="ename" class="col-sm-2 control-label">姓名</label>

              <div class="col-sm-10">
                <input type="text" class="form-control" id="ename" name="ename" placeholder="姓名">
              </div>
            </div>
            <div class="form-group">
              <label for="mgr" class="col-sm-2 control-label">领导</label>

              <div class="col-sm-10">
                <select class="form-control" id="mgr" name="mgr">
                  <c:forEach items="${mgrs}" var="mgr">
                    <option value="${mgr.empno}">
                      ${mgr.ename}
                    </option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label for="hiredate" class="col-sm-2 control-label">入职日期</label>

              <div class="col-sm-10">
                <input type="date" class="form-control" id="hiredate" name="hiredate" placeholder="入职日期">
              </div>
            </div>
            <div class="form-group">
            <label for="deptno" class="col-sm-2 control-label">部门号</label>

            <div class="col-sm-10">
              <input type="text" class="form-control" id="deptno" name="deptno" placeholder="部门号">
            </div>
          </div>
            <div class="form-group">
              <label for="salary" class="col-sm-2 control-label">薪资</label>

              <div class="col-sm-10">
                <input type="number" class="form-control" id="salary" name="salary" placeholder="薪资">
              </div>
            </div>

            <div class="form-group">
              <label for="comm" class="col-sm-2 control-label">奖金</label>

              <div class="col-sm-10">
                <input type="number" class="form-control" id="comm" name="comm" placeholder="奖金">
              </div>
            </div>
          </div>
          <!-- /.box-body -->
          <div class="box-footer">
            <button type="submit" class="btn btn-info pull-right">保存</button>
          </div>
          <!-- /.box-footer -->
        </form>
      </div>
      <!-- /.box -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
 <jsp:include page="/WEB-INF/include/footer.jsp"></jsp:include>

  <!-- Control Sidebar -->
  <jsp:include page="/WEB-INF/include/sidebar.jsp"></jsp:include>
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