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
            background: linear-gradient(135deg, #f6f9ff, #e4ecfa);
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .card {
            width: 100%;
            max-width: 450px;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 20px;
            backdrop-filter: blur(15px);
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.1);
            padding: 40px;
            animation: fadeIn 0.7s ease-in-out;
            transition: transform 0.3s ease;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .card:hover {
            transform: translateY(-5px);
        }

        .card h2 {
            text-align: center;
            font-weight: 600;
            color: #2c3e50;
            margin-bottom: 30px;
        }

        .form-label {
            font-weight: 500;
            color: #555;
        }

        .form-control {
            border-radius: 30px;
            padding-left: 45px;
            font-size: 15px;
            height: 45px;
            border: 1px solid #ccc;
            background-color: #fff;
            transition: all 0.2s ease;
        }

        .form-control:focus {
            border-color: #007bff;
            box-shadow: 0 0 0 0.1rem rgba(0,123,255,0.25);
        }

        .input-group {
            position: relative;
            margin-bottom: 20px;
        }

        .input-icon {
            position: absolute;
            left: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #007bff;
            font-size: 18px;
        }

        .btn-primary {
            background: linear-gradient(135deg, #007bff, #0056b3);
            color: white;
            border-radius: 30px;
            font-weight: 500;
            width: 100%;
            height: 45px;
            border: none;
            font-size: 16px;
            transition: background 0.3s ease;
        }

        .btn-primary:hover {
            background: linear-gradient(135deg, #0056b3, #003d80);
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
            color: #e74c3c;
            font-size: 13px;
            margin-top: 5px;
        }

        /* Toast Styles */
        .toast-container {
            position: fixed;
            bottom: 0;
            end: 0;
            z-index: 9999;
        }

        .rounded-circle {
            border: 2px solid #007bff;
        }

        .card-header {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 30px;
        }
    </style>
</head>

<body>
    <div class="card">
        <div class="card-header">
            <img src="resort1.jpg" alt="Logo" class="rounded-circle shadow-sm" style="width: 80px; height: 80px;">
        </div>
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

    <!-- Toast Container -->
    <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 9999">
        <div id="liveToast" class="toast align-items-center text-white bg-danger border-0" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body" id="toastMessage">
                    <!-- Error Message -->
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
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

            <% String errorToast = (String) request.getAttribute("error"); %>
            <% if (errorToast != null) { %>
                $("#toastMessage").text("<%= errorToast %>");
                const toast = new bootstrap.Toast(document.getElementById('liveToast'));
                toast.show();
            <% } %>
        });
    </script>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
