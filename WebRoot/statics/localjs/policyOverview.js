// $(".showPayMathod").on("click",function() {
$(".showPayMathod").ready(function(){
    var paymentType2=$('input:text[name="paymentType"]').val();
    // var paymentType2 = 'qweee';
    // $("input:radio[name='payment'][value ="paymentType2"]").attr('checked','true');
    $("input:radio[name='payment'][value = '"+paymentType2+"']").attr('checked','true');
    // $("input:radio[name='payment'][value='qwe']").attr('checked','true');
});
