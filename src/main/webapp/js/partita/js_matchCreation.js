//Setting min of #creation-data
function minDate() {
    var currentDate = new Date();
    var dd = String(currentDate.getDate()).padStart(2, '0');
    var mm = String(currentDate.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = currentDate.getFullYear();

    currentDate = yyyy + '-' + mm + '-' + dd;
    $('#creation-data').attr("min",currentDate);
}
//Setting max of #creation-data
function maxDate(){
    var currentDate = new Date();
    var dd = String(currentDate.getDate()).padStart(2, '0');
    var mm = String(currentDate.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = currentDate.getFullYear();
    currentDate = yyyy + '-' + mm + '-' + dd;

    var maxDate = new Date(currentDate.getFullYear()+1,currentDate.getMonth(),currentDate.getDate());
    $('#creation-data').attr("max",maxDate);
}
$(document).ready( function(){
        minDate()
        maxDate();
    }
);

