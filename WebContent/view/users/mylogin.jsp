<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../common.jsp" >
 <jsp:param name="title" value="value"/>
 <jsp:param name="content">
 <jsp:attribute name="value">
 Hello JSP ActionTags!
 <h3>日本語は使えない⇒テンプレート側の文字コード設定で使えるようになる</h3>
 </jsp:attribute>
 </jsp:param>
</jsp:include>


