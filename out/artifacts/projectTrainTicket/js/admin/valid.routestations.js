/**
 * Created by Дмитрий on 28.02.2018.
 */
function validRouteStation() {

    var routeId = document.getElementById('routeId');
    var stationId = document.getElementById('stationId');
    var orderStation = document.getElementById('orderStation');

    var arrivalYear = document.getElementById('arrivalYear');
    var arrivalMonth = document.getElementById('arrivalMonth');
    var arrivalDay = document.getElementById('arrivalDay');
    var arrivalHour = document.getElementById('arrivalHour');
    var arrivalMinute = document.getElementById('arrivalMinute');

    var departureYear = document.getElementById('departureYear');
    var departureMonth = document.getElementById('departureMonth');
    var departureDay = document.getElementById('departureDay');
    var departureHour = document.getElementById('departureHour');
    var departureMinute = document.getElementById('departureMinute');

    var distance = document.getElementById('distance');

    var redBorder = "2px solid red";
    var noBorder = "2px solid #cccccc";
    var countNotValid = 0;
    var max = 1000000000;

    var erRoute = document.getElementById('erRoute');
    var erStation = document.getElementById('erStation');
    var erOrderStation = document.getElementById('erOrderStation');


    if (routeId.value.trim().length == 0 || !(isInteger(routeId.value.trim())) || routeId.value.trim().length<40){
        erRoute.innerHTML = "укажите целое число";
        erRoute.style.color = "red";
        routeId.style.border = redBorder;
        countNotValid++;
    }
    else{
        erRoute.innerHTML = "";
        routeId.style.border = noBorder
    }

    if (stationId.value.trim().length == 0 || !(isInteger(stationId.value.trim())) || stationId.value.trim().length<40){
        erStation.innerHTML = "укажите целое цисло";
        erStation.style.color = "red";
        stationId.style.border = redBorder;
        countNotValid++;
    }
    else{
        erStation.innerHTML = "";
        stationId.style.border = noBorder
    }

    if (orderStation.value.trim().length == 0 || !(isInteger(orderStation.value.trim())) || orderStation.value.trim()<1000){
        erOrderStation.innerHTML = "укажите целое цисло";
        erOrderStation.style.color = "red";
        orderStation.style.border = redBorder;
        countNotValid++;
    }
    else{
        erOrderStation.innerHTML = "";
        orderStation.style.border = noBorder
    }

    var erArrivalTime = document.getElementById('erArrivalTime');

    var date = new Date();
    var now = new Date(arrivalYear.value.trim(), arrivalMonth.value.trim()-1, arrivalDay.value.trim(), arrivalHour.value.trim(), arrivalMinute.value.trim(), 0, 0);

    if (arrivalYear.value.trim().length == 0 ||
        !(isInteger(arrivalYear.value.trim())) ||
        arrivalYear.value.trim().length > 4 ||
        arrivalMonth.value.trim().length == 0 ||
        !(isInteger(arrivalMonth.value.trim())) ||
        arrivalMonth.value.trim() < 1 ||
        arrivalMonth.value.trim() > 12 ||
        arrivalDay.value.trim().length == 0 ||
        !(isInteger(arrivalDay.value.trim())) ||
        arrivalDay.value.trim() < 1 ||
        arrivalDay.value.trim() > 31 ||
        !(isInteger(arrivalHour.value.trim())) ||
        arrivalHour.value.trim()<1 ||
        arrivalHour.value.trim()>23 ||
        !(isInteger(arrivalMinute.value.trim())) ||
        arrivalMinute.value.trim()<1 ||
        arrivalMinute.value.trim()>59||
        now<date ||
        validYear(parseInt(arrivalYear.value.trim()), parseInt(arrivalMonth.value.trim()), parseInt(arrivalDay.value.trim())))
    {
        arrivalYear.style.border = redBorder;
        arrivalMonth.style.border = redBorder;
        arrivalDay.style.border = redBorder;
        arrivalHour.style.border = redBorder;
        arrivalMinute.style.border = redBorder;
        erArrivalTime.innerHTML = "неверный формат даты, либо дата меньше текущей";
        erArrivalTime.style.color = "red";
        countNotValid++;
    }else{
        arrivalYear.style.border = noBorder;
        arrivalMonth.style.border = noBorder;
        arrivalDay.style.border = noBorder;
        arrivalHour.style.border = noBorder;
        arrivalMinute.style.border = noBorder;
        erArrivalTime.innerHTML = "";
    }

    var erDepartureTime = document.getElementById('erDepartureTime');

    var now2 = new Date(departureYear.value.trim(), departureMonth.value.trim()-1, departureDay.value.trim(), departureHour.value.trim(), departureMinute.value.trim(), 0, 0);

    if (departureYear.value.trim().length == 0 ||
        !(isInteger(departureYear.value.trim())) ||
        departureYear.value.trim().length > 4 ||
        departureMonth.value.trim().length == 0 ||
        !(isInteger(departureMonth.value.trim())) ||
        departureMonth.value.trim() < 1 ||
        departureMonth.value.trim() > 12 ||
        departureDay.value.trim().length == 0 ||
        !(isInteger(departureDay.value.trim())) ||
        departureDay.value.trim() < 1 ||
        departureDay.value.trim() > 31 ||
        !(isInteger(departureHour.value.trim())) ||
        departureHour.value.trim()<1 ||
        departureHour.value.trim()>23 ||
        !(isInteger(departureMinute.value.trim())) ||
        departureMinute.value.trim()<1 ||
        departureMinute.value.trim()>59||
        now2<date ||
        validYear(parseInt(departureYear.value.trim()), parseInt(departureMonth.value.trim()), parseInt(departureDay.value.trim())))
    {
        departureYear.style.border = redBorder;
        departureMonth.style.border = redBorder;
        departureDay.style.border = redBorder;
        departureHour.style.border = redBorder;
        departureMinute.style.border = redBorder;
        erDepartureTime.innerHTML = "неверный формат даты, либо дата меньше текущей";
        erDepartureTime.style.color = "red";
        countNotValid++;
    }else{
        departureYear.style.border = noBorder;
        departureMonth.style.border = noBorder;
        departureDay.style.border = noBorder;
        departureHour.style.border = noBorder;
        departureMinute.style.border = noBorder;
        erDepartureTime.innerHTML = "";
    }

    var distance = document.getElementById('distance');
    if (!(isInteger(distance.value.trim())) || distance.value.trim().length==0 || distance.value.trim().length>4){

        distance.style.border = redBorder;
        document.getElementById('messageDis').innerHTML = "дистанция указывается целым числом";
        document.getElementById('messageDis').style.color = "red";
        countNotValid++;

    }else {

        distance.style.border = noBorder;
        document.getElementById('messageDis').innerHTML = "";

    }


        return countNotValid == 0;
}

function check(inp)
{

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
function validYear(year, month, day) {

    var fevbool = year%4;
    var result = 0;
    switch (month){
        case 1:
        case 3:
        case 4:
        case 7:
        case 8:
        case 10:
        case 12:
        {
            result = day-31;
            break;
        }
        case 4:
        case 5:
        case 6:
        case 7:
        {
            result = day-30;
            break;
        }
        case 2:
        {
            if (fevbool == 0) result = day-29;
            else if (fevbool > 0) result = day-28;
            break;
        }
    }
    return result>0;

}