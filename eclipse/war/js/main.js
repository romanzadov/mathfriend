$(function() {
	window.setInterval(attachDraggable,100);
	$(document).bind('mousemove', function(e){
		var ghost = $('#ghostContainer');
	    ghost.css({
	       left:  e.pageX + 30,
	       top:   e.pageY - 30
	    });
	});
});

function attachDraggable() {
	$( ".term" ).mouseup(select);
}

var selected;
var lastEvent;
function select(event) {
	if (!$(this).hasClass("selected") && (ghost == null)) {
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
	$("body").mouseup(function(event){
	  clearTimeout(pressTimer);
	  if (ghost != null) {
		  postDrop(ghost.data().id, $(event.target).data().id);
	  }
	  clearGhost();
	  return false;
	}).mousedown(function(){
	  pressTimer = window.setTimeout(function() {
		  makeGhost(term);
	  },300)
	  return false; 
	});
}

function postDrop(ghost, target) {

}

var ghost= null;
function makeGhost(term) {
	$('#ghostContainer').html(term.clone());
	$(".ghosted").removeClass("ghosted");
	term.addClass('ghosted');
	ghost = term;
}
function clearGhost() {
	$('#ghostContainer').html("");
	$(".ghosted").removeClass("ghosted");
	ghost = null;
}
