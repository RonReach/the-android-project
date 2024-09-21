<?PHP
if (isset($_GET['api_key']) && $_GET['api_key'] == "roth") {
    include_once("connection.php");
    $sql = "SELECT * FROM tbl_product";
    $records = array();
    if ($result = $conn->query($sql)) {
        while ($row = $result->fetch_array(MYSQLI_ASSOC)) {
            $records[] = $row;
        }
        $output["success"] = true;
        $output["records"] = $records;
        echo json_encode($output);
    } else {
        $output["success"] = false;
        $output["message"] = "Error SQL";
        echo json_encode($output);
    }
} else {
    $output["success"] = false;
    $output["message"] = "Invalid API key";
    echo json_encode($output);
}
