/**
 * Created by Дмитрий on 27.02.2018.
 */
function validCity() {

    var city = document.getElementById('city');
    var countNotValid = 0;

    var redBorder = "2px solid red";
    var noBorder = "2px solid #cccccc";

    if (city.value.trim().length == 0 || city.value.trim().length > 128){

        city.style.border = redBorder;
        document.getElementById('message').innerHTML = "заполните поле (максимум 128 символов)";
        document.getElementById('message').style.color = "red";
        countNotValid++;

    }else{

        city.style.border = noBorder;
        document.getElementById('message').innerHTML = "";

    }

    return countNotValid == 0;

}