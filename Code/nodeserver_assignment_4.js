var cors = require('cors');
var express = require('express');
var bodyParser = require('body-parser')
var fs = require('fs');

var app = express()
app.use(cors())
app.use(bodyParser.json())

// loads the csv module
const csv = require('csv'); 
// gets the csv module to access the required functionality
const obj = csv(); 
//Define the dccdublinbikesstationsgpscoords object with parameterized constructor, this will be used for storing the data read from the csv into an array of dccdublinbikesstationsgpscoords.
function dccdublinbikesstationsgpscoords(StationName, Lat, Lon) {
    this.FieldOne = StationName;
    this.FieldTwo = Lat;
    this.FieldThree = Lon;
}; 
// MyData array will contain the data from the CSV file.
var MyData = [];
//Reads the CSV file from the path you specify, and the data is stored in the array we specified using callback function. This function iterates through an array and each line from the CSV file.
obj.from.path('/home/ubuntu/urban/dccdublinbikesstationsgpscoords.csv').to.array(function (data) {
    for (var index = 0; index < data.length; index++) {
        MyData.push(new dccdublinbikesstationsgpscoords(data[index][0], data[index][1], data[index][2]));
    }
    //console.log(MyData[0].FieldOne);
});

function degRad(degrees) {
  return degrees * Math.PI / 180;
}
//function to find the minimum distance between two points
function distanceInKmBetweenEarthCoordinates(Lat1, Lon1, Lat2, Lon2) {
  var erthRadKm = 6369;

  var Lat_d = degRad(Lat2-Lat1);
  var Lon_d = degRad(Lon2-Lon1);

  Lat1 = degRad(Lat1);
  Lat2 = degRad(Lat2);

  var calculation = Math.sin(Lat_d/2) * Math.sin(Lat_d/2) +
          Math.sin(Lon_d/2) * Math.sin(Lon_d/2) * Math.cos(Lat1) * Math.cos(Lat2); 
  var output = 2 * Math.atan2(Math.sqrt(calculation), Math.sqrt(1-calculation)); 
  return erthRadKm * output;
}
//Post request to get the data from the andriod aplication to the node
app.post('/accel',function(req,res){
  write_string= '{"lat":' + req.body.lat + ',"lon":' + req.body.lon + '}';
 //fs.appendFile('rachit.csv',write_string, 'utf8',function(err) { 
  //      if (err) throw err;
  fs.writeFile('/var/www/html/rachit.txt',write_string, 'utf8',function(err) { 
          if (err) throw err;
        });
//function to find the neareast bike station from the given location
var minDistance = 10000;
var minIndex = 0;

for (i = 0 ; i < MyData.length ; i++) {
  distance = distanceInKmBetweenEarthCoordinates(req.body.lat, req.body.lon , MyData[i].FieldTwo , MyData[i].FieldThree);
  if (distance < minDistance) {
    minDistance = distance;
    minIndex = i;
  } 
}
//Storing the mininium distance, Station name, latitude and langitude in the text file 
  write_string_bikeData = '{"minDistance":' + minDistance + ',"StationName":"' + MyData[minIndex].FieldOne + '","StationLat":' + MyData[minIndex].FieldTwo + ',"StationLon":' + MyData[minIndex].FieldThree + '}';
  fs.writeFile('/var/www/html/rachit_bike_data.txt',write_string_bikeData, 'utf8',function(err) { 
          if (err) throw err;
          console.log('logged');
        });
console.log(minDistance, minIndex , MyData[minIndex].FieldOne);

response_string = 'Nearest Dublin bike station is '+  MyData[minIndex].FieldOne;
//console.log(write_string);

//});
  //res.end('1');
res.end(response_string);
});

app.listen(5000);
