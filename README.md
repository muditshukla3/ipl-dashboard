## Introduction
This is a full stack app built using spring boot and react. It's also using HSQL as an in-memory database to persist the data. During startup application ingest data to database from src/main/resources/match-data.csv using spring-batch.

### Running Docker Image
Use the following command to run this application as docker image.
```
docker run -d --name=ipl-dashboard -p 8080:8080 muditshukla3/ipl-dashboard:1-RELEASE
```

### Running as Kubernetes Deployment
Use the following command to run the application as k8 deployment.
```
kubectl apply -f deployment.yml
```
Access the application on http://localhost:31111. 31111 is the node port exposed vi k8 service component.

Run the following command to delete the deployment and service
```
kubectl delete deployment ipl-dashboard
kubectl delete services ipl-dashboard-nodeport
```
### Running frontend
```
cd src/frontend 
npm install
npm start
```
Make sure to uncomment REACT_APP_API_ROOT_URL property having service url in .env file.

### Running spring boot app
```
mvn spring-boot:run
```

### Package
```
mvn clean install
```
Above command will package the frontend and backend application in a single jar file. Running the jar file e.g. ```java -jar target/ipl-dashboard-0.0.1-SNAPSHOT.jar ``` will run both and frontend and backend. Following maven plugin has been used to package frontend application as part of jar. The plugin does node and npm install and eventually copies the generated frontend artifact into public folder. This makes frontend application available at http://localhost:8080/

```
<groupId>com.github.eirslett</groupId>
<artifactId>frontend-maven-plugin</artifactId>
```


## Application

### Dashboard Page

![Dashboard Page Page](screens/dashboard-page.jpg)

### Team Page

![Team Page Page](screens/team-page.jpg)

### Matches Page

![Matches Page](screens/matches-page.jpg)
