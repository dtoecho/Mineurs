window.addEventListener('load', () => {
    const userName = document.querySelector('input[name="username"]')
    const userNameValidation = document.getElementById('username-validation');

    const password = document.querySelector('input[name="password"]')
    const passwordValidation = document.getElementById('password-validation');

    const passwordCheck = document.querySelector('input[name="passwordCheck"]')
    const passwordCheckValidation = document.getElementById('passwordCheck-validation');

    const name = document.querySelector('input[name="name"]')
    const nameValidation = document.getElementById('name-validation');

    const phoneNumber = document.querySelector('input[name="phoneNumber"]')
    const phoneNumberValidation = document.getElementById('phoneNumber-validation');

    const email = document.querySelector('input[name="email"]')
    const emailValidation = document.getElementById('email-validation');


    /* 아이디 유효성검사 */
    userName.addEventListener('input', () => {
        let regex = /^[a-z0-9]{4,16}$/;
        if (regex.test(userName.value)) {
            userNameValidation.textContent = '올바른 형식입니다.';
            userNameValidation.classList.add('text-green-500');
            userNameValidation.classList.remove('text-red-500');
        } else if (userName.value.length === 0) {
            userNameValidation.textContent = '';
            userNameValidation.classList.remove('text-green-500');
            userNameValidation.classList.remove('text-red-500');
        } else {
            userNameValidation.textContent = '4~16자의 영문 소문자, 숫자만 사용할 수 있습니다.';
            userNameValidation.classList.remove('text-green-500');
            userNameValidation.classList.add('text-red-500');
        }
    });

    /* 비밀번호 유효성 검사*/
    password.addEventListener('input', () => {
        /*영문 대소문자/숫자/특수문자 중 3가지 이상 조합, 8자~16자*/
        let regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;
        /*한글로 입력해도 알파벳으로 변경*/
        let mapping = {
            'ㅂ': 'q', 'ㅈ': 'w', 'ㄷ': 'e', 'ㄱ': 'r', 'ㅅ': 't', 'ㅛ': 'y', 'ㅕ': 'u', 'ㅑ': 'i', 'ㅐ': 'o', 'ㅔ': 'p',
            'ㅁ': 'a', 'ㄴ': 's', 'ㅇ': 'd', 'ㄹ': 'f', 'ㅎ': 'g', 'ㅗ': 'h', 'ㅓ': 'j', 'ㅏ': 'k', 'ㅣ': 'l',
            'ㅋ': 'z', 'ㅌ': 'x', 'ㅊ': 'c', 'ㅍ': 'v', 'ㅠ': 'b', 'ㅜ': 'n', 'ㅡ': 'm',
        }
        const lastChar = password.value[password.value.length - 1];
        const replacement = mapping[lastChar];
        if (replacement) {
            password.value = password.value.slice(0, -1) + replacement;
        }
        if (regex.test(password.value)) {
            passwordValidation.textContent = '올바른 형식입니다.';
            passwordValidation.classList.add('text-green-500');
            passwordValidation.classList.remove('text-red-500');
        } else if (password.value.length === 0) {
            passwordValidation.textContent = '';
            passwordValidation.classList.remove('text-green-500');
            passwordValidation.classList.remove('text-red-500');
        } else {
            passwordValidation.textContent = '영문 대소문자/숫자/특수문자 중 3가지 이상 조합, 8자~16자만 사용할 수 있습니다.';
            passwordValidation.classList.remove('text-green-500');
            passwordValidation.classList.add('text-red-500');
        }
    });

    /*비밀번호 재확인*/
    passwordCheck.addEventListener('input', () => {
        if (passwordCheck.value === password.value){
            passwordCheckValidation.textContent ='';
        }
        else{
            passwordCheckValidation.textContent = '비밀번호가 일치하지 않습니다.';
        }
    });

    /*이름 유효성 검사*/
    name.addEventListener('input', () => {
        let regex = /^[가-힣]{2,4}$/;
        if (regex.test(name.value)) {
            nameValidation.textContent = '';
        }else if (name.value==='') {
            nameValidation.textContent = '';
        }else{
            nameValidation.textContent = '2~4자의 한글만 사용할 수 있습니다.';
        }
    });

    /*휴대전화번호 유효성검사*/
    phoneNumber.addEventListener('input', () => {
        let regex = /^010\d{8}$/;
        if (regex.test(phoneNumber.value)) {
            phoneNumberValidation.textContent = '';
        }else if (phoneNumber.value==='') {
            phoneNumberValidation.textContent = '';
        }else{
            phoneNumberValidation.textContent = '올바른 형식이 아닙니다.';
        }
    });

    /*이메일 유효성검사*/
    email.addEventListener('input', () => {
        let regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (regex.test(email.value)) {
            emailValidation.textContent = '';
        }else if (email.value==='') {
            emailValidation.textContent = '';
        }else{
            emailValidation.textContent = '올바른 형식이 아닙니다.';
        }
    });
});
