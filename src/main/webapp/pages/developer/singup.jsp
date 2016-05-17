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
        <a class="button" href="${pageContext.request.contextPath}/">About</a>
        <a class="button" href="${pageContext.request.contextPath}/customer/singin">Customers</a>
        <a class="button" href="${pageContext.request.contextPath}/manager/singin">Managers</a>
        <a class="button" href="${pageContext.request.contextPath}/developer/singin">Developers</a>
    </nav>
</header>

<section class="main_section" id="main_section">
    <article>
        <header id="section_header">
        </header>

        <section class="entry">
            <h2 class="entry-header">Sing up</h2>

            <form class="entry-container">
                <p><input type="text" placeholder="Email"></p>

                <p><input type="password" placeholder="Password"></p>

                <p><input type="password" placeholder="Repeat Password"></p>

                <p><input type="text" placeholder="Name"></p>

                <p><input type="text" placeholder="Team ID"></p>

                <p><select Level="Level">
                    <option disabled>choose your level</option>
                    <option value="Junior">Junior</option>
                    <option value="Middle">Middle</option>
                    <option value="Seignior">Seignior</option>
                    <option value="Lead">Lead</option>
                </select></p>
                <p><input type="submit" value="Sign up"></p>
                <a href="${pageContext.request.contextPath}/developer/singin">Sing in</a>
            </form>
        </section>

    </article>
</section>

<footer id="page_footer">
    <p>2016 by D.Perestoronin</p>
</footer>
</body>
</html>
