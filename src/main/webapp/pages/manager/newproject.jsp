<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Jobs Plainer</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
<header class="page_header" id="page_header">
    <h1>Jobs Plainer</h1>
    <nav class="page_nav" id="page_nav">
        <a class="button" href="${pageContext.request.contextPath}/manager/team">My team</a>
        <a class="button" href="${pageContext.request.contextPath}/manager/technicaltasks">Technical tasks</a>
        <a class="button" href="${pageContext.request.contextPath}/manager/projects">Projects</a>
        <a class="button" href="${pageContext.request.contextPath}/manager/singout">SingOut</a>
    </nav>
</header>
<section class="main_section" id="main_section">
    <article>
        <header id="section_header">
        </header>
        <section class="workspace">
            <h2 class="workspace-header">New project</h2>

            <form method="post"
                  action="${pageContext.request.contextPath}/manager/createproject">
                <p>Technical task Id :
                    <input readonly name="taskId" type="text" value="<c:out value="${technicalId}"/>"></p>

                <p>Project name :
                    <input name="name" type="text" value="Project"></p>

                <p>Project description :
                    <input name="description" type="text" value="Project description"/>
                </p>

                <p>Project score :
                    <input name="score" type="number" min="0" value="0.01" value="Project description"/>
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
