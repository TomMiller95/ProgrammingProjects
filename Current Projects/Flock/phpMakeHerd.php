<?php
//GLOBAL VARIABLES
$servername = 'localhost'; 		
$username = "id40300_adminmiller";		 
$password = "hyXY&T3fe6tMTuVEjfGF";			
$dbname = "id40300_flockstuff";	
$conn = '';

error_reporting (E_ALL ^ E_NOTICE);
session_start();

$usernameINPUT = (String)$_POST['a'];	//name of herd creator
$ownerIDINPUT = (String)$_POST['b'];	//id of herd creator
$tableNameINPUT = (String)$_POST['c'];	//name of herd
$herdDescriptionINPUT = (String)$_POST['d'];	//description of herd

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection   
	if ($conn->connect_error) {
	    die("Connection failed: " . $conn->connect_error);
	}



// sql to create table
$sql = "CREATE TABLE " . $tableNameINPUT . "(id INT(6), herdOwner VARCHAR(30),ownerId VARCHAR(30),herdDescription VARCHAR(100),herdEventTitle VARCHAR(100), herdEventDescription TEXT(65535),eventDay INT(2), eventMonth INT(2), eventYear INT(4), eventTime VARCHAR(10))";

$result = $conn->query($sql);

if ($conn->query($sql) === TRUE) {
    echo "Table created successfully";
} else {
    echo "Error creating table: " . $conn->error;
}


//insert initial data
$sql = "INSERT INTO " . $tableNameINPUT . "(herdOwner,ownerId,herdDescription) VALUES ('". $usernameINPUT . "', '". $ownerIDINPUT ."' , '". $herdDescriptionINPUT ."')";
	$result = $conn->query($sql);

if ($conn->query($sql) === TRUE) {
    echo "Info added";
} else {
    echo "Error adding data: " . $conn->error;
}


$conn->close();
?>