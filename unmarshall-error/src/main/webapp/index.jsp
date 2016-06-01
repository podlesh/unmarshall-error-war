<%@ page import="eu.lmc.experiment.FailingDeserializationClass" %>
<html>
<head>
    <title>Unmarshalling error producer</title>
</head>
<body>
<h2>Hello, session <code><%=session.getId()%></code></h2>
</body>

<%
    session.setAttribute("good-field-1", "good");
    session.setAttribute("bad-field-1", new FailingDeserializationClass());

%>

</html>
