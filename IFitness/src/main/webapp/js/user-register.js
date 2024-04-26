"use strict"

window.onload = initPage;

function initPage(){
	let form = document.getElementById('form1');
	form.noValidate = true;
	form.addEventListener('submit', function(e){
		let valid = processValidity(this);
		if(!valid){
			e.preventDefault();
		}
	});
}

function processValidity(form){
	let valid;
	validatePassword(form);
	valid = applyValidity(form);
	return valid;
}

function validatePassword(form){
	let password = form.elements['password'];
	let confirmPassword = form.elements['confirmPassword'];
	if(password.value != confirmPassword.value){
		password.setCustomValidity
		('Valores de senha e confirmação de senha diferentes.');
	}else{
		password.setCustomValidity('');
	}
}

function applyValidity(form){
	let valid = true;
	let count = 0;
	let elements = form.elements;
	for(let i = 0; i < elements.length - 1; i++){
		let element = elements[i];
		let span = document.getElementById(i);
		if(!element.validity.valid){
			span.innerHTML = element.validationMessage;
			count++;
		}else{
			span.innerHTML = ' ';
		}
	}
	if(count > 0){
		valid = false;
	}
	return valid;
}










