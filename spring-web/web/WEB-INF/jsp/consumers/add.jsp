<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Add new domain</h3>

<form action="<c:url value="/connector/consumers/add"/>" method="post">
    <label>domain
        <input type="text" name="domain">
    </label>
    <hr>
    <button type="submit" class="btn bgm-blue waves-effect">add</button>
</form>

