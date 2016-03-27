<%@ page import="com.google.gson.Gson" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Gson gson = new Gson(); %>
<table border="1px">
    <thead>
    <tr>
        <th>id</th>
        <th>site</th>
        <th>page</th>
        <th>type</th>
        <th>source page</th>
        <th>visited</th>
    </tr>
    </thead>

    <tr>
        <td>${page.id}</td>
        <td>${page.site}</td>
        <td>${page.page}&nbsp;<a target="_blank" href="${page.page}">open</a></td>
        <td>${page.type}</td>
        <td>${page.sourcePage}</td>
        <td>${page.visited}</td>
    </tr>
</table>
<div>
    <br>
    <pre>
        Total: ${attrs.size()} attributes
    </pre>
</div>
<c:forEach var="attr" items="${attrs}">
    <pre>
            ${gson.toJson(attr)}
    </pre>
</c:forEach>

