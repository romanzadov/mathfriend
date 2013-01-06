$(function() {
	window.setInterval(attachDraggable,100);
});

function attachDraggable() {
	$( ".term" ).mouseup(select);
}


var lastEvent;
function select(event) {
	if (!$(this).hasClass("selected")) {
		if (!$(this).parent().hasClass("term") || $(this).parent().hasClass("selected")) {
			if (lastEvent != event.originalEvent) {
				$( ".term" ).removeClass("selected");
				$(this).addClass("selected");
				lastEvent = event.originalEvent;
			}
		} 	
	}
}

//disable select after drag