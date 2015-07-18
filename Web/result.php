<?php
extract($_POST);
echo "$uname sends message to $uname2";
$con=mysqli_connect("localhost","root","","wichat");
 

if (mysqli_connect_errno())
  
{
  
echo "Failed to connect to MySQL: " . mysqli_connect_error();
  
}
 
$query = "INSERT INTO messages(mfrom, mto, message, time) VALUES ('$uname','$uname2','$msg',CURRENT_TIMESTAMP)";
if($result1 = mysqli_query($con,$query))
	echo "<br>message successfully sent";
else
	echo "<br>message delivery failed";
?>