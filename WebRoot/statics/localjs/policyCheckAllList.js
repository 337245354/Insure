

$(".viewPolicy").on("click",function(){
    var obj = $(this);
    window.location.href="policyview/"+ obj.attr("insuredinfoid");
});


$(".changePolicy").on("click",function(){
    var obj = $(this);
    window.location.href="policymodify/"+ obj.attr("insuredinfoid");
});

$(".approvePolicy").on("click",function(){
    var obj = $(this);
    var status = obj.attr("status");
    if(status == "2" ){//待审核状态下才可以进行审核操作
        window.location.href="approve?pid="+ obj.attr("insuredInfoid");
    }else if(status != "2"){
        alert("该保单的状态为：【"+obj.attr("statusname")+"】,不能进行审核操作！");
    }
});


	
