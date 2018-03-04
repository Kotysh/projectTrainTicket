/**
 * Created by Дмитрий on 28.02.2018.
 */
function validRoute() {

    var route = document.getElementById('addRoute');
    var countNotValid = 0;

    var redBorder = "2px solid red";
    var noBorder = "2px solid #cccccc";

    if (route.value.trim().length == 0 || route.value.trim().length > 256){

        route.style.border = redBorder;
        document.getElementById('message').innerHTML = "заполните поле (максимум 256 символов)";
        document.getElementById('message').style.color = "red";
        countNotValid++;

    }else{

        route.style.border = noBorder;
        document.getElementById('message').innerHTML = "";

    }

    return countNotValid == 0;

}

