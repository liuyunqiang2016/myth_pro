// JavaScript Document
(function($){
	$('#menu ul li a').click(function() {
        $(this).addClass('current').parents('li').siblings().find('a').removeClass('current');
    });
	
	$('#leftcontent ul li a').click(function() {
        $(this).addClass('current').parents('li').siblings().find('a').removeClass('current');
    });
	
	var index =4 ;
	$('#moresel').click(function() {
	   $('#rightcontent .title .border').find('.searchbox').eq(index).show();
	   index++;
    }); 
})(jQuery)