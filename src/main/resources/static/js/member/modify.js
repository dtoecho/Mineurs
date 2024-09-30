function validatePassword() {
    var password1 = document.getElementById("pwd1").value;
    var password2 = document.getElementById("pwd2").value;

    var errorDiv = document.getElementById("passwordError");

    if (password1 !== password2) {
        errorDiv.style.display = "block";
        return false;
    } else {
        errorDiv.style.display = "none";
        return true;
    }
}

function validateForm() {
    return validatePassword();
}

function submitForm() {
    if (validatePassword()) {
        document.getElementById("modifyForm").submit();
       
        alert("회원 정보 수정 완료");
    }
}
