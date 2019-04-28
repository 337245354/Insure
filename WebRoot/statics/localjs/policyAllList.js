

$(".viewPolicy").on("click",function(){
    var obj = $(this);
    window.location.href="policyview/"+ obj.attr("insuredinfoid");
});


$(".changePolicy").on("click",function(){
    var obj = $(this);
    window.location.href="policyview/"+ obj.attr("insuredinfoid");
});


	
