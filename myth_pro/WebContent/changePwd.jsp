<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    	function checkFrm()
    	{
    		if(checkIsNull('0','logPwd')==false)
    		{
    			alert('旧密码不能为空');
    			return false;
    		}
    		if(checkIsNull('0','newPwd')==false)
    		{
    			alert('新密码不能为空');
    			return false;
    		}
    		if(checkIsNull('0','confirmPwd')==false)
    		{
    			alert('确认密码不能为空');
    			return false;
    		}
    		if(checkpwd('0','newPwd','confirmPwd')==false)
    		{
    			return false;
    		}
    		document.forms[0].submit();
    	}
</SCRIPT>
<html:form action="/user">
<html:hidden property="method" value="changePw" />
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">您可能是第一次登陆本系统或者是密码已经过期，请修改密码</th>
          </tr>
        </thead>
        <tbody>
        <tr>
            <td class="t0">旧密码：</td>
            <td class="t1"><html:password property="logPwd"></html:password></td>
          </tr>
          <tr>
            <td class="t0">新密码：</td>
            <td class="t1"><input type="password" id="newPwd" name="newPwd"></td>
          </tr>
          <tr>
            <td class="t0">密码确认：</td>
            <td class="t1"><html:password property="confirmPwd"></html:password></td>
          </tr>
        </tbody>
      </table>
      <div class="operbtn">
        <span class="btnspan btnspanc"><input type="button" value="保存" class="btn" onclick="checkFrm()"></span>
      </div>
    </div>
    
  </div>
 </div>
 <div class="clear"></div>
</html:form>
<jsp:include page="/share/foot.jsp" />