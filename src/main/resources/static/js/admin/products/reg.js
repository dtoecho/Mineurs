window.addEventListener("load", function (node, child) {

    let mainImgInput = document.querySelector(".main-img-input");
    let subImgInput = document.querySelector(".sub-img-input");

    let mainImgBox = document.querySelector(".main-img-box");
    let subImgBox = document.querySelector(".sub-img-box");

    let mainImgLabel = document.querySelector(".main-img-label");
    let subImgLabel = document.querySelector(".sub-img-label");

    mainImgInput.oninput = (e) => handleInput(e, mainImgBox);
    subImgInput.oninput = (e) => handleInput(e, subImgBox);

    mainImgLabel.ondrop = (e) =>
        handleDrop(e, mainImgLabel, mainImgInput, mainImgBox);
    subImgLabel.ondrop = (e) =>
        handleDrop(e, subImgLabel, subImgInput, subImgBox);

    mainImgLabel.ondragover = (e) => handleDragOver(e, mainImgLabel);
    subImgLabel.ondragover = (e) => handleDragOver(e, subImgLabel);

    mainImgLabel.ondragleave = (e) => handleDragLeave(e, mainImgLabel);
    subImgLabel.ondragleave = (e) => handleDragLeave(e, subImgLabel);

});

// -------------- 이벤트 핸들러 ----------
function handleInput(e, imgBox) {
    let files = e.target.files;

    console.log("input files: ", files);

    let append = false;
    renderThumbnails(imgBox, files, append);
}

function handleDrop(e, imgLabel, imgInput, imgBox) {
    e.stopPropagation();
    e.preventDefault();
    console.log("imgInput: {}", imgInput);

    resetValidState(imgLabel);

    const filesFromDrag = e.dataTransfer.files;

    if (imgInput.multiple === false) {
        updateSingleInputAndRender(imgInput, filesFromDrag, imgBox);
    } else {
        updateMultiInputAndRender(imgInput, filesFromDrag, imgBox);
    }
}

function handleDragOver(e, imgLabel) {
    e.stopPropagation();
    e.preventDefault();

    let valid = e.dataTransfer.types.indexOf("Files") >= 0;
    if (!valid)
        imgLabel.classList.add("invalid");
}

function handleDragLeave(e, imgLabel) {
    imgLabel.classList.remove("invalid");
}

function handleFileLoad(e, imgPreview) {
    let img = document.createElement("img");
    img.src = e.target.result;

    // img.onload = () => {
    //   img.classList.add("fade-in");
    //   img.classList.add("slide-in");
    // };

    setTimeout(() => {
        img.classList.add("fade-in");
        img.classList.add("slide-in");
        img.classList.add("w-56");
    }, 10);

    imgPreview.append(img);
}


// -------------- 유틸 함수 ----------
function setInputFiles(imgInput, files) {
    imgInput.files = files;
    console.log("input files: {}", files);
}

function hasSameName(oldFilesArray, file) {
    for (let oldFile of oldFilesArray)
        if (oldFile.name === file.name) return true;

    return false;
}

function removeDuplicate(oldFiles, appendingFiles) {
    const oldFilesArray = [...oldFiles];
    return [...appendingFiles].filter(
        (file) => !hasSameName(oldFilesArray, file),
    );
}

function appendFiles(imgInput, files) {
    const dt = new DataTransfer();
    for (let file of imgInput.files) dt.items.add(file);

    for (let file of files) {
        dt.items.add(file);
    }
    setInputFiles(imgInput, dt.files);
}

function renderThumbnails(imgPreview, files, append) {
    if (!append) imgPreview.innerHTML = "";
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
        reader.onload = (e) => handleFileLoad(e, imgPreview);
        reader.readAsDataURL(file);
    }
}

function updateSingleInputAndRender(imgInput, filesFromDrag, imgPreview) {
    setInputFiles(imgInput, filesFromDrag);

    let append = false;
    renderThumbnails(imgPreview, filesFromDrag, append);
}

function updateMultiInputAndRender(imgInput, filesFromDrag, imgPreview) {
    const filteredFiles = removeDuplicate(imgInput.files, filesFromDrag);

    appendFiles(imgInput, filteredFiles);

    let append = true;
    renderThumbnails(imgPreview, filteredFiles, append);
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
