$(document).ready(
function()
{
  $("#leftcontent dt").click(
  function()
  {
    $(this).parent().toggleClass("current");
  }
  );
}
);

// 时间格式化  add by lxj ++
function dateformatter(obj){
	var strDate = $(obj).html() ;
	strDate = strDate.substring(0,4) + "-" + strDate.substring(4, 6) + "-" + strDate.substring(6, 8) + " " + strDate.substring(8, 10) + ":" + strDate.substring(10, 12) ;
	$(obj).html(strDate)
}