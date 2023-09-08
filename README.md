1) build ./mvnw clean package
2) run ./mvnw spring-boot:run

3) demo user with id=1 already created. To create another
   curl -d '{"name":"test"}' -H "Content-Type: application/json" -X POST http://localhost:8080/user

4) to see all endpoints :
   http://localhost:8080/swagger-ui.html

5) to add new measurement:
   curl -d '{ "user": { "id": 1 }, "type" : "COLD_WATER", "createDate": "2000-09-29T01:30:01", "qty": 12345.789}'  \
     -H "Content-Type: application/json" -X POST http://localhost:8080/measurement

6) to search for measurements:
   curl http://localhost:8080/measurement/search?userId=1&type=COLD_WATER&startDate=2000-09-30T01:30:00&endDate=2000-09-30T01:30:02 \
      &page=0&size=5&sort=createDate,asc

   parameters:

   userId: mandatory
   type: optional, values GAS/COLD_WATER/HOT_WATER
   startDate: optional
   endDate: optional
   page: optional, current page number
   size: optional, current page size
   sort: optional, query can be sorted by type/createDate/qty