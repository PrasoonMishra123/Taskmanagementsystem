<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Task Management</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 20px;
        background-color: #f5f5f5;
    }

    h1 {
        margin-bottom: 20px;
        color: #333;
    }

    form {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin-bottom: 30px;
    }

    label {
        font-weight: bold;
        color: #666;
    }

    input[type="text"],
    input[type="date"],
    textarea {
        width: calc(100% - 40px);
        padding: 10px;
        margin-top: 6px;
        margin-bottom: 16px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type="checkbox"] {
        margin-top: 8px;
        margin-bottom: 16px;
    }

    button {
        background-color: #4CAF50;
        color: white;
        padding: 12px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    button:hover {
        background-color: #45a049;
    }

    .message {
        margin-top: 20px;
        padding: 10px;
        background-color: #dff0d8;
        border: 1px solid #3c763d;
        border-radius: 4px;
        color: #3c763d;
    }

    .error {
        background-color: #f2dede;
        border-color: #ebccd1;
        color: #a94442;
    }

    #searchResults,
    #filterResults,
    #createResults,
    #updateResults,
    #taskcResults,
    #deleteResults,
    #create1Results{
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin-bottom: 30px;
    }

</style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>

//Function to validate the create task form
function validateCreateTaskForm() {
    // Example validation (customize as needed)
    var title = $("#title").val();
    var description = $("#description").val();
    var dueDate = $("#dueDate").val();
    var assignUserName = $("#assignUserName").val();

    if (title.trim() === '') {
        showError("Title is required.");
        return false;
    }

    if (description.trim() === '') {
        showError("Description is required.");
        return false;
    }

    if (dueDate.trim() === '') {
        showError("Due date is required.");
        return false;
    }

    if (assignUserName.trim() === '') {
        showError("Assignee name is required.");
        return false;
    }

    // Validation passed
    return true;
}

$(document).ready(function() {
    // Function to create a task
    $("#createTaskForm").submit(function(event) {
        event.preventDefault();
        if (validateCreateTaskForm()) {
            var formData = {
                title: $("#title").val(),
                description: $("#description").val(),
                dueDate: $("#dueDate").val(),
                assignUserName: $("#assignUserName").val()
            };

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/createtask",
                data: JSON.stringify(formData),
                success: function(data) {
                    $("#createTaskForm")[0].reset();
                    $("#createResults").text("Task created successfully!").show();
                },
                error: function(xhr, textStatus, errorThrown) {
                    $("#createResults").text("fields are not filled").show();
                }
            });
        }
    });
});

$(document).ready(function() {
    $("#deleteTaskBtn").click(function() {
        var taskName = $("#taskNameInput").val();

        $.ajax({
            type: "POST",
            url: "/deletetask/"+taskName,
            data: {
                taskName: taskName
            },
            success: function(response) {
                $("#deleteResults").text(response).show();
                $("#deleteResults").text("Task deleted successfully!").show();
            },
            error: function(xhr, status, error) {
                $("#deleteResults").text("Error deleting task: " + xhr.responseText).show();
            }
        });
    });
    // Function to update a task
    $("#updateTaskForm").submit(function(event) {
        event.preventDefault();
        if (validateUpdateTaskForm()) {
            var taskId = $("#updateTaskId").val();
            var updatedTask = {
                title: $("#updateTitle").val(),
                description: $("#updateDescription").val(),
                dueDate: $("#updateDueDate").val(),
                assignUserName: $("#updateAssignUserName").val()
            };

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/update/" + taskId,
                data: JSON.stringify(updatedTask),
                dataType: 'json',
                success: function(data) {
                    $("#updateTaskForm")[0].reset();
                    showMessage("Task updated successfully!", false);
                    $("#updateResults").text("Task updated successfully!").show();
                },
                error: function(xhr, textStatus, errorThrown) {
                    showError("Error updating task: " + errorThrown);
                    $("#updateResults").text("Error creating task: ").show();
                }
            });
        }
    });

   
    // Function to validate update task form
    function validateUpdateTaskForm() {
        var taskId = $("#updateTaskId").val();
        var title = $("#updateTitle").val();
        var assignUserName = $("#updateAssignUserName").val();

        if (!taskId || !title || !assignUserName) {
            showError("Task ID, Title, and Assign User Name are required fields.");
            return false;
        }

        return true;
    }

    // Function to show success or error message
    function showMessage(message, isSuccess) {
        var messageClass = isSuccess ? "message" : "error";
        $("<div></div>")
            .addClass(messageClass)
            .text(message)
            .appendTo("body")
            .fadeOut(4000, function() {
                $(this).remove();
            });
    }

    // Function to show error message
    function showError(message) {
        showMessage(message, false);
    }

    // Function to handle search and filter tasks
    $("#searchForm").submit(function(event) {
        event.preventDefault();
        var searchTerm = $("#searchTerm").val();
        var completed = $("#completed").val();
        var dueDate = $("#dueDateFilter").val();

        $.get("/searchAndFilter", { searchTerm: searchTerm, completed: completed, dueDate: dueDate }, function(tasks) {
            $("#searchResults").empty();
            tasks.forEach(function(task) {
                $("<div></div>")
                    .addClass("task-item")
                    .html("<h3>" + task.title + "</h3><p><strong>Assign User:</strong> " + task.assignUserName + "</p><p><strong>Description:</strong> " + task.description + "</p><p><strong>Due Date:</strong> " + task.dueDate + "</p>")
                    .appendTo("#searchResults");
            });
        });
    });
});

