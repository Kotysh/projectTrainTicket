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


function goToPlace(json){

    var parametrs = "idWagon="+json.wagonId+"&"+
        "typeWagon="+json.typeWagon+"&"+
        "bioTiolet="+json.bioTiolet+"&"+
        "airCondition="+json.airCondition+"&"+
        "order="+json.order+"&"+
        "countPlace="+json.countPlace+"&"+
        "idTrain="+json.train.id+"&"+
        "numberTrain="+json.train.numberTrain+"&"+
        "idRoute="+json.train.route.id+"&"+
        "nameRoute="+json.train.route.nameRoute+"&"+
        "firstStationID="+json.train.route.firstStation.id+"&"+
        "firstNameStation="+json.train.route.firstStation.nameStation+"&"+
        "firstStationCityID="+json.train.route.firstStation.city.id+"&"+
        "firstStationCityName="+json.train.route.firstStation.city.nameCity+"&"+
        "secondStationID="+json.train.route.secondStation.id+"&"+
        "secondNameStation="+json.train.route.secondStation.nameStation+"&"+
        "secondStationCityID="+json.train.route.secondStation.city.id+"&"+
        "secondStationCityName="+json.train.route.secondStation.city.nameCity+"&"+
        "firstStationDate="+json.train.route.timeDateFirstStation+"&"+
        "secondStationDate="+json.train.route.timeDateSecondStation;
    document.location.href = "/getplace?"+parametrs;

}

