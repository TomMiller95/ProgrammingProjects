
<?php
//GLOBAL VARIABLES
$servername = 'localhost'; 		
$username = "id40300_adminmiller";		 
$password = "hyXY&T3fe6tMTuVEjfGF";			
$dbname = "id40300_flockstuff";	
$conn = '';


error_reporting (E_ALL ^ E_NOTICE);
session_start();

$tableNameINPUT = $_POST['a'];

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection   
	if ($conn->connect_error) {
	    die("Connection failed: " . $conn->connect_error);
	}




$sql = "SELECT herdDescription FROM " . $tableNameINPUT;
	$result = $conn->query($sql);
	


	if ($result->num_rows >= 0) {
 	  while($row = $result->fetch_assoc()) {
		print($row["herdDescription"]);
		} 
	}


	$conn->close();

?>