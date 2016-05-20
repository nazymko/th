<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>History</h3>


<table border="1px">
    <thead>
    <c:if test="${history.size()>0}">

    <tr>
            <%--<th>${history[0].field1()}</th>
        <th>${history[0].field2()}</th>
        <th>${history[0].field3()}</th>
        <th>${history[0].field4()}</th>
        <th>${history[0].field5()}</th>--%>

        <th>id</th>
        <th>page</th>
        <th>site id</th>
        <th>site</th>
        <th>consumer</th>
        <th>time</th>
        <th>response code</th>


    </tr>

    </c:if>
    </thead>
    <c:forEach items="${history}" var="item">
        <tr>
            <td>${item.value1()}</td>
            <td><a href="${item.value2()}">${item.value2()}</a></td>
            <td>${item.value3()}</td>
            <td>${item.value4()}</td>
            <td>${item.value5()}</td>
            <td>${item.value6()}</td>
            <td>${item.value7()}</td>
        </tr>


    </c:forEach>

</table>