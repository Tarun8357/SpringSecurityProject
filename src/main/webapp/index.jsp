<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Hello Security</title>
</head>
<body>
<div>
    <h1>Hello Security</h1>

  <security:authorize access="hasRole('ADMIN')">
  <p>Logged in as admin</p>
  </security:authorize>

  <security:authorize access="hasRole('USER')">
    <p>Logged in as user</p>
    </security:authorize>

    <form th:action="@{/logout}" method="post">

    <security:csrfInput />
        <input type="submit" value="Logout"/>
     <!--   <a href="http://localhost:8080/calculator">click</a> -->
    </form>
</div>
</body>
</html>