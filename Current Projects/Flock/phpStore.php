<?php
$servername = 'localhost'; 		
$username = "id40300_adminmiller";		 
$password = "hyXY&T3fe6tMTuVEjfGF";			
$dbname = "id40300_flockstuff";	
$conn = new mysqli($servername, $username, $password, $dbname);

error_reporting (E_ALL ^ E_NOTICE);
session_start();
  
        $user = $_POST['a'];
	$email = $_POST['b'];
        $pass = $_POST['c'];

        if ($conn->connect_error) {
	    die("Connection failed: " . $conn->connect_error);
	} 
        else {echo("Connected\n");}

	$sql = "INSERT INTO Users (username,password,email)
VALUES ('". $user . "', '". $pass ."' , '". $email ."')";

	if ($conn->query($sql) === true)
        {
            echo("yay");
        }
        else {echo("boo");}

	

$conn->close();
?>