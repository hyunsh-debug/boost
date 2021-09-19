document.addEventListener("DOMContentLoaded", function() {
	updatePromotionImage();
	startSlide();
	addProductList();
	clickCategory();
	clickMoreButton();
});

function updatePromotionImage() {
	var httpRequest = new XMLHttpRequest();
	var promotionItem = document.querySelector("#promotionItem").innerHTML;
	var slide = document.querySelector("#slide");
	var insertItem = "";

	httpRequest.onreadystatechange = function() {
		if (httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200) {
			var data = JSON.parse(httpRequest.responseText);

			for ( var i=0;i<data.length;i++) {
				insertItem = promotionItem.replace("{saveFileImage}",data[i].saveFileImage)
				.replace("{description}", data[i].description)
				.replace("{placeName}", data[i].placeName);
				slide.innerHTML += insertItem;
			}
			

		}
	};

	httpRequest.open("GET", "/reservation/api/promotions");
	httpRequest.send();
}

function startSlide(){

	var ul = document.querySelector("#slide");
	var curIndex = 0;
	var width = document.querySelector(".header_tit").clientWidth;
	var i = 1;
	var time = 0;
	function step() {
		time+=20;
		if(time >= i*1000){
			ul.style.transform = "translate3d(-"+ (width*curIndex)+"px, 0px, 0px)";
			curIndex++;
			i++;
			

			if(curIndex >= ul.childElementCount){
				curIndex = 0;
			}
		}
		window.requestAnimationFrame(step);
	}
	window.requestAnimationFrame(step);
}

function addProductList(){
	var httpRequest = new XMLHttpRequest();
	var categoryId = document.querySelector(".active").parentNode.getAttribute("data-category");
	var insertTag = document.querySelector(".pink");	
	var inputTag = document.querySelectorAll(".lst_event_box");
	var start = document.querySelectorAll(".lst_event_box > li").length;
	var itemList = document.querySelector("#itemList").innerHTML;
	var insertItem = "";

	httpRequest.onreadystatechange = function() {
		if (httpRequest.readyState == XMLHttpRequest.DONE && httpRequest.status == 200) {
			var data = JSON.parse(httpRequest.responseText);
			insertTag.innerHTML = data.totalCount + "개";	

			for ( var i =0;i<data.items.length;i++){
				insertItem = itemList.replace("{id}",data.items[i].displayInfoId)
				.replace("{description}",data.items[i].description)
				.replace("{saveFileName}", data.items[i].saveFileName)
				.replace("{placeName}", data.items[i].placeName)
				.replace("{content}", data.items[i].content)
				.replace("{description}",data.items[i].description);
				if(i%2 === 0){
					inputTag[0].innerHTML += insertItem;
				}
				else{
					inputTag[1].innerHTML += insertItem;
				}
			}
			var liCount = document.querySelectorAll(".lst_event_box > li").length;
			deleteMoreButton(liCount, data.totalCount);

		}
	}	
	if(categoryId === "0"){
		httpRequest.open("GET", "/reservation/api/products/productall?start=" + start);
	}
	else{
		httpRequest.open("GET", "/reservation/api/products?categoryId="+categoryId+"&start=" + start);
	}
	httpRequest.send();
}

function clickCategory(){
	var selectCategory = document.querySelector(".event_tab_lst");

	selectCategory.addEventListener("click", function(event){
		if(event.target.tagName === "SPAN" || event.target.tagName === "A"){
			moveCategory(event);
			deleteProductList();
			addProductList();
			checkMoreButton();
		}
	});
	function moveCategory(event){
		var preCategory = document.querySelector(".active");

		if(event.target.tagName === "SPAN"){
			preCategory.className = "anchor";
			event.target.parentNode.className += " active";
		}
		else if(event.target.tagName === "A"){
			preCategory.className = "anchor";
			event.target.className += " active";
		}
	}
	function deleteProductList(){
		var inputTag = document.querySelectorAll(".lst_event_box");

		while (inputTag[0].hasChildNodes()) {
			inputTag[0].removeChild(inputTag[0].firstChild);
		}
		while (inputTag[1].hasChildNodes()) {
			inputTag[1].removeChild(inputTag[1].firstChild);
		}
	}
	function checkMoreButton(){
		var moreButton = document.querySelector(".more .btn");

		if(moreButton.style.display ==="none"){
			moreButton.style.display = "block"
		}
	}
}

function clickMoreButton(){
	var moreButton = document.querySelector(".more .btn");

	moreButton.addEventListener("click", function(){
		addProductList();
	});
}

function deleteMoreButton(start, totalCount){
	var moreButton = document.querySelector(".more .btn");

	if(start>=totalCount){
		moreButton.style.display ="none";
	}
}

