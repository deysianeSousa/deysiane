Sequencia para
cadastrar um novo usuário
POST -> https://deysiane.herokuapp.com/user
{ "name" : "Deysiane", "email" : "deysiane.sousa06@hotmail.com", "password" : "teste1234", "phones" : [ { "number" : "98181-3949", "ddd" : "13" } ] }

GET -> https://deysiane.herokuapp.com/user/1
irá retornar o usuário criado

Login
POST -> https://deysiane.herokuapp.com/login
{"email" : "deysiane.sousa06@hotmail.com", "password" : "teste1234"}

A rota "/Home" não esta liberada para acesso sem token jwt para isso é necessário passar o JWT (Token) que gerado no login.

No header (Postman headers) da request com a palavra Bearer
Ex: Authorization -> Bearer Hajssasidisduu123243434.545454656575767ggfdgfdgfdg.qw12123213sdgrtret454545DWDD

Acessar
https://deysiane.herokuapp.com/home

Irá retornar mensagem de sucesso

Pronto Autenticação implementada com sucesso!
