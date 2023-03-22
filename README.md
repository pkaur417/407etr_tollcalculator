# 407etr_tollcalculator
407etr_tollcalculator

- This is a REST API that calculates toll charges for vehicles traveling on 407ETR by examining the total distance between their entry and exit locations. The application has been developed using Spring Boot and Java programming language.

<h1><b>Prerequisites</b></h1>
<ol>
<li>Require Java1.8 or more to run the project
<li>Eclipse IDE (Eclipse or Intellij)
<li>Postman to post the request 
</ol>

<h1><b>Actual Flow</b></h1>
<ul>
<li>This application runs on the localhost with end point as /tollCalculator.</br>
URL: localhost:8080/tollCalculator
<li>This service accepts the JSON PostRequest to calculate the cost.</br>
The JSON Request required on the POST</br>
{
    "entryPostiton": "Dundas Street",
    "exitPosition": "Appleby Line"
}
<li>The application will firstly fetch the distance associated with the entry and exit location and then use that distance to calculate the cost.
</ul>
<h1><b>Code Flow</b></h1>
<ul>
<li><b>Controller</b>: Controller is the request handler, it will accept the post request and pass it to the service.
<li><b>Service</b>: It will validate the request and calculate the cost with the help of utility.
<li><b>Utility</b>: This is the class which contains the main business logic of calculating the cost and creating a response which is returned to the controller.
  <ul>
   <li>In this clas, the method <b>loadInterChangeMap</b> reads the json file and stores the data in the map (interChangesMap).It is made static, so it will be loaded  only one time.
   <li>In the interChangesMap map, the key is the name of the location, and value it the object which contains rest of the data(routeId, distance, tolId).
   <li>The validation method <b>validateRequest()</b> is validating that the request should not be emplty.
   <li>Using this map, the method calculateTollCost calculates the cost. In this method, two objects of entry and exit locations are created, and using these objects the corresponding distance is fetched.
    <li>Lastly, the using that distance the toll cost is calculated and returned.
  </ul>
</ul>

<h1><b>Running the Application</b></h1>
<ol>
<li>Make a clone of the project using the command.<br>
$ git clone https://github.com/pkaur417/407etr_tollcalculator.git
<li>Import the cloned project in IDE.
<li>Run the application as a springboot application, the TOMCAT will provide the port.
<li>Open the postman, add the URL(localhost:8080/tollCalculator)(NOTE: port can vary) and in the POST method, provide the JSON request as mentioned above.
<li>After clicking the send, the actual response will be displayed.
</ol>
