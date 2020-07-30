$(document).ready(main);

var count = 1;

function main(){
	$(".menu").on("click",function(){
		
		if(count == 1){
			$("nav").animate({
				left: "0"
			});
			$("div a:first-child").css({
				display:"none"
			});
			$("div a:last-child").css({
				display:"block"
			});
			
			count = 0;
		}else{
			count = 1
			$("nav").animate({
				left: "-100%"
			});
			
			$("div a:first-child").css({
				display:"block"
			});
			
			$("div a:last-child").css({
				display:"none"
			});
		}
		
	});
        
        $("#inicioSesion").on("click",function (){
            $("#logueo").slideToggle();
        });
        
        $(".usuario").on("click",function (){
            $(this).css("cursor","pointer");
            $("#cerrarSesion").slideToggle();
        });
        
        $("ul.breadcrumb li:last-child").addClass("active");
}