//making task complete and incomplete

$(document).ready(function(){
    $("#completeBtn").click(function(){
        completeTask();
    });

    $("#incompleteBtn").click(function(){
        incompleteTask();
    });
});

function completeTask() {
    var taskInput = $("#taskInput").val();
    $.ajax({
        type: "POST",
        url: "/complete/" + taskInput, // Assuming your backend endpoint is mapped to '/complete/{taskId}'
        success: function(response){
            console.log("Task completed: " + response);
            // You can handle the response here, e.g., show a success message to the user
            $("#taskcResults").text("Task made completed successfully!").show();
        },
        error: function(xhr, status, error) {
            console.error(xhr.responseText);
 
            // You can handle errors here, e.g., show an error message to the user
            $("#taskcResults").text("Error creating task:").show();
        }
    });
}

function incompleteTask() {
    var taskInput = $("#taskInput").val();
    $.ajax({
        type: "POST",
        url: "/incomplete/" + taskInput, // Assuming your backend endpoint is mapped to '/incomplete/{taskId}'
        success: function(response){
            console.log("Task marked incomplete: " + response);
            // You can handle the response here, e.g., show a success message to the user
            $("#taskcResults").text("Task made incomplete successfully!").show();
        },
        error: function(xhr, status, error) {
            console.error(xhr.responseText);
            $("#taskcResults").text("Error to change task:").show();
            // You can handle errors here, e.g., show an error message to the user
        }
    });
}
</script>
</head>
<body>
    <h1>Create Task</h1>
    <form id="createTaskForm">
        <label for="title">Title:</label><br>
        <input type="text" id="title" name="title"><br>
        <label for="description">Description:</label><br>
        <textarea id="description" name="description"></textarea><br>
        <label for="dueDate">Due Date:</label><br>
        <input type="date" id="dueDate" name="dueDate"><br>
        <label for="assignUserName">Assign User Name:</label><br>
        <input type="text" id="assignUserName" name="assignUserName"><br><br>
        <button type="submit">Create Task</button>
    </form>
      <div id="createResults"></div>
     


    <h1>Update Task</h1>
    <form id="updateTaskForm">
        <label for="updateTaskId">Task ID:</label><br>
        <input type="text" id="updateTaskId" name="updateTaskId"><br>
        <label for="updateTitle">Title:</label><br>
        <input type="text" id="updateTitle" name="updateTitle"><br>
        <label for="updateDescription">Description:</label><br>
        <textarea id="updateDescription" name="updateDescription"></textarea><br>
        <label for="updateDueDate">Due Date:</label><br>
        <input type="date" id="updateDueDate" name="updateDueDate"><br>
        <label for="updateAssignUserName">Assign User Name:</label><br>
        <input type="text" id="updateAssignUserName" name="updateAssignUserName"><br><br>
        <button type="submit">Update Task</button>
        <div id="updateResults"></div>
    </form>
    <h1>Make task as complete or incomplete</h1>
         <input type="text" id="taskInput" placeholder="Enter task...">
			<button id="completeBtn">Complete</button>
			<button id="incompleteBtn">Incomplete</button>
			 <div id="taskcResults"></div>  
        

   <h1>Search and Filter Tasks</h1>
        <form id="searchForm">
            <label for="searchTerm">Search Term:</label><br>
            <input type="text" id="searchTerm" name="searchTerm"><br><br>
            <label for="completed">Completed:</label>
            <select id="completed" name="completed">
                <option value="">All</option>
                <option value="true">Completed</option>
                <option value="false">Incomplete</option>
            </select><br>
            <label for="dueDate">Due Date:</label><br>
            <input type="date" id="dueDateFilter" name="dueDate"><br><br>
            <button type="submit">Search & Filter</button>
        </form>
        <div id="searchResults"></div>
        
        <h2>Delete Task</h2>
    <label for="taskNameInput">Task Name:</label>
    <input type="text" id="taskNameInput">
    <button id="deleteTaskBtn">Delete Task</button>
    <div id="deleteResults"></div>
        
        
          
</body>
</html>
