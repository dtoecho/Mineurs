function deleteSelectedAddresses() {
    const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    const addressIds = Array.from(checkboxes).map(checkbox => checkbox.value);

    if (addressIds.length === 0) {
        alert("삭제할 항목을 선택해주세요.");
        return;
    }

    if (confirm("선택한 주소를 삭제하시겠습니까?")) {
        // AJAX 요청을 사용하여 선택한 주소를 삭제하는 서버 API 호출
        // 주소 ID 리스트를 서버로 전송하여 선택한 주소를 삭제합니다.
        fetch('/delete-addresses', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
            },
            body: JSON.stringify({ addressIds: addressIds })
        })
            .then(response => {
                if (response.ok) {
                    // 삭제가 성공하면 페이지를 다시로드하여 변경된 주소 목록을 표시합니다.
                    location.reload();
                } else {
                    alert("주소 삭제 중 오류가 발생했습니다.");
                }
            })
            .catch(error => {
                console.error('주소 삭제 중 오류 발생:', error);
                alert("주소 삭제 중 오류가 발생했습니다.");
            });
    }
}

// 체크박스 전체 체크하기
function toggleCheckboxes(source) {
    const checkboxes = document.querySelectorAll('tbody input[type="checkbox"]');
    checkboxes.forEach(checkbox => {
        if (checkbox !== source) {
            checkbox.checked = source.checked;
        }
    });
}


