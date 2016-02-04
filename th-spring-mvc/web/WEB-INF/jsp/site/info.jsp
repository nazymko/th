<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<pre>${site}</pre>
<c:if test="${!empty pages}">
    <table class="table-row-cell">
        <thead>
        <tr>
            <th>id</th>
            <th>page</th>
            <th>source page</th>
            <th>visited at</th>
        </tr>
        </thead>
        <c:forEach items="${pages}" var="page">
            <tr>
                <td style="padding-left:10px;padding-right: 10px">${page.id}</td>
                <td style="padding-left:10px;padding-right: 10px">${page.page}</td>
                <td style="padding-left:10px;padding-right: 10px">${page.sourcePage}</td>
                <td style="padding-left:10px;padding-right: 10px"><a href="/task/attrs/${page.id}">${page.visited}</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>