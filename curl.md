
- getAll

`curl http://localhost:8080/rest/meals/`

- get

`curl http://localhost:8080/rest/meals/100002`

- delete

`curl -X DELETE http://localhost:8080/rest/meals/100002`

-create

`curl -X POST -H "Content-Type: application/json" -d '{"dateTime": "2018-08-19T10:00","description": "Новый завтрак","calories": 500}' http://localhost:8080/rest/meals`

- put 

`curl --header "Content-Type: application/json"   --request PUT     --data '{"dateTime": "2015-05-30T10:00","description": "Измененный завтрак","calories": 500}' http://localhost:8080/rest/meals/100013`

- getBetween

`curl http://localhost:8080/rest/meals/filter/?startDate=&startTime=&endDate=&endTime=`



