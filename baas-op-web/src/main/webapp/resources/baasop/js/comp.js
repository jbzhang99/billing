$(function(){
$(".login-wrapper-cnt-section .user").click(function () {
	$(".login-wrapper-cnt-section .user i").css('border-color', '#0697ef')
	$(".login-wrapper-cnt-section .user i").css('color', '#0697ef')
	$(".login-wrapper-cnt-section .user").css('border-color', '#0697ef')
	$(".login-wrapper-cnt-section .password i").css('border-color', '#666')
	$(".login-wrapper-cnt-section .password i").css('color', '#666')
	$(".login-wrapper-cnt-section .password").css('border-color', '#e4e4e4')
});});


$(function(){
$(".login-wrapper-cnt-section .password").click(function () {
	$(".login-wrapper-cnt-section .password i").css('border-color', '#0697ef')
	$(".login-wrapper-cnt-section .password i").css('color', '#0697ef')
	$(".login-wrapper-cnt-section .password").css('border-color', '#0697ef')
	$(".login-wrapper-cnt-section .user i").css('border-color', '#666')
	$(".login-wrapper-cnt-section .user i").css('color', '#666')
	$(".login-wrapper-cnt-section .user").css('border-color', '#e4e4e4')
});
});


$(function(){
$(".regsiter-wrapper-cnt .user").click(function () {
	$(".regsiter-wrapper-cnt .user").css('border-color', '#0697ef')
	$(".regsiter-wrapper-cnt .password .icon-eye-open").css('color', '#666')
	$(".regsiter-wrapper-cnt .password").css('border-color', '#e4e4e4')
	$(".regsiter-wrapper-cnt .SMSidentifying").css('border-color', '#e4e4e4')
	$(".regsiter-wrapper-cnt .int-xlarge-identifying").css('border-color', '#e4e4e4')
	
});});

$(function(){
$(".regsiter-wrapper-cnt .password").click(function () {
	$(".regsiter-wrapper-cnt .password").css('border-color', '#0697ef')
	$(".regsiter-wrapper-cnt .password .icon-eye-open").css('color', '#0697ef')
	$(".regsiter-wrapper-cnt .user").css('border-color', '#e4e4e4')
	$(".regsiter-wrapper-cnt .SMSidentifying").css('border-color', '#e4e4e4')
	$(".regsiter-wrapper-cnt .int-xlarge-identifying").css('border-color', '#e4e4e4')
});});


$(function(){
$(".regsiter-wrapper-cnt .SMSidentifying").click(function () {
	$(".regsiter-wrapper-cnt .SMSidentifying").css('border-color', '#0697ef')
	$(".regsiter-wrapper-cnt .user").css('border-color', '#e4e4e4')
	$(".regsiter-wrapper-cnt .password .icon-eye-open").css('color', '#666')
	$(".regsiter-wrapper-cnt .password").css('border-color', '#e4e4e4')
	$(".regsiter-wrapper-cnt .int-xlarge-identifying").css('border-color', '#e4e4e4')
});});

$(function(){
$(".regsiter-wrapper-cnt .int-xlarge-identifying").click(function () {
	$(".regsiter-wrapper-cnt .int-xlarge-identifying").css('border-color', '#0697ef')
	$(".regsiter-wrapper-cnt .user").css('border-color', '#e4e4e4')
	$(".regsiter-wrapper-cnt .password .icon-eye-open").css('color', '#666')
	$(".regsiter-wrapper-cnt .password").css('border-color', '#e4e4e4')
	$(".regsiter-wrapper-cnt .SMSidentifying").css('border-color', '#e4e4e4')
});});


$(document).ready(function(){
$(".int-xlarge").click(function () {
   $(".login-wrapper-cnt-section ul .user i").animate({width:'26px',fontSize:'12px',marginTop:'18px'},100);
   $(".login-wrapper-cnt-section ul .password i").animate({width:'43px',fontSize:'20px',marginTop:'14px'},100);
});
});
$(document).ready(function(){
$(".int-xlarge-password").click(function () {
   $(".login-wrapper-cnt-section ul .password i").animate({width:'26px',fontSize:'12px',marginTop:'18px'},100);
   $(".login-wrapper-cnt-section ul .user i").animate({width:'43px',fontSize:'20px',marginTop:'14px'},100);
});
});


$(document).ready(function(){
$(".ctn-a").click(function(){
  $(".ctn-b").show();
  $(this).hide();
});
});

$(function(){
$(".management-tab ul").click(function () {
                $(".management-tab ul").each(function () {
                    $(this).removeClass("current");
                });
                $(this).addClass("current");
            });
 $(".management-tab ul").click(function () {
    var index=$('.management-tab ul').index(this);
      if(index==0){
      $('.cnt-one').show();
      $('.cnt-two').hide();
   }
   if(index==1){
     $('.cnt-one').hide();
     $('.cnt-two').show();
   }
  });
});

$(function(){
$(".table-cnt .stepc").click(function () {
    $(this).parent(".table-cnt").hide(300);
});
});

$(function(){
$(".table-bg-title ul li.right").click(function () {
    $(this).parent().parent().parent().parent(".tab-cnt-table").hide(200);
});
});

$(function(){
$(".Discount ul").click(function () {
                $(".Discount ul").each(function () {
                    $(this).removeClass("current");
                });
                $(this).addClass("current");
            });
			
$(".Discount ul").click(function () {
    if($('.Discount ul').length > 1){
    	var index=$('.Discount ul').index(this);
    	showDiscountCnt(index);
    }
});

function showDiscountCnt(index){
	   if(index==0){
	      $('.Discount-cnt1').show();
	      $('.Discount-cnt2').hide();
		  $('.Discount-cnt3').hide();
		  $('.Discount-cnt4').hide();
		  $('.Discount-cnt5').hide();
	   }
	   if(index==1){
	      $('.Discount-cnt1').hide();
	      $('.Discount-cnt2').show();
		  $('.Discount-cnt3').hide();
		  $('.Discount-cnt4').hide();
		  $('.Discount-cnt5').hide();
	   }
	   if(index==2){
	      $('.Discount-cnt1').hide();
	      $('.Discount-cnt2').hide();
		  $('.Discount-cnt3').show();
		  $('.Discount-cnt4').hide();
		  $('.Discount-cnt5').hide();
	   }
	   if(index==3){
	      $('.Discount-cnt1').hide();
	      $('.Discount-cnt2').hide();
		  $('.Discount-cnt3').hide();
		  $('.Discount-cnt4').show();
		  $('.Discount-cnt5').hide();
	   }
	   if(index==4){
	      $('.Discount-cnt1').hide();
	      $('.Discount-cnt2').hide();
		  $('.Discount-cnt3').hide();
		  $('.Discount-cnt4').hide();
		  $('.Discount-cnt5').show();
	   }
}

});

$(function(){
$(".configure-cnts td img").click(function () {
    $(this).parent().parent().parent().parent(".configure-cnts table").hide(200);
});
});

