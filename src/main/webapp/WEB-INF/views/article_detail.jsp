<%--
  Created by IntelliJ IDEA.
  User: xudai
  Date: 2018/8/3
  Time: 下午8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/bootstrap-4.0.0-dist/js/markdown.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/simplemde/latest/simplemde.min.css">
    <script src="https://cdn.jsdelivr.net/simplemde/latest/simplemde.min.js"></script>

    <style>
        .container-fluid{
            height:100%;
            width: 100%;
            margin: 0;
            padding: 0;
            max-height: 100vh;
            max-width: 100vw;
        }
        .row{
            margin-left: 0;
            margin-right: 0;
        }
        .navbar_area{
        }
        .navbar{
        }
        .article_edit_area{
        }
        .article_input_area{
        }
        .column{
            padding-left:0;
            padding-right:0;
        }
        .article_title_area{
        }
        .article_title{
            width: 90%;
            margin-left: 5%;
            margin-right: 5%;
            margin-bottom: 20px;
        }
    </style>

    <title>Title</title>
</head>
<body>

    <div class="container-fluid">
        <div class="row clearfix navbar_area">
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
                                <a href="main" onclick="publish_article()">Publish</a>
                            </li>
                            <li>
                                <a href="main">Back</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <div class="row clearfix article_edit_area">
            <div class="col-md-12 column article_title_area">
                <input id="article_title" type="text" class="article_title" placeholder="Input Your Title">
            </div>
            <div class="col-md-12 column article_input_area">
                <textarea id="article_editor" class="article_editor" style="display: none"></textarea>
            </div>
        </div>
    </div>

    <script>
        var simplemde = new SimpleMDE({
            element: document.getElementById("article_editor"),
            spellChecker: false,
            toolbar: false,
        });

        $.ajax({
            type: "POST",
            url: "/article/1",
            dataType: "json",
            success:function (data) {
                var article = eval(data);
                simplemde.value(article.articleContent);
                var article_title = document.getElementById("article_title");
                article_title.value = article.articleTitle;
                article_title.disabled = true;
                simplemde.togglePreview();
            },
            error:function(err) {
                alert(err);
            }
        });

    </script>
    <script>
        function publish_article() {
            var article_title = document.getElementById("article_title");
            var data;
            if(!article_title.value || !simplemde.value()){
                alert("Title Or Content Cannot Be Null");
                return;
            }else{
                data = {"articleTitle" : article_title.value, "articleContent" : simplemde.value()}
            }
            $.ajax({
                data:{"data": JSON.stringify(data)},
                dataType:"html",
                type: "POST",
                url: "article",
                success:function(){
                    alert("Success!" + data);
                },
                error:function(){
                    alert("Failed!");
                }
            });
        }
    </script>

</body>
</html>
