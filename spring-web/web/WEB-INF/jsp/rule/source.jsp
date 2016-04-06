<%--@elvariable id="gson" type="com.google.gson.Gson"--%>
<%--@elvariable id="rule" type="org.nazymko.th.parser.autodao.tables.records.ThRuleRecord"--%>

<link rel="stylesheet" href="/resources/js/hl_9_2/styles/zenburn.css">
<script src=/resources/js/hl_9_2/highlight.pack.js></script>
<script>
    $(document).ready(function () {
        $('pre code').each(function (i, block) {
            hljs.highlightBlock(block);
        });
    });
</script>
<div>
    <p>rule:${rule.authority}</p>

    <p>version:${rule.version}</p>
</div>
<pre><code>${rule.rule}</code></pre>














