
document.addEventListener("DOMContentLoaded", function() {
	var myreservationPageAjaxModule = new MyreservationPageAjaxModule();
	myreservationPageAjaxModule.sendAjax();
});

function MyreservationPageAjaxModule(){

}
MyreservationPageAjaxModule.prototype = {
		sendAjax : function(){
			var httpRequest = new XMLHttpRequest();
			var viewReservation = document.querySelector(".viewReservation");
			var reservationEmail = viewReservation.innerHTML;
			var reservationInfoData;
			

			httpRequest.onreadystatechange = function(){
				if(httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200){
					reservationInfoData = JSON.parse(httpRequest.responseText);
					var reservationInfoModule = new ReservationInfoModule();
					var cancelEventModule = new CancelEventModule();
					var commentModule = new CommentModule();

					reservationInfoModule.updateReservationInfo(reservationInfoData);
					cancelEventModule.clickCancelBtn();
					commentModule.clickCommentBtn();
				}
				
			}
			httpRequest.open("GET", "/reservation/api/reservations?reservationEmail=" + reservationEmail);
			httpRequest.send();	
		}
}
function ReservationInfoModule(){}

ReservationInfoModule.prototype = {
		updateReservationInfo : function(reservationInfoData){
			var summaryModule = new SummaryModule();
			var reservationTemplate = document.querySelector("#reservationInfoItem").innerText;
			var bindTemplate = Handlebars.compile(reservationTemplate);
			for(var reservationList of reservationInfoData.reservations){
				if(reservationList.cancelYn === false){
					var today = new Date();
					var resrvDate = new Date(reservationList.reservationDate);
					
					if(today <= resrvDate){
						//이용예정
						this.insertPreUseTemplate(reservationList, bindTemplate);
					}
					else{
						//이용완료
						this.insertComplietionTemplate(reservationList, bindTemplate);
					}					
				}
				else{
					//취소된 항목
					this.insertCancelTemplate(reservationList, bindTemplate);
				}
			}
			this.checkErrTemplate();
			summaryModule.updateSummarySection();
			summaryModule.clickSummaryBoard();
		},
		
		insertPreUseTemplate : function(reservationList, bindTemplate){
			var preUseInputBox = document.querySelector(".card.confirmed");
			this.changeBtnType("취소");
			this.setBtnClassName("cancelBtn");
			preUseInputBox.innerHTML += bindTemplate(reservationList);
		},

		insertComplietionTemplate : function(reservationList, bindTemplate){
			var completionInputBox = document.querySelector(".card.used");
			this.changeBtnType("예매자 리뷰 등록");
			this.setBtnClassName("reviewBtn");
			completionInputBox.innerHTML += bindTemplate(reservationList);
		},
		
		insertCancelTemplate : function(reservationList, bindTemplate){
			var cancelInputBox = document.querySelector(".card.cancel");
			cancelInputBox.innerHTML += bindTemplate(reservationList);
			cancelInputBox.querySelector(".booking_cancel").remove();
		},
		checkErrTemplate : function(){
			var errTemplate = document.querySelector("#errTemplate").innerText;
			var errBox = document.querySelectorAll(".link_booking_details");

			for(var list of errBox){
				if(list.nextElementSibling === null){
					list.parentNode.innerHTML += errTemplate;
				}
			}
		},
		removeErrTemplate : function(){
			var errTemplate = document.querySelectorAll(".err");
			if(errTemplate != null){
				for(var list of errTemplate){
					list.parentNode.removeChild(list);
				}
			}
		},
		
		changeBtnType : function(type){
			Handlebars.registerHelper("changeType", function() {			
				return type;
			});
		},
		
		setBtnClassName : function(type){
			Handlebars.registerHelper("btnType", function(){
				return type;
			})
		}
}

