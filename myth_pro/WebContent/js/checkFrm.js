//����������
function checkOrgCode(formname,element)
{
	var input = document.forms[formname].elements[element].value.replace(/(^\s*)|(\s*$)/g,"");
        var reg = /^[0-9]{4,9}$/;
        if(reg.test(input)==false)
	{
          window.document.forms[formname].elements[element].focus();
          document.forms[formname].elements[element].select();
          return false;
        }
        else
        {
          return true;
        }
}
//У����ͨ�绰��������룺���ԡ�+����ͷ���������⣬�ɺ��С�-�� 
function PhoneCheck(formname, element)   {       
	var input = document.forms[formname].elements[element].value;
	var reg=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
	if(reg.test(input)==false){
		window.document.forms[formname].elements[element].focus();
        document.forms[formname].elements[element].select();
        return false;
	} 
	return true;     
}  

function isPostalCode(formname, element)   {       
	var input = document.forms[formname].elements[element].value.replace(/(^\s*)|(\s*$)/g,"");
	var reg = /^[0-9]{6,6}$/;
	if(reg.test(input)==false){
		window.document.forms[formname].elements[element].focus();
        document.forms[formname].elements[element].select();
        return false;
	} 
	return true;     
}   
 
function checkUsername(formname,element)
{
	var input = document.forms[formname].elements[element].value.replace(/(^\s*)|(\s*$)/g,"");
        var reg = /^[0-9]{6,12}$/;
        if(reg.test(input)==false)
	{
          window.document.forms[formname].elements[element].focus();
          document.forms[formname].elements[element].select();
          return false;
        }
        else
        {
          return true;
        }
}
//����û������Ƿ����ֻ�����
function checkIsMoblie(formname,element)
{
	var input = document.forms[formname].elements[element].value.replace(/(^\s*)|(\s*$)/g,"");
        var reg = /^0?13\d{9}$/;
        if(reg.test(input)==false)
	{
          alert ("�ֻ������ʽ������13xxxxxxxxx����013xxxxxxxxx��") ;
          window.document.forms[formname].elements[element].focus();
          document.forms[formname].elements[element].select();
          return false;
        }
        else
        {
          return true;
        }
}

function checkMin(formname,element)
{
	var input = document.forms[formname].elements[element].value.replace(/(^\s*)|(\s*$)/g,"");
        if(input<0)
	{
          alert ("���������С��0") ;
          window.document.forms[formname].elements[element].focus();
          document.forms[formname].elements[element].select();
          return false;
        }
        else
        {
          return true;
        }
}

function checkMin2(formname,element)
{
	var input = document.forms[formname].elements[element].value.replace(/(^\s*)|(\s*$)/g,"");
        if(input<0||input>100)
	{
          alert ("���������С��0���ߴ���100") ;
          window.document.forms[formname].elements[element].focus();
          document.forms[formname].elements[element].select();
          return false;
        }
        else
        {
          return true;
        }
}

//����û������ʺ��Ƿ���Ϲ���
function checkIszhanghao(formname,element)
{
	var input = document.forms[formname].elements[element].value.replace(/(^\s*)|(\s*$)/g,"");
//        var reg = /^[a-zA-Z][0-9_]{6,12}$/;
        var reg = /^[a-zA-Z0-9]{6,12}$/;
        if(reg.test(input)==false)
	{
          window.document.forms[formname].elements[element].focus();
          document.forms[formname].elements[element].select();
          return false;
        }
        else
        {
          return true;
        }
}

function checkIsNull(formname,element)
{
	if ( window.document.forms[formname].elements[element].value.replace(/(^\s*)|(\s*$)/g,"") == "" || window.document.forms[formname].elements[element].value == null) {
		window.document.forms[formname].elements[element].focus();
		return false;
	}
    else
        {
        	return true;
        }
}

/**
 * ��֤�Ƿ�������,���ҵ�һ������Ϊ0
 * @param {Object} val
 * @return {TypeName} 
 */
function checkIsNumber(val){
	var patter = /^[1-9]\d*$/;
	return patter.test(val) ;
}

