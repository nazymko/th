<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>
<h4>Parser activities</h4>
<ul>
    <c:forEach items="${tasks}" var="task">
        <li>${task}</li>
    </c:forEach>

    <hr>
    <div>
        <div>request:</div>
        <div>
            <p>Page size: ${size}</p>

            <p>Page: ${page}</p>
        </div>

    </div>
    <h3>Latest</h3>
    <table border="1px">

        <thead>
        <tr>
            <th>id</th>
            <th>visited at</th>
            <th>registered at</th>
            <th>page id</th>
            <th>site url</th>
        </tr>
        </thead>
        <%--@elvariable id="last" type="java.util.List<org.nazymko.th.parser.autodao.tables.records.PageRecord>"--%>
        <c:forEach items="${last}" var="row">
            <tr>
                <td>&nbsp;${row.id}</td>
                <td>&nbsp;<a href="/task/attrs/${row.id}">${row.visitedAt}</a></td>
                <td>&nbsp;${row.registeredAt}</td>
                <td>&nbsp;${row.sourcepage}</td>
                <td>&nbsp;<a href="/site/${row.siteId}/info">${row.siteUrl}</a></td>
            </tr>
        </c:forEach>
    </table>
</ul>

