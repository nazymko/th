<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Task manager</h3>
<h6> THP Status:${message}</h6>
<table border="1px" cellspacing="3px">
    <jsp:include page="history-head.jsp"/>
    <%--@elvariable id="started" type="java.util.List<org.nazymko.th.parser.autodao.tables.records.TTaskRecord>"--%>
    <c:forEach items="${started}" var="str">
        <tr>
            <td>${str.id}</td>
            <td>${str.status}</td>
            <td>${str.siteId}</td>
            <td>${str.runType}</td>
        </tr>
    </c:forEach>
</table>

<h5>History (last 10)</h5>

<table border="1px" cellspacing="3px">
    <jsp:include page="history-head.jsp"/>
    <%--@elvariable id="finished" type="java.util.List<org.nazymko.th.parser.autodao.tables.records.TTaskRecord>"--%>

    <c:forEach items="${finished}" var="str">
        <tr>
            <td>${str.id}</td>
            <td>${str.status}</td>
            <td><a href="<c:url value="/site/${str.siteId}/info"/>">${str.siteId}</a></td>
            <td>${str.runType}</td>
        </tr>
    </c:forEach>
</table>

<div>

    <br>

    <div> Can submit tasks: ${isActive?"yes":"no"}</div>
</div>