<?php
	extract( $_POST );
	$out="";
	if ( !( $database = mysql_connect( "localhost","root", "","wichat" ) ) )                      
		die( "Could not connect to database </body></html>" );
   
         if ( !mysql_select_db( "wichat", $database ) )
            die( "Could not open course database </body></html>" );

         $res=mysql_query("SELECT fullname,password FROM users WHERE userid='$uname'",$database);

		while($row = mysql_fetch_array($res))
  
		{
  
		   $out=$row['password'];
  
		   $nam=$row['fullname'];
  
		}

         if($out==$pswd)

          {
            echo "$uname logged in";
            mysql_close( $database );
          }
        else
              die( "<br><br><div class='alert alert-error'> Invalid Username or Password</div></body></html>" );	
?>



<?php
extract($_POST);
$con=mysqli_connect("localhost","root","","wichat");
 

if (mysqli_connect_errno())
  
{
  
echo "Failed to connect to MySQL: " . mysqli_connect_error();
  
}
 
$result1 = mysqli_query($con,"SELECT userid FROM users");
echo "<h3>User who are all members of the Wi-forum</h3>";
echo "<table border=1 cellspacing=0 cellpadding=0>";
$count=1;
while($row = mysqli_fetch_array($result1))

{

	$temp=$row['userid'];
	echo "<tr><th>$count</th>";
	echo "<th>$temp";
	echo "</th></tr>";
	$count++;
}
echo "</table><br>";
?>
<form name="notification" action="notification.php" method="post">
<button type="submit" class="btn">View Electronic notifications from faculties</button>
</form>
<form name="chat" action="retrieve.php" method="post">
<input type="hidden" name="u1" value="<?php echo($uname)?>"/>
<input type="hidden" name="pswd" value="<?php echo($pswd)?>" />
<input type="text" name="u2" placeholder="Enter User-name to chat with" />
<input type="submit" value="Retrieve" />
</form>