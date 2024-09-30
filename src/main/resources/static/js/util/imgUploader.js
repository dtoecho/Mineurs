import {getImgAddButton} from "/js/template/imgAddButton.js";

import {getImgFrame} from "/js/template/imgFrame.js";


export function handleImgDelBtnClick(e, isMulti = false) {
    const imgPreview = deleteImgFrame(e);

    if (!isMulti && imgPreview.children.length == 0) {
        imgPreview.append(getImgAddButton())
    }
}


export function handleInput(e, isMulti = false) {
    const imgInput = e.target;
    let files = imgInput.files;
    const imgPreview = imgInput.closest(".img-uploader").querySelector(".img-preview")

    console.log("input files: ", files);

    renderThumbnails(imgPreview, files, isMulti);
}

export function handleDrop(e) {
    e.stopPropagation();
    e.preventDefault();

    const imgUploader = e.target.closest(".img-uploader")
    const imgInput = imgUploader.querySelector(".img-input")
    const imgPreview = imgUploader.querySelector(".img-preview")

    resetValidState(imgUploader);

    const filesFromDrag = e.dataTransfer.files;
    console.log("filesFromDrag: {}", filesFromDrag);

    if (imgInput.multiple === false) {
        console.log("single uploader input")
        updateSingleInputAndRender(imgInput, filesFromDrag, imgPreview);
    } else {
        console.log("multi uploader input")
        updateMultiInputAndRender(imgInput, filesFromDrag, imgPreview);
    }
}

export function handleDragOver(e) {
    e.stopPropagation();
    e.preventDefault();

    const imgUploader = e.target.closest(".img-uploader")

    let valid = e.dataTransfer.types.indexOf("Files") >= 0;
    if (!valid)
        imgUploader.classList.add("invalid");
}

export function handleDragLeave(e, imgLabel) {
    const imgUploader = e.target.closest(".img-uploader")

    imgUploader.classList.remove("invalid");
}


// 내부 핸들러 (이 모듈안에서만 사용됨) =============================
function handleFileLoad(e, imgPreview, isMulti) {

    const imgFrame = getImgFrame()
    const img = imgFrame.querySelector("img")
    const imgDelBtn = imgFrame.querySelector(".img-del-btn")
    imgDelBtn.onclick = e => handleImgDelBtnClick(e, isMulti)

    // 이미지 주소가 아닌 바이너리 값이 담긴다
    const imgSrc = e.target.result;
    img.src = imgSrc

    // img.onload = () => {
    //   img.classList.add("fade-in");
    //   img.classList.add("slide-in");
    // };

    setTimeout(() => {
        img.classList.add("fade-in");
        img.classList.add("slide-in");
        img.classList.add("w-56");
    }, 10);

    imgPreview.append(imgFrame);
}

// 유틸 함수 =======================================

function deleteImgFrame(e) {
    e.preventDefault()
    const delButton = e.target
    const imgPreview = delButton.closest(".img-preview")
    const imgFrame = delButton.closest('.img-frame')

    imgFrame.remove()
    return imgPreview;
}

function renderThumbnails(imgPreview, files, isMulti = false) {
    if (!isMulti) imgPreview.innerHTML = "";
    // 유효성 검사
    for (let file of files) {
        // 크기 제약
        if (!isImg(file)) {
            alert("이미지만 업로드해라");
            return;
        }
        // 타입 제약 바이트 단위. 단위기호 미제공
        let sizeLimit = 40 * 1024 * 1024;
        if (file.size > sizeLimit) {
            alert("파일이 너무큼, file size: " + formatByteToMB(file.size));
            console.log("파일사이즈: ", file.size);
            return;
        }
        // 여기서 파일 배열을 전송
        let reader = new FileReader();
        reader.onload = (e) => handleFileLoad(e, imgPreview, isMulti);
        reader.readAsDataURL(file);
    }
}


function updateSingleInputAndRender(imgInput, filesFromDrag, imgPreview) {
    setInputFiles(imgInput, filesFromDrag);

    renderThumbnails(imgPreview, filesFromDrag);
}

function setInputFiles(imgInput, files) {
    imgInput.files = files;
    console.log("input files: {}", files);
}

function updateMultiInputAndRender(imgInput, filesFromDrag, imgPreview) {
    const filteredFiles = removeDuplicate(imgInput.files, filesFromDrag);

    appendFiles(imgInput, filteredFiles);

    renderThumbnails(imgPreview, filteredFiles, true);
}

function appendFiles(imgInput, files) {
    const dt = new DataTransfer();
    for (let file of imgInput.files) dt.items.add(file);

    for (let file of files) {
        dt.items.add(file);
    }
    setInputFiles(imgInput, dt.files);
}

function removeDuplicate(oldFiles, appendingFiles) {
    const oldFilesArray = [...oldFiles];
    return [...appendingFiles].filter(
        (file) => !hasSameName(oldFilesArray, file),
    );
}

function hasSameName(oldFilesArray, file) {
    for (let oldFile of oldFilesArray) {
        if (oldFile.name === file.name) {
            return true;
        }
    }

    return false;
}


function resetValidState(imgLabel) {
    imgLabel.classList.remove("valid");
    imgLabel.classList.remove("invalid");
}


function formatByteToMB(byteSize) {
    return (byteSize / 1024).toFixed(2) + "MB";
}

function isImg(file) {
    return file.type.indexOf("image/") === 0;
}



