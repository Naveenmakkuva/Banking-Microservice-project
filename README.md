A typical banking payment project that was build using microservices involving in various concepts like:
1. Service registry (eureka)
2. API-gateway
3. config server
4. Internal communication (using feign client and Rest calls)
5. resilience4j (circuit breaker fault tolerance)
6. actuater for health and metrics tracking using circuit breaker

Banking project
	1. user service
	2. transaction service
	3. account service

Project description : 
	1. when ever user makes transaction, transactions should be updated.
	2. Account balance should be updated along with transaction.
	3. User should be able to see all transactions that are happened in past.
	4. User should be able to see all accounts linked with his name.
	5. each service should be able to perform basic crud operations like (all, byId, save/add, remove).
