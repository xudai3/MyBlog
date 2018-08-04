<%--
  Created by IntelliJ IDEA.
  User: xudai
  Date: 2018/7/21
  Time: 下午11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- CDN方式引入Bootstrap JQuery popper.js -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!--script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script-->
    <!--link rel="stylesheet" href="/bootstrap-4.0.0-dist/css/bootstrap.css">
    <script src="/bootstrap-4.0.0-dist/js/jquery-3.3.1.min.js"></script>
    <script src="/bootstrap-4.0.0-dist/js/popper.min.js"></script>
    <link rel="stylesheet" href="/bootstrap-4.0.0-dist/css/bootstrap-markdown.min.css">
    <script src="/bootstrap-4.0.0-dist/js/bootstrap.min.js"></script-->

    <!--link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script-->

    <title>MyBlog</title>
</head>
<body>
${user.userName}, "Welcome to MyBlog, Your Credits:" ${sessionScope.user.credits};
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <nav class="navbar navbar-default" role="navigation">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">MyBlog</a>
                    </div>

                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li class="active">
                                <a href="#">Link</a>
                            </li>
                            <li>
                                <a href="#">Link</a>
                            </li>
                        </ul>
                        <form class="navbar-form navbar-left" role="search">
                            <div class="form-group">
                                <input type="text" class="form-control" />
                            </div> <button type="submit" class="btn btn-default">Search</button>
                        </form>
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a href="article_editor">Add Article</a>
                            </li>
                            <li class="dropdown">
                                <a href="#" data-toggle="dropdown" class="dropdown-toggle">下拉<strong class="caret"></strong></a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="#">操作</a>
                                    </li>
                                    <li>
                                        <a href="#">设置栏目</a>
                                    </li>
                                    <li>
                                        <a href="#">更多设置</a>
                                    </li>
                                    <li class="divider">
                                    </li>
                                    <li>
                                        <a href="#">分割线</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="/">Log out</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-md-10 column">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#">首页</a>
                    </li>
                    <li>
                        <a href="#">简介</a>
                    </li>
                    <li class="disabled">
                        <a href="#">信息</a>
                    </li>
                </ul>
                <div id="article_list_container">
                    <ul id="article_list">
                    </ul>
                </div>
            </div>
            <div class="col-md-2 column">
                <div class="catListTag">
                    <h3 id="catListTitle" class="catListTitle">Tags</h3>
                    <ul>
                        <li>
                            <a href="main">main</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <script>
        $.ajax({
            type: "POST",
            url: "/u/1",
            dataType: "html",
            success:function (data) {
                var arr = eval(data);
                for(var i = 0; i < arr.length; i++){
                    addLi(arr[i].articleTitle, arr[i].articleContent, arr[i].articleId);
                }
            },
            error:function (err) {
                alert(err);
            }
        });

        function addLi(articleTitle, articleContent, articleId) {
            var articleList = document.getElementById("article_list");
            var li = document.createElement("li");
            li.setAttribute("class", "ariticleLi");
            var div = document.createElement("div");
            div.setAttribute("class", "content")
            var href_a = document.createElement("a");
            href_a.setAttribute("class", "title");
            href_a.setAttribute("href", "/article/"+articleId);
            href_a.setAttribute("target", "_blank")
            href_a.innerHTML = articleTitle;
            var p = document.createElement("p");
            p.setAttribute("class", "abstract");
            p.innerHTML = articleContent;
            //document.getElementById("article_list");
            articleList.appendChild(li);
            li.appendChild(div);
            div.appendChild(href_a);
            div.appendChild(p);
        }
    </script>
</body>
</html>
