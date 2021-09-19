document.addEventListener("DOMContentLoaded", function() {
	PageUpdateModule.showProductDetailInfo();
});

const PageUpdateModule = {
		showProductDetailInfo :function(){
			var httpRequest = new XMLHttpRequest();
			var displayInfoId =  getParameterByName("id");
			var productInfoData;

			httpRequest.onreadystatechange = function(){
				if(httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200){
					productInfoData = JSON.parse(httpRequest.responseText);
					
					SliderModule.bindSlideImage(productInfoData);
					SliderModule.showPagination(productInfoData);
					BtnModule.reserveBtn();
					BtnModule.moreReviewBtn();
					BtnModule.moreContentBtn(productInfoData);
					CommentModule.gradeArea(productInfoData);
					CommentModule.shortReview(productInfoData);
					SectionInfoModule.detailInfoLDsc(productInfoData);
					SectionInfoModule.detailLocation(productInfoData);
					SectionInfoModule.tabCategory();
				}	
			};

			httpRequest.open("GET", "/reservation/api/products/" + displayInfoId);
			httpRequest.send();
		}
}

const SliderModule = {
		bindSlideImage: function(productInfoData){
			var slideBox = document.querySelector(".detail_swipe");
			var slideTemplate = document.querySelector("#slideItem").innerText;
			var bindTemplate = Handlebars.compile(slideTemplate);

			slideBox.innerHTML = bindTemplate(productInfoData);
		},

		showPagination : function(productInfoData){
			var total = productInfoData.productImages.length;
			var maxIndex = 1;
			var totalBox = document.querySelector("#total");
			var curIndexBox = document.querySelector("#curIndex");
			var curIndex = 0;
			var prevBtn = document.querySelector(".prev_inn");
			var nextBtn = document.querySelector(".nxt_inn");

			countTotal(total);		
			showCurIndex(curIndex);

			prevBtn.addEventListener("click", function() {
				countIndex(1);

			});
			nextBtn.addEventListener("click", function() {
				countIndex(1);

			});

			function countIndex(n){
				curIndex += n; 

				if(curIndex ===  maxIndex){
					curIndex = 0;
				}
				runSlide(curIndex);
			}

			function runSlide(curIndex){
				var slideBox = document.querySelector(".detail_swipe");
				var width = document.querySelector(".container_visual").clientWidth;

				slideBox.style.transform = "translate3d(-"+ width*(curIndex)+"px, 0px, 0px)";
				showCurIndex(curIndex);

			}		

			function showCurIndex(curIndex){
				curIndexBox.innerHTML = ++curIndex;
			}

			function countTotal(total){
				if(total >= 2){
					maxIndex = 2;
				}
				totalBox.innerHTML = maxIndex;
				removeBtn(maxIndex);

				function removeBtn(maxIndex){
					if(maxIndex === 1){
						prevBtn.remove();
						nextBtn.remove();
					}
				}
			}				
		}
}


const BtnModule = {
		reserveBtn : function(){
			var reserveBtn = document.querySelector(".bk_btn");
			reserveBtn.addEventListener('click',function(){
				window.location.href="reserve?id=" + getParameterByName("id");
			})
		},

		moreContentBtn : function(productInfoData){
			$(".store_details .dsc").html(productInfoData.displayInfo.productContent);

			$("a.bk_more._open").on("click", function(){
				$("a.bk_more._open").css("display","none");
				$("a.bk_more._close").css("display","block");
				$("div.store_details").removeClass("close3");
			});

			$("a.bk_more._close").on("click", function(){
				$("a.bk_more._close").css("display","none");
				$("a.bk_more._open").css("display","block");
				$("div.store_details").addClass("close3");
			});
		},
		
		moreReviewBtn : function(){
			var moreBtn = document.querySelector(".btn_review_more");
			moreBtn.setAttribute("href", "review?id=" + getParameterByName("id"));
		}
}

const CommentModule = {		
		gradeArea :function(productInfoData){
			var graphValue = document.querySelector(".graph_value");
			var textValue = document.querySelector(".text_value span");
			var joinCount = document.querySelector(".green");
			var avg = (productInfoData.averageScore).toFixed(1);
			var avgPercent = avg * 20;
			
			graphValue.style.width = avgPercent+"%";
			textValue.innerHTML = avg;
			joinCount.innerHTML = productInfoData.comments.length +"건";
		},
		
		shortReview: function(productInfoData){
			var shortReviewBox = document.querySelector(".list_short_review");
			var reviewItem = document.querySelector("#reviewItem").innerText;
			var bindTemplate = Handlebars.compile(reviewItem);
			
			var data = {
					displayInfo : productInfoData.displayInfo,
					comments : productInfoData.comments.slice(0,3)
			};
			
			Handlebars.registerHelper("saveFileName", function(item) {
				return item.commentImages.shift().saveFileName;
			});
			
			Handlebars.registerHelper("imgCount", function(item) {
				return 1;
			});
			
			Handlebars.registerHelper("score", function(score) {
				return score.toFixed(1);
			});
			
			Handlebars.registerHelper("email", function(reservationEmail) {
				return reservationEmail.substring(0,8).replaceAll(/(?<=.{4})./gi, "*");;
			});
			Handlebars.registerHelper("date", function(reservationDate) {
				var sub = reservationDate.year + "." + reservationDate.monthValue + "."+ reservationDate.dayOfMonth;
				return sub;
			});
			
			shortReviewBox.innerHTML = bindTemplate(data);
		}
}


const SectionInfoModule ={	
		 detailInfoLDsc : function(productInfoData){
			var inDsc = document.querySelector(".detail_info_group .in_dsc");
			
			inDsc.innerHTML = productInfoData.displayInfo.productContent;
		},

		detailLocation : function(productInfoData){
			var mapImage = document.querySelector(".store_location .img_thumb");
			var title = document.querySelector(".store_name");
			var placeStreet = document.querySelector(".store_addr_bold");
			var placeLot = document.querySelector(".addr_old_detail");
			var placeName = document.querySelector(".addr_detail");
			var tel = document.querySelector(".store_tel");
			
			mapImage.src = productInfoData.displayInfoImage.saveFileName;
			title.innerHTML = productInfoData.displayInfo.productDescription;
			placeStreet.innerHTML = productInfoData.displayInfo.placeStreet;
			placeLot.innerHTML = productInfoData.displayInfo.placeLot;
			placeName.innerHTML = productInfoData.displayInfo.placeName;
			tel.innherHTML = productInfoData.displayInfo.telephone;
			tel.setAttribute("href", productInfoData.displayInfo.telephone);
		},
		
		tabCategory:function(){
			var detailBtn = document.querySelector("._detail");
			var pathBtn = document.querySelector("._path");
			var detailAnchor = document.querySelector("._detail .anchor");
			var pathAnchor = document.querySelector("._path .anchor");
			var areaWrap = document.querySelector(".detail_area_wrap");
			var location = document.querySelector(".detail_location");
			
			detailBtn.addEventListener("click", function(){
				addClass(detailAnchor, "active");
				removeClass(pathAnchor, "active");
				addClass(location, "hide");
				removeClass(areaWrap, "hide");

			});
			
			pathBtn.addEventListener("click", function(){
				 addClass(pathAnchor, "active");
				 removeClass(detailAnchor, "active");
				 addClass(areaWrap, "hide");
				 removeClass(location, "hide");
			});
		}
}






