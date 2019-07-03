# Spring Web Application
---
### 1. About

A simple application in a multi-layered architecture that allows you to manage your shopping list. The application provides registering and logging functionalities. On the user page, you can enter a list of your purchases in selected categories, and delete them if necessary. Based on the entered data, various shoppping statistics are calculated and presented in charts and tables. The application frontend content is written in polish language (due to postgraduate studies requirements). Main pages are presented below:

Home page 
![home_page](https://i.imgur.com/01FoDX9.jpg)

User Page
![user_page](https://i.imgur.com/B7KuJci.jpg)
***

### 2. Prerequisities

* JRE 12
* A fully functional database (An open sourced **MySQL DB** was used originally). You can also use in memory database application configuration - it's avilable in the second branch 
***
### 3. Build with

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring boot]() - starting point for building Spring-based applications with minimized upfront configuration of Spring
***
### 4. Main dependencies, libraries, frameworks and technologies:

* [spring boot](https://spring.io/projects/spring-boot) - it's used to build stand-alone and production ready spring applications.
* [spring data jpa](https://spring.io/projects/spring-data-jpa) - part of the larger Spring Data family, deals with enhanced support for JPA based data access layers
* [spring security](https://spring.io/projects/spring-security) - standard for securing Spring-based applications.
* [JSR-303/JSR-349 Bean Validation]() - specification of the Java API for JavaBean validation in Java EE and Java SE, provides an easy   way of ensuring that the properties of JavaBean have the right values in them. Used to validate forms.
* [j2html](https://j2html.com/examples.html) - used for html table structure generation
* [lombok](https://projectlombok.org/) - minimized boilerplate code, used also to generate a logger field
* [javaMail API](https://mvnrepository.com/artifact/javax.mail/mail/1.4.7) - Java API used to send and receive email via SMTP
* [mapStruct](http://mapstruct.org/) - used to map between different object models (entities and DTOs)
* [thymeleaf](https://www.thymeleaf.org/) - server-side Java template engine for processing and creating HTML, XML, JavaScript, CSS
* [javaScript](https://javascript.info/) - programming language of HTML and the Web, scripts run automatically as the page loads
* [html 5](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference) - the latest evolution of the standard that defines HTML, software solution stack that defines the properties and behaviors of web page content by implementing a markup based pattern to it
* [css ](https://developer.mozilla.org/en-US/docs/Web/CSS/Reference) - stylesheet language used to describe the presentation of a document written in HTML

### 5. How to run it

The easiest and fastest way is to run application with in memory database (h2) configuration (available in the 2nd branch). Finally go to http://localhost:8080/ on your browser. If you want to browse the contents of a database, then after starting the application, you can navigate to http://localhost:8080/h2-console which will present you with a login page. On the login page, you’ll need to supply the same credentials as in the application.properties file: login: admin, pass: admin123

![](https://i.imgur.com/NCVyv4Q.jpg)

Once you connect, you’ll see a comprehensive webpage that lists all the tables on the left side of the page and a textbox for running SQL queries, as shown below:

![](https://i.imgur.com/gKL2I6m.jpg)
