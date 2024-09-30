window.addEventListener('load', () => {

    const findEmailRadio = document.querySelector('input[id="find-email"]');
    const findPhoneRadio = document.querySelector('input[id="find-phone"]');

    const findEmailForm = document.querySelector('#find-email-form');
    const findPhoneForm = document.querySelector('#find-phone-form');


    findEmailRadio.addEventListener('change', function() {
        if (findEmailRadio.checked) {
            findEmailForm.classList.remove('hidden');
            findPhoneForm.classList.add('hidden')
        }
    });

    findPhoneRadio.addEventListener('change', function() {
        if (findPhoneRadio.checked) {
            findPhoneForm.classList.remove('hidden');
            findEmailForm.classList.add('hidden')
        }
    });

    /*아이디 찾기 실패시 */
    if (errorMessage) {
        alert(errorMessage);
    }

});