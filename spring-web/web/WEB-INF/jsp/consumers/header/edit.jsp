<pre>
    ${headers}
</pre>

<form action="<c:url value="/connector/consumers/${headers.consumerId}/headers/${headers.id}/update"/>" method="post">
    <table>
        <tr>
            <td>header</td>
            <td><input name="header" size="40" type="text" value="${headers.header}"></td>

        </tr>

        <tr>
            <td>value</td>
            <td><input name="value" size="40" type="text" value="${headers.value}"/></td>
        </tr>
    </table>
    <button type="submit" class="btn bgm-blue">update</button>
</form>