<?php

$host = "localhost" ;
$user = "atakan";
$password = "";
$db ="atakan_example";

$con = mysqli_connect($host,$user,$password,$db);

if (mysqli_connect_error())
{
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
    //you need to exit the script, if there is an error
    exit();
}

?>
