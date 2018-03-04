/**
 * Created by Дмитрий on 28.02.2018.
 */
function validStation() {

    var station = document.getElementById('station');
    var cityId = document.getElementById('cityId');
    var countNotValid = 0;

    var redBorder = "2px solid red";
    var noBorder = "2px solid #cccccc";

    if (station.value.trim().length == 0 || station.value.trim().length > 256){

        station.style.border = redBorder;
        document.getElementById('message').innerHTML = "заполните поле (максимум 256 символов)";
        document.getElementById('message').style.color = "red";
        countNotValid++;

    }else{

        station.style.border = noBorder;
        document.getElementById('message').innerHTML = "";

    }

    if (!(isInteger(cityId.value.trim())) && cityId.value.trim().length > 0){

        cityId.style.border = redBorder;
        document.getElementById('messageCity').innerHTML = "id города должно быть целое число";
        document.getElementById('messageCity').style.color = "red";
        countNotValid++;

    }else{

        cityId.style.border = noBorder;
        document.getElementById('messageCity').innerHTML = "";

    }

    return countNotValid == 0;

}
function isInteger(num) {
    return num%1 == 0 && num>0;
}
