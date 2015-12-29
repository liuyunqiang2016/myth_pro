<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%String img = (String)request.getParameter("img"); %>
<jsp:include page="/share/top.jsp" />
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
    	 <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="1" class="th0" align="center">查看图片信息</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0"><img src="/upFile<%=img %>" width="200px" height="300px"/></td>
          </tr>
        </tbody>
      </table>
      <div class="operbtn">
        <span class="btnspan btnspanc"><input type="button" value="关闭" class="btn" onclick="window.close();"></span>
      </div>
    </div>
    
  </div>
 </div>
 <div class="clear"></div>
</div>
<jsp:include page="/share/foot.jsp" />
