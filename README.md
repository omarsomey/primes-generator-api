# primes-generator-api

Primes Generator API implemented using Spring Boot maven

This API compute the primes between a range given by 3 different strategies:

1. BruteForce strategy

2. Sieve of Eratosthenes

3. Sieve of Sundaram

Then it records each execution in a H2 database (In memory Database) including  timestamp, range, time elapsed, algorithm chosen and number of primes returned.

## How to run

* Run the project using the following command line inside primegeneratorapi directory :

   `mvn spring-boot:run`
   
## REST APIs Endpoint

**getPrimes**
----
 
 Returns a list of the primes in the range and a HttpStatus.

* **URL**

  `/getPrimes?method=:method&rLeft=:rLeft&rRight=:rRight`

* **getPrimes**
  
  gets the range and the strategy.

  `GET` 
  
*  **URL Params**

  * method=[string]
  
    example: method=BRUTEFORCE or method=SIEVEERA or method=SIEVESUN
  
  * rLeft=[integer]
  
    example: rLeft=2
  
  * rRight=[integer]
  
    example: rRight=100

  
* **Success Response:**
  

  * **Code:** 200 <br />
    **Content:** `List of Integers}`
 
* **Error Response:**

  * **Code:** 400 BADREQUEST <br />
    **Content:** `{ empty List }`
    
 * **Sample Call:**

    `curl http://localhost:8080/primegeneratorapi/v1/getPrimes?method=BRUTEFORCE&rLeft=5&rRight=100000`
 

* **Notes:**

    root Url:  `http://localhost:8080/primegeneratorapi/v1/`
    
## Database

  **Credentials**

   We are using H2 Database for recording the executions.

   You could set the credentials in the `src/resources/application.properties file.`

   The default Credentials are : 

   * URL: `jdbc:h2:mem:simscaledb`

   * username: `sa`

   * password: `""`

  **Visualize the data**

   To visualize the data enter the following URL and then enter the credentials.
   
   * Url : http://localhost:8080/h2-console/
   
   Execute SQL queries to see the table PRIMES_LOGGER.
   
   example: `SELECT * FROM PRIMES_LOGGER`
  
