import {fromHTML} from "/js/util/parseDom.js";
export const getImgAddButton = (forName) => fromHTML(
    `<span class="flex justify-center items-center w-44 h-44 bg-base-200">
        <span class="text-2xl">+</span>
    </span>`
);