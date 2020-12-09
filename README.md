# Start Spring Boot Application

You can start the spring boot application by running the /scalableweb/src/main/java/com/kopuz/scalableweb/scalableweb/ScalableWebApplication.java

When you execute the class, you will be able to send the left or right side of the files with the specified id and also you can find 
out the difference between left and right side of the files.

# Send Files 

You can send files to system by executing POST Request : 

Post Request :

http://localhost:8100/v1/diff/<ID_OF_THE_FILE>/<SIDE>

Sample Post Request for Right Side:

http://localhost:8100/v1/diff/1454/right

You should send encoded data with the POST request which will decode it inside the application and writes it inside the application.
It's advised to use POSTMAN as sending requests, but you can choose other applications.

In the Body you should send encoded data such like : 

{
    "data" : "SmF2YWNvZGVnZWVrcw=="
}

For the Left Side data, you can use :

Sample POST Request for Left Side: 

http://localhost:8100/v1/diff/1454/left

Do not forget to send the encoded data as specified above.

# Read files and find out if there is any difference between left and right sided files 

The following GET request will read the left and right side of the file and return as offset and length of the differences if there is any

http://localhost:8100/v1/diff/<ID>/

Sample GET Request for Comparing Left and Right Side with the ID 1454 and giving the results : 

http://localhost:8100/v1/diff/1454/

Sample GET Response should be like that:

{
    "equal": false,    
    "equalsize": true,  
    "nonEqualDataList": [     
        {
            "length": 2,
            "offSet": 11
        }
    ] 
   }

equal -> Displays if the Left Side and Right Side are Equal
equalsize -> Displays if the size of the Left Size and Right Size are Equal
nonEqualDataList -> If sided files  they are not equal, displays length and offset of the byte arrays 

# NOTES : 

ID is assigned to 8100 as 8080 PORT is widely used. But you can change it from the appliciation.properties file of the server.port section.
