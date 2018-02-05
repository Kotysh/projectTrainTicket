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
        "secondStationDate="+json.route.timeDateSecondStation+"&"+
        "price="+json.route.price+"&"+
        "distance="+json.route.distance;
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
        "secondStationDate="+json.train.route.timeDateSecondStation+"&"+
        "price="+json.price;
    document.location.href = "/getplace?"+parametrs;

}

function goBuy(){

    var arrayGender = document.getElementsByName('gender');
    var gender;
    for (var i=0; i<arrayGender.length; i++){
        if (arrayGender[i].checked){
            gender = arrayGender[i].value
            break;
        }
    }

    var arrayDoc = document.getElementsByName('document');
    var doc;
    for (var i=0; i<arrayDoc.length; i++){
        if (arrayDoc[i].checked){
            doc = arrayDoc[i].value;
            break;
        }
    }

    var arrayPlace = document.getElementsByName('place');
    var place;
    for (var i=0; i<arrayPlace.length; i++){
        if (arrayPlace[i].checked){
            place = arrayPlace[i].value;
            break;
        }
    }

    var parameters = "firstName="+document.getElementById('firstName').value+"&"+
        "middleName="+document.getElementById('middleName').value+"&"+
        "lastName="+document.getElementById('lastName').value+"&"+
        "year="+document.getElementById('year').value+"&"+
        "month="+document.getElementById('month').value+"&"+
        "day="+document.getElementById('day').value+"&"+
        "gender="+gender+"&"+
        "document="+doc+"&"+
        "docNumber="+document.getElementById('docNumber').value+"&"+
        "email="+document.getElementById('email').value+"&"+
        "telephone="+document.getElementById('telephone').value+"&"+
        "route="+document.getElementById('route').innerHTML+"&"+
        "numberTrain="+document.getElementById('numberTrain').innerHTML+"&"+
        "orderWagon="+document.getElementById('orderWagon').innerHTML+"&"+
        "firstRouteStation="+document.getElementById('firstRouteStation').innerHTML+"&"+
        "secondRouteStation="+document.getElementById('secondRouteStation').innerHTML+"&"+
        "place="+place+"&"+
        "price="+document.getElementById('price').innerHTML;

    document.location.href = "/buy?"+parameters

}

