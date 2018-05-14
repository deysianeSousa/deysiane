Sequencia para
cadastrar um novo usuário
POST -> http://localhost:8080/user
{ "name" : "Deysiane", "email" : "deysiane.sousa06@hotmail.com", "password" : "teste1234", "phones" : [ { "number" : "98181-3949", "ddd" : "13" } ] }

GET -> http://localhost:8080/user/1
irá retornar o usuário criado

Login
POST -> http://localhost:8080/login
{"email" : "deysiane.sousa06@hotmail.com", "password" : "teste1234"}

