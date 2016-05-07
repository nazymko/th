<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="/connector/consumers/${consumer.id}/mapping/add" method="post">
    <table>
        <tr>
            <td>target</td>
            <td>
                <select name="siteId">
                    <c:forEach items="${sites}" var="site">
                        <option value="${site.id}">${site.name}</option>
                    </c:forEach>
                </select>

            </td>
        </tr>
        <tr>
            <td>site</td>
            <td>
                <textarea name="rule" cols="30" rows="10"></textarea>
            </td>
        </tr>
    </table>
    <button type="submit" class="btn bgm-blue">add</button>
</form>