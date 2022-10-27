##Funcionamiento JWT
1. Cliente envia una peticion a un servidor(/api/login)
2. Servidor valida username y password, si no son validos devolvera una respuesta 401 unauthorized
3. Servidor valida username y password, si son validas entonces genera token JWT utilizando una secret key
4. Servidor devuelve el token JWT generando el Cliente
5. Cliente envia peticiones a los endpoints Rest del servidor utilizando el token JWT en las cabeceras
6. Servidor valida el token JWT en cada peticion y si es correcto permite el acceso a los datos

* El token se almacena en el Cliente, de manera que consume menos recursos en el Servidor, lo cual permite mejor escalabilidad