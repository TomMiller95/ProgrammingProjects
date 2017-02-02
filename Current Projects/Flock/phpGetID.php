<?php
//GLOBAL VARIABLES
$servername = 'localhost'; 		
$username = "id40300_adminmiller";		 
$password = "hyXY&T3fe6tMTuVEjfGF";			
$dbname = "id40300_flockstuff";	
$conn = '';


error_reporting (E_ALL ^ E_NOTICE);
session_start();

$usernameINPUT = $_POST['a'];
$emailINPUT = $_POST['b'];

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection   
	if ($conn->connect_error) {
	    die("Connection failed: " . $conn->connect_error);
	}




$sql = "SELECT username, password, email, id FROM Users";
	$result = $conn->query($sql);
	
$userID = "";

	if ($result->num_rows >= 0) {
 	  while($row = $result->fetch_assoc()) {
		if (strcmp($row["username"],$usernameINPUT))
		{
			//found wrong username
		}
		else 
		{
			if (strcmp($row["email"],$emailINPUT))
			{
				//found wrong password
			}
			else 
			{
				$userID = $row["id"];
			}
		}
	  } 
	}

echo($userID);

	

	$conn->close();

?>