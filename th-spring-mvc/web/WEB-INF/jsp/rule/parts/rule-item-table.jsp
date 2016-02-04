<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tr>
    <td>${rule.id}</td>
    <td>${rule.name}</td>
    <td>${rule.url}</td>
    <td><c:forEach items="${rule.page}" var="page">

        <li>${page.type}</li>
        <ul>
            <c:forEach items="${page.attrs}" var="attr">
                <li>${attr.type},${attr.attr} : ${attr.path}</li>
            </c:forEach>
        </ul>

    </c:forEach></td>

</tr>

