<%--@elvariable id="rule" type="org.nazymko.thehomeland.parser.rule.ParsingRule"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tr>
    <td>${rule.id}</td>
    <td>${rule.name}</td>
    <td>${rule.url}</td>
    <td>${rule.version}</td>
    <td><a href="<c:url value="/rule/source/${rule.id}"/>">open</a></td>

</tr>

