function displayContent( url, params, target){  
    var tempform = document.createElement("form");  
    tempform.action = url;  
    tempform.method = "post";  
    tempform.style.display="none"  
    if(target) {  
        tempform.target = target;  
    }  
  
    for (var x in params) {  
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

function readIt(i){
	var params = [];
	params[document.getElementById(i+"_in1").name] = document.getElementById(i+"_in1").value;
	params[document.getElementById(i+"_in2").name] = document.getElementById(i+"_in2").value;
	params[document.getElementById(i+"_in3").name] = document.getElementById(i+"_in3").value;
	params[document.getElementById(i+"_in4").name] = document.getElementById(i+"_in4").value;
	params[document.getElementById(i+"_in5").name] = document.getElementById(i+"_in5").value;
	params[document.getElementById(i+"_in6").name] = document.getElementById(i+"_in6").value;
	params[document.getElementById(i+"_in7").name] = document.getElementById(i+"_in7").value;
	params[document.getElementById(i+"_in8").name] = document.getElementById(i+"_in8").value;
	displayContent( "readArticle.jsp", params, '_blank');
}