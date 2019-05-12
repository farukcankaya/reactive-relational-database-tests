# reactive-relational-database-tests
Some combinations of frameworks are tested below. You can find implementation details by clicking through title. 
Presentation link is here: https://github.com/farukcankaya/reactive-relational-database-tests/blob/master/reactive-relational-database.pdf

Load test was run with maximum 1000000 users and 100000 hatch rate. Also, minimum user waiting duration was 1000ms and maximum user waiting duration was 2000ms.
You can change this configuration in <a href="https://github.com/farukcankaya/reactive-relational-database-tests/blob/master/load-test.py">load-test.py</a> file.

Follow instructions below to run load test:

### Install Locust
- For python2.7: `python -m pip install locustio`
- For python3: `python3 -m pip install locustio`

### Run load test 
`locust -f load-test.py --host=<host>`

# <a href="https://github.com/farukcankaya/reactive-relational-database-tests/tree/master/blocking-io">Spring Web + JPA (blocking io, thread per request)</a>
- Approx. Memory Usage: 500MB 
- Approx. CPU Usage: 15% 
- It creates thread per request. Threads will increase up to instant request counts. It does not scale.
- Request fail: 0%
- Load test host: `http://localhost:9494/api/jpa`
<img src="https://github.com/farukcankaya/reactive-relational-database-tests/blob/master/blocking-io/blocking-jpa.png" width="100%" />

# <a href="https://github.com/farukcankaya/reactive-relational-database-tests/tree/master/r2dbc">Webflux + R2dbc</a>
- Approx. Memory Usage: 1GB
- Approx. CPU Usage: 30% 
- Approx. Thread count: 30 
- Request fail: 36%
- Load test host: `http://localhost:9595/api/r2dbc`
<img src="https://github.com/farukcankaya/reactive-relational-database-tests/blob/master/r2dbc/r2dbc.png"/>
R2DBC has not have connection pool in this project. It causes some request fails. After adding R2DBC connection pool, it could be better. 
R2DBC Pool repository: https://github.com/r2dbc/r2dbc-pool

# <a href="https://github.com/farukcankaya/reactive-relational-database-tests/tree/master/reactive-pg">Webflux + Reactive PG Client</a>
- Approx. Memory Usage: 500MB
- Approx. CPU Usage: 15% 
- Approx. Thread count: Fixed 30 threads from pool.
- Request fail: 0%
- Load test host: `http://localhost:9696/api/reactive-pg`
<img src="https://github.com/farukcankaya/reactive-relational-database-tests/blob/master/reactive-pg/reactivepg-paralel.png" width="100%" />

# <a href="https://github.com/farukcankaya/reactive-relational-database-tests/tree/master/reactor-blocking-jdbc-and-jpa">Webflux + JDBC and JPA</a>
## JDBC
- Approx. Memory Usage: 2GB
- Approx. CPU Usage: 40% 
- Approx. Thread count: 230
- Request fail: 1%
- In /jdbc endpoint, fixed 200 thread pool is used to open JDBC connection to database. 
- Load test host: `http://localhost:9393/api/jdbc`
<img src="https://github.com/farukcankaya/reactive-relational-database-tests/blob/master/reactor-blocking-jdbc-and-jpa/netty-blocking-jdbc-fixed200threadpool.png" width="100%" />

## JPA
- Approx. Memory Usage: 500MB
- Approx. CPU Usage: 15% 
- Approx. Thread count: 22
- Request fail: 0%
- In /jpa endpoint, it uses NIO threads to open JDBC connection to database.
- Load test host: `http://localhost:9393/api/jpa`
<img src="https://github.com/farukcankaya/reactive-relational-database-tests/blob/master/reactor-blocking-jdbc-and-jpa/netty-jpa.png" width="100%" />
Webflux + JPA looks better but only select query is tested here. JPA gives better result with using caching, lazy loading properties. However, JPA is not work with webflux properly. JPA uses `ThreadLocal` to store some variables but in reactor threads could be changed. To look in details you can checkout the <a href="https://github.com/farukcankaya/reactive-relational-database-tests/tree/feature/city">feature/city</a> branch. JPA throws `org.hibernate.LazyInitializationException` exception when <a href="https://github.com/farukcankaya/reactive-relational-database-tests/blob/feature/city/reactor-blocking-jdbc-and-jpa/src/main/java/com/trendyol/hermes/reactorblockingjdbcandjpa/Controller.java#L41">/api/jpa-city</a> endpoint tries to return City with collection of addresses.

# Conclusion
All tests run in 8-Core 2,9 GHz Intel Core i7, 16 GB 2133 MHz LPDDR3 machine and VisualVM is used to get information from applications. Results could be change based on physical server configurations.
As a result, `Webflux + PgClient` gives best result compares to other implementations(`Spring Web + JPA`, `Webflux + JDBC and JPA`, `Webflux + R2dbc`) for now(10/05/2019).

# References
- https://www.toptal.com/back-end/server-side-io-performance-node-php-java-go 
- https://stackoverflow.com/questions/53244964/why-does-spring-not-provide-reactive-non-blocking-clients-for-relational-datab 
- https://www.kotlindevelopment.com/kotlin-webflux/ 
- https://www.slideshare.net/SpringCentral/the-new-kid-on-the-block-spring-data-jdbc 
- https://jsblog.insiderattack.net/event-loop-and-the-big-picture-nodejs-event-loop-part-1-1cb67a182810 
- https://reactiverse.io 
- https://github.com/r2dbc/r2dbc-postgresql 
- http://www.mybatis.org/mybatis-3/ 
- https://projectreactor.io/
