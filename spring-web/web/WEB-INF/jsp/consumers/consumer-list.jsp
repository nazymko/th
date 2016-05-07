<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt" %>
<%--@elvariable id="items" type="java.util.List<org.nazymko.th.parser.autodao.tables.pojos.ConnectorConsumer>"--%>

<h2>Consumers page</h2>
<table>

    <c_rt:forEach items="${items}" var="cons">
        <tr>
            <td>
                <li>${cons.domain}</li>
            </td>
            <td>
                <form action="/connector/consumers/start" method="post" enctype="application/x-www-form-urlencoded">
                    <input type="submit" title="send" class="btn bgm-bluegray waves-effect">
                    <input type="hidden" value="${cons.domain}" name="domain">
                </form>
            </td>
        </tr>
    </c_rt:forEach>
</table>