import {handleImgDelBtnClick,  handleInput, handleDrop, handleDragOver, handleDragLeave} from '/js/util/imgUploader.js'

window.onload = function() {
    alert('주의!! 추가 사진 수정 시 기존의 이미지를 모두 지우고 새로 올려주세요');
};

window.addEventListener("load", e => {



    // dom 초기화 =============================================================
    const form = document.querySelector("#form");

    // 이미지 업로더
    const singleImgUploader = form.querySelector(".single-img-uploader")
    const multiImgUploader = form.querySelector(".multi-img-uploader")

    // 인풋삭제버튼
    const singleImgDelBtn = singleImgUploader.querySelector(".img-del-btn");
    const multiImgDelButtons = multiImgUploader.querySelectorAll(".img-del-btn")

    // 인풋드랍존
    const singleImgPreview = singleImgUploader.querySelector(".img-preview")
    // multi는 이미지 추가 버튼을 포함한 영역인 img-uploader 가 드랍존.

    // 인풋
    const singleImgInput = singleImgUploader.querySelector(".img-input")
    const multiImgInput = multiImgUploader.querySelector(".img-input")
    singleImgInput.value = "";
    multiImgInput.value = "";
    // input.files=null 은 보안상 이유로 파일시스템에 직접 접근이라 안된다
    // 초기화 안하면 새로고침 해도 이전 입력파일이 남아있다


    // 콜백설정 ==============================================================
    // 삭제 버튼 콜백
    singleImgDelBtn.onclick = e => handleImgDelBtnClick(e)

    // sub 삭제 버튼 콜백
    multiImgDelButtons.forEach(
        btn =>
            btn.onclick = (e) => handleImgDelBtnClick(e, true))

    // 인풋 콜백
    singleImgInput.oninput = (e) => handleInput(e);
    multiImgInput.oninput = (e) => handleInput(e, true);

    // 드랍존 콜백
    singleImgUploader.ondrop = (e) =>
        handleDrop(e);
    multiImgUploader.ondrop = (e) =>
        handleDrop(e);

    singleImgUploader.ondragover = (e) =>
        handleDragOver(e);
    multiImgUploader.ondragover = (e) =>
        handleDragOver(e);

    singleImgUploader.ondragleave = (e) =>
        handleDragLeave(e);
    multiImgUploader.ondragleave = (e) =>
        handleDragLeave(e);

})

// function parseProductId(currentUrl) {
//     const parts = currentUrl.split('/'); // URL을 '/'로 분할
//     const lastPart = parts[parts.length - 1]; // 마지막 부분 추출
//     return parseInt(lastPart);
// }

// window.addEventListener("load", (e) => {
//     const form = document.querySelector("#form"); // detail.html 의 요소(의 id:frm) 선택.
//
//     const productId = parseProductId(window.location.href); // 문자열을 숫자로 변환
//
//     const editButton = form.querySelector(".edit")
//     const mainImgDelButton = form.querySelector(".mainImgDel")
//     const subImgDelButtons = form.querySelectorAll(".subImgDel")
//
//     console.log("ㅇㅇ")
//     editButton.onclick = (e) => {
//         e.preventDefault();
//         console.log("실행되나");
//         edit(form, productId);
//     };
//
//     mainImgDelButton.onclick = e => {
//         e.preventDefault()
//         e.stopPropagation()
//         const delButton = e.target
//         const imgFrame = delButton.closest('.img-frame')
//         const img = imgFrame.querySelector("img")
//         const isOldImg = img.dataset.isoldimg
//         console.log("isOldImg: {}", isOldImg);
//
//         imgFrame.remove()
//     }
//
//     subImgDelButtons.forEach(btn => {
//         console.log("btn: {}", btn);
//         btn.onclick = e => {
//             e.preventDefault()
//             e.stopPropagation()
//             console.log("e.target: {}", e.target);
//             const delButton = e.target
//             const imgFrame = delButton.closest('.img-frame')
//             const img = imgFrame.querySelector("img")
//             const isOldImg = img.dataset.isoldimg
//
//             imgFrame.remove()
//         }
//     })
//
// });

// async function edit(form, productId) {
//     const formData = new FormData(form);
//
//
//     const response = await fetch('/admin/products/' + productId, {
//         method: "PUT",
//         body: formData,
//     });
//
//     if (response.ok) {
//         console.log(await response.text());
//         await window.location.replace("/admin/products")
//     } else {
//         console.error("File upload failed.");
//     }
// }