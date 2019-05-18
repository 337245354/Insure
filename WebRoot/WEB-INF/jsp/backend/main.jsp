<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/header.jsp" %>
<div class="page-title">
	<div class="title_left">
		<h3>
			<h3>
			欢迎你：${userSession.userName }<strong> | 角色：${userSession.userTypeName }</strong>
			</h3>
		</h3>
	</div>
</div>

<%--&lt;%&ndash;display js&ndash;%&gt;--%>
<%--<script src="${pageContext.request.contextPath }/statics/locallib/3/amcharts.js?x"></script>--%>
<%--<script src="${pageContext.request.contextPath }/statics/locallib/3/serial.js?x"></script>--%>
<%--<script src="${pageContext.request.contextPath }/statics/locallib/3/themes/dark.js"></script>--%>
<%--<div id="chartdiv"></div>--%>

<%--<script src="${pageContext.request.contextPath }/statics/js/jquery.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath }/statics/localjs/index_BAK.js"></script>--%>

<%--&lt;%&ndash;display js&ndash;%&gt;--%>
<div class="clearfix"></div>
<%@include file="common/footer.jsp" %>  
     