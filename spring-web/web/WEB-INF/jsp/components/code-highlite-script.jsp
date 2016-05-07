<%--@elvariable id="selector" type="java.lang.String"--%>
<%--selector - css selector passed from parent--%>
<script>
    $(document).ready(function () {
        $('${param.selector}').each(function (i, block) {
            hljs.highlightBlock(block);
        });
    });
</script>