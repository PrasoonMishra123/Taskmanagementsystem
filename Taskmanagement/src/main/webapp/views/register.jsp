<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login and Signup Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f2f2f2;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        input[type="text"],
        input[type="password"],
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: green;
            color: #fff;
            cursor: pointer;
        }
        .error {
            color: red;
            font-size: 12px;
            margin-top: -10px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Signup</h2>
        <form id="signupForm">
            <input type="text" id="signupUsername" placeholder="Username">
            <div id="signupUsernameError" class="error"></div>
            <input type="password" id="signupPassword" placeholder="Password">
            <div id="signupPasswordError" class="error"></div>
            <input type="submit" value="Sign Up">
        </form>
        <hr>
        <h2>Login</h2>
        <form id="loginForm">
            <input type="text" id="loginUsername" placeholder="Username">
            <div id="loginUsernameError" class="error"></div>
            <input type="password" id="loginPassword" placeholder="Password">
            <div id="loginPasswordError" class="error"></div>
            <input type="submit" value="Login">
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
    $(document).ready(function() {
        $('#signupForm').submit(function(event) {
            event.preventDefault();
            var username = $('#signupUsername').val();
            var password = $('#signupPassword').val();
            
            // Validate username and password
            if (username.trim() === '') {
                $('#signupUsernameError').text('Username is required');
                return;
            }
            $('#signupUsernameError').text('');
            
            if (password.trim() === '') {
                $('#signupPasswordError').text('Password is required');
                return;
            }
            $('#signupPasswordError').text('');
            
            // Perform AJAX signup request
            $.ajax({
                type: 'POST',
                url: 'http://localhost:9000/register', 
                contentType: 'application/json', // Set content type to JSON
                data: JSON.stringify({ username: username, password: password }), // Convert data to JSON format
                success: function(response) {
                    alert('Signup successful!');
                },
                error: function(xhr, status, error) {
                    alert('user already exists. Please try again.');
                }
            });
        });

            $('#loginForm').submit(function(event) {
                event.preventDefault();
                var username = $('#loginUsername').val();
                var password = $('#loginPassword').val();
                
                // Validate username and password
                if (username.trim() === '') {
                    $('#loginUsernameError').text('Username is required');
                    return;
                }
                $('#loginUsernameError').text('');
                
                if (password.trim() === '') {
                    $('#loginPasswordError').text('Password is required');
                    return;
                }
                $('#loginPasswordError').text('');
                
                // Perform AJAX login request
               $.ajax({
                    type: 'POST',
                    url: '/login',
                    contentType: 'application/json',
                    data: JSON.stringify({ username: username, password: password }),
                    success: function(response) {
                        alert('Login successful!');
                        // Redirect to dashboard page
                        window.location.href ='/dashboard';
                    },
                    error: function(xhr, status, error) {
                        alert('Login failed. Please try again.');
                    }
                });
            });
        });
    </script>
</body>
</html>
