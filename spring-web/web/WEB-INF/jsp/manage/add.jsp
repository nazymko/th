<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<form method="post" action="<c:url value="/task/submit"/>">

    <p>
        <input name="site" width="500px">
    </p>

    <button type="submit" value="Submit"></button>
</form>
