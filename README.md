## Clean Architecture - IBM Assessment

## How it works:
### **1. Docker. First, you need to install docker**
* Then open the terminal and check:
```bash
docker info
```
or check docker version
```bash
docker -v
```
and docker compose version
```bash
docker-compose -v
```
### **2. Spring boot app**
* Clone the repository:
```bash
git clone https://github.com/ramsantos/ibm.git
```
* Build the maven project:
```bash
./mvnw clean package -DskipTests
```

## Run Spring Boot App & PostgreSQL
* Build and Running the containers:

This command will build the docker containers and start them.
```bash
docker-compose up --build
```

### **Guide for using endpoints the app:**

URL BASE =`http://localhost:8080`

####To include an address stating the TypeEnumAddress.

Enums availables:

`RESIDENCIAL
COMERCIAL
NAO_ESPECIFICADO`

[DOWNLOAD POSTMAN COLLECTION](https://github.com/ramsantos/ibm/blob/master/postman/ibm_postman_requests.json)

* POST request to `/api/cliente` with a object as JSON creates a new "Cliente";
* PUT request to `/api/cliente` with a object as JSON updates the "Cliente" with UUID informed;
* GET request to `/api/cliente?nome=Ricardo&cpf=02404626188` returns the "Cliente" with NAME Ricardo and CPF 02404626188;
* DELETE request to `/api/cliente/0ada8da5-3abb-4c58-942f-7ca2d4fbc7bc` deletes the "Cliente" with UUID "0ada8da5-3abb-4c58-942f-7ca2d4fbc7bc";


### **4. Docker control commands**
* Check all the images you have:
```bash
docker images
```
### **5. End stop app**
*  Stop containers:
```bash
docker-compose down
```
* Remove old stopped containers of docker-compose
```bash
docker-compose rm -f
```



