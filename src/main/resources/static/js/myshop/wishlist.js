// 체크박스 전체 체크하기
function toggleCheckboxes(source) {
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(checkbox => {
        if (checkbox !== source) {
            checkbox.checked = source.checked;
        }
    });
}

// wishlist 전체 삭제 버튼 기능 구현을 위한 함수
function submitClearWishlistForm() {

    const form = document.getElementById('wishlistForm');
    // 모든 상품 ID를 숨은 입력 필드로 폼에 추가
    document.querySelectorAll('input[name="productIds"]').forEach(input => {
        const hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'productIds';
        hiddenInput.value = input.value;
        form.appendChild(hiddenInput);
    });
    // 폼을 제출합니다.
    form.submit();
}

// Modal을 구현하고 그 안에 iframe 설정

document.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("my_modal_1");
    const openModalButton = document.getElementById("openModalButton");
    const iframe = document.getElementById("iframe");

    // 모달 열기
    function openModal() {
        // iframe에 불러올 URL 설정
        iframe.src = "http://localhost:8083/products/64"; // 원하는 URL로 변경
        modal.showModal();
    }

    // 모달 닫기
    function closeModal() {
        modal.close();
        iframe.src = ""; // 모달 닫힐 때 iframe 초기화
    }

    // 버튼 클릭 이벤트 리스너
    openModalButton.addEventListener("click", (event) => {
        event.preventDefault();
        openModal();
    });

    // ESC 키로 모달 닫기
    window.addEventListener("keydown", (event) => {
        if (event.key === "Escape") {
            closeModal();
        }
    });

    // 모달 외부 클릭으로 닫기
    modal.addEventListener("click", (event) => {
        if (event.target === modal) {
            closeModal();
        }
    });
});