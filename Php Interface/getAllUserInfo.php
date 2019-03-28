<?PHP

//Define constants for the mysql database grab
define("DB_HOST", "localhost");
define("DB_USER", "root");
define("DB_PASS", "");
define("DB_NAME", "hack_heist_db");

//Define table names
define("USER_TABLE", "users");

$table = array(array("First_Name","Last_Name","Username","Email","Password","Security_Question","Security_Question_Answer"));
//$table = array(array());


$connection = mysqli_connect(DB_HOST, DB_USER, DB_PASS, DB_NAME) or die ("Error message");

$query = 'SELECT * FROM '.USER_TABLE;
$result = mysqli_query($connection, $query);

while($row = mysqli_fetch_array($result,MYSQLI_ASSOC))
{
	
	$id = $row['ID'];
	$f_name = $row['First_Name'];
	$l_name = $row['Last_Name'];
	$u_name = $row['Username'];
	$email = $row['Email'];
	$password = $row['Password'];
	$SQ = $row['Security_Question'];
	$SQA = $row['Security_Question_Answer'];
				
	$output = array("ID"=>$id, "First_Name"=>$f_name, "Last_Name"=>$l_name, "Username"=>$u_name, "Email"=>$email, "Password"=>$password, "Security_Question"=>$SQ, "Security_Question_Answer"=>$SQA);
	array_push($table, $output);
}

array_shift($table);

header("Content-Type: application/json; charset=UTF-8");
echo json_encode($table, JSON_FORCE_OBJECT);

?>