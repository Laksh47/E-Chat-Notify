<?php
extract($_POST);
$con=mysqli_connect("localhost","root","","wichat");
if (mysqli_connect_errno())
{  
echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$res=mysqli_query($con,"INSERT INTO users VALUES('".$name."','".$uname."','".$pwd."','".$email."');");
if($res!=null)
{
echo("success");
header( 'Location: startpage.html' );
}
else
echo("fail");

mysqli_close($con);
?>