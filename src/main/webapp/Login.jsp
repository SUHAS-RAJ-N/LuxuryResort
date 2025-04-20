<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">

<!-- Font Awesome -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">

<style>
/* Fullscreen Background */
body, html {
  height: 100%;
  margin: 0;
  font-family: 'Inter', sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* Main Login Card */
.login-container {
  border-radius: 15px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  width: 380px;
  padding: 40px;
  text-align: center;
  transition: all 0.3s ease-in-out;
  border: 1px solid #ddd;  /* Light border for the card */
}

.login-container:hover {
  transform: scale(1.05);
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
}

/* Heading Style */
h2 {
  font-size: 2rem;
  font-weight: 700;
  margin-bottom: 30px;
}

/* Input Group */
.input-group {
  position: relative;
  margin-bottom: 25px;
}

.input-group input {
  width: 100%;
  padding: 15px;
  border-radius: 10px;
  border: 1px solid #ddd;
  font-size: 16px;
  color: #333;
  background: transparent;
  transition: all 0.3s ease;
}

.input-group input::placeholder {
  color: rgba(0, 0, 0, 0.7);
}

.input-group input:focus {
  background: #f1f1f1;
  outline: none;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

/* Password Visibility Toggle */
.toggle-password {
  position: absolute;
  right: 10px;
  top: 12px;
  cursor: pointer;
  font-size: 18px;
}

/* Button Styling */
.btn-primary {
  width: 100%;
  padding: 15px;
  border-radius: 10px;
  border: 1px solid #ddd;
  color: #333;
  font-weight: 600;
  transition: 0.3s ease;
}

.btn-primary:hover {
  transform: translateY(-2px);
}

/* Links */
a {
  color: #333;
  font-weight: 500;
  text-decoration: none;
  transition: color 0.3s;
}

a:hover {
  color: #555;
}

/* Error & Success Messages */
p {
  margin-top: 10px;
}

.text-danger {
  color: red;
}

.text-success {
  color: green;
}
</style>
</head>
<body>

  <div class="login-container">
    <h2>Login</h2>

    <% String logoutMes = (String) session.getAttribute("message"); %>
    <% if (logoutMes != null) { %>
    <p class="text-danger fw-bold"><%= logoutMes %></p>
    <% } %>

    <% String error = (String) session.getAttribute("error"); %>
    <% if (error != null) { %>
    <p class="text-danger fw-bold"><%= error %></p>
    <% } %>

    <% String successMessage = (String) request.getAttribute("successful"); %>
    <% if (successMessage != null) { %>
    <p class="text-success fw-bold"><%= successMessage %></p>
    <% } %>

    <form action="login" method="POST">
      <!-- Email Field -->
      <div class="input-group">
        <input type="email" class="form-control" name="mail" placeholder="Email ID" required>
      </div>

      <!-- Password Field -->
      <div class="input-group">
        <input type="password" class="form-control" id="pw" name="password" placeholder="Password" required>
        <span class="toggle-password" id="togglePw"><i class="fas fa-eye"></i></span>
      </div>

      <button type="submit" class="btn btn-primary">Login</button>
    </form>

    <p>
      Don't have an account? <a href="Signup.jsp">Sign up</a>
    </p>
    <p>
      Forgot your password? <a href="Forgot.jsp">Reset</a>
    </p>
  </div>

  <!-- Bootstrap JS -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

  <!-- Password Toggle Script -->
  <script>
    document.getElementById("togglePw").addEventListener("click", function() {
      let pwField = document.getElementById("pw");
      let type = pwField.type === "password" ? "text" : "password";
      pwField.type = type;
      this.querySelector("i").classList.toggle("fa-eye");
      this.querySelector("i").classList.toggle("fa-eye-slash");
    });
  </script>

</body>
</html>
