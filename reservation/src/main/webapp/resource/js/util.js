document.addEventListener("DOMContentLoaded",function(){
			goTop();
});

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function addClass(element, classString) { 
	element.className = element.className.split(' ').filter(function (name) { 
		return name !== classString; 
	}).concat(classString).join(' '); 
}

function removeClass(element, classString) { 
	element.className = element.className.split(' ').filter(function (name) { 
		return name !== classString; 
	}).join(' '); 
}

function goTop(){
	var goTopBtn = document.querySelector(".gototop");
	
	goTopBtn.addEventListener("click", function(){
		window.scrollTo({top:0, left:0, behavior:'smooth'});
	});
}

function goMain(){
	
}
