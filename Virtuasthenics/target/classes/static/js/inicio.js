/*Sticky Header*/
$(window).scroll(function() {
	var scroll = $(window).scrollTop();
	
	if (scroll >= 100) {
		$(".top-nav").addClass("light-header");
	} else {
		$(".top-nav").removeClass("light-header");
	}
});

/*Script que carga la cabecera con la clase cabeceraContainer*/
<script>
$(document).ready(function() {
	$('.cabeceraContainer').load('cabecera.html');
});
</script>


