document.addEventListener("DOMContentLoaded", function() {
	let submitModule = new SubmissionModule();
	submitModule.submitForm();
});

function ValidityModule(){}
ValidityModule.prototype ={
		validateEmail : function(){
			var email = document.querySelector("#resrv_id");
			console.log(email);
			var validEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			
			if(email.value === ""){
				alert("이메일을 입력해주세요.");
				email.value="";
				return false;
			}
			if(!validEmail.test(email.value)){
				alert("이메일 양식이 옳바르지 않습니다.");
				email.value="";
				return false;
			}
			
			return true;	
		}
}

function SubmissionModule(){}
SubmissionModule.prototype = {
	submitForm : function(){
		var formBox = document.querySelector("#form1");
		var loginBtn = document.querySelector(".login_btn");
		
		loginBtn.addEventListener("click",function(event){
			let validityModule = new ValidityModule();
			var valid = validityModule.validateEmail();
			
			if(!valid){
				event.preventDefault();
			}
			
			if(valid){
				formBox.submit();
			}	
		})
	}	
}