//����û������Ƿ�Ϊ��
function checkelement(formname,element,msg)
{
	if ( window.document.forms[formname].elements[element].value.replace(/(^\s*)|(\s*$)/g,"") == "" || window.document.forms[formname].elements[element].value == null) {
		alert (msg+"����Ϊ�գ�") ;
		window.document.forms[formname].elements[element].focus();
		return false;
	}
        else
        {
        	return true;
        }
}

//�����������һ����
function checkpwd(formname,element1,element2)
{
if(window.document.forms[formname].elements[element1].value!==window.document.forms[formname].elements[element2].value)
   {alert ("��������������벻һ�£�") ;
   	window.document.forms[formname].elements[element1].focus();
       window.document.forms[formname].elements[element1].select();
        return false ;
   }
   else
   return true;
}

function isNum(formname,element1)
{
    var input = document.forms[formname].elements[element1].value;
    var charset = "1234567890";
    if ((!checkChar(charset, input, true))) {
        alert ("���������֣�ֻ�ɰ������֣�");
        document.forms[formname].elements[element1].focus();
        document.forms[formname].elements[element1].select();
        return false;
    }
    return true;
}
function isFloat(formname,element1)
{
    var input = document.forms[formname].elements[element1].value;
    var charset = "1234567890.-";
    if ((!checkChar(charset, input, true))) {
        alert ("���������֣�ֻ�ɰ������֣�");
        document.forms[formname].elements[element1].focus();
        document.forms[formname].elements[element1].select();
        return false;
    }
    return true;
}
function isNum2(formname)
{
var s=formname;//window.document.forms[formname].elements[element].value.replace(/(^\s*)|(\s*$)/g,"");
    if(s.charAt(0)=='.')
    {
        	alert('С���㲻�����ڵ�һλ');
        	//document.forms[formname].elements[element].focus();
        	//document.forms[formname].elements[element].select();
                return false;
    }
    if(s.charAt(0)=='-'&&s.charAt(1)=='.')
    {
        	alert('��Ϊ�Ǹ���������С���㲻�����ڵڶ�λ');
        	//document.forms[formname].elements[element].focus();
        	//document.forms[formname].elements[element].select();
                return false;
    }
    if(s>100||s<0)
    {
    	 	alert('�Ż��ʲ����Դ���100����С��0');
        	//document.forms[formname].elements[element].focus();
        	//document.forms[formname].elements[element].select();
                return false;
    }
    else
    {
    	return true;
    }
}

function checkelementmore(formname,element1,msg,length1)
{
	if ( window.document.forms[formname].elements[element1].value == "" || window.document.forms[formname].elements[element1].value == null) {
		alert (msg+"����Ϊ�գ�") ;

		window.document.forms[formname].elements[element1].focus() ;

		return false ;
	}

	var str=window.document.forms[formname].elements[element1].value;
	if (str.length<length1)
	{
		alert (msg+"�ĳ�������Ϊ"+length1+"λ��") ;
		window.document.forms[formname].elements[element1].focus() ;
		return false ;
	}
	return true;
}


function checkelementmore2(formname,element1,msg,length1)
{
	if ( window.document.forms[formname].elements[element1].value == "" || window.document.forms[formname].elements[element1].value == null) {
		alert (msg+"����Ϊ�գ�") ;

		window.document.forms[formname].elements[element1].focus() ;

		return false ;
	}

	var str=window.document.forms[formname].elements[element1].value;
	if (str.length>length1)
	{
		alert (msg+"�ĳ��Ȳ����Դ���"+length1+"λ��") ;
		window.document.forms[formname].elements[element1].focus() ;
		return false ;
	}
	return true;
}

