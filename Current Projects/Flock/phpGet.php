<?php
//GLOBAL VARIABLES
$servername = 'localhost'; 		
$username = "id40300_adminmiller";		 
$password = "hyXY&T3fe6tMTuVEjfGF";			
$dbname = "id40300_flockstuff";	
$conn = '';


error_reporting (E_ALL ^ E_NOTICE);
session_start();
?>

<!DOCTYPE html>
<html>
<head>
	<title>YAY FLOCK</title>
</head>
<body>

<?php showName(); ?>

			<!--============PHP===============-->
<?php


//This will post the users info on their side panel
function showName()
{
	global $servername, $username, $password, $dbname, $conn; 
	$conn = new mysqli($servername, $username, $password, $dbname);

	openConnection();
	$sql = "SELECT id, username, email FROM Users";
	$result = $conn->query($sql);
	

	if ($result->num_rows >= 0) {
 	  while($row = $result->fetch_assoc()) {
		echo "<b>" . "ID: " . $row["id"] . "</b> " . $row["username"] . "<b>" . " Email Address: " . "</b>" .  $row["email"] . "<br>";
	 }
	} 

	closeConnection();
}







//Opens the connection to the database
function openConnection()
{
	global $servername, $username, $password, $dbname, $conn;

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection   
	if ($conn->connect_error) {
	    die("Connection failed: " . $conn->connect_error);
	} 
}
//Closes the connection to the database


function closeConnection()
{
	global $servername, $username, $password, $dbname, $conn;

	$conn->close();
}

?>
		      <!--=========END OF PHP===========-->



</body>
</html>