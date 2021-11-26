# Read Me First
This is a User Interface for Github Repository Search Application which enable users search for public repository on github. Also, it allows users to check and view list of contributors and their associated commits on a repository.

# Getting Started
The application was compiled with JDK 11. In order to run the jar, simply download the [zip file](https://github.com/Hezdon/frontEndtechHireNg/blob/chidalu.egeonu-dev/githubRepoDetailsUi-0.0.1-SNAPSHOT.zip)  and run the command below and you are good to go - just make sure you have Java
running on your system. The application runs on port 8081 but of course you can change the port in the ***application.properties*** file in the resources folder.
 ```
java -jar githubRepoDetailsUi-0.0.1-SNAPSHOT.zip
```
**Once the app starts running, go to http://localhost:8081/repository/search to search for github repository. Note, this service relies on http://localhost:8080 (Backend Service), therefore, http://localhost:8080 needs to run before http://localhost:8081 can function properly.**.

