<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods:POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

$password = $_POST['password'];
$email=  $_POST['email'];
$responsed="";
include "config.php";

$query1= "SELECT * from users where email='{$email}' and pswd='{$password}'";
$raw=mysqli_query($conn,$query1);
$count=mysqli_num_rows($raw);
if($count==1){
    $responsed="Succeed";
}
else{
	$responsed="Denied";
}

echo $responsed;
?>
