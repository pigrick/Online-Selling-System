function removeCart(productid){
    var temp = "#cart"+productid;
    var jqueryli = $(temp);

    $.ajax({
        type: "POST",
        url: "/order/shoppingcart/remove",
        data: {"productid" : productid},
        success : function(){
            jqueryli.remove();
        }
    });
}