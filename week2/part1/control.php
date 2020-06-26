<?php

//connect to db
$conn = mysqli_connect('localhost', 'maryam', 'test12', 'robot_control');

//check connection
if (!$conn){
    echo 'Connection erorr: ' . mysqli_connect_error();
}

//query to retrive latest entry
$sql = 'SELECT * FROM robot_positions ORDER BY id DESC LIMIT 1';

//query and get result
$result = mysqli_query($conn, $sql);

//fetch the result as an array
$position = mysqli_fetch_all($result, MYSQLI_ASSOC);

//free result from mem
mysqli_free_result($result);

//close connection 
mysqli_close($conn);

//print_r($position);


switch($position){
    case $position[0]['right_']=='R': echo "<h1>R</h1>"; break;
    case $position[0]['left_']=='L': echo "<h1>L</h1>"; break;
    case $position[0]['back_']=='B': echo "<h1>B</h1>"; break;
    case $position[0]['forward_']=='F': echo "<h1>F</h1>"; break;
    case $position[0]['stop_']=='S': echo "<h1>S</h1>"; break;
        
}

//refresh every 3 sec
header("Refresh:3");

?>