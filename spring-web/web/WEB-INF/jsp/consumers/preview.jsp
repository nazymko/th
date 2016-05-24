<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../components/code-highlite-include.jsp"/>

<jsp:include page="../components/code-highlite-script.jsp">
    <jsp:param name="selector" value="pre code"/>
</jsp:include>

<%--<form action="/"></form>--%>

<div>
    <h3>Some info text</h3>
</div>
<pre>
    <code>${preview}</code>
</pre>