(function (window) {
	$(document).ready(function() {
		viewAll();
		
		$(".new-todo").on('change', function() {
			createNewTodo();
		});
	});

})(window);

function createNewTodo() {
	var newTodo = $(".new-todo").val();
	var nullCheck = newTodo.replace(/\s|　/gi, '');
	
	if(nullCheck != "") {
		$.ajax({
			url : "/api/newTodoAjax",
	        type : "POST",
	        data : {
	        	"newTodo" : newTodo,
	        },
	        success : function(data){
	            $(".new-todo").val("");
	            viewAll();
	        }
	        ,error : function(data, status, err) {
	        	console.log(err);
	        }
		});
	}
}

function viewAll() {
	$.ajax({
		url : "/api/viewAllAjax",
		type : "GET",
		data : "json",
		success : function(data) {
			$(".filters>li>a").removeClass("selected");
			$(".all").addClass("selected");
			
			listAppend(data);
			countTodo();
		}
		,error : function(data, status, err) {
			console.log(err);
		}
	});
}

function listAppend(data) {
	var list;
	
	for(var i=0; i<data.length; i++) {
		if(data[i].completed == 1) {
			list += "<li class='completed' data-value="+data[i].id+">";
			list += "<div class='view'>";
			list += "<input class='toggle' type='checkbox' checked onclick='changeState("+data[i].id+","+data[i].completed+")'>";
		}
		else {
			list += "<li class='nonCompleted' data-value="+data[i].id+">";
			list += "<div class='view'>";
			list += "<input class='toggle' type='checkbox' onclick='changeState("+data[i].id+","+data[i].completed+")'>";
		}
		
		list += "<label>"+data[i].todo+"</label>";
		list += "<button class='destroy' onclick='deleteTodo("+data[i].id+")'></button>";
		list += "</div>";
		list += "<input class='edit' id="+data[i].id+">";
		list += "</li>";
		
		$(".todo-list").empty();
		$(".todo-list").append(list);
	}
}

function countTodo(){
	var count;
	count = $(".nonCompleted").length;
	
	$(".todo-count").empty();
	$(".todo-count").append("<strong>"+count+"</strong> item left");
}

function changeState(id, completed) {
	// 미완료 상태는 완료 상태로 변경
	if(completed == 0) {
		completed = 1;
	}
	// 완료 상태는 미완료 상태로 변경
	else {
		completed = 0;
	}
	
	var stateData = {};
	stateData["id"] = id;
	stateData["completed"] = completed;
	
	$.ajax({
		url : "/api/changeStateAjax",
		type : "PUT",
		data : JSON.stringify(stateData),
		contentType : "application/json",
		success : function(data) {
			viewAll();
		}
		,error : function(data, status, err) {
			console.log(err);
		}
	});
}

function deleteTodo(id) {
	$.ajax({
		url : "/api/" + id,
        type : "DELETE",
        contentType: "application/json; charset=UTF-8",
        success : function(data){
        	$("*[data-value="+id+"]").remove();
        	countTodo();
        }
        ,error : function(data, status, err) {
        	console.log(err);
        }
	});
}

function deleteCompleted() {
	$.ajax({
		url : "/api/deleteCompletedAjax",
        type : "DELETE",
        data : "json",
        success : function(data){
        	viewAll();
        }
        ,error : function(data, status, err) {
        	console.log(err);
        }
	});
}


$(".filters>li>a").click(function(){
	$(".filters>li>a").removeClass("selected");
	$(this).addClass("selected");
	$(this).each(function(){
		var $this = $(this).text();
	
		if($this == "All"){
			$(".completed").css("display", "block");
			$(".nonCompleted").css("display", "block");
		}else if($this == "Active"){
			$(".nonCompleted").css("display", "block");
			$(".completed").css("display", "none");
		}else{
			$(".completed").css("display", "block");
			$(".nonCompleted").css("display", "none");
		}
	})
	
});
