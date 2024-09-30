window.addEventListener("load", ev => {

    let html = window.document.querySelector("html");
    let header = window.document.querySelector("header");

    let themeToggle = header.querySelector(".theme-controller");
    themeToggle.addEventListener("change", ()=>{
        if (themeToggle.checked)
            html.setAttribute("data-theme", "dark")
        else
            html.setAttribute("data-theme", "light")
    })
})
