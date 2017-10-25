<?php 
require "init.php";
$Username = $_POST["Username"];
$Password = $_POST["Password"];
$sql = "SELECT * FROM Users Where Username='$Username' AND Password='$Password';";
$result = $con->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "Login Success";
    }
} else {
    echo "Login Failed";
}
$con->close();
?>
