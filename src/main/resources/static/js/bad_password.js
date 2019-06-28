window.onload = function () {

    var h1 = document.getElementById("header");
    var i = 0;
    var txt = h1.textContent;
    var speed = 50;

    function clearContent(){
        h1.textContent = "";
    }
    clearContent();

    var button = document.getElementById('myButton');
    button.addEventListener('click', function () {
        window.location.href = '/home';
    });

    function typeWriter() {
        if (i < txt.length) {
            h1.innerHTML += txt.charAt(i);
            i++;
            setTimeout(typeWriter, speed);
        }
    }
    typeWriter();

};
