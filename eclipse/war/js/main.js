$(function() {
	window.setInterval(attachDraggable,100);
	$(document).bind('mousemove', function(e){
	    $('#ghostContainer').css({
	       left:  e.pageX,
	       top:   e.pageY + 20
	    });
	});
});

function attachDraggable() {
	$( ".term" ).mouseup(select);
}

var selected;
var lastEvent;
function select(event) {
	if (!$(this).hasClass("selected") && !ghostMade) {
		if (!$(this).parent().hasClass("term") || $(this).parent().hasClass("selected")) {
			if (lastEvent != event.originalEvent) {
				$( ".term" ).removeClass("selected");
				$(this).addClass("selected");
				selected = $(this);
				makeDraggable(selected);
				lastEvent = event.originalEvent;
			}
		} 	
	}
}

function makeDraggable(term) {
	var pressTimer
	$("body").mouseup(function(){
	  clearTimeout(pressTimer);
	  clearGhost();
	  return false;
	}).mousedown(function(){
	  pressTimer = window.setTimeout(function() {
		  makeGhost(term);
	  },300)
	  return false; 
	});
}

var ghostMade = false;
function makeGhost(term) {
	$('#ghostContainer').html(term.clone());
	$(".ghosted").removeClass("ghosted");
	term.addClass('ghosted');
	ghostMade = true;
}
function clearGhost() {
	$('#ghostContainer').html("");
	$(".ghosted").removeClass("ghosted");
	ghostMade = false;
}
