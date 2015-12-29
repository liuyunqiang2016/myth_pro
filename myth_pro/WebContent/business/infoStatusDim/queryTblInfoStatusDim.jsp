<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/infoStatusDim.do">
<html:hidden property="method" value="queryTblInfoStatusDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">设备编号<input type="text" name="strEquCode" class="searchtxt" value="<c:out value='${strEquCode }'/>" /></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">设备编号</th>
            <th class="t2">信息编号</th>
            <th class="t3">信息类型</th>
            <th class="t3">标题</th>
            <th class="t4">操作类型</th>
            <th class="t5">发送状态</th>
            <th class="t6">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblInfoStatusDimBo" items="${tblInfoStatusDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t1"><c:out value="${TblInfoStatusDimBo.equCode}" /></td>
            <td class="t2"><c:out value="${TblInfoStatusDimBo.infoCode}" /></td>
            <td class="t3">
            	<c:choose>
            		<c:when test="${TblInfoStatusDimBo.infoType=='s'}">服务信息</c:when>
            		<c:when test="${TblInfoStatusDimBo.infoType=='n'}">公告信息</c:when>
            		<c:when test="${TblInfoStatusDimBo.infoType=='a'}">广告信息</c:when>
            		<c:when test="${TblInfoStatusDimBo.infoType=='f'}">缴费信息</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
            </td>
            <td class="t3"><c:out value="${TblInfoStatusDimBo.infoTitle}" /></td>
            <td class="t4">
            	<c:choose>
            		<c:when test="${TblInfoStatusDimBo.updType=='0'}">新增</c:when>
            		<c:when test="${TblInfoStatusDimBo.updType=='1'}">修改</c:when>
            		<c:when test="${TblInfoStatusDimBo.updType=='2'}">删除</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
            </td>
            <td class="t5">
            	<c:choose>
            		<c:when test="${TblInfoStatusDimBo.sendZt=='0'}">未发送</c:when>
            		<c:when test="${TblInfoStatusDimBo.sendZt=='1'}">已发送</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
            </td>
            <td class="t6">
            <c:choose>
            	<c:when test="${TblInfoStatusDimBo.infoType=='s'}">
					<span class="btnoperal"><input type="button" value="查看" class="btn"
            			onclick="document.location.href='<%=path%>/serviceDim.do?method=viewTblServiceDim&serCode=<c:out value="${TblInfoStatusDimBo.remark }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            		</span>
				</c:when>
            	<c:when test="${TblInfoStatusDimBo.infoType=='n'}">
					<span class="btnoperal"><input type="button" value="查看" class="btn"
            			onclick="document.location.href='<%=path%>/noticeDim.do?method=viewTblNoticeDim&noticeCode=<c:out value="${TblInfoStatusDimBo.infoCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            		</span>
				</c:when>
            	<c:when test="${TblInfoStatusDimBo.infoType=='a'}">
					<span class="btnoperal"><input type="button" value="查看" class="btn"
            			onclick="document.location.href='<%=path%>/adDim.do?method=viewTblAdDim&adId=<c:out value="${TblInfoStatusDimBo.infoCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            		</span>
				</c:when>
            	<c:otherwise></c:otherwise>
            </c:choose>
            </td>
          </tr>
        </c:forEach>
     </tbody>
    </table>
   </div>   
   <div class="page"><c:out value="${pr}" escapeXml="false" /></div>
  </div>
 </div>
 <div class="clear"></div>
</div>
</html:form>
<jsp:include page="/share/foot.jsp" />
