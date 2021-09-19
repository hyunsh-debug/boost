document.addEventListener("DOMContentLoaded", function() {
	pageUpdate.showReviewPage();
});

const pageUpdate ={
		showReviewPage : function(){

			var httpRequest = new XMLHttpRequest();
			var displayInfoId =  getParameterByName("id");
			let productInfoData;

			httpRequest.onreadystatechange = function(){
				if(httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200){
					productInfoData = JSON.parse(httpRequest.responseText);
					
					comments.showComment(productInfoData);
					btn.prevBtn(displayInfoId);
				}	
			};

			httpRequest.open("GET", "/reservation/api/products/" + displayInfoId);
			httpRequest.send();

		}
}

const btn = {
		prevBtn : function(displayInfoId){
			var prevBtn = document.querySelector(".btn_back");
			prevBtn.setAttribute("href", "detail?id=" + displayInfoId);
		}
}

const comments = {
		showComment : function(productInfoData){
			this.gradeArea(productInfoData);
			this.shortReview(productInfoData);	
		},

		gradeArea : function(productInfoData){
			var graphValue = document.querySelector(".graph_value");
			var textValue = document.querySelector(".text_value span");
			var joinCount = document.querySelector(".green");
			var avg = (productInfoData.averageScore).toFixed(1);
			var avgPercent = avg * 20;

			graphValue.style.width = avgPercent+"%";
			textValue.innerHTML = avg;
			joinCount.innerHTML = productInfoData.comments.length + "건";
		},

		shortReview : function(productInfoData){
			var shortReviewBox = document.querySelector(".list_short_review");
			var reviewItem = document.querySelector("#reviewItem").innerText;
			var bindTemplate = Handlebars.compile(reviewItem);


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
				var sub = reservationEmail.substring(0,4);
				return new Handlebars.SafeString(sub)+"****";
			});

			Handlebars.registerHelper("date", function(reservationDate) {
				var sub = reservationDate.year + "." + reservationDate.monthValue + "."+ reservationDate.dayOfMonth;
				return sub;
			});

			shortReviewBox.innerHTML = bindTemplate(productInfoData);
		}
}