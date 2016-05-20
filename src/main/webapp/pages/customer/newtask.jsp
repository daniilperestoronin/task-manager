<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jobs Plainer</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
<header class="page_header" id="page_header">
    <h1>Jobs Plainer</h1>
    <nav class="page_nav" id="page_nav">
        <a class="button" href="">New technical task</a>
        <a class="button" href="${pageContext.request.contextPath}/customer/myscoring">My scoring</a>
        <a class="button" href="${pageContext.request.contextPath}/customer/mytask">My technical task</a>
        <a class="button" href="${pageContext.request.contextPath}/customer/singout">SingOut</a>
    </nav>
</header>

<section class="main_section" id="main_section">
    <article>
        <header id="section_header">
        </header>
        <section class="workspace">
            <h2 class="workspace-header">New technical task</h2>

            <form method="post"
                  action="${pageContext.request.contextPath}/customer/createtask">
                <p>Technical task name :
                    <input name="name" type="text" value="Task name"></p>

                <p>Technical task description :
                    <input name="description" type="text" value="Project description"/>
                </p>
                <p><input type="submit" value="Create"></p>
            </form>
        </section>
    </article>
</section>

<footer id="page_footer">
    <p>2016 by D.Perestoronin</p>
</footer>
</body>
</html>
