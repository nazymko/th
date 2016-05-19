<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>History</h3>


<table border="1px">
    <thead>
    <tr>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <%--<th></th>--%>
    </tr>
    </thead>
    <c:forEach items="${history}" var="item">
        <tr>
            <td>${item.pageId}</td>
            <td>${item.time}</td>
            <td>${item.consumerEndpoint}</td>
            <td>${item.responseCode}</td>
            <td>${item.consumer}</td>
            <td>${item.responseText}</td>
            <%--<td>${item.message}</td>--%>
        </tr>


    </c:forEach>

</table>