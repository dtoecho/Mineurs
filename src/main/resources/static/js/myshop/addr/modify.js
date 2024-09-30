document.addEventListener('DOMContentLoaded', function() {
    // 폼 제출 시 유효성 검사 실행
    document.getElementById('modifyForm').addEventListener('submit', function(event) {
        // 전화번호 유효성 검사
        if (!validatePhoneNumber()) {
            // 폼 제출을 막음
            console.log("전화번호 양식 잘못됨");
            event.preventDefault();
        }
    });

    // 전화번호 입력 시 유효성 검사 실행
    document.getElementById('mobile2').addEventListener('input', function() {
        // 전화번호 유효성 검사
        if (!validatePhoneNumber()) {
            // 유효하지 않은 경우 오류 메시지 표시
            document.getElementById('phoneError').style.display = 'inline';
        } else {
            // 유효한 경우 오류 메시지 숨김
            document.getElementById('phoneError').style.display = 'none';
        }
    });
});

// 전화번호 유효성 검사 함수
function validatePhoneNumber() {
    const phoneNumberInput = document.getElementById('mobile2');
    const phoneError = document.getElementById('phoneError');
    const phoneNumber = phoneNumberInput.value;
    const phoneRegex = /^\d{11}$/;

    // 전화번호 유효성 검사
    if (phoneRegex.test(phoneNumber)) {
        phoneError.style.display = 'none'; // 유효한 경우 오류 메시지 숨김
        return true;
    } else {
        phoneError.style.display = 'inline'; // 유효하지 않은 경우 오류 메시지 표시
        return false;
    }
}