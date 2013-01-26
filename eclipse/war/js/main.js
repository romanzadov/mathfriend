$(function() {
	window.setInterval(setup,100);
	
});

function setup() {
	$('.term').mouseup(select);
	MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
}

var timeoutId = 0;
$('.term').mousedown(function() {
    timeoutId = setTimeout(createDoubleToDrag(term), 500);
}).bind('mouseup mouseleave', function() {
    clearTimeout(timeoutId);
});


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

function createDoubleToDrag(term) {
	var x = $(term).clone();
	$('#draggedTerm').html(x);
	$(document).bind('mousemove', function(e){
	    $('#draggedTerm').css({
	       left:  e.pageX - 20,
	       top:   e.pageY - 180
	    });
	});
}