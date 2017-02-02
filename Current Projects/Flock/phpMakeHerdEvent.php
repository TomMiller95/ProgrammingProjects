<?php
//GLOBAL VARIABLES
$servername = 'localhost'; 		
$username = "id40300_adminmiller";		 
$password = "hyXY&T3fe6tMTuVEjfGF";			
$dbname = "id40300_flockstuff";	
$conn = '';

error_reporting (E_ALL ^ E_NOTICE);
session_start();

$usernameINPUT = (String)$_POST['a'];	//name of event creator
$herdTitleINPUT = (String)$_POST['b'];	//name of herd/table
$eventNameINPUT = (String)$_POST['c'];	//name of event
$eventDateINPUT = (String)$_POST['d'];	//date of event
$eventDescINPUT = (String)$_POST['e'];	//description of event

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection   
	if ($conn->connect_error) {
	    die("Connection failed: " . $conn->connect_error);
	}


//insert initial data
$sql = "INSERT INTO " . $herdTitleINPUT . "(herdEventDescription, herdEventTitle,eventTime) VALUES ('". $eventDescINPUT . "', '". $ownerIDINPUT ."' , '". $eventDateINPUT ."')";
	$result = $conn->query($sql);

if ($conn->query($sql) === TRUE) {
    echo "Info added";
} else {
    echo "Error adding data: " . $conn->error;
}


$conn->close();
?>