<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<form action="/rule/addnew" method="post">

    <textarea name="rule" id="rule-candidate" cols="60" rows="30" placeholder="insert rule there"></textarea>

    <p/>
    <input type="submit" name="submit"/>
</form>

