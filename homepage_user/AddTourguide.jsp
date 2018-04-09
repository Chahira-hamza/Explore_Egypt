<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="styleother.css">
<link href='https://fonts.googleapis.com/css?family=Bungee Inline' rel='stylesheet'>
</head>

<body>
<h1>Add Sites</h1>
<div class="ex">
    <form action = "AddTourist" method="post" enctype="multipart/form-data">
            <table style="with: 50%">
            <tr>
                    <td>Mobile Number</td>
                    <td><input type="number" name="mobile"  required/></td>
            </tr>
            <tr>
                    <td>Email</td>
                    <td><input type="email" name="email"required /></td>
            </tr>
            <tr>
                    <td>Country</td>
                    <td><input type="text" name="country" required/></td>
            </tr>
            <tr>
                    <td>First Language</td>
                    <td><input type="text" name="L1" required/></td>
            </tr>
            <tr>
                    <td>Second Language</td>
                    <td><input type="text" name="L2" /></td>
            </tr>
            <tr>
                    <td>Third Language</td>
                    <td><input type="text" name="L3" /></td>
            </tr>

            </table>

            <button type = "submit" >ADD</button>
        </form>
</div>

</body>
</html>
