<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>rule\parts\rule-manage-view.jsp</div>
<tr>
    <td style="padding: 5px;">${rule.id}</td>
    <td><a href="/rule/view/${rule.id}">${rule.site}</a></td>
    <spring:eval expression="rule.status == T(utils.support.rule.RuleStatus).ACTIVE" var="isValid"/>
    <c:choose>
        <c:when test="${isValid}">
            <td><a class="btn bgm-green waves-effect" href="/rule/${rule.id}/disable">disable</a></td>
        </c:when>
        <c:otherwise>
            <td><a class="btn bgm-red waves-effect" href="/rule/${rule.id}/activate">activate</a></td>
        </c:otherwise>
    </c:choose>
</tr>
<c:remove var="rule" scope="request"/>