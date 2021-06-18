<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>
<script>
	$(function() {
		btn = $('#btn-checkemail');
		btn.click(function() {
			var id = $("#blog-id").val();
			if (id == "") {
				return;
			}
			$.ajax({
				url : "/jblog03/user/api/checkid?id=" + id,
				type : "get",
				dataType : "json",
				error : function(xhr, status, e) {
					console.error(status, e);
				},
				success : function(response) {
					console.log(response);

					if (response.result != "success") {
						console.error(response.message);
						return;
					}

					if (response.data) {
						alert("존재하는 이메일입니다. 다른 이메일을 사용하세요.");
						$("#blog-id").val("");
						$("#blog-id").focus();
						return;
					}

					$("#btn-checkemail").hide();
					$("#img-checkemail").show();
				}
			});
		});
	});
</script>

</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<form:form modelAttribute="userVo" class="join-form" id="join-form"
			method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
			<p style="color: #f00; text-align: left; padding-left: 0">
				<spring:hasBindErrors name="userVo">
					<c:if test="${errors.hasFieldErrors('name') }">
						<spring:message code="${errors.getFieldError('name').codes[0] }" />
					</c:if>
				</spring:hasBindErrors>
			</p>


			<label class="block-label" for="blog-id">아이디</label>
			<form:input id="blog-id" path="id" />
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail"
				src="${pageContext.request.contextPath}/assets/images/check.png"
				style="width: 18px; vertical-align: bottom; display: none" />
			<p style="color: #f00; text-align: left; padding-left: 0">
				<form:errors path="id" />
			</p>


			<label class="block-label"><spring:message
					code="user.join.label.password" /></label>
			<form:password path="password" />
			<p style="color: #f00; text-align: left; padding-left: 0">
				<form:errors path="password" />
			</p>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
