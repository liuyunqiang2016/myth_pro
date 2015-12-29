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
<html:form action="/visitorDim.do">
<html:hidden property="method" value="queryTblVisitorDim" />
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
    <span class="searchbox">刷卡时段<input type="text" name="strBegin" class="searchtxt" value="<c:out value='${strBegin }'/>" maxlength="8"/>
    -<input type="text" name="strEnd" class="searchtxt" value="<c:out value='${strEnd }'/>"  maxlength="8"/></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">门禁模块编号</th>
            <th class="t2">房号</th>
            <th class="t3">来访时间</th>
            <th class="t4">通话时长</th>
            <th class="t5">访客图片</th>
            <th class="t5">访客图片1</th>
            <th class="t5">访客图片2</th>
            <th class="t5">访客图片3</th>
            <th class="t5">访客图片4</th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblVisitorDimBo" items="${tblVisitorDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t1"><c:out value="${TblVisitorDimBo.equCode}" /></td>
            <td class="t2"><c:out value="${TblVisitorDimBo.roomCode}" /></td>
            <td class="t3"><c:out value="${TblVisitorDimBo.visiTime}" /></td>
            <td class="t4"><c:out value="${TblVisitorDimBo.comuTime}" /></td>
            <td class="t5">
            <a class="thickbox"  href="/upFile/visitorDim/<c:out value="${TblVisitorDimBo.visiImg}"/>"/>
            <c:out value="${TblVisitorDimBo.visiImg}" /></a></td>
            <td class="t5">
            <a class="thickbox" href="/upFile/visitorDim/<c:out value="${TblVisitorDimBo.visiImg1}"/>"/>
            <c:out value="${TblVisitorDimBo.visiImg1}" /></a></td>
            <td class="t5">
            <a class="thickbox" href="/upFile/visitorDim/<c:out value="${TblVisitorDimBo.visiImg2}"/>"/>
            <c:out value="${TblVisitorDimBo.visiImg2}" /></a></td>
            <td class="t5">
            <a class="thickbox" href="/upFile/visitorDim/<c:out value="${TblVisitorDimBo.visiImg3}"/>"/>
            <c:out value="${TblVisitorDimBo.visiImg3}" /></a></td>
            <td class="t5">
            <a class="thickbox" href="/upFile/visitorDim/<c:out value="${TblVisitorDimBo.visiImg4}"/>"/>
            <c:out value="${TblVisitorDimBo.visiImg4}" /></a></td>
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

<!-- tickbox  start (必须放这里，不然失效juery.js位置必须在 thickbox.js文件的上面)-->
<link rel="stylesheet" href="../../css/thickbox/thickbox.css" type="text/css"></link>
<link rel="alternate stylesheet" type="text/css" href="../../css/thickbox/1024.css" title="1024 x 768" />

<script src="../../js/thickbox/global.js" type="text/javascript"></script>
<script src="../../js/thickbox/thickbox.js" type="text/javascript"></script>
<!-- tickbox end -->