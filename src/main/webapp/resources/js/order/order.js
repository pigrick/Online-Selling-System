function updateQuantity(productid) {
    var temp = "#"+productid;
    var jquerytr = $(temp);
    var price = parseFloat(jquerytr.find("#price").text().substring(1).split(",").join(""));
    var updatedquantity = parseInt(jquerytr.find("#cartquantity2").val());
    var totalProductPrice = parseFloat(jquerytr.find("#totalprice").text().substring(1).split(",").join(""));
    var currentTax = parseFloat($("#tax").text().substring(1).split(",").join(""));
    var totalPriceWithTax = parseFloat($("#totalpricewithtax").text().substring(1).split(",").join(""));
    var productIdAndQuantity = {"productid" : productid, "updatedquantity" : updatedquantity};
    $.ajax({
        type : "POST",
        url : "/order/shoppingcart/update",
        data : productIdAndQuantity,
        success : function(){
            jquerytr.find("#quantity").text(updatedquantity);
            var newTotalProductPrice = price * updatedquantity;
            var totalProductPriceDifferent = totalProductPrice - newTotalProductPrice;
            var taxDifference = totalProductPriceDifferent * 0.07;
            var newTax = currentTax - taxDifference;
            var newTotalPriceWithTax = totalPriceWithTax - taxDifference - totalProductPriceDifferent;
            jquerytr.find("#totalprice").text("$" + newTotalProductPrice.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
            $("#tax").text("$" + newTax.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
            $("#totalpricewithtax").text("$" + newTotalPriceWithTax.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
            if(updatedquantity == 0){
                jquerytr.remove();
            }

        }
    })

}