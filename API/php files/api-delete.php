<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: DELETE');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

$bus_no = $_POST['bus_no'];
include "config.php";
$sql = "DELETE FROM buses WHERE bus_no='{$bus_no}'";
if(mysqli_query($conn, $sql)){
	echo "Record Deleted";
}else{
 echo "Record Not Deleted";
} 

?>
