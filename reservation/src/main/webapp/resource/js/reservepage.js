document.addEventListener("DOMContentLoaded", function() {
	let reservationPageAjaxModule = new ReservationPageAjaxModule();
});

//Ajax
function ReservationPageAjaxModule(){
	var httpRequest = new XMLHttpRequest();
	var displayInfoId =  getParameterByName("id");
	var productInfoData;

	httpRequest.onreadystatechange = function(){
		if(httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200){
			productInfoData = JSON.parse(httpRequest.responseText);
			
			let displayModule = new DisplayModule();
			let ticketModule = new TicketModule();
			let agreementModule = new AgreementModule();
			let requestModule = new RequestModule();
			
			displayModule.showTitle(productInfoData);
			displayModule.showImage(productInfoData);
			displayModule.showInfo(productInfoData);
			
			ticketModule.showTicketInfo(productInfoData);
			ticketModule.selectTicket();
			
			agreementModule.clickAgreementBtn();
			agreementModule.openAgreementBtn();
			
			requestModule.requestData(productInfoData);
		}
	}
	httpRequest.open("GET", "/reservation/api/products/" + displayInfoId);
	httpRequest.send();
}

//info
function DisplayModule(){}
DisplayModule.prototype.showTitle = function(productInfoData){
	var title = document.querySelector(".title");
	
	title.innerHTML = productInfoData.displayInfo.productDescription;
}

DisplayModule.prototype.showImage = function(productInfoData){
	var img = document.querySelector(".img_thumb");
	var briefInfoInputBox = document.querySelector(".preview_txt");
	var briefInfoTemplate = document.querySelector("#briefInfo").innerText;
	var bindTemplate = Handlebars.compile(briefInfoTemplate);

	for(var i=0; i<productInfoData.productImages.length; i++){
		if(productInfoData.productImages[i].type === "ma"){
			img.src = productInfoData.productImages[i].saveFileName;
			break;
		}
	}
	
	Handlebars.registerHelper("lowPrice", function(productPrices) {	
		return productPrices[0].price;
	});
	
	briefInfoInputBox.innerHTML = bindTemplate(productInfoData);
}

DisplayModule.prototype.showInfo = function(productInfoData){
	//price = 성인(A), 청소년(Y), 유아(B), 지역주민(C), 어얼리버드(E), VIP(V), R석(R),  S석(S), 평일(D)
	var detailInfoInputBox = document.querySelector(".section_store_details");
	var detailInfoTemplate = document.querySelector("#detailInfo").innerText;
	var bindTemplate = Handlebars.compile(detailInfoTemplate);

	Handlebars.registerHelper("getFee", function(index) {
		var typeName = productInfoData.productPrices[index].priceTypeName;
		return checkTypeName(typeName) + " : " + productInfoData.productPrices[index].price + "원<br>";
	});
	
	detailInfoInputBox.innerHTML = bindTemplate(productInfoData);
}

