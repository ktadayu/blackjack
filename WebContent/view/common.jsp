<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.title}</title>

</head>
<body>
<jsp:include page="<%= request.getContextPath() %>/view/components/nav.jsp"/>
<jsp:include page="<%= request.getContextPath() %>/view/components/nav-top.jsp"/>
<div id="main">
${param.contant}
</div>
<jsp:include page="<%= request.getContextPath() %>/view/components/footer.jsp"/>

</body>
</html>
