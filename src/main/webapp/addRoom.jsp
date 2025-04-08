<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Room</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Add New Room</h2>
    <form action="addRoom" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label class="form-label">Room Name</label>
            <input type="text" name="name" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Room Type</label>
            <input type="text" name="type" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Price</label>
            <input type="number" name="price" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea name="description" class="form-control" required></textarea>
        </div>
        <div class="mb-3">
            <label class="form-label">Room Image</label>
            <input type="text" name="imageUrl" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Availability</label>
            <select name="availability" class="form-control">
                <option value="true">Available</option>
                <option value="false">Not Available</option>
            </select>
        </div>
        <button type="submit" class="btn btn-success">Add Room</button>
    </form>
</div>
</body>
</html>
