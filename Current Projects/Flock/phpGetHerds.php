<?php
//GLOBAL VARIABLES
$servername = 'localhost'; 		
$username = "id40300_adminmiller";		 
$password = "hyXY&T3fe6tMTuVEjfGF";			
$dbname = "id40300_flockstuff";	
$conn = '';


error_reporting (E_ALL ^ E_NOTICE);
session_start();

$idINPUT = $_POST['a'];

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection   
	if ($conn->connect_error) {
	    die("Connection failed: " . $conn->connect_error);
	}




$sql = "SELECT id, herds FROM Users";
	$result = $conn->query($sql);
	
$usersHerds = "No herds found";

	if ($result->num_rows >= 0) {
 	  while($row = $result->fetch_assoc()) {
		if ($row["id"] == $idINPUT)
		{
			$usersHerds = $row["herds"];
		}
	  } 
	}

echo($usersHerds);

	

	$conn->close();

?>