<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods:POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

$username = $_POST['username'];
$password = $_POST['password'];
$email=  $_POST['email'];
$responsed="";
include "config.php";

$query1= "SELECT * from users where email='{$email}'";
$raw=mysqli_query($conn,$query1);

$count=mysqli_num_rows($raw);
if($count>0){
    $responsed="Already Exist";
}
else{
$sql = "INSERT into users (id,username,email,pswd) Values (NULL,'{$username}', '{$email}', '{$password}')";

if(mysqli_query($conn, $sql)){
	$responsed="Registered";
}
else
{
 $responsed="Failed";
} 
}
echo $responsed;
?>
