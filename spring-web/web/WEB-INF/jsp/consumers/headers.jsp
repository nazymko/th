<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>${consumer.domain}</h2>
<a href="<c:url value="/connector/consumers/${consumer.id}/headers/add"/>" class="btn bgm-blue">add</a>
<hr>

<table border="1px" cellpadding="3px">
    <thead>
    <tr>
        <th>#</th>
        <th>header</th>
        <th>value</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${headers}" var="_header" varStatus="status">
        <tr>
            <td>#${status.index}</td>
            <td>${_header.header}</td>
            <td>${_header.value}</td>
            <td><a class="btn bgm-blue" href="<c:url value="/connector/consumers/headers/${_header.id}/edit"/>">edit</a></td>
            <td><a class="btn bgm-black"
                   href="<c:url value="/connector/consumers/${consumer.id}/headers/${_header.id}/delete"/>">delete</a>
            </td>
        </tr>

    </c:forEach>
</table>


