# Portfolio Java Web Application
A simple portfolio Java web app that displays the profile image, name, some text with the experience and a 5 tweet list of the user’s Twitter timeline.

The backend has been built as an API Rest Service using JSON, the frontend consumes that API in order to get and display the portfolio informmation. The backend implements basic authentication, the frontend sends the corresponding credentials. 

There are three endpoints, for detailed information please check the swagger file 'portfolio-backend/build/api-docs.json':
* GET portfolio/v1/information/{user}, get the portfolio information for an user.
* PUT portfolio/v1/information, create or modify the portfolio information for an user.
* GET portfolio/v1/timeline, get the twitter timeline for an user.

### Software Prerequisites
* Java 11 (or higher).
* A terminal in order to run java command.
* A web browser.

### Steps to build de app
* Clone or download the repository. 
* Using the terminal, go to portfolio-0.0.1-SNAPSHOT.jar location, 
    * You can use the existing jar file in the root of the repository.
    * You can execute 'gradle clean build' to generate a new jar file, it will be located in 'portfolio-backend/build/libs/portfolio-0.0.1-SNAPSHOT.jar'
    * You can run the backend directly from the folder 'portfolio-backend' running the command 'gradle bootRun'.
* Execute 'java -jar portfolio-0.0.1-SNAPSHOT.jar' command. 
* Wait for the message 'Tomcat started on port(s): 8888 (http) with context path' in order to confirm the backend is running up.
* Once the backend is running up:
    * Using a web browser open the html file 'porfolio-frontend/index.html'.
    * Send the necessary parameters by url in order to get dynamic information in the portfolio page.
        * Example: file:///Users/eduard/workspace/portfolio/portfolio-frontend/index.html?user=daenerys&count=5
        * user: The username that you want to get the portfolio information.
        * count: The number of tweets that you want to see in the timeline section. 

### Backend
Technologies used:
* Java.
* Spring Boot.
* Tomcat server embedded.
* Spring Security.
* Spring Data.
* Twitter4j.
* Lombok.
* JUnit.
* PowerMock.
* Swagger.
* Jacoco.

### Frontend
Technologies used:
* Bootstrap.
* HTML 5.
* CSS.
* Javascript.

### Swagger
Swagger has been implemented in order to get a detailed API documentation: 
* Run the command 'gradle clean build' from portfolio-backend folder.
* Now you can get 'portfolio-backend/build/api-docs.json' with the Swagger documentation.  

### Jacoco
Jacoco has been implemented in order to get a coverage report for the backend, to get the report:
* Run the command 'gradle build jacocoTestReport' from portfolio-backend folder.
* Now you can find the coverage report in 'portfolio-backend/build/reports/jacoco/test/html/index.html'.

### Total invested time
20 hours
