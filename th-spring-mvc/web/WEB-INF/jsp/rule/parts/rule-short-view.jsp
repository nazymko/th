<%--@elvariable id="rule" type="org.nazymko.thehomeland.parser.rule.ParsingRule"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tr>
    <td style="padding: 5px;">${rule.id}</td>
    <td><a href="/rule/view/${rule.id}">${rule.name}</a></td>
    <td>${rule.url}</td>
    <td>${rule.selector}</td>
    <td>${rule.version}</td>
    <td><a href="/rule/source/${rule.id}">open</a></td>
</tr>
