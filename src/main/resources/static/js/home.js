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
}

function closeRegisterForm() {
    document.getElementById('registerForm').style.display = 'none';
}

function showLoginForm() {
    document.getElementById("loginForm").style.display = 'block';
}

function closeLoginForm() {
    document.getElementById('loginForm').style.display = 'none';
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
