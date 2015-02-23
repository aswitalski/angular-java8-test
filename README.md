# Sky test

### First important thing is a remark is that I did read the instruction with understanding :)

After conversation with Joffrey and me clearly stating that I do not have huge experience with node.js we agreed that I will implement the solution with a different backend stack - Java based.
I went for Java 8 + Spring MVC running on Jetty 9. Because of such stack the build tool is Maven.

I focused on providing clear and fully-unit tested solution on both frontend and backend sides.

### To build the project
    $mvn clean install

### To run the server:
    $mvn jetty:run

### Then the application will be available at:
    http://localhost:8080/index.html

Because of the Java backend, the sign-in in based on sessions (with jsessionid being passed over), an alternative could be based on auth tokens.

### With this solution I tried to demonstrate how to:
  * structure the applications correctly
  * define components with single responsibility
  * integrate the components with one another
  * fully unit test all the components

### If the application was developed further the next steps would be:
  * provide end-to-end testing
  * use directives and templates in the frontend
  * use Spring Security for a security aspect
  * move authentication attempts data gathering to an interceptor


