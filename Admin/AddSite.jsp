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
		<form action = "AddSites" method="post" enctype="multipart/form-data">
			<table style="with: 50%">
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input type="text" name="address" /></td>
				</tr>
				<tr>
					<td>Description</td>
					<td><input type="text" name="description" /></td>
				</tr>
				<tr>
					<td>Start</td>
					<td><input type="text" name="Stime" /></td>
				</tr>
				<tr>
					<td>Close</td>
					<td><input type="text" name="Etime" /></td>
				</tr>
                    
				<tr>
                    <td> Photo: </td>
                    <td><input type="file" name="photo" size="50"/></td>
                </tr>
			</table>
                    
			<button type = "submit" >ADD</button>
		</form>
	</div>
</body>
</html>
