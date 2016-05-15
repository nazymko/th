<%--@elvariable id="rule" type="org.nazymko.th.parser.autodao.tables.records.ThRuleRecord"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tr>
    <td style="padding: 5px;">${rule.id}</td>
    <td>${rule.authority}</td>
    <td><a href="<c:url value="/rule/view/${rule.id}"/>">view</a></td>
    <td>${rule.version}</td>
    <td><a href="<c:url value="/rule/source/${rule.id}"/>">open</a></td>
</tr>
