<%--@elvariable id="page" type="java.lang.Integer"--%>
<%--@elvariable id="size" type="java.lang.Integer"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>
<h4>Parser activities</h4>
<ul>
    <%--@elvariable id="tasks" type="java.util.List<java.lang.String>"--%>
    <c:forEach items="${tasks}" var="task">
        <li>${task}</li>
    </c:forEach>

    <hr>

    <c:choose>

        <c:when test="${page!= 0}">
            <a href="<c:url value="/task/activity?page=${page-1}&size=${size}"/>"
               class="btn btn-default btn-icon-text waves-effect"><i
                    class="zmdi zmdi-arrow-back"></i> back</a>
        </c:when>

        <c:otherwise>
            <button class="btn btn-default btn-icon-text waves-effect disabled"><i
                    class="zmdi zmdi-arrow-back"></i> back
            </button>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${page*size<count}">
            <a href="<c:url value="/task/activity?page=${page+1}&size=${size}"/>" class="btn btn-default btn-icon-text waves-effect "><i
                    class="zmdi zmdi-arrow-forward"></i>next</a>
        </c:when>
        <c:otherwise>
            <button class="btn btn-default btn-icon-text waves-effect disabled"><i
                    class="zmdi zmdi-arrow-forward"></i>next
            </button>
        </c:otherwise>
    </c:choose>


    <h3>Latest</h3>
    <table border="1px">

        <thead>
        <tr>
            <th>id</th>
            <th>page url</th>
            <th>visited at</th>
            <th>registered at</th>
            <th>page id</th>
            <th>domain</th>
        </tr>
        </thead>
        <%--@elvariable id="last" type="java.util.List<org.nazymko.th.parser.autodao.tables.records.ThPageRecord>"--%>
        <c:forEach items="${last}" var="row">
            <tr>
                <td>&nbsp;${row.id}</td>
                <td>&nbsp;${row.pageUrl}</td>
                <td>&nbsp;<a href="<c:url value="/task/attrs/${row.id}"/>">${row.visitedAt}</a></td>
                <td>&nbsp;${row.registeredAt.toGMTString()}</td>
                <td>&nbsp;${row.sourcepage}</td>
                <td>&nbsp;<a href="<c:url value="/site/${row.siteId}/info"/>">${row.authority}</a></td>
            </tr>
        </c:forEach>
    </table>
</ul>

