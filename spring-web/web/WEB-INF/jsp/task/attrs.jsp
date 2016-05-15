<%--@elvariable id="attrs" type="java.util.List<org.nazymko.th.parser.autodao.tables.records.ThAttributeDataRecord>"--%>
<%--@elvariable id="page" type="org.nazymko.th.parser.autodao.tables.records.ThPageRecord"--%>
<%--@elvariable id="gson" type="com.google.gson.Gson"--%>
<%@ page import="com.google.gson.Gson" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Gson gson = new Gson(); %>
<table border="1px">
    <thead>
    <tr>
        <th>id</th>
        <th>site</th>
        <th>page</th>
        <th>type</th>
        <th>source page</th>
        <th>visited</th>
    </tr>
    </thead>

    <tr>
        <td>${page.id}</td>
        <td>${page.siteId}</td>
        <td>${page.pageUrl}&nbsp;<a target="_blank" href="${page.pageUrl}">open</a></td>
        <td>${page.type}</td>
        <td>${page.sourcepage}</td>
        <td>${page.visitedAt}</td>
    </tr>
</table>
<div>
    <br>
    <pre>
        Total: ${attrs.size()} attributes
    </pre>
</div>
<table cellpadding="3px" border="1px">
    <thead>
    <th>id</th>
    <th>site Id</th>
    <th>page Id</th>
    <th>attribute name</th>
    <th>attribute value</th>
    <th>attribute index</th>
    <th>attribute type</th>
    <th>rule id</th>
    </thead>
    <c:forEach var="attr" items="${attrs}">
        <tr>
            <td>${attr.id}</td>
            <td>${attr.siteId}</td>
            <td>${attr.pageId}</td>
            <td>${attr.attributeName}</td>
            <td>${attr.attributeValue}</td>
            <td>${attr.attributeIndex}</td>
            <td>${attr.attributeType}</td>
            <td>${attr.ruleId}</td>
        </tr>
    </c:forEach>

</table>