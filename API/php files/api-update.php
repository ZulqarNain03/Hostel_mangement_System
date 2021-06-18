<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: PUT');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');


$bus_no = $_POST['bus_no'];
$driver = $_POST['driver_name'];
$r_from = $_POST['route_from'];
$r_to = $_POST['route_to'];
$seats = $_POST['seats'];

include "config.php";
$sql = "UPDATE buses SET bus_no='{$bus_no}', driver_name='{$driver}', route_from ='{$r_from}', route_to='{$r_to}', seats='{$seats}' WHERE bus_no='{$bus_no}'";

if(mysqli_query($conn, $sql)){
	echo "Record Updated";
}else{
  echo "Record Not Updated";
}


?>
