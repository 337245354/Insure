$('.form_date').datetimepicker({
    // language:  'fr',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0
});

$(".showPayMathod").ready(function(){
    var paymentType_Ext=$('input:text[name="paymentType"]').val();
    $("input:radio[name='payment'][value = '"+paymentType_Ext+"']").attr('checked','true');
});

$(".showInsuredPeopleGender").ready(function(){
    var InsuredPeopleGender_Ext=$('input:text[name="insuredPeopleGender"]').val();
    $("input:radio[name='gender'][value = '"+InsuredPeopleGender_Ext+"']").attr('checked','true');
});

$(".showCALineCoverage").ready(function(){
    var carDamage_Ext = $('input:text[name="carDamage"]').val();
    var carFire_Ext = $('input:text[name="carFire"]').val();
    var carGlass_Ext = $('input:text[name="carGlass"]').val();
    $("input:checkbox[name='carDamage'][value = '"+carDamage_Ext+"']").attr('checked','true');
    $("input:checkbox[name='carFire'][value = '"+carFire_Ext+"']").attr('checked','true');
    $("input:checkbox[name='carGlass'][value = '"+carGlass_Ext+"']").attr('checked','true');
});

$(".showGLLineCoverage").ready(function(){
    var peopleAccident_Ext = $('input:text[name="peopleAccident"]').val();
    var peopleIllness_Ext = $('input:text[name="peopleIllness"]').val();
    $("input:checkbox[name='peopleAccident'][value = '"+peopleAccident_Ext+"']").attr('checked','true');
    $("input:checkbox[name='peopleIllness'][value = '"+peopleIllness_Ext+"']").attr('checked','true');
});

$("#back").on("click",function(){
    window.location.href = "./policyBasicInfo";
});