//ticket
function TicketModule(){}
TicketModule.prototype = {
		
		showTicketInfo : function(productInfoData){
			var tiketInputBox = document.querySelector(".ticket_body");
			var tiketTemplate = document.querySelector("#ticket").innerText;
			var bindTemplate = Handlebars.compile(tiketTemplate);
			Handlebars.registerHelper("getType", function(index) {
				var typeName = productInfoData.productPrices[index].priceTypeName;
				return checkTypeName(typeName);
			});
			
			Handlebars.registerHelper("dcPrice", function(index) {
				var productPrice = productInfoData.productPrices[index];
				return productPrice.price - (productPrice.price * productPrice.discountRate / 100);
			});
			
			tiketInputBox.innerHTML = bindTemplate(productInfoData);	
		},
		
		selectTicket : function(){
			var ticketBtn = document.querySelector(".ticket_body");
			var curNumOfTicket = 0;
			
			ticketBtn.addEventListener("click", function(event) {
				if(event.target.classList[2] === "ico_plus3"){
					console.log(event)
					_getNumOfTicketByName(event, 1);
					_getTotalPrice(event, curNumOfTicket);
					_getTotalNumOfTicket();
				}
				else if(event.target.classList[2] === "ico_minus3" && event.target.nextSibling.nextSibling.getAttribute("value") != 0){
					_getNumOfTicketByName(event, -1);
					_getTotalPrice(event, curNumOfTicket);
					_getTotalNumOfTicket();
				}
			});
			
			function _getNumOfTicketByName(event, index){
				var countInputBox = event.target.parentNode.querySelector(".count_control_input");
				curNumOfTicket = parseInt(countInputBox.getAttribute("value")) + (index);
				
				countInputBox.setAttribute("value", curNumOfTicket);
				
				_checkClearFix(event, curNumOfTicket);
				
				function _checkClearFix(event, curNumOfTicket){
					var minusBtn = event.target.parentNode.querySelector(".ico_minus3");
					
					if(curNumOfTicket == 0){
						addClass(countInputBox, "disabled");
						addClass(minusBtn, "disabled");
					}
					else if(curNumOfTicket > 0){
						removeClass(minusBtn, "disabled");
						removeClass(countInputBox, "disabled");
					}
				}
			}
			
			function _getTotalPrice(event, curNumOfTicket){
				var totalPriceInputBox = event.target.parentNode.parentNode.querySelector(".total_price");
				var discountPrice = parseInt(event.target.parentNode.parentNode.parentNode.querySelector(".product_dsc").getAttribute("value"));
				var totalPrice = discountPrice * curNumOfTicket;
				var priceColorCheck = totalPriceInputBox.parentNode;
				
				totalPriceInputBox.innerHTML = totalPrice;
				
				if(totalPrice > 0){
					addClass(priceColorCheck, "on_color");
				}
				else if(totalPrice == 0){
					removeClass(priceColorCheck, "on_color");
				}
			}
			
			function _getTotalNumOfTicket(){
				var totalTicketList = document.querySelectorAll(".count_control_input");
				var totalTicketInputBox = document.querySelector("#totalCount");
				var totalOfTicket = 0;
				
				for(var i = 0; i < totalTicketList.length; i++){
					totalOfTicket += parseInt(totalTicketList[i].getAttribute("value"));
				}
				
				totalTicketInputBox.innerHTML = totalOfTicket;
				
			}
		}	
}

//argreement
function AgreementModule(){}
AgreementModule.prototype = {
		clickAgreementBtn : function(){
			var agreementCheckBtn = document.querySelector("#chk3");
			var reserveBtn = document.querySelector(".bk_btn_wrap");
			
			agreementCheckBtn.addEventListener("click", function() {
				if (agreementCheckBtn.checked === true)
					removeClass(reserveBtn, "disable");
				else {
					addClass(reserveBtn, "disable");
				}
			});
		},
		
		openAgreementBtn : function(){
			var agreementBox = document.querySelector(".section_booking_agreement");
			
			agreementBox.addEventListener("click", function(event){
				if(event.target.className === "btn_text" || event.target.className === "fn fn-down2"){
					 if(event.target.parentNode.parentNode.className === "agreement" || event.target.parentNode.parentNode.className === "agreement open"){
						 var agreement = event.target.parentNode.parentNode;

						 if(agreement.className === "agreement open"){
							removeClass(agreement, "open");
							agreement.childNodes[3].childNodes[1].innerHTML = "보기";
						 }
						 else{
							 addClass(agreement, "open");
							 agreement.childNodes[3].childNodes[1].innerHTML = "닫기";
						 } 
					 }
				}
			});
		}
}


//valid
$(document).on("keyup", "#tel", function() { 
	$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/([0-9]{3})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") ); 
});

