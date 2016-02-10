<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>

<table role="grid" border="1px">
    <thead>
    <tr>
        <th>id</th>
        <th>siteId</th>
        <th>startPage</th>
        <th>pageType</th>
        <th>startAt</th>
        <th>cron</th>
        <th>isEnabled</th>
        <th>suspend</th>
        <th>delete</th>

    </tr>
    </thead>

    <%--<jsp:useBean id="item" scope="request" type="org.nazymko.th.parser.autodao.tables.records.TScheduleRecord"/>--%>
    <c:forEach items="${list}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.siteid}</td>
            <td>${item.startPage}</td>
            <td>${item.pageType}</td>
            <td>${item.startAt}</td>
            <td>${item.cron}</td>
            <td>${item.isEnabled}</td>
            <td><a href="/task/schedule/${item.id}/suspend">suspend</a></td>
            <td><a href="/task/schedule/${item.id}/delete">delete</a></td>
        </tr>
    </c:forEach>

</table>