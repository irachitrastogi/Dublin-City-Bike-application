# Dublin-City-Bike-application

- This is an application that is using the real time sensor data from our mobile GPS mobile sensor and sinking it to the real time data of Dublin Bikes. 
- Geo sensor help me to get the location of the particular device in the present time, this can then be used with the data of Dublin bikes to get find the nearest location of the docking station. 
- Along with this the application also help in visualizing the data from the google maps as it shows the present location of the person requesting the application and the docking  station. 
- This application let the user to locate and find the nearest bike station in just single tap. The existing application that is there does not have this feature and it was difficult to manually select and find the docking station near us.
- Initially I was using accelerometer as my sensor and I did came up with an approach to find the slope of a given path by using the latitude, longitude as well as the X, Y and Z axis. With this the Maps on the google maps showed whenever the slope of a path was changing. The idea of doing this was supported by a paper that  went through during my research. 
- The paper which I was referring to was : “Road Slope Estimation using a Longitudinal Accelerometer and Kalman Filtering” – by “Tobias Bonnedahl”. 
- I did the implementation to display the slope on the google maps with different colours, but I did not got any open data to integrate this with my application.
- I then had to change my sensor from and then choose to integrate GPS location to get the nearest bike station application.

**Data Fusion and Data Analysis:**
 - The motivation of building this application came from the trouble which I was facing while searching for the nearest bike docking station. I am a consistent user of Dublin bikes and since I am new to Dublin, I always faced this issues. So, I thought of developing a small application to solve this issue. 
- So, for this task I got the real time GPS location from the mobile sensor. This gave me the two values that is the latitude and the longitude. 
- I then moved this to the server and was gathering the data at a csv file. At the server, I got the Dublin bikes information by calling an API from the path : “https://developer.jcdecaux.com/#/login”. 
- This API helped me in gathering all the details of station which included the latitude and the longitude of the bike station as well. 
- Now, I had both the open data and the data from the server. And I had all the flexibility to play with the same as it was on cloud and I gathered it in real time.
- So, what I did was researched to get the closest distance from the location of sensor and the location of the different bike station. 
- I this I was used the “Haversine formula” algorithm to determine the same. This formula ignores the hills that the path fly over. This is the given formula used:
 
**<img width="268" alt="Screenshot 2019-11-22 at 7 59 00 PM" src="https://user-images.githubusercontent.com/22388218/80224089-dbc30a00-8666-11ea-9391-9525b8c6352b.png">**    <img width="244" alt="Screenshot 2019-11-22 at 7 32 16 PM" src="https://user-images.githubusercontent.com/22388218/80224373-3b211a00-8667-11ea-987c-da1ba3edb7e9.png">    <img width="251" alt="Screenshot 2019-11-22 at 7 50 10 PM" src="https://user-images.githubusercontent.com/22388218/80224475-5be96f80-8667-11ea-8bf2-c78ab0f64f07.png">


- This is used to get find the closest bike station that is there from the location shared from the sensor. The algorithms gives us the result.
- Then the closest station is being  gathered after the result of shortest distance.

**The primary and the secondary features used:**

<img width="490" alt="Picture 1" src="https://user-images.githubusercontent.com/22388218/80225506-cc44c080-8668-11ea-80fb-e57cab284a5c.png">


**How to run the code: **

1) To run the application, just download and install the APK(DublinBikes.apk) that is attached in the folder to your mobile application.
2)After installation, please go the the settings and give permission to the application to use the GPS. This is required as we need to share the Latitude and Longitude to the server for further processing. 
3) Find the application by the name ‘’acceleator’ on your phone. 
4) Open the app which will then give you your nearest Dublin bike station.  
5) Click on the button to get the location of the station form your position. 
