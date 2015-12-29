<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<jsp:directive.page import="com.viatt.zhjtpro.common.Page" />
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="content-type" />
    <title>物业信息管理系统</title>
    <link href="<%=path%>/css/global.css" rel="stylesheet" type="text/css" />
    <link href="<%=path%>/css/index.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=path%>/js/checkFrm.js"></script>
</head>
<body>
<div class="topmenu">
<div id="header">
  <h1 id="logo"><a href="#">这儿放标题</a></h1>
  <div id="menu">
    <ul>
	  <li><a href="<%=path%>/sys_mng/menu_mng/index.jsp"><span class="menu8"></span><strong>系统管理</strong></a></li>
      <li><a href="#"><span class="menu1"></span><strong>物业信息管理</strong></a></li>
      <li><a href="#"  class="current"><span class="menu2"></span><strong>业主信息管理</strong></a></li>
      <li><a href="#"><span class="menu3"></span><strong>门禁管理</strong></a></li>
      <li><a href="#"><span class="menu4"></span><strong>服务信息管理</strong></a></li>
      <li><a href="#"><span class="menu5"></span><strong>缴费管理</strong></a></li>
      <li><a href="#"><span class="menu6"></span><strong>设备信息管理</strong></a></li>
      <li><a href="#"><span class="menu7"></span><strong>公共信息管理</strong></a></li>
    </ul>
  </div>
 </div>
</div>
<div id="maincontent">
 <div id="leftcontent">
   <dl>
     <dt>菜单列表</dt>
     <dd>
       <ul>
       	<!-- 系统管理菜单 
       	<li><a  href="#">菜单信息管理</a></li>
       	<li><a  href="#">系统日志</a></li>
        -->
         <li><a class="current" href="<%=path%>/commDim.do?method=queryTblCommDim">小区信息管理</a></li>
         <li><a href="<%=path%>/commDim.do?method=forAddTblCommDim">新增小区</a></li>
         <li><a href="<%=path%>/areaDim.do?method=queryTblAreaDim">区域信息管理</a></li>
         <li><a href="<%=path%>/buildingDim.do?method=queryTblBuildingDim">楼宇信息管理</a></li>
         <li><a href="<%=path%>/unitDim.do?method=queryTblUnitDim">单元信息管理</a></li>
         <li><a href="<%=path%>/roomDim.do?method=queryTblRoomDim">房号信息管理</a></li>
         <li><a href="<%=path%>/housetypeDim.do?method=queryTblHousetypeDim">户型列表</a></li>
         <li><a href="<%=path%>/housetypeDim.do?method=forAddTblHousetypeDim">新增户型</a></li>
         <li><a href="<%=path%>/ownerDim.do?method=queryTblOwnerDim">业主列表</a></li>
         <li><a href="<%=path%>/ownerDim.do?method=forAddTblOwnerDim">新增业主</a></li>
         <li><a href="<%=path%>/cardDim.do?method=queryTblCardDim">门禁卡列表</a></li>
         <li><a href="<%=path%>/cardDim.do?method=forAddTblCardDim">新增门禁卡</a></li>
         <li><a href="<%=path%>/fingDim.do?method=queryTblFingDim">指纹门禁列表</a></li>
         <li><a href="<%=path%>/fingDim.do?method=forAddTblFingDim">新增指纹门禁</a></li>
         <li><a href="<%=path%>/contrDim.do?method=queryTblContrDim">门禁刷卡列表</a></li>
         <li><a href="<%=path%>/noticeDim.do?method=queryTblNoticeDim">公告信息列表</a></li>
         <li><a href="<%=path%>/noticeDim.do?method=forAddTblNoticeDim">新增公告信息</a></li>
         <li><a href="<%=path%>/adDim.do?method=queryTblAdDim">广告信息列表</a></li>
         <li><a href="<%=path%>/adDim.do?method=forAddTblAdDim">新增广告信息</a></li>
         <li><a href="<%=path%>/serviceDim.do?method=queryTblServiceDim">服务信息列表</a></li>
         <!-- <li><a href="/serviceDim.do?method=forAddTblServiceDim">新增服务信息</a></li> -->
         <li><a href="<%=path%>/equDim.do?method=queryTblEquDim">设备信息列表</a></li>
         <li><a href="<%=path%>/equDim.do?method=forAddTblEquDim">新增设备信息</a></li>
         <li><a href="<%=path%>/softDim.do?method=queryTblSoftDim">软件信息列表</a></li>
         <li><a href="<%=path%>/softDim.do?method=forAddTblSoftDim">新增软件信息</a></li>
         <li><a href="<%=path%>/orgDim.do?method=queryTblOrgDim">机构信息列表</a></li>
         <li><a href="<%=path%>/orgDim.do?method=forAddTblOrgDim">新增机构信息</a></li>
         <li><a href="<%=path%>/roleDim.do?method=queryTblRoleDim">角色信息列表</a></li>
         <li><a href="<%=path%>/roleDim.do?method=forAddTblRoleDim">新增角色信息</a></li>
         <li><a href="<%=path%>/menuDim.do?method=queryTblMenuDim">菜单信息列表</a></li>
         <li><a href="<%=path%>/menuDim.do?method=forAddTblMenuDim">新增菜单信息</a></li>
         <li><a href="<%=path%>/userDim.do?method=queryTblUserDim">用户信息列表</a></li>
         <li><a href="<%=path%>/userDim.do?method=forAddTblUserDim">新增用户信息</a></li>
         <li><a href="<%=path%>/changePwd.jsp">修改密码</a></li>
         <li><a href="<%=path%>/paramDim.do?method=queryTblParamDim">参数信息列表</a></li>
         <li><a href="<%=path%>/paramDim.do?method=forAddTblParamDim">新增参数信息</a></li>
         <li><a href="<%=path%>/calDim.do?method=queryTblCalDim">计算方式信息列表</a></li>
         <li><a href="<%=path%>/calDim.do?method=forAddTblCalDim">新增计算方式信息</a></li>
         <li><a href="<%=path%>/itemDim.do?method=queryTblItemDim">缴费项目信息列表</a></li>
         <li><a href="<%=path%>/itemDim.do?method=forAddTblItemDim">新增缴费项目信息</a></li>
         <li><a href="<%=path%>/billsDim.do?method=queryTblBillsDim">缴费单据信息列表</a></li>
         <li><a href="<%=path%>/billsDim.do?method=forAddTblBillsDim">新增缴费单据信息</a></li>
       </ul>
     </dd>
   </dl>
 </div>
