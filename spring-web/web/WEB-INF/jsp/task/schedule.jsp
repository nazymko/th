<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script type="text/javascript" src="<c:url value="/resources/styles/libs/jquery-cron/cron/jquery-cron.js"/>"></script>
<link type="text/css" href="<c:url value="/resources/styles/libs/jquery-cron/cron/jquery-cron.css"/>" rel="stylesheet"/>

<jsp:include page="header.jsp"/>

<table role="grid" border="1px">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>url</th>
        <th>schedule</th>
        <th>schedule</th>
        <th>submit</th>
    </tr>
    </thead>
    <%--@elvariable id="sites" type="java.util.List<org.nazymko.thehomeland.parser.db.model.Site>"--%>
    <c:forEach items="${sites}" var="site">
        <tr>
            <td>${site.id}</td>
            <td>${site.name}</td>
            <td>${site.url}</td>
            <td>
                <div class="cron-selector cron-sel-${site.id}"></div>
            </td>
            <td>
                <div class="cron-val-${site.id}"></div>
            </td>
            <td>
                <form id="form-${site.id}" action="/task/schedule" method="post">
                    <input type="hidden" name="site_id" value="${site.id}">
                    <input type="hidden" name="cron" class="cron-val-form-${site.id}">
                    <input class="btn bgm-blue waves-effect" value="submit" type="submit">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>




<script type="text/javascript">
    $(document).ready(function () {
        <c:forEach items="${sites}" var="site">
        $('.cron-sel-${site.id}').cron({
            initial: "59 23 31 12 *",
            onChange: function () {
                $('.cron-val-${site.id}').text("* " + $(this).cron("value"));
                $('.cron-val-form-${site.id}').attr('value', "* " + $(this).cron("value"));
            }
        });
        </c:forEach>
    });
</script>

