<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"/>
<table border="1px" cellspacing="3px" >
    <jsp:include page="parts/rule-header-table.jsp"/>

    <%--@elvariable id="rules" type="java.util.List<org.nazymko.thehomeland.parser.rule.ParsingRule>"--%>
    <c:forEach items="${rules}" var="mRule">
        <c:set var="rule" value="${mRule}" scope="request"/>
        <jsp:include page="parts/rule-short-view.jsp"/>
        <c:remove var="rule" scope="request"/>
    </c:forEach>


</table>

