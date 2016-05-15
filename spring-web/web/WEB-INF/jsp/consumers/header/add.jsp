<h4>${consumer.domain}</h4>
<br>
<form action="<c:url value="/connector/consumers/${consumer.id}/headers/add"/>" method="post">
    <label>header name
        <input type="text" name="header">
    </label>
    <br>
    <label>header value
        <input type="text" name="value">
    </label>
    <hr>
    <button type="submit"class="btn bgm-blue">add</button>
</form>