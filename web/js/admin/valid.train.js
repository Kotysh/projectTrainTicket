/**
 * Created by Дмитрий on 01.03.2018.
 */
function validTrain() {

    var numberTrain = document.getElementById('numberTrain');
    var routeId = document.getElementById('routeId');
    var countNotValid = 0;

    var redBorder = "2px solid red";
    var noBorder = "2px solid #cccccc";

    if (numberTrain.value.trim().length == 0 || numberTrain.value.trim().length > 30){

        numberTrain.style.border = redBorder;
        document.getElementById('erNumber').innerHTML = "заполните поле (максимум 30 символов)";
        document.getElementById('erNumber').style.color = "red";
        countNotValid++;

    }else{

        numberTrain.style.border = noBorder;
        document.getElementById('erNumber').innerHTML = "";

    }

    if (routeId.value.trim().length == 0 || !(isInteger(routeId.value.trim())) || routeId.value.trim().length>40){

        routeId.style.border = redBorder;
        document.getElementById('message').innerHTML = "заполните поле (id маршрута должно быть целое число)";
        document.getElementById('message').style.color = "red";
        countNotValid++;

    }else{

        routeId.style.border = noBorder;
        document.getElementById('message').innerHTML = "";

    }

    var radioExpress = document.getElementsByName('express');
    var perExpress = check(radioExpress);

    var express = document.getElementById('erExpress');

    if (perExpress == 0){
        express.innerHTML = "укажите тип поезда";
        express.style.color = "red";
        countNotValid++;
    }else {
        express.innerHTML = "";
    }
    return countNotValid == 0;

}
function check(inp){

        for (var i = 0; i < inp.length; i++) {
            if (inp[i].type == "radio" && inp[i].checked) {
                console.log(inp[i]);
                return 1;
            }
        }
        return 0;
}
function isInteger(num) {
    return num%1 == 0 && num>0;
}
