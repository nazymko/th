<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tr>
    <td style="padding: 5px;">${rule.id}</td>
    <td><a href="/rule/view/${rule.id}">${rule.name}</a></td>
    <td>${rule.url}</td>
    <td>${rule.path_provider}</td>
</tr>
