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
$passwordINPUT = $_POST['b'];

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection   
	if ($conn->connect_error) {
	    die("Connection failed: " . $conn->connect_error);
	}




$sql = "SELECT id, username, email, password FROM Users";
	$result = $conn->query($sql);
	

	if ($result->num_rows >= 0) {
 	  while($row = $result->fetch_assoc()) {
		if (strcmp($row["username"],$usernameINPUT)) {
			//For some reason I have to fail this if to get what I want...
		}
                else {
			if (strcmp($row["password"],$passwordINPUT)) {
				//For some reason I have to fail this if to get what I want...
			}
                        else{
                               echo ($row["username"] . $row["password"]);
                        }
		}
	 }
} 
	

	$conn->close();

?>