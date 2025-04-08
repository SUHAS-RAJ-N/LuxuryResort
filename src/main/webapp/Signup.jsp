<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SignUp Form</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- jQuery Validation -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>

    <style>
        body, html {
            height: 100%;
            margin: 0;
            font-family: 'Poppins', sans-serif;
            background-color: #f9f9f9;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .card {
            width: 100%;
            max-width: 400px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            padding: 35px;
            transition: transform 0.3s;
        }

        .card:hover {
            transform: scale(1.05);
        }

        .card h2 {
            text-align: center;
            font-weight: 600;
            color: #333;
            margin-bottom: 25px;
        }

        .form-label {
            font-weight: 500;
            color: #555;
        }

        .form-control {
            border-radius: 25px;
            padding-left: 40px;
            font-size: 16px;
            height: 45px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
        }

        .input-group-text {
            background-color: transparent;
            border: none;
            color: #999;
            font-size: 18px;
        }

        .input-group {
            position: relative;
            margin-bottom: 20px;
        }

        .input-group .input-icon {
            position: absolute;
            left: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #aaa;
        }

        .btn-primary {
            background-color: #007bff;
            color: white;
            border-radius: 25px;
            font-weight: 500;
            width: 100%;
            height: 45px;
            border: none;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .text-center a {
            font-weight: 500;
            color: #007bff;
            text-decoration: none;
        }

        .text-center a:hover {
            text-decoration: underline;
        }

        .error {
            color: #ff4757;
            font-size: 12px;
            margin-top: 5px;
        }
    </style>
</head>

<body>
    <div class="card">
        <h2>Create an Account</h2>

        <% String successMessage = (String) request.getAttribute("success");
        if (successMessage != null) { %>
            <p class="text-success fw-bold"><%= successMessage %></p>
        <% } %>

        <% String error = (String) request.getAttribute("error");
        if (error != null) { %>
            <p class="text-danger fw-bold"><%= error %></p>
        <% } %>

        <form id="registrationForm" action="signup" method="post">
            <div class="input-group">
                <span class="input-icon"><i class="fa fa-user"></i></span>
                <input type="text" class="form-control" name="name" placeholder="Enter your name" required>
            </div>

            <div class="input-group">
                <span class="input-icon"><i class="fa fa-phone"></i></span>
                <input type="text" class="form-control" name="phone" placeholder="Enter your phone number" required>
            </div>

            <div class="input-group">
                <span class="input-icon"><i class="fa fa-envelope"></i></span>
                <input type="email" class="form-control" name="mail" placeholder="Enter your email" required>
            </div>

            <div class="input-group">
                <span class="input-icon"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" id="pw" name="password" placeholder="Create a password" required>
            </div>

            <div class="input-group">
                <span class="input-icon"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" name="confirmPassword" placeholder="Confirm your password" required>
            </div>

            <button type="submit" class="btn btn-primary">Sign Up</button>
        </form>

        <p class="text-center mt-3">
            Already have an account? <a href="Login.jsp">Login</a>
        </p>
    </div>

    <!-- jQuery Validation Script -->
    <script>
        $(document).ready(function () {
            $("#registrationForm").validate({
                rules: {
                    name: {
                        required: true,
                        minlength: 3
                    },
                    phone: {
                        required: true,
                        digits: true,
                        maxlength: 10
                    },
                    mail: {
                        required: true,
                        email: true
                    },
                    password: {
                        required: true,
                        minlength: 6
                    },
                    confirmPassword: {
                        required: true,
                        equalTo: "#pw"
                    }
                },
                messages: {
                    name: {
                        required: "Please enter your name",
                        minlength: "Name must be at least 3 characters"
                    },
                    phone: {
                        required: "Please enter your phone number",
                        digits: "Only numbers are allowed",
                        maxlength: "Phone number must not exceed 10 digits"
                    },
                    mail: {
                        required: "Please enter your email",
                        email: "Enter a valid email address"
                    },
                    password: {
                        required: "Please enter a password",
                        minlength: "Password must be at least 6 characters"
                    },
                    confirmPassword: {
                        required: "Please confirm your password",
                        equalTo: "Passwords do not match"
                    }
                },
                errorElement: "div",
                errorPlacement: function (error, element) {
                    error.addClass("error");
                    error.insertAfter(element);
                }
            });
        });
    </script>
</body>
</html>
