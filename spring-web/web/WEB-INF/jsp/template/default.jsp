<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="method" type="java.lang.String"--%>
<%--@elvariable id="TEMPLATE_BODY" type="com.sun.org.apache.xpath.internal.operations.String"--%>
<%--@elvariable id="title" type="com.sun.org.apache.xpath.internal.operations.String"--%>
<%--@elvariable id="css" type="com.sun.org.apache.xpath.internal.operations.String"--%>
<%--@elvariable id="js" type="com.sun.org.apache.xpath.internal.operations.String"--%>
<c:if test="${not empty TEMPLATE_BODY}">
    <!-- ${TEMPLATE_BODY} -->
</c:if>
<c:if test="${not empty method}">
    <!-- ${method} -->
</c:if>
<html>
<head>
    <title>${title}</title>
    <jsp:include page="${css}"/>
    <jsp:include page="${js}"/>
    <link rel="icon" sizes="32x32" href="<c:url value="/favicon.ico"/>">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <%--<link rel="stylesheet" href="//cdn.jsdelivr.net/highlight.js/9.2.0/styles/default.min.css">--%>
    <%--<script src="//cdn.jsdelivr.net/highlight.js/9.2.0/highlight.min.js"></script>--%>

</head>
<body>
<jsp:include page="../group-header.jsp"/>
<div style="margin: 5px">
    <c:import url="default-message-components.jsp"/>
    <jsp:include page="${TEMPLATE_BODY}"/>
</div>
</body>
</html>
