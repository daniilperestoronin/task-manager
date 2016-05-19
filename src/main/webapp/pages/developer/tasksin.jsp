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
        <a class="button" href="${pageContext.request.contextPath}/developer/newtasks">New tasks</a>
        <a class="button" href="">Tasks in</a>
        <a class="button" href="${pageContext.request.contextPath}/developer/singout">SingOut</a>
    </nav>
</header>

<section class="main_section" id="main_section">
    <article>
        <header id="section_header">
        </header>
        <section class="workspace">
            <h2 class="workspace-header">Tasks</h2>
            <c:forEach var="task" items="${tasksIn}">
                <tr>
                    <td>Id</td>
                    <td><input name="textfield" type="text" value="<c:out value="${task.id}"/>" disabled></td>
                    <td>Name</td>
                    <td><input name="textfield" type="text" value="<c:out value="${task.name}"/>" disabled></td>
                    <td>Description</td>
                    <td><input name="textfield" type="text" value="<c:out value="${task.description}"/>" disabled>
                    </td>
                    <td>Time</td>
                    <td><input name="textfield" type="text" value="<c:out value="${task.developerTime}"/>" disabled>
                    </td>
                </tr>
            </c:forEach>
        </section>
    </article>
</section>

<footer id="page_footer">
    <p>2016 by D.Perestoronin</p>
</footer>
</body>
</html>
