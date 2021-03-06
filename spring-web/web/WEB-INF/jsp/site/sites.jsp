<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>
<table role="grid" border="1px">
    <thead>
    <tr>
        <th>id</th>
        <th>domain</th>
        <th>name</th>
        <th>history</th>
        <th>command</th>
    </tr>
    </thead>
    <%--@elvariable id="sites" type="java.util.List<org.nazymko.th.parser.autodao.tables.records.ThSiteRecord>"--%>
    <c:forEach items="${sites}" var="site">

        <tr>
            <td>${site.id}</td>
            <td>${site.authority}</td>
            <td><a href="<c:url value="/site/${site.id}/supportedtypes"/>">${site.name}</a></td>
            <td><a href="<c:url value="/site/${site.id}/info"/>">history</a></td>
            <td><a class="btn bgm-teal waves-effect" href="<c:url value="/task/submit/${site.id}"/>">start</a></td>
        </tr>

    </c:forEach>
</table>
