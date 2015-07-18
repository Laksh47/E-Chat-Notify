<?php

extract($_POST);

$con=mysqli_connect("localhost","root","","wichat");


if (mysqli_connect_errno())
  
{
  
echo "Failed to connect to MySQL: " . mysqli_connect_error();
  
}


$res=mysqli_query($con,"INSERT INTO `wichat`.`pmessages` (`mfrom`, `message`, `time`) VALUES ('".$uname."','".$msg."', CURRENT_TIMESTAMP);");

if($res!=null)

$outt=array('result' => 'success');

else
$outt=array('result' => 'fail');

echo(json_encode($outt));

mysqli_close($con);


?>
