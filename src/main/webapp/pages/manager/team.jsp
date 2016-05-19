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
        <a class="button" href="">Projects</a>
        <a class="button" href="${pageContext.request.contextPath}/manager/singout">SingOut</a>
    </nav>
</header>
<section class="main_section" id="main_section">
    <article>
        <header id="section_header">
        </header>
        <section class="workspace">
            <h2 class="workspace-header">Tasks</h2>
            <form method="post" action="">
                <c:forEach var="team" items="${team}">
                    <p>Id :
                        <input name="textfield" type="text" value="<c:out value="${team.id}"/>" disabled></p>
                    <p>Name :
                        <input name="textfield" type="text" value="<c:out value="${team.name}"/>" disabled></p>
                    <c:forEach var="developer" items="${team.developerList}">
                        <p>Developers:</p>
                        <p>Name :
                            <input name="textfield" type="text" value="<c:out value="${developer.name}"/>" disabled></p>
                        <p>Email :
                            <input name="textfield" type="text" value="<c:out value="${developer.email}"/>"
                                   disabled></p>
                        <p>Developer level :
                            <input name="textfield" type="text" value="<c:out value="${developer.developerLevel}"/>"
                                   disabled></p>
                        <p>isBusy :
                            <input name="textfield" type="text" value="<c:out value="${developer.isBusy}"/>"
                                   disabled></p>
                    </c:forEach>
                </c:forEach>
            </form>
        </section>
    </article>
</section>

<footer id="page_footer">
    <p>2016 by D.Perestoronin</p>
</footer>
</body>
</html>
