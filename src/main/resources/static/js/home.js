var formLoginTxt = document.getElementById("form_login_instruction_text");
var txt = formLoginTxt.textContent;

var formRegisterTxt = document.getElementById("form_register_instruction_text");
var txt2 = formRegisterTxt.textContent;


function clearLoginContent() {
    formLoginTxt.textContent = "";
    i = 0;
}

clearLoginContent();


function clearRegisterContent() {
    formRegisterTxt.textContent = "";
    j = 0;
}

clearRegisterContent();

var image = document.getElementById("image");
var images = [
    "../images/pic1.jpg",
    "../images/shopping2.png"
];

function change() {
    var strings = images.reverse();
    image.src = strings[0];
}

setInterval(change, 2000);

change();
var link = document.getElementById('link');

link.addEventListener('click', function () {
    this.style.color = 'chocolate';
    alert('Przekierowanie do strony zewnętrznej');
});

function showRegisterForm() {
    document.getElementById("registerForm").style.display = 'block';
    formRegisterWriter()
}

function closeRegisterForm() {
    document.getElementById('registerForm').style.display = 'none';
    clearRegisterContent();
}

function showLoginForm() {
    document.getElementById("loginForm").style.display = 'block';
    formLoginWriter()
}

function closeLoginForm() {
    document.getElementById('loginForm').style.display = 'none';
    clearLoginContent();
}

window.onscroll = function () {
    myFunction()
};

var header = document.getElementById("header");

var sticky = header.offsetTop;

function myFunction() {
    if (window.pageYOffset >= sticky) {
        header.classList.add("sticky")
    } else {
        header.classList.remove("sticky");
    }
}


var i = 0;

var speed = 50;

function formLoginWriter() {
    if (i < txt.length) {
        formLoginTxt.innerHTML += txt.charAt(i);
        i++;
        setTimeout(formLoginWriter, speed)

    }
}

var j = 0;

function formRegisterWriter() {
    if (j < txt2.length) {
        formRegisterTxt.innerHTML += txt2.charAt(j);
        j++;
        setTimeout(formRegisterWriter, speed);
    }
}