function SummaryModule(){}
SummaryModule.prototype = {
		updateSummarySection : function(){
			this.sumTotalCount();
			this.sumPreUseCount();
			this.sumCompletionCount();
			this.sumCancelCount();
		},
		
		sumTotalCount : function(){
			document.querySelector("#total").innerHTML = document.querySelectorAll(".card_item").length;
		},
		
		sumPreUseCount : function(){
			var preUseBox = document.querySelector(".card.confirmed");
			document.querySelector("#preuse").innerHTML = preUseBox.querySelectorAll(".card_item").length;
		},
		
		sumCompletionCount : function(){
			var preUseBox = document.querySelector(".card.used")
			document.querySelector("#completion").innerHTML = preUseBox.querySelectorAll(".card_item").length;
		},
		
		sumCancelCount : function(){
			var cancelBox = document.querySelector(".card.cancel");
			document.querySelector("#cancel").innerHTML = cancelBox.querySelectorAll(".card_item").length;
		},
		
		clickSummaryBoard : function(){
			var summaryBoard = document.querySelector(".summary_board");
			
			summaryBoard.addEventListener("click", function(event){
				var target;
				
				if(event.target.classList[0] === "link_summary_board"){
					target = event.target.lastChild.previousSibling.getAttribute("id");
				}
				else if(event.target.className === "figure"){
					target = event.target.getAttribute("id");
				}
				else if(event.target.className === "tit"){
					target = event.target.nextSibling.nextSibling.getAttribute("id");
				}
				else if(event.target.classList[0] === "spr_book2"){
					target = event.target.nextSibling.nextSibling.nextSibling.nextSibling.getAttribute("id");
				}
				
				_moveSummaryBoard(target);
				
			});
			function _moveSummaryBoard(targetName){
				var onClass = document.querySelector(".on");
				var height = document.querySelector(".middle").offsetHeight;
				removeClass(onClass, "on");
				
				if(targetName === "total"){
					var summaryTotalBox = document.querySelector("#total").parentNode;
					addClass(summaryTotalBox, "on");
				}
				
				else if(targetName === "completion"){
					var summaryCompletionBox = document.querySelector("#completion").parentNode;
					var resrvUsed = document.querySelector(".card.used").offsetTop;
					
					window.scrollTo({top:resrvUsed-height, behavior:'smooth'});
					addClass(summaryCompletionBox, "on");
				}
				
				else if(targetName === "preuse"){
					var summaryPreuseBox = document.querySelector("#preuse").parentNode;
					var resrvConfirmed = document.querySelector(".card.confirmed").offsetTop;
					
					window.scrollTo({top:resrvConfirmed-height, behavior:'smooth'});
					addClass(summaryPreuseBox, "on");
				}
				
				else if(targetName === "cancel"){
					var summaryCancelBox = document.querySelector("#cancel").parentNode;
					var resrvCancle = document.querySelector(".card.cancel").offsetTop;
					
					window.scrollTo({top:resrvCancle-height, behavior:'smooth'});
					addClass(summaryCancelBox, "on");
				}
			}
		}	
}

function CancelEventModule(){}
CancelEventModule.prototype = {
		clickCancelBtn : function(){
			var cardNode;
			
			var btn = document.querySelector(".card.confirmed");
			
			btn.addEventListener("click", function(event){
                if(event.target.classList[0] === "btn"){
                	cardNode = event.target.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement;
                	this.moveCardItem(cardNode);
                }
                else if(event.target.nodeName === "SPAN"){
                	cardNode = event.target.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement;
                	this.moveCardItem(cardNode);
                }
       
			}.bind(this));
		},
		
		moveCardItem : function(cardNode){
			var view = "block";
			
			var cancelPopUp = document.querySelector(".popup_booking_wrapper");
			document.querySelector(".popup_tit").innerHTML = cardNode.querySelector(".tit").innerHTML;
			document.querySelector(".sm").innerHTML = cardNode.querySelector(".resrv_date").innerHTML;
			cancelPopUp.style.display = view;

			
			cancelPopUp.addEventListener("click", function(evt){			
				
				if(evt.target.innerHTML === "예" || evt.target.parentElement.className === "btn_green"){
					view="none";
					var httpRequest = new XMLHttpRequest();
					var reservationInfoId = cardNode.querySelector(".booking_number").getAttribute("value");
					

					
					httpRequest.onreadystatechange = function() {
						if (httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200) {
							var cancelModule = new ReservationInfoModule();
							var summaryModule = new SummaryModule();
							var confirmedBox = document.querySelector(".card.confirmed");
							var cancelBox = document.querySelector(".card.cancel");
							
							cancelBox.appendChild(cardNode);
							cardNode.querySelector(".booking_cancel").remove();
							cancelModule.removeErrTemplate();
							cancelModule.checkErrTemplate();
							summaryModule.updateSummarySection();
							
							
							moveFocus(cardNode);
						}
					};
					
					httpRequest.open("PATCH", "/reservation/api/reservations/" + reservationInfoId);
					httpRequest.send();
					
			
				}
				else if(evt.target.innerHTML === "아니오" || evt.target.parentElement.className === "btn_gray" || evt.target.className === "spr_book2 ico_cls"){
					view="none";
					
				}
				cancelPopUp.style.display = view;
			});
		}
}

function CommentModule(){}
CommentModule.prototype = {
		clickCommentBtn : function(){
			var usedBox = document.querySelector(".card.used");
			var reservationInfoId;
			var displayInfoId;
			usedBox.addEventListener("click", function(event){
				if(event.target.classList[0] === "btn"){
					reservationInfoId = event.target.parentNode.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.getAttribute("value");
					displayInfoId = event.target.parentNode.previousElementSibling.previousElementSibling.previousElementSibling.getAttribute("value");
				}
				else if( event.target.nodeName === "SPAN"){
					reservationInfoId = event.target.parentNode.parentNode.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.getAttribute("value");
					displayInfoId = event.target.parentNode.parentNode.previousElementSibling.previousElementSibling.previousElementSibling.getAttribute("value");
				}
		
				window.location.href="./reviewWrite?reservationInfoId=" + reservationInfoId + "&displayInfoId=" + displayInfoId;
																		 
			});
		}
}

function moveFocus(cardNode){
	console.log(cardNode.offsetTop);
	window.scrollTo({top:cardNode.offsetTop + cardNode.clientHeight});
	
}