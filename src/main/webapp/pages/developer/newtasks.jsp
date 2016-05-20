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
        <a class="button" href="">New tasks</a>
        <a class="button" href="${pageContext.request.contextPath}/developer/tasksin">Tasks in</a>
        <a class="button" href="${pageContext.request.contextPath}/developer/singout">SingOut</a>
    </nav>
</header>

<section class="main_section" id="main_section">
    <article>
        <header id="section_header">
        </header>
        <section class="workspace">
            <h2 class="workspace-header">New tasks</h2>
            <c:forEach var="progect" items="${tasksIn}">
                <form class="entry-container" method="post"
                      action="${pageContext.request.contextPath}/developer/settime">
                    <p> Id :
                        <input name="id" type="number" min="1" value="1" value="<c:out value="${progect.id}"/>"
                               disabled>
                    </p>
                    <p>Name :
                        <input name="textfield" type="text" value="<c:out value="${progect.name}"/>" disabled></p>
                    <p>Description :
                        <input name="textfield" type="text" value="<c:out value="${progect.description}"/>" disabled>
                    </p>
                    <p> Time :
                        <input name="developerTime" type="text" value="<c:out value="${progect.developerTime}"/>"></p>
                    <p><input type="submit" value="Set"></p>
                </form>
            </c:forEach>
        </section>
    </article>
</section>

<footer id="page_footer">
    <p>2016 by D.Perestoronin</p>
</footer>
</body>
</html>
