<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"/>
<table border="1px">
    <jsp:include page="parts/rule-header-table.jsp"/>

    <c:forEach items="${rules}" var="rule">
        <c:set var="rule" value="${rule}" scope="request"/>
        <jsp:include page="parts/rule-item-table.jsp"/>
    </c:forEach>

</table>

