<%--
  Created by IntelliJ IDEA.
  User: xudai
  Date: 2018/7/21
  Time: 下午11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <!-- CDN方式引入Bootstrap JQuery popper.js -->
    <!--link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script-- src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script-->
    <!--link rel="stylesheet" href="/bootstrap-4.0.0-dist/css/bootstrap.css">
    <script src="/bootstrap-4.0.0-dist/js/jquery-3.3.1.min.js"></script>
    <script src="/bootstrap-4.0.0-dist/js/popper.min.js"></script>
    <script src="/bootstrap-4.0.0-dist/js/bootstrap.min.js"></script-->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>Blog Login</title>
</head>
<body>
    <c:if test="${!empty error}">
        <c:out value="${error}" />
    </c:if>

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <form class="form-horizontal" role="form" action="<c:url value="/loginCheck"/>" method="post">
                    <div class="form-group">
                        <label for="userName" class="col-sm-2 control-label">UserName</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="userName" name="userName" placeholder="UserName" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Password" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label><input type="checkbox" />Remember me</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default" value="login">Sign in</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>