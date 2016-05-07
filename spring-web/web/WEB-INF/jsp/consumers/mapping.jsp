<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../components/code-highlite-include.jsp"/>

<jsp:include page="../components/code-highlite-script.jsp">
    <jsp:param name="selector" value="pre code"/>
</jsp:include>

<a href="/connector/consumers/${consumerId}/mapping/add" class="btn bgm-blue">add</a>
<c:forEach items="${mapping}" varStatus="status" var="rule">
    <div>
        <div><b>#${status.index}</b></div>
        <pre><code>${rule.rule}</code></pre>

        <div style="display: inline">
            <div style="display: inline">
                <a style="display: inline" class="btn bgm-blue"
                   href="/connector/consumers/${rule.consumerId}/rule/${rule.id}/edit">edit</a>
            </div>
            <div style="display: inline">
                <form style="display: inline" method="post"
                      action="/connector/consumers/${rule.consumerId}/rule/${rule.id}/delete">
                    <button style="display: inline" type="submit" class="btn bgm-black">delete</button>
                </form>
            </div>
        </div>
    </div>
</c:forEach>
