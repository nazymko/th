<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../components/code-highlite-include.jsp"/>

<jsp:include page="../components/code-highlite-script.jsp">
    <jsp:param name="selector" value="pre code"/>
</jsp:include>
<%--@elvariable id="items" type="java.util.HashMap<java.lang.Integer,org.nazymko.th.parser.autodao.tables.records.ThSiteRecord>"--%>
<a href="<c:url value="/connector/consumers/${consumerId}/mapping/add"/>" class="btn bgm-blue">add</a>
<c:forEach items="${mapping}" varStatus="status" var="rule">
    <div>
        <div><h3>${sites[rule.siteId].authority}</h3></div>
        <pre><code>${rule.rule}</code></pre>

        <div style="display: inline">
            <div style="display: inline">
                <a style="display: inline" class="btn bgm-blue"
                   href="<c:url value="/connector/consumers/${rule.consumerId}/rule/${rule.id}/edit"/>">edit</a>
            </div>
            <div style="display: inline">
                <form style="display: inline" method="post"
                      action="<c:url value="/connector/consumers/${rule.consumerId}/rule/${rule.id}/delete"/>">
                    <button style="display: inline" type="submit" class="btn bgm-black">delete</button>
                </form>
            </div>
        </div>
    </div>
</c:forEach>
