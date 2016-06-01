<%@ page import="eu.lmc.experiment.FailingDeserializationClass" %>
<html>
<head>
    <title>Unmarshalling error producer</title>
</head>
<body>
<h2>Hello World!</h2>
</body>

<%
    session.setAttribute("bad-field-1", new FailingDeserializationClass());

%>

</html>
