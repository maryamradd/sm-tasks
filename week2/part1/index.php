<?php
//connect to db
$conn = mysqli_connect('localhost', 'maryam', 'test12', 'robot_control');

//check connection
if (!$conn){
    echo 'Connection erorr: ' . mysqli_connect_error();
}

//handle button clicks
$var = true;
switch ($var){
  case array_key_exists('forward',$_POST): forward(); break;
  case array_key_exists('left',$_POST): left(); break;
  case array_key_exists('right',$_POST): right(); break;
  case array_key_exists('backward',$_POST): backward(); break;
  case array_key_exists('stop',$_POST): stop(); break;
}

//add to db on buton click 
function forward(){ 
  global $conn;
  $sql = "INSERT INTO robot_positions (right_, left_, forward_, back_, stop_) VALUES (0, 0, 'F', 0, 0)";
  sendQuery($conn, $sql);  
}
function left(){
  global $conn;
  $sql = "INSERT INTO robot_positions (right_, left_, forward_, back_, stop_) VALUES (0, 'L', 0, 0, 0)";
  sendQuery($conn, $sql);  
}
function right(){
  global $conn;
  $sql = "INSERT INTO robot_positions (right_, left_, forward_, back_, stop_) VALUES ('R', 0, 0, 0, 0)";
  sendQuery($conn, $sql);  
}
function backward(){
  global $conn;
  $sql = "INSERT INTO robot_positions (right_, left_, forward_, back_, stop_) VALUES (0, 0, 0, 'B', 0)";
  sendQuery($conn, $sql);  
}
function stop(){
  global $conn;
  $sql = "INSERT INTO robot_positions (right_, left_, forward_, back_, stop_) VALUES (0, 0, 0, 0, 'S')";
  sendQuery($conn, $sql);  
}
   

function sendQuery($conection, $sql){
  if (mysqli_query($conection, $sql)){
    //echo 'successfully moved';
  } else {
    echo 'query error: ' . mysqli_error($conn);
  }
}

?>



<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Part 1</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles.css" />
  </head>
  <body>
    <h1>Part 1</h1>
    <h3>objective: on button click store movement in database and update control.php</h3>
    <div class="container">
      
      <div class="item" id="item1">
        <h4>Control panel</h4>
        <form method="post">
          <input class="button" type="submit" name="forward" id="forward" value="Forward" /><br/>
          <div>
            <input class="button" type="submit" name="left" id="left" value="Left" />
            <input class="button" style="color: red;" type="submit" name="stop" id="stop" value="STOP" />
            <input class="button" type="submit" name="right" id="right" value="Right" />
          </div>   
          <input class="button" type="submit" name="backward" id="backward" value="Backward" /><br/>
        </form>
      </div>

      <div class="item" id="item2">
        <h4>control.php screenshot after clicking forward</h4>
        <img src="./images/control.jpg" alt="control.php screentshot">
      </div>

      <div class="item">
        <h4>Database screenshot</h4>
          <img src="./images/db.jpg" alt="database screentshot">
          <img src="./images/db2.jpg" alt="database screentshot">
      </div>
    </div>
  </body>
</html>
