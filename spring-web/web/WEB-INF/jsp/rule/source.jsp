<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="gson" type="com.google.gson.Gson"--%>
<%--@elvariable id="rule" type="org.nazymko.th.parser.autodao.tables.records.ThRuleRecord"--%>

<c:import url="../components/code-highlite-include.jsp"/>

<jsp:include page="../components/code-highlite-script.jsp">
    <jsp:param name="selector" value="pre code"/>
</jsp:include>

<div>
    <p>rule:${rule.authority}</p>

    <p>version:${rule.version}</p>
</div>
<pre><code>${rule.rule}</code></pre>














