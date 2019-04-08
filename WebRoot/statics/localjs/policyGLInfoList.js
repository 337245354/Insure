
$(".choiceGender").on("click",function() {
    var gender=$('input:radio[name="gender"]:checked').val();
    gender = parseInt(gender)
    $("#genderhidden").val(gender)
});
