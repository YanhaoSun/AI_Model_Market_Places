$(document).ready(function(){
    let dateInput = document.getElementById("contactDate");
    dateInput.min = new Date().toISOString().slice(0,new Date().toISOString().lastIndexOf(":"));
})

function showAdminKeyInput() {
    var checkBox = document.getElementById("adminCheck");
    var text = document.getElementById("adminKeyInput");

    if (checkBox.checked === true) {
        text.style.display = "block";
    } else {
        text.style.display = "none";
    }
}

function validateRegister() {
    let password = document.forms["register"]["passwd"].value;
    let cpassword = document.forms["register"]["cpasswd"].value;

    if (password !== cpassword) {
        alert("Passwords must be the same");
        return false;
    }
}