<?php
$user = "root";
$pass = "";
$db = "android";

$conn = new mysqli('localhost', $user, $pass, $db);
if ($conn->connect_error) {
  // die("Connection failed: " . $conn->connect_error);
  $output["success"] = false;
  $output["message"] = "Database Connection Failed";
  echo json_encode($output);
}
?>