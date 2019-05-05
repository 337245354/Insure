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

$(".viewPolicy").on("click",function(){
    var obj = $(this);
    var insuredinfoid=document.getElementById("insuredinfoid").value
    window.location.href="policyview/"+ insuredinfoid;

});


$(".choicePayMathod").on("click",function() {
    var paymentType=$('input:radio[name="payment"]:checked').val();
    paymentType = parseInt(paymentType)
    $("#paymethodhidden").val(paymentType)
});

$("#back").on("click",function(){
    window.location.href = "policyCAInfo";
});