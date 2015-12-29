var $=function(id){return document.getElementById(id);}
function SwitchNews(obj,num,sum,class1,class2)
{
 	ClearNews(obj,sum,class1,class2);
	document.getElementById("tag" + obj + num).className=class1;
	document.getElementById(obj+num).style.display = "";								
}

function ClearNews(name, num,class1,class2)
{					
	for(i=1;i<= num;i++)
	{										
		var tag=document.getElementById("tag"+ name + i).className;
		if(tag==class1){
			document.getElementById("tag"+ name + i).className=class2; 
			document.getElementById(name + i).style.display="none";
		}
	}
}