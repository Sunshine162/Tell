function isUsernameOk() {
	var username = document.getElementById("in1").value;

	if (username.length == 0) {
		document.getElementById("in1").focus;
		document.getElementById("error1").innerHTML = "请输入用户名!";
		return false;
	}

	if (username.length > 0 && username.length < 4) {
		document.getElementById("in1").focus;
		document.getElementById("error1").innerHTML = "用户名不能小于 4 位!";
		return false;
	}
	
	if (username.length > 20) {
		document.getElementById("in1").focus;
		document.getElementById("error1").innerHTML = "用户名不能大于 20 位!";
		return false;
	}

	var zmnumReg = /^[0-9a-zA-Z_]*$/;
	if (username != "" && !zmnumReg.test(username)) {
		document.getElementById("in1").focus;
		document.getElementById("error1").innerHTML = "用户名只能由英文字母、数字、下划线组成!";
		return false;
	}

	// 判断数据库中是否已经有同名的用户
	if (userCheck(username) == "true") {
		document.getElementById("error1").innerHTML = "此用户名已注册！请重新填写";
		return false;
	}

	Tab(2);
}

function isPasswordOk() {
	var password = document.getElementById("in2").value;
	if (0 == password.length) {
		document.getElementById("error2").innerHTML = "请输入密码!";
		document.getElementById("in2").focus;
		return false;
	} else if (password.length < 6 || password.length > 20) {
		document.getElementById("in2").focus;
		document.getElementById("error2").innerHTML = "密码必须是6-20位";
		return false;
	} else {

		var firstChar = password.substr(0, 1).charCodeAt();
		if ((firstChar >= 65 && firstChar <= 90)
				|| (firstChar >= 97 && firstChar <= 122) == false) {
			document.getElementById("in2").focus;
			document.getElementById("error2").innerHTML = "首字母必须是英文字母！";
			return false;
		}
		var numasc = 0;
		var charasc = 0;
		var otherasc = 0;
		for (var i = 0; i < password.length; i++) {
			var asciiNumber = password.substr(i, 1).charCodeAt();
			if (asciiNumber >= 48 && asciiNumber <= 57) {
				numasc += 1;
			}
			if ((asciiNumber >= 65 && asciiNumber <= 90)
					|| (asciiNumber >= 97 && asciiNumber <= 122)) {
				charasc += 1;
			}
			if ((asciiNumber >= 33 && asciiNumber <= 47)
					|| (asciiNumber >= 58 && asciiNumber <= 64)
					|| (asciiNumber >= 91 && asciiNumber <= 96)
					|| (asciiNumber >= 123 && asciiNumber <= 126)) {
				otherasc += 1;
			}
		}
		if ((numasc + charasc + otherasc) != password.length) {
			document.getElementById("in2").focus;
			document.getElementById("error2").innerHTML = "密码只能由数字、字母、特殊字符组成";
			return false;
		} else if (0 == numasc) {
			document.getElementById("in2").focus;
			document.getElementById("error2").innerHTML = "密码必须包含有数字";
			return false;
		}
	}
	Tab(3);
}

function isConfirmPwOk() {
	var password = document.getElementById("in2").value;
	var confirm_pw = document.getElementById("in3").value;
	if (password != confirm_pw) {
		document.getElementById("in3").focus;
		document.getElementById("error3").innerHTML = "密码不一致，请重新填写！";
		return false;
	}
	Tab(4);
}
function isEamilOk() {
	var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); // 正则表达式
	var email = document.getElementById("in4").value; // 要验证的对象
	if (email === "") { // 输入不能为空
		document.getElementById("in4").focus;
		document.getElementById("error4").innerHTML = "请填写您的注册邮箱！";
		return false;
	} else if ((reg.test(email))==false) { // 正则验证不通过，格式不对
		document.getElementById("in4").focus;
		document.getElementById("error4").innerHTML = "邮箱格式错误！";
		return false;
	} 
	submitInfo("Register.do");
}

// 注册时翻到第 num 页
function Tab(num) {
	var i;
	for (i = 1; i <= 4; i++) {
		if (i == num)
			document.getElementById("d" + i).style.display = "block";
		else
			document.getElementById("d" + i).style.display = "none";
	}
}

// ajax判断是否存在同名用户
function userCheck(username) {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	// xmlhttp.onreadystatechange = function() {
	// if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	//	
	// }
	// }
	url = "IsUserExist.do?username=" + username;
	xmlhttp.open("get", url, false);
	xmlhttp.send();

	return xmlhttp.responseText;
}


// 收集注册好的信息
function submitInfo(url) {
	var params = [];
	params[document.getElementById("in1").name] = document.getElementById("in1").value;
	params[document.getElementById("in2").name] = document.getElementById("in2").value;
	params[document.getElementById("in3").name] = document.getElementById("in3").value;
	params[document.getElementById("in4").name] = document.getElementById("in4").value;
	// postcall( url, parms, '_blank');
	postcall(url, params, '_self');
}

// 建立虚拟表单并跳转目标servlet
function postcall(url, params, target) {
	var tempform = document.createElement("form");
	tempform.action = url;
	tempform.method = "post";
	tempform.style.display = "none"
	if (target) {
		tempform.target = target;
	}

	for ( var x in params) {
		var opt = document.createElement("input");
		opt.name = x;
		opt.value = params[x];
		tempform.appendChild(opt);
	}

	var opt = document.createElement("input");
	opt.type = "submit";
	tempform.appendChild(opt);
	document.body.appendChild(tempform);
	tempform.submit();
	document.body.removeChild(tempform);
}


function inputAgain(e,n)
{		
	if(n==1)
		document.getElementById("error1").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	else if(n==2)
		document.getElementById("error2").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	else if(n==3)
		document.getElementById("error3").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	else if(n==4)
		document.getElementById("error4").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    
}

