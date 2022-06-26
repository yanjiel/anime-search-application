# stackFinalVaadin


//to build a war archive, change  packaginug to war   <packaging>war</packaging>
//run the following
$mvn clean package -Pproduction



#############################################

# Cognito Integration. See comments in the application.properties file for locations of data

#############################################

Go to user-pool | App clients | navigate to the second one labelled with ...Web | show details

Auth Flows Configuration
make sure that "Enable lambda trigger ...", "Enable SRP ..."

Security configuration
Prevent User Existence Errors
"Enabled (Recommended)


#############################################

Go to user-pool | App client settings | navigate to the second one labelled with ...Web

Make sure to check Select All and Cognito User Pool.
The Callback URL will be (replace localhost:8080 with beanstalk root url):
http://localhost:8080/login/oauth2/code/cognito

The Sing out URL will be (replace localhost:8080 with beanstalk root url):
http://localhost:8080

OAuth2 2.0
Make sure to check "Authorization code grant", "email", "openid"

#############################################


This project was created from https://start.vaadin.com.

## Running and debugging the application

### Running the application from the command line.
To run from the command line, use `mvn` and open http://localhost:8080 in your browser.

### Running and debugging the application in Intellij IDEA
- Locate the Application.java class in the Project view. It is in the src folder, under the main package's root.
- Right-click on the Application class
- Select "Debug 'Application.main()'" from the list

After the application has started, you can view it at http://localhost:8080/ in your browser. 
You can now also attach breakpoints in code for debugging purposes, by clicking next to a line number in any source file.

### Running and debugging the application in Eclipse
- Locate the Application.java class in the Package Explorer. It is in `src/main/java`, under the main package.
- Right-click on the file and select `Debug As` --> `Java Application`.

Do not worry if the debugger breaks at a `SilentExitException`. This is a Spring Boot feature and happens on every startup.

After the application has started, you can view it at http://localhost:8080/ in your browser.
You can now also attach breakpoints in code for debugging purposes, by clicking next to a line number in any source file.
## Project structure

- `MainView.java` in `src/main/java` contains the navigation setup. It uses [App Layout](https://vaadin.com/components/vaadin-app-layout).
- `views` package in `src/main/java` contains the server-side Java views of your application.
- `views` folder in `frontend/` contains the client-side JavaScript views of your application.

## What next?

[vaadin.com](https://vaadin.com) has lots of material to help you get you started:

- Follow the tutorials in [vaadin.com/tutorials](https://vaadin.com/tutorials). Especially [vaadin.com/tutorials/getting-started-with-flow](https://vaadin.com/tutorials/getting-started-with-flow) is good for getting a grasp of the basic Vaadin concepts.
- Read the documentation in [vaadin.com/docs](https://vaadin.com/docs).
- For a bigger Vaadin application example, check out the Full Stack App starter from [vaadin.com/start](https://vaadin.com/start).
