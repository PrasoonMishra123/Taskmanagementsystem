<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Authentication</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h2>User Registration</h2>
    <form id="registerForm">
        Username: <input type="text" id="usernameRegister"><br>
        Password: <input type="password" id="passwordRegister"><br>
        <button type="submit">Register</button>
    </form>

    <h2>User Login</h2>
    <form id="loginForm">
        Username: <input type="text" id="usernameLogin"><br>
        Password: <input type="password" id="passwordLogin"><br>
        <button type="submit">Login</button>
    </form>

    <div id="message"></div>

    <script>
        $(document).ready(function () {
            $("#registerForm").submit(function (event) {
                event.preventDefault();
                var username = $("#usernameRegister").val();
                var password = $("#passwordRegister").val();
                $.ajax({
                    type: "POST",
                    url: "/register",
                    data: {
                        username: username,
                        password: password
                    },
                    success: function (response) {
                        $("#message").text(response);
                    }
                });
            });

            $("#loginForm").submit(function (event) {
                event.preventDefault();
                var username = $("#usernameLogin").val();
                var password = $("#passwordLogin").val();
                $.ajax({
                    type: "POST",
                    url: "/login",
                    data: {
                        username: username,
                        password: password
                    },
                    success: function (response) {
                        $("#message").text(response);
                    }
                });
            });
        });
    </script>
</body>
</html>
