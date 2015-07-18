<?php

extract($_POST);

$con=mysqli_connect("localhost","root","","wichat");


if (mysqli_connect_errno())
  
{
  
echo "Failed to connect to MySQL: " . mysqli_connect_error();
  
}



$result = mysqli_query($con,"SELECT password FROM pusers WHERE userid='$val'");


$out="";
while($row = mysqli_fetch_array($result))
  
{
  
$out=$row['password'];
  
  
}

if($out==$vall)

$outt=array('result'=>'success');

else

$outt=array('result' => 'fail');

echo(json_encode($outt));

mysqli_close($con);

?>