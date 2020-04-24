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
