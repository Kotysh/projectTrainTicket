/**
 * Created by Дмитрий on 15.01.2018.
 */
function goToTrain(json){

    var parametrs = "idTrain="+json.id+"&"+
        "numberTrain="+json.numberTrain+"&"+
        "idRoute="+json.route.id+"&"+
        "nameRoute="+json.route.nameRoute+"&"+
        "firstStationID="+json.route.firstStation.id+"&"+
        "firstNameStation="+json.route.firstStation.nameStation+"&"+
        "firstStationCityID="+json.route.firstStation.city.id+"&"+
        "firstStationCityName="+json.route.firstStation.city.nameCity+"&"+
        "secondStationID="+json.route.secondStation.id+"&"+
        "secondNameStation="+json.route.secondStation.nameStation+"&"+
        "secondStationCityID="+json.route.secondStation.city.id+"&"+
        "secondStationCityName="+json.route.secondStation.city.nameCity+"&"+
        "firstStationDate="+json.route.timeDateFirstStation+"&"+
        "secondStationDate="+json.route.timeDateSecondStation;
    document.location.href = "/gettrain?"+parametrs;

}

