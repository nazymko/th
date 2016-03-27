<%--@elvariable id="site" type="org.nazymko.th.parser.autodao.tables.records.SiteRecord"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<pre>${site}</pre>
<%--@elvariable id="pages" type="java.util.List<org.nazymko.thehomeland.parser.db.model.Page>"--%>
<c:if test="${!empty pages}">
    <h3>Latest</h3>
    <table border="1px" cellspacing="3px">
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