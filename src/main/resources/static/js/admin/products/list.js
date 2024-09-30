const searchSec = document.querySelector("#searchSec");
const dateA = searchSec.querySelector(".date");

dateA.onclick = function updateURL(dateValue) {
    // Update the URL with the selected date value and end-date
    var today = new Date().toISOString().split('T')[0]; // Get today's date in YYYY-MM-DD format
    var urlParams = new URLSearchParams(window.location.search);
    urlParams.set('date', dateValue);
    urlParams.set('end-date', today);
    var newURL = window.location.pathname + '?' + urlParams.toString();
    window.history.pushState({path: newURL}, '', newURL);
}