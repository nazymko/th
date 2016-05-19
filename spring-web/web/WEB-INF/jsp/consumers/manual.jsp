<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1px">
    <thead>
    <tr>
        <th>url</th>
        <th>type</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${latest}" var="itemToSend">
        <tr>
            <td>
                <div>${itemToSend.pageUrl}</div>
            </td>
            <td>
                <div>${itemToSend.type}</div>
            </td>
            <td>
                <pre>TODO:start</pre>
            </td>
            <td>
                <pre>TODO:preview</pre>

            </td>
        </tr>
    </c:forEach>
</table>