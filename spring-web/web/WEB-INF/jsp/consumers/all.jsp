<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="items" type="java.util.List<org.nazymko.th.parser.autodao.tables.pojos.ConnectorConsumer>"--%>

<c:import url="../consumers/header/page-header.jsp"/>


<h2>Consumers page</h2>
<table border="1px" cellspacing="3px" role="grid">
    <thead>
    <tr>
        <th>#</th>
        <th>consumer</th>
        <th></th>
        <th>created at</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach varStatus="status" items="${items}" var="cons">
        <tr>
            <td>
                ${status.index}
            </td>
            <td>${cons.domain}</td>
            <td>
                <div style="padding-top: 12pt"><%--Magick!--%>
                    <form action="<c:url value="/connector/consumers/start"/>" method="post" enctype="application/x-www-form-urlencoded">
                        <button type="submit" class="btn bgm-green waves-effect">start</button>
                        <input type="hidden" value="${cons.domain}" name="domain">
                    </form>
                </div>
            </td>
            <td>
                    ${cons.time.toGMTString()}
            </td>
            <td><a href="<c:url value="/connector/consumers/${cons.id}/headers/view"/>" class="btn bgm-brown waves-effect">headers</a>
            </td>
            <td><a href="<c:url value="/connector/consumers/${cons.id}/mapping/view"/>" class="btn bgm-brown waves-effect">mapping
                rule</a></td>
            <td><a href="<c:url value="/connector/consumers/${cons.id}/disable"/>" class="btn bgm-black waves-effect">disable</a></td>
        </tr>
    </c:forEach>
</table>