<%@ page import="eu.lmc.experiment.FailingDeserialization2" %>
<html>
<head>
    <title>Unmarshalling error producer</title>
</head>
<body>
<h2>Hello, session <code><%=session.getId()%>
</code></h2>
</body>

<%
    session.setAttribute("good-field-2", "good");
    session.setAttribute("bad-field-2", new FailingDeserialization2());

%>

</html>
