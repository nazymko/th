<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${dirty == true}">
        <c:set var="btncolor" value="bgm-red"/>
    </c:when>
    <c:otherwise>
        <c:set var="btncolor" value="bgm-gray"/>
    </c:otherwise>
</c:choose>


<a class="${btncolor} bgm-blue btn" href="/connector/consumers/${current}/manual?dirty=${!dirty}">Dirty</a>
<hr>
<table border="1px">
    <thead>
    <tr>
        <th>id</th>
        <th>url</th>
        <th>type</th>
        <th>visited at></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${latest}" var="itemToSend">
        <tr>
            <td>
                <div>${itemToSend.id}</div>
            </td>
            <td>
                <div>${itemToSend.pageUrl}</div>
            </td>
            <td>
                <div>${itemToSend.type}</div>
            </td>
            <td>
                <div>${itemToSend.visitedAt}</div>
            </td>
            <td>
                <pre>TODO:start</pre>
            </td>
            <td>
                <a class="btn bgm-bluegray" href="/connector/consumers/manual/page/${itemToSend.id}/preview">preview</a>
            </td>
        </tr>
    </c:forEach>
</table>