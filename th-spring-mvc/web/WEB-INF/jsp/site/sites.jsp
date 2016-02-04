<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header.jsp"/>
<table role="grid" border="1px">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>history</th>
        <th>command</th>
    </tr>
    </thead>
    <c:forEach items="${sites}" var="site">

        <tr>
            <td>${site.id}</td>
            <td><a href="/site/${site.id}/supportedtypes">${site.name}</a></td>
            <td><a href="/site/${site.id}/info">history</a></td>
            <td><a href="/task/submit/${site.id}">start</a></td>
        </tr>

    </c:forEach>
</table>
