

// *윈도우 이벤트 로드 시작*
window.addEventListener('load', function () {
// *요소*

    const preview = document.querySelector(".preview");
    const closeButton = document.querySelector('.close-button');
    const beforeDateInput = document.querySelector('.before-date-input')
    const afterDateInput = document.querySelector('.after-date-input')

    //HTML 요소 변수 매핑 영역
    // const previewId = preview.getAttribute("data-id");
    modal = document.querySelector('.modal');
    //관리자 미리보기 클릭 이벤트 데이터요청영역
    // preview.onclick = function () {
    //     $.ajax({
    //         url : '/api/post/'+previewId,
    //         type : 'get',
    //         dataType : 'json',
    //         success : postView,
    //         error: function () {
    //             alert('Error occurred:' + errorThrown);
    //         }
    //     })
    // };

    //미리보기 열린 후 닫기 버튼 이벤트 처리 영역
    // closeButton.addEventListener('click', function () {
    //     setTimeout(() => {
    //         modal.classList.add('hidden');
    //     }, 130);
    // });

    // #util Form필드를 초기화하는 reset button
    document.querySelector('button[type="reset"]').addEventListener('click', () => {
        // 기본 reset 이벤트를 막습니다. 이렇게 하지 않으면 폼은 페이지를 새로고침하게 됩니다.
        event.preventDefault();

        // 텍스트 필드를 비웁니다.
        document.querySelector('input[name="searchKeyword"]').value = '';

        // 여기에 다른 폼 필드를 비우는 코드를 추가할 수 있습니다.
        // 예: document.querySelector('input[name="otherField"]').value = '';
    });

    // #1 검색폼 작성일 설정
    const today = new Date();
    beforeDateInput.value = `${today.getFullYear()}-${String(today.getMonth()).padStart(2, '0')}-${String(today.getDate()+1).padStart(2, '0')}`;
    afterDateInput.value = `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, '0')}-${String(today.getDate()).padStart(2, '0')}`;

    //#1-1 작성일 버튼에 대한 날짜변경 이벤트 설정
    const todayBtn = document.querySelector('.today-btn');
    todayBtn.addEventListener('click', ()=> {
        beforeDateInput.value = `${today.getFullYear()}-${String(today.getMonth() +1).padStart(2, '0')}-${String(today.getDate()).padStart(2, '0')}`;
        afterDateInput.value = `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, '0')}-${String(today.getDate()).padStart(2, '0')}`;
    });
    const threeDayBtn = document.querySelector('.three-day-btn');
    threeDayBtn.addEventListener('click', ()=> {
        beforeDateInput.value = `${today.getFullYear()}-${String(today.getMonth() +1).padStart(2, '0')}-${String(today.getDate()-3).padStart(2, '0')}`;
        afterDateInput.value = `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, '0')}-${String(today.getDate()).padStart(2, '0')}`;
    });
    const sevenDayBtn = document.querySelector('.seven-day-btn');
    sevenDayBtn.addEventListener('click', ()=> {
        beforeDateInput.value = `${today.getFullYear()}-${String(today.getMonth() +1).padStart(2, '0')}-${String(today.getDate()-7).padStart(2, '0')}`;
        afterDateInput.value = `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, '0')}-${String(today.getDate()).padStart(2, '0')}`;
    });
    const oneMonthBtn = document.querySelector('.one-month-btn');
    oneMonthBtn.addEventListener('click', ()=> {
        beforeDateInput.value = `${today.getFullYear()}-${String(today.getMonth() ).padStart(2, '0')}-${String(today.getDate()+1).padStart(2, '0')}`;
        afterDateInput.value = `${today.getFullYear()}-${String(today.getMonth()+1).padStart(2, '0')}-${String(today.getDate()).padStart(2, '0')}`;
    });

});
// *윈도우 로드 이벤트 끝*


//미리보기 클릭후에 데이터 받아서 모달에 데이터 뿌려주는 영역
function postView(post) {
    modalContent = document.querySelector('.modal-content');
    modal.classList.remove('hidden');
    modalContent.innerHTML = `
        <label>
            제목
            <input type="text" class="n-textbox n-textbox-type:outline n-textbox-label:top" value="${post.title}"/>
        </label>
        <label>
            작성자
            <input type="text" class="n-textbox n-textbox-type:outline n-textbox-label:top" value="${post.writer}"/>
        </label>
        <label>
            답변상태 
            <input type="text" class="n-textbox n-textbox-type:outline n-textbox-label:top" value="${post.status}"/>
            <button class="my:3 n-btn">답변하기</button>
        </label>
        <label>
            원글
            <textarea type="text" class="n-textbox n-textbox-type:outline n-textbox-label:top h:5">
                ${post.content}
            </textarea>
        </label>
    `;

}