function ValidityModule(validErrorMessage){
	this.message = validErrorMessage;
}
ValidityModule.prototype = {	
		validateTicket : function(){
			var totalOfTicket = parseInt(document.querySelector("#totalCount").innerHTML);

			if(totalOfTicket <= 0){
				this.message += "선택된 티켓이 없습니다.\n";
				return false;
			}
			return true;
		},
		
		validateName : function(){
			var name = document.getElementById("name");
			var validName = /^[가-힣]{2,15}$/;
			
			if(name.value == ""){
				this.message += "예매자를 입력해주세요.\n";
				return false;
			}
			if(!validName.test(name.value)){
				this.message += "예매자 양식이 옳바르지 않습니다.\n";
				return false;
			}
			return true;
		},
		validateTel : function(){
			var tel = document.getElementById("tel");
			var validTel =  /^\d{3}-\d{3,4}-\d{4}$/; 

			if(tel.value === ""){
				this.message += "연락처를 입력해주세요.\n";
				return false;
			}
			if(!validTel.test(tel.value)){
				this.message += "연락처 양식이 옳바르지 않습니다.\n";
				return false;
			}
			return true;
		},
		validateEmail : function(){
			var email = document.getElementById("email");
			var validEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			
			if(email.value === ""){
				this.message += "이메일을 입력해주세요.\n";
				return false;
			}
			if(!validEmail.test(email.value)){
				this.message += "이메일 양식이 옳바르지 않습니다.\n";
				return false;
			}
			
			return true;	
		},
		validateAll : function(){
			var validAll = true;
			var chkTicket = this.validateTicket();
			var chkName = this.validateName();
			var chkTel = this.validateTel();
			var chkEmail = this.validateEmail();
			
			validAll = chkTicket && chkName && chkTel && chkEmail;
			if(!validAll){
				alert(this.message);
				return false;
			}
			else{
				return true;
			}
		}
}


//reservBtn
function RequestModule(){}
RequestModule.prototype = {
		requestData : function(productInfoData){
			var reserveBtn = document.querySelector(".bk_btn");
			reserveBtn.addEventListener("click", function(){
				var validErrorMessage="";
				var validModule = new ValidityModule(validErrorMessage);
				var valid = validModule.validateAll();

				//POST송신
				if(valid){
					var reserveParam = new PostDataModule(productInfoData); //전송데이트 생성
					var httpRequest = new XMLHttpRequest();
					httpRequest.onreadystatechange = function(){
						if(httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200){
							alert("예매성공.");
							window.location.href="/reservation";
						}
					}
					
					httpRequest.open("POST", "/reservation/api/reservations");
					httpRequest.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
					httpRequest.send(JSON.stringify(reserveParam));
				}
			});
		}
}
	
function PostDataModule(productInfoData){
	this.displayInfoId = productInfoData.displayInfo.displayInfoId;
	this.prices = this.pricesData(productInfoData);
	this.productId = productInfoData.displayInfo.productId;
	this.reservationEmail = document.querySelector("#email").value;
	this.reservationName = document.querySelector("#name").value;
	this.reservationTelephone = document.querySelector("#tel").value;
	this.reservationYearMonthDay = document.querySelector("#reservationDate").innerHTML;
}

PostDataModule.prototype = {
		pricesData : function(productInfoData){
			var pricesBox = document.querySelectorAll(".qty");
			var prices = [];

			pricesBox.forEach(function(item, index){
				var count = item.querySelector(".count_control_input").value;
				var productPriceId = productInfoData.productPrices[index].productPriceId;
				var price = new Prices(count, productPriceId);
				prices.push(price);
			});

			return prices;
		}
}

function Prices(count, productPriceId, reservationInfoId, reservationInfoPriceId){
	this.count = count;
	this.productPriceId = productPriceId;
	this.reservationInfoId = reservationInfoId;
	this.reservationInfoPriceId = reservationInfoPriceId;
}



function checkTypeName(typeName){
	switch (typeName) {
		case "A":
			return "성인";
		case "Y":
			return "청소년";
		case "B":
			return "유아";
		case "S":
			return "세트";
		case "C":
			return "지역주민";
		case "E":
			return "얼리버드";
		case "V":
			return "VIP";
		case "D":
			return "평일";
		default:
			return typeName + "석";
	}
}
