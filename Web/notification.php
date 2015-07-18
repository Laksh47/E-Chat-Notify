<?php
$con=mysqli_connect("localhost","root","","wichat");
 

if (mysqli_connect_errno())
  
{
  
echo "Failed to connect to MySQL: " . mysqli_connect_error();
  
}
 
$query = "select mfrom,message,time from pmessages order by time;";
$result1 = mysqli_query($con,$query);
$count=1;
echo "<table border=1><tr><th>Sender</th><th>Message</th><th>ChatTime</th></tr>";
while($row = mysqli_fetch_array($result1))

{

	$mfrom=$row['mfrom'];
	$msg=$row['message'];
	$time=$row['time'];
	echo "<td>$mfrom</td>";
	echo "<th>$msg</th>";
	echo "<td>$time</td></tr>";
	$count++;
}
echo "</table><br>";
?>