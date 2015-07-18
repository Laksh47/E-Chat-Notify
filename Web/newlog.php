<?php

extract($_POST);

$con=mysqli_connect("localhost","root","","wichat");


if (mysqli_connect_errno())
  
{
  
echo "Failed to connect to MySQL: " . mysqli_connect_error();
  
}



$result = mysqli_query($con,"SELECT fullname,password FROM users WHERE userid='$val'");


while($row = mysqli_fetch_array($result))
  
{
  
$out=$row['password'];
  
$nam=$row['fullname'];
  
}

if($out==$vall)

$outt=array('result'=>$nam);

else

$outt=array('result' => 'fail');

echo(json_encode($outt));

mysqli_close($con);

?>