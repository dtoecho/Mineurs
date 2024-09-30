import {fromHTML} from "/js/util/parseDom.js";

export const getImgFrame = () => fromHTML(
    `<span class="img-frame flex relative w-44 h-44">
        <button class="btn btn-primary absolute top-0 right-0 img-del-btn">X</button>
        <img
                id="imgId"
                alt="첨부이미지"
                class="my-0"
                th:data-isOldImg="${true}"
        >
    </span>`
);