function isLoginInfoOk(){
	var len_un = document.loginInfo.username.value.length;
	var len_pw = document.loginInfo.password.value.length;
	if(len_un==0 && len_pw==0) { 
		document.loginInfo.username.focus();
		document.getElementById("error").innerHTML="请输入用户名和密码!"; 
		return false;
	}
	else if(len_un==0 && len_pw>0) { 
		document.loginInfo.username.focus();
		document.getElementById("error").innerHTML="请输入用户名!"; 
		return false;
	}
	else if(len_un>0 && len_pw==0) { 
		document.loginInfo.password.focus();
		document.getElementById("error").innerHTML="请输入密码!";  
		return false;
	} 
	else{
		return true;
	}
}


function inputAgain(e)
{		
	document.getElementById("error").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
}









