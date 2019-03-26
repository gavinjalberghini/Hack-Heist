<?PHP

//Define constants for the mysql database grab
define("DB_HOST", "localhost");
define("DB_USER", "root");
define("DB_PASS", "");
define("DB_NAME", "hack_heist_db");

//Define table names
define("USER_TABLE", "users");


$incommingContentType = $_SERVER['CONTENT_TYPE'];

//if($incommingContentType != 'application/json'){

//	header($_SERVER['SERVER_PROTOCOL'] . ' 500 Internal Service Error');
//	exit();

//}

$content = trim(file_get_contents("php://input"));
$decodedContent = json_decode($content, true);
$connection = mysqli_connect(DB_HOST, DB_USER, DB_PASS, DB_NAME) or die ("Error message");
$query = 'INSERT INTO '.USER_TABLE.' VALUES ('.$decodedContent["ID"].',\''.$decodedContent["First_Name"].'\',\''.$decodedContent["Last_Name"].'\',\''.$decodedContent["Username"].'\',\''.$decodedContent["Email"].'\',\''.$decodedContent["Password"].'\',\''.$decodedContent["Security_Question"].'\',\''.$decodedContent["Security_Question_Answer"].'\') ';
//echo $query;
$result = mysqli_query($connection, $query);

$returnArr = array(

  "status" => 0,
  "message" => "Success",
  "data" => $decodedContent

);


header("Content-Type: application/json; charset=UTF-8");
echo json_encode($returnArr);

?>