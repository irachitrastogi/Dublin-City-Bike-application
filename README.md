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
 
<img>
