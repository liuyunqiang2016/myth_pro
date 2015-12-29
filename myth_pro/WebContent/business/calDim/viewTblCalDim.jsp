<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<html:form action="/softDim.do" enctype="multipart/form-data">
<html:hidden property="method" value="addTblSoftDim" />
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">计算方式信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">计算方式编号：</td>
            <td class="t1"><c:out value="${TblCalDimForm.calCode}"/></td>
          </tr>
          <tr>
            <td class="t0">计算方式名称：</td>
            <td class="t1"><c:out value="${TblCalDimForm.calName}"/></td>
          </tr>
          <tr>
            <td class="t0">计算方式说明：</td>
            <td class="t1"><c:out value="${TblCalDimForm.calExp}"/></td>
          </tr>
        </tbody>
      </table>
      <div class="operbtn">
        <span class="btnspan btnspanc"><input type="button" value="返回" class="btn" onclick="history.back(-1);"></span>
      </div>
    </div>
    
  </div>
 </div>
 <div class="clear"></div>
</div>
</html:form>
<jsp:include page="/share/foot.jsp" />
