<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<table border="1px" cellspacing="3px">
    <jsp:include page="parts/rule-manage-header.jsp"/>

    <%--@elvariable id="rules" type="java.util.List<org.nazymko.th.parser.autodao.tables.records.RuleRecord>"--%>
    <c:forEach items="${rules}" var="mRule">
        <c:set var="rule" value="${mRule}" scope="request"/>
        <jsp:include page="parts/rule-manage-view.jsp"/>
    </c:forEach>


</table>

