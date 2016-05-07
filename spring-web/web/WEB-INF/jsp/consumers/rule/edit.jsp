<form action="/connector/consumers/${rule.consumerId}/rule/${rule.id}/update" method="post">
    <table>
        <tr>
            <td>id</td>
            <td>${rule.id}</td>
        </tr>
        <tr>
            <td>rule</td>
            <td><textarea name="rule" cols="40" rows="10">${rule.rule}</textarea></td>
        </tr>
    </table>
    <button class="btn bgm-blue" type="submit">update</button>
</form>