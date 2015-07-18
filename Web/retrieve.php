<?php 
extract($_POST);
echo "<h3>$u1 chats with $u2</h3>" ;
?>

<form name="post" action="result.php" method="post">
<input type="hidden" name="uname" value="<?php echo($u1)?>"/>
<input type="hidden" name="uname2" value="<?php echo($u2)?>"/>
<textarea name="msg" placeholder="Type to chat" rows="4" cols="40"></textarea>
<button type="submit" class="btn"> Send </button>
</form>

<?php
$con=mysqli_connect("localhost","root","","wichat");
 

if (mysqli_connect_errno())
  
{
  
echo "Failed to connect to MySQL: " . mysqli_connect_error();
  
}
 
$query = "select mfrom,mto,message,time from messages where mfrom='$u1' and mto='$u2' or mfrom='$u2' and mto='$u1' order by time;";
$result1 = mysqli_query($con,$query);
$count=1;
echo "<table border=1><tr><th>Sender</th><th>Message</th><th>ChatTime</th></tr>";
while($row = mysqli_fetch_array($result1))

{

	$mfrom=$row['mfrom'];
	$mto=$row['mto'];
	$msg=$row['message'];
	$time=$row['time'];
	if($mfrom==$u1)
		echo "<tr><td>You have sent:</td>";
	else
		echo "<tr><td>$u2 has replied:</td>";
	echo "<td>$msg</td>";
	echo "<td>$time</td></tr>";
	$count++;
}
echo "</table><br>";
?>

