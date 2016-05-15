<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<form action="<c:url value="/rule/addnew"/>" method="post" enctype="application/x-www-form-urlencoded">
    <textarea name="rule" id="rule-candidate" cols="60" rows="30" placeholder="insert rule there"></textarea>
    <p/>
    <br>
    <button class="btn bgm-green waves-effect" type="submit">add</button>
</form>

