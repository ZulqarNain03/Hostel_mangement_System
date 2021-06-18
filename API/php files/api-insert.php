<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');


$bus_no = $_POST['bus_no'];
$driver = $_POST['driver_name'];
$r_from = $_POST['route_from'];
$r_to = $_POST['route_to'];
$seats = $_POST['seats'];


include "config.php";
$sql = "INSERT INTO buses(id,bus_no, driver_name, route_from, route_to, seats) VALUES (NULL, '{$bus_no}', '{$driver}', '{$r_from}', '{$r_to}', '{$seats}')";

if(mysqli_query($conn, $sql)){
print( 'Record Inserted Successfully.');
}
else{
echo 'Record Not Inserted.';
}
?>
