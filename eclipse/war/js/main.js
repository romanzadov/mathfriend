$(function() {
	$('button').click(attachDraggable);
});

function attachDraggable() {
	$( ".term" ).mousedown(select);
	$("span, .gwt-HTML").sortable({cancel: ".not-sortable"});
}

function select(event) {
	if (event.gotSelection) {
		//already selected
	} else {
		if ($(this).hasClass("selected")) {
			event.gotSelection = true;
		}
		else if (!$(this).parent().hasClass("term") || $(this).parent().hasClass("selected")) {
			$( ".term" ).removeClass("selected");
			$(this).addClass("selected");
			event.gotSelection = true;
		} 	
	}
}
