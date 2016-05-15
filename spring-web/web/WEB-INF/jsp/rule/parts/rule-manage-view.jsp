<%--@elvariable id="rule" type="org.nazymko.th.parser.autodao.tables.records.ThRuleRecord"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tr>
    <td style="padding: 5px;">${rule.id}</td>
    <td><a href="<c:url value="/rule/view/${rule.id}"/>">${rule.authority}</a></td>
    <td>${rule.version}</td>
    <spring:eval expression="rule.status == T(utils.support.rule.RuleStatus).ACTIVE" var="isValid"/>
    <c:choose>
        <c:when test="${isValid}">
            <td><a class="btn bgm-green waves-effect" href="<c:url value="/rule/${rule.id}/disable"/>">disable</a></td>
        </c:when>
        <c:otherwise>
            <td><a class="btn bgm-red waves-effect" href="<c:url value="/rule/${rule.id}/activate"/>">activate</a></td>
        </c:otherwise>
    </c:choose>
</tr>
<c:remove var="rule" scope="request"/>