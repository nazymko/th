<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header.jsp"/>
<ul>
    <c:forEach items="${types}" var="type">

        <li>${type}</li>

    </c:forEach>
</ul>

