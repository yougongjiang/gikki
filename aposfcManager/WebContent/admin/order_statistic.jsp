<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<base href="${pageContext.request.contextPath}/admin/"> 
<link href="images/skin.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
-->
</style>
</head>

<body>
	<table width="100%" height="1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="top" bgcolor="#F7F8F9">
				<div align="center">
					<table id="table2" class="line_table"
						style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
						cellPadding="0">
						<tbody style="margin: 0; padding: 0">
							<tr>
								<td class="line_table" align="center" colspan="8" height="20">
									<span class="left_bt2">本日销售额统计</span>
								</td>
							</tr>
							<tr>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">菜品名称</span></td>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">订购数量</span></td>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">单价</span></td>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">合计</span></td>
							</tr>

                            <c:forEach items="${list}" var="menu">
							<tr>
								<td class="line_table" align="center" width="25%"><span
									class="left_txt">${menu.menuname}	</span></td>
								<td class="line_table" align="center" width="25%"><span
									class="left_txt">
									<fmt:formatNumber type="number" value="${menu.sum }" maxFractionDigits="0"/></span></td>
								<td class="line_table" align="center" width="25%"><span
									class="left_txt">${menu. price}</span></td>
								<td class="line_table" align="center" width="25%"><span
									class="left_txt">${menu.total }元</span></td>
							</tr>
							</c:forEach>
						
							
							<tr>
								<td class="line_table" align="center" colspan="8"><span
									class="left_bt2">本日销售总额：<c:out value="${requestScope.salas }"></c:out>元
									
								</span></td>
							</tr>
						<tr>
								<td class="line_table" align="center" colspan="11" height="20">
								<span class="left_bt2">第 <c:out value="${page.curPage}"></c:out> 页
										&nbsp;&nbsp;共<c:out value="${page.totalPage}"></c:out>页
								</span>&nbsp;&nbsp; 
								<c:choose>
								<c:when test="${page.curPage eq 1}"><span style="color: gray; font-size: 12px;">[首页]</span></c:when>
								<c:otherwise>
								    <a href="${requestScope.pageUrl}&curpage=1">[首页]</a>
								    </c:otherwise>
								    </c:choose>
								    <c:choose>
								    <c:when test="${page.curPage eq page.totalPage}"><span style="color: gray;font-size: 12px;">[尾页]</span></c:when>
								    <c:otherwise>
								    <a href="${requestScope.pageUrl}&curpage=${page.totalPage}">[尾页]</a>&nbsp;&nbsp; 
								    </c:otherwise>
								    </c:choose>
								    <c:choose>
								    <c:when test="${page.curPage eq 1}"><span style="color: gray;font-size: 12px;">[上一页]</span></c:when>
								    <c:otherwise>
								    <a href="${requestScope.pageUrl}&curpage=${page.curPage-1}">[上一页]</a>
								    </c:otherwise>
								    </c:choose>
								    <c:choose>
								     <c:when test="${page.curPage eq page.totalPage}"><span style="color: gray;font-size: 12px;">[下一页]</span></c:when>
								     <c:otherwise>
									<a href="${requestScope.pageUrl}&curpage=${page.curPage+1}">[下一页]</a>
									</c:otherwise>
									</c:choose>
								</td>
							</tr>
					</table>
				</div>

			</td>

		</tr>
	</table>
</body>
</html>
