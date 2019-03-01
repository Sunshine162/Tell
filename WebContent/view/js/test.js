  function loadXMLDoc()
  {
  var xmlhttp;
  if (window.XMLHttpRequest){
    // code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
  }
  else{
    // code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function(){
    if (xmlhttp.readyState==4 && xmlhttp.status==200){
      document.getElementById("error1").innerHTML=xmlhttp.responseText;
    }
  }
  var pa = "hello";
  url = "IsUserExist.do?param="+pa;
  xmlhttp.open("get",url,true);
  xmlhttp.send();
  }

  function test(){
	  loadXMLDoc();
  }