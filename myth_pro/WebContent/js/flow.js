var imageheight =Number( $("#listdetail").css("height").replace("px",''));
var imagewidth = 980;
$("#fullcontent").css("width", (document.body.clientWidth > document.documentElement.clientWidth ? document.body.clientWidth : document.documentElement.clientWidth)-1);
$("#fullcontent").css("height", document.body.clientHeight > document.documentElement.clientHeight ? document.body.clientHeight : document.documentElement.clientHeight);
document.write("<style>* html{background-image: url(image.jpg);}#listdetail,.flowcontent{position:fixed;_position:absolute;top:" + (document.documentElement.clientHeight - imageheight) / 2 + "px;_top:expression(" + (document.documentElement.clientHeight - imageheight) / 2 + "+((e=document.documentElement.scrollTop)?e:document.body.scrollTop)+'px');_top:expression(" + (document.documentElement.clientHeight - imageheight) / 2 + "+((e=document.documentElement.scrollTop)?e:document.body.scrollTop)+'px');left:" + (document.documentElement.clientWidth - imagewidth) / 2 + "px; _left:expression(" + (document.documentElement.clientWidth - imagewidth) / 2 + "+((e=document.documentElement.scrollLeft)?e:document.body.scrollLeft)+'px');_left:expression(" + (document.documentElement.clientWidth - imagewidth) / 2 + "+((e=document.documentElement.scrollLeft)?e:document.body.scrollLeft)+'px');}</style>");
function closeflow()
{
  $("#fullcontent").hide();
  $("#listdetail").hide();
}
function showflow()
{
  $("#fullcontent").show();
  $("#listdetail").show();
}