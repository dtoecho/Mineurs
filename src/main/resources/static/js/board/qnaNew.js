
window.addEventListener('load', () => {

    document.querySelector('form').addEventListener('submit', function(event) {
        var selectElement = document.querySelector('select[name="categoryId"]');
        if (selectElement.value === '-문의유형을 선택후 작성해주세요') {
            alert('문의 유형을 선택해주세요.');
            event.preventDefault();
        }
    });


});