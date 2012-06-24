$(function() {
	$( ".term" ).mousedown(select);
	$("#list").sortable({cancel: ".not-sortable"});
	$("#parse-button").click(handleClick);
});


function select(event) {
		$( ".term" ).removeClass("selected");
		$(this).addClass("selected");
}

function handleClick() {
	alert(parse($("input").val()));
}

function parse(string) {
	var noWhitespace = string.replace(/\s/g, "");
	
	return noWhitespace;

}