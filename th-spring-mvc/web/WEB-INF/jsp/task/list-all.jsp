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
        <th>status</th>
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
            <c:choose>
                <c:when test="${item.isEnabled}">
                    <td><div class="btn bgm-lightgreen">active</div></td>
                </c:when>
                <c:otherwise>
                    <td><div class="btn bgm-gray">inactive</div></td>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${item.isEnabled}">
                    <td><a class="btn bgm-lightblue" href="/task/schedule/${item.id}/suspend">suspend</a></td>
                </c:when>
                <c:when test="${!item.isEnabled}">
                    <td><a class="btn bgm-lightblue" href="/task/schedule/${item.id}/activate">activate</a></td>
                </c:when>
            </c:choose>
            <td><a class="btn bgm-red" href="/task/schedule/${item.id}/delete">delete</a></td>
        </tr>
    </c:forEach>

</table>