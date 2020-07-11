const canvas = document.getElementById("canvas");
const ctx = canvas.getContext("2d");
var endpoint_x = 300;
var endpoint_y = 300;
var length, direction, record;
var db = [];

function map() {
  var directions = document.getElementsByName("direction");
  length = parseInt(document.getElementById("length").value, 10);
  direction = getDirection(directions);
  if (direction == undefined || isNaN(length)) {
    document.getElementById("alert").style.color = "#d72323";
    document.getElementById("alert").innerHTML =
      "you must enter length and choose direction";
  } else {
    document.getElementById("alert").innerHTML = "";
    draw(endpoint_x, endpoint_y, length, direction);
    record = {rX: endpoint_x, rY: endpoint_y, rLength: length, rDir: direction};
    db.push(record);
  }
}

function getDirection(dir) {
  for (i = 0; i < dir.length; i++) {
    if (dir[i].checked) {
      return dir[i].value;
    }
  }
}

//draw line with arrow head
function draw(x, y, length, direction) {
  var move;
  var headlen = 5;
  var angle;
  var fromx = x;
  var fromy = y;
  var tox, toy;
  var dx, dy;

  if (direction == "forward") {
    move = y - length;
  }
  if (direction == "backward") {
    move = y + length;
  }

  if (direction == "right") {
    move = x + length;
  }
  if (direction == "left") {
    move = x - length;
  }
  ctx.beginPath();
  ctx.moveTo(x, y);
  if (direction == "forward" || direction == "backward") {
    tox = x;
    toy = move;

    dx = tox - fromx;
    dy = toy - fromy;
    angle = Math.atan2(dy, dx);

    ctx.lineTo(tox, toy);
    ctx.lineTo(
      tox - headlen * Math.cos(angle - Math.PI / 6),
      toy - headlen * Math.sin(angle - Math.PI / 6)
    );
    ctx.moveTo(tox, toy);
    ctx.lineTo(
      tox - headlen * Math.cos(angle + Math.PI / 6),
      toy - headlen * Math.sin(angle + Math.PI / 6)
    );

    ctx.stroke();
    //update endpoint
    this.endpoint_x = x;
    this.endpoint_y = move;
  }
  if (direction == "right" || direction == "left") {
    tox = move;
    toy = y;

    dx = tox - fromx;
    dy = toy - fromy;
    angle = Math.atan2(dy, dx);

    ctx.lineTo(tox, toy);
    ctx.lineTo(
      tox - headlen * Math.cos(angle - Math.PI / 6),
      toy - headlen * Math.sin(angle - Math.PI / 6)
    );
    ctx.moveTo(tox, toy);
    ctx.lineTo(
      tox - headlen * Math.cos(angle + Math.PI / 6),
      toy - headlen * Math.sin(angle + Math.PI / 6)
    );

    ctx.stroke();
    //update endpoint
    this.endpoint_x = move;
    this.endpoint_y = y;
  }
}

function saveToTable() {
  var dbTbody = document.querySelector("#db tbody");
  for (i = 0; i < db.length; i++) {
    var tr = dbTbody.insertRow(i);
    Object.keys(db[i]).forEach((k, j) => {
      // Keys from object represent th.innerHTML
      var cell = tr.insertCell(j);
      cell.innerHTML = db[i][k]; // Assign object values to cells
    });
    dbTbody.appendChild(tr);
    db[i] = "";
  }

  document.getElementById("alert").style.color = "#278ea5";
  document.getElementById("alert").innerHTML = "Saved to db";
}

function deleteAll() {
  db = [];
  document.getElementById("tbodyId").innerHTML = "";
  document.querySelector('input[name="direction"]:checked').checked = false;
  document.getElementById("length").value = "";
  ctx.clearRect(0, 0, 600, 550);
  document.getElementById("alert").innerHTML = "Deleted all records";
}
