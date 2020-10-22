/**
 * 
 */
$(document).ready(function() {
	
	$(".btn-star").click(function(){
		var id = $(this).closest("div").attr("data-id")
		
		$.ajax({
			url:"/product/add-to-favo/"+id,
			success: function(response) {
				if(response) {
					alert("Đã thêm vào thành công")
				}
				else {
					alert("Đã có sẵn")
				}
			}
		})
	});
	
	
	$(".btn-cart-clear").click(function(){
		$.ajax({
			url:"/cart/clear",
			success: function(response) {
				$("#cart-cnt").html(0)
				$("#cart-amt").html(0)
				$("table>tbody").html("")
			}
		})		
	});
	
	$(".btn-cart-remove").click(function(){
		var id = $(this).closest("tr").attr("data-id")
		$.ajax({
			url:"/cart/remove/"+id,
			success: function(response) {
				$("#cart-cnt").html(response[0])
				$("#cart-amt").html(response[1])
			}
		})		
		$(this).closest("tr").remove();
	});
	
	$("tr[data-id] input").on("input", function(){
		var id = $(this).closest("tr").attr("data-id");
		var price = $(this).closest("tr").attr("data-price");
		var discount = $(this).closest("tr").attr("data-discount");
		var qty = $(this).val();
		$.ajax({
			url:`/cart/update/${id}/${qty}`,
			success: function(response) {
				$("#cart-cnt").html(response[0]),
				$("#cart-amt").html(response[1])
			}
		});
		
		var amt = qty*price*(1-discount);
		$(this).closest("tr").find("td.amt").html(amt);
	})
	
	$(".btn-add-to-cart").click(function(){
		var id = $(this).closest("div").attr("data-id")
		$.ajax({
			url:"/cart/add/"+id,
			success: function(response) {
				$("#cart-cnt").html(response[0])
				$("#cart-amt").html(response[1])
			}
		})	
		;
		var img = $(this).closest(".thumbnail").find("a>img");		
		var cart_css = '.cart-fly{background-image: url("'+img.attr("src")+'");background-size: 100% 100%}'
		$('style#cart_css').html(cart_css)	
		var options = {to:'#cart-img', className:'cart-fly'}	
		img.effect('transfer', options, 1000);
		
	});
	
	$(".btn-open-dialog").click(function(){
		var id = $(this).closest("div").attr("data-id")
		$("#myModal #id").val(id);
	});
	
	
	
	$(".btn-send").click(function(){
		var form = {
			id: $("#myModal #id").val(),
			to: $("#myModal #email").val(),
			body: $("#myModal #comments").val(),
			from: $("#myModal #sender").val()
		}
		
		$.ajax({
			url:"/product/send-to-friend",
			data:form,
			success: function(response) {
				if(response) {
					$("[data-dismiss]").click();
					alert("Đã gửi vào thành công")
				}
				else {
					alert("Lỗi gửi mail")
				}
			}
		})		
	});
	
})