//����ַ������Ƿ��й涨�ַ�����/����ַ�
function checkChar(charset, val, should_in)
{
    var num = val.length;
    for (i=0; i < num; i++) {
       var char = val.charAt(i);
       char = char.toUpperCase();
       if ((charset.indexOf(char) > -1) && (!should_in))
          return false;
       else if ((charset.indexOf(char) == -1) && (should_in))
          return false;
    }
    return true;
}
//��������Ƿ�Ϊ�գ��Լ�����ĸ�ʽ�Ƿ�Ϸ�
function checkIsFloat(formname,element)
{
	var nc=event.keyCode;
        var s=window.document.forms[formname].elements[element].value.replace(/(^\s*)|(\s*$)/g,"");


        if(s.charAt(0)=='.')
        {
        	alert('С���㲻�����ڵ�һλ');
        	document.forms[formname].elements[element].focus();
        	document.forms[formname].elements[element].select();
        	event.keyCode=0;
                return;
        }

        if(s.charAt(0)=='-'&&s.charAt(1)=='.')
        {
        	alert('��Ϊ�Ǹ���������С���㲻�����ڵڶ�λ');
        	document.forms[formname].elements[element].focus();
        	document.forms[formname].elements[element].select();
        	event.keyCode=0;
                return;
        }

        if((nc>=48) && (nc<=57) )
        {}
        else
          if(nc==46)
          {
          	for(var i=0;i<s.length;i++)
                {
        		if(s.charAt(i)=='.')
                        {
              			event.keyCode=0; return;
        		}
                }
          }
          else
          if(nc==45)
          {
          	for(var i=0;i<s.length;i++)
                {
        		/*if(s.charAt(0)=='-')
                        {
              			event.keyCode=0; return;
        		}
                        else
                        {
                        	event.keyCode=0; return;
                        }*/event.keyCode=0; return;
                }
          }
          else
          {
          	event.keyCode=0;return;
          }
}

//��ʱ��ת��Ϊ����Ϊʮ�ĸ�ʽ
function converttime(formname,element)
{
	var str=window.document.forms[formname].elements[element].value;

	var index=str.indexOf("-");
	var last=str.lastIndexOf("-");
	var year=str.substring(0,4);
	var month=str.substring(index+1,last);
	if(month.length<2)
	{
	month="0"+month;
	}
	var day=str.substring(last+1,str.length);
	if(day.length<2)
	{
	day="0"+day;
	}
	str=year+"-"+month+"-"+day;

	return str;
}
//��ֵΪ��ѡ��ʱ�䣬���element1>element2,�򷵻�false
function checktwotime(formname,element1,element2)
{
	var time1=converttime(formname,element1);
	var time2=converttime(formname,element2);
	if (time1>time2)
	{
		alert ("����ʱ��С����ʼʱ�䣡") ;
		return false ;
	}
	return true;

}
function isIP(formname,element1) {
 	var strIp=window.document.forms[formname].elements[element1].value.replace(/(^\s*)|(\s*$)/g,"");
	var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g;
	if(re.test(strIp))
	{
		if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256) 
		return true;
	}
	return false;
}
function checkpro(formname,element1,element2)
{
if(window.document.forms[formname].elements[element1].value-window.document.forms[formname].elements[element2].value<0)
   {alert ("��߽��׶�С����ͽ��׶��") ;
   	window.document.forms[formname].elements[element1].focus();
    window.document.forms[formname].elements[element1].select();
    return false ;
   }
   else
   return true;
}
function checksb(formname,element1,element2)
{
if(window.document.forms[formname].elements[element1].value-window.document.forms[formname].elements[element2].value<0)
   {alert ("��߱��������ѽ��С����ͱ��������ѽ�") ;
   	window.document.forms[formname].elements[element1].focus();
       window.document.forms[formname].elements[element1].select();
        return false ;
   }
   else
   return true;
}
function checkzhanghao(formname,element)
{
	var input = document.forms[formname].elements[element].value.replace(/(^\s*)|(\s*$)/g,"");
        var reg = /^[0-9]{16,19}$/;
        if(reg.test(input)==false)
	{
          window.document.forms[formname].elements[element].focus();
          document.forms[formname].elements[element].select();
          return false;
        }else if(input.length!=16&&input.length!=19){
        	window.document.forms[formname].elements[element].focus();
          document.forms[formname].elements[element].select();
          return false;
        }else
        {
          return true;
        }
}