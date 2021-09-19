document.addEventListener("DOMContentLoaded",function(){
	var page = new ReviewWritePageUpdateModule();

	page.updatePage();
});
function ReviewWritePageUpdateModule(){
	
}
ReviewWritePageUpdateModule.prototype = {
		updatePage : function(){
			var httpRequest = new XMLHttpRequest();
			var displayInfoId = getParameterByName("displayInfoId");
			var displayInfoData;
			
			httpRequest.onreadystatechange = function(){
				if(httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200){
					displayInfoData = JSON.parse(httpRequest.responseText);
					
					var rating = new RatingModule();
					var focus = new textAreaModule();
					var submit = new CommentRegisterModule();
					var image = new ImageModule();
					var titleBox = document.querySelector(".title");
					var title = displayInfoData.displayInfo.productDescription;
					
					titleBox.innerHTML = title;
					
					rating.updateRank();
					focus.clickTextArea();
					image.showImage();
					
					submit.clickSubmitBtn(displayInfoData.displayInfo.productId);
					
				}
			}
			httpRequest.open("GET", "/reservation/api/products/" + displayInfoId);
			httpRequest.send();
		}
}

function RatingModule(){}
RatingModule.prototype = {
		updateRank : function(){
			var ratingBox = document.querySelector(".rating");
			var rank = document.querySelector(".star_rank");
			var starList = document.querySelectorAll(".rating_rdo");
			
			ratingBox.addEventListener("click",function(){
				if(event.target.className === "rating_rdo"){
					var num = event.target.value;

					if(num <= rank.innerHTML){
						this.checkStar(num, starList, -1);
						rank.innerHTML = num-1;
					}
					else{
						this.checkStar(num, starList, 0);
						rank.innerHTML = num;
					}
					if(rank.innerHTML === "0"){
						addClass(rank, "gray_star");
					}
					else{
						removeClass(rank, "gray_star");
					}
					
				}
			}.bind(this));
		},		
		checkStar : function(num, starList, updown){			
			var max = parseInt(num)+parseInt(updown);

			for(var i = 0; i<5; i++){
				if(i < max){
					starList[i].checked = true;
				}
				else{
					starList[i].checked = false;
				}
			}
		},	
}

function textAreaModule(){}
textAreaModule.prototype = {
		clickTextArea : function(){
			var clickBox = document.querySelector(".review_write_info");
			
			clickBox.addEventListener("click",function(){
				clickBox.style.display = "none";
				$('.review_textarea').focus();

			});
			
			this.validateTextArea(clickBox);
		},
		
		validateTextArea : function(clickBox){
			var textArea = document.querySelector(".review_textarea");
			var curInputBox = document.querySelector(".guide_review");
			
			$('.review_textarea').focusout(function() {
				if(textArea.value.length === 0){
					clickBox.style.display = "";
				}
			});

			textArea.onkeyup = function(){
				var curCount = textArea.value.length;
				var maxCount = 400;
				
				if(curCount > maxCount){
					textArea.value = textArea.value.substring(0,400);
				}
				curInputBox.children[0].innerHTML = curCount;	
			}
		}
}
function ImageModule(){
	this.ellimage = document.querySelector("#reviewImageFileOpenInput");
	
}
ImageModule.prototype = {
		showImage : function(){			
			this.ellimage.addEventListener("change", (evt) => {
				var image = evt.target.files[0];
				if(!this.validateImage(image)){
					alert("잘못된 타입의 사진입니다!(jpg, png 파일만 가능)");
					return false;
				}
				var elImage = document.querySelector(".item_thumb");
				document.querySelector(".item").style.display = "inline-block";
	            elImage.src = window.URL.createObjectURL(image);				
			});
			this.deleteImage();
			
		},
		
		validateImage : function(image){
			const result = ([ 'image/png',  'image/jpg', 'image/jpeg'].indexOf(image.type) > -1);
			return result;
		},
		
		deleteImage : function(){
			var deleteBox = document.querySelector(".anchor");
			var elImage = document.querySelector(".item_thumb");
			
			deleteBox.addEventListener("click", function(evt){
				var className = evt.target.className;
				if(className === "anchor" || className === "spr_book ico_del"){
					document.querySelector(".item").style.display = "none";
					document.querySelector("#reviewImageFileOpenInput").value = "";
				}
			})
		}
}

function CommentRegisterModule(){}
CommentRegisterModule.prototype = {
		clickSubmitBtn : function(productId){
			var btnBox = document.querySelector(".box_bk_btn");
			var displayInfoId = getParameterByName("displayInfoId");
			btnBox.addEventListener("click", function(event){
				var className = event.target.className;
				
				if(className === "box_bk_btn" || className === "bk_btn" || className === "btn_txt"){			
					var textArea = document.querySelector(".review_textarea").value;
					
					if(textArea.length > 5){
						var httpRequest = new XMLHttpRequest();
						var oData = new FormData();
						
						var score = document.querySelector(".star_rank").innerHTML;
						var reservationInfoId = getParameterByName("reservationInfoId");
						var image = document.querySelector("#reviewImageFileOpenInput").files[0];
						

						if(image != undefined)
							oData.append("commentImage", image);
						oData.append("score", score);
						oData.append("reservationInfoId", reservationInfoId);
						oData.append("textArea", textArea);
						oData.append("productId", productId);
						
						httpRequest.onreadystatechange = function(){
							if(httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200){
								window.location.href="/reservation/detail?id=" + displayInfoId;
							}
						}
						
						httpRequest.open("POST", "/reservation/api/reservations/"+ reservationInfoId +"/comments");
						httpRequest.send(oData);
					}
					else{
						alert("리뷰의 글자수는 5자 이상이어야 합니다.");
					}
				}
			});
		}	
}
