$(function() {
	$( ".term" ).mousedown(select);
	$("ul").sortable({cancel: ".not-sortable"});
});


function select(event) {
	$( ".term" ).removeClass("selected");
	$(this).addClass("selected");
}
