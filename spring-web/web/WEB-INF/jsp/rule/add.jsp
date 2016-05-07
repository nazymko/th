<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<form action="/rule/addnew" method="post" enctype="application/x-www-form-urlencoded">
    <textarea name="rule" id="rule-candidate" cols="60" rows="30" placeholder="insert rule there"></textarea>
    <p/>
    <br>
    <input class="btn bgm-bluegray waves-effect" type="submit" name="submit"/>
</form>

