<?php
require "init.php";

$Username = $_POST["Username"];
$Password = $_POST["Password"];
$Name = $_POST["Name"];
$Email = $_POST["Email"];

$sql = "INSERT INTO Users(Username, Password, Name, Email) VALUES ('$Username','$Password','$Name','$Email')"or die(mysqli_error($con));



if(mysqli_query($con,$sql))
{
echo "Succesfully Registered";
}
else{

  
echo "Error in Registeration ..." . mysqli_error($con);
}
?>
