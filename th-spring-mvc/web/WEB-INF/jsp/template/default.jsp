<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <jsp:include page="${css}"/>
    <jsp:include page="${js}"/>
    <link rel="icon" sizes="32x32" href="/favicon.ico">

</head>
<body>
<jsp:include page="../group-header.jsp"/>
<div style="margin: 5px">
    <jsp:include page="${TEMPLATE_BODY}"/>
</div>
</body>
</html>
