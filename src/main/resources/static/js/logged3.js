var el = document.getElementById('datetime');

function incrementSeconds() {
    var dt = new Date();
    el.innerText = dt.toLocaleString()
}

setInterval(incrementSeconds, 1000);

var inputsArray = document.getElementsByClassName('input');

Array.from(inputsArray).forEach(function (element) {
    element.addEventListener('focus', function () {
        this.style.border = '5px solid lightgrey'
    });
    element.addEventListener('blur', function () {
        this.style.border = '1px solid lightgrey'
    })
});


var buttonsArray = document.getElementsByClassName('myButton');

buttonsArray.item(0).addEventListener('click', function () {
    window.location.href = '/endSession'
});

buttonsArray.item(1).addEventListener('click', function () {
    window.location.href = '/mail'
});

buttonsArray.item(2).addEventListener('click', function () {
    window.location.href = '/delete'
});
