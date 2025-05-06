Ejecutar la API localmente paso a paso

1. Descargar la extension de Spring boot Dashboard en VS Code .

(OPCION 2)
    1.Descargar Apache Maven desde https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip .
    2.copiar la ruta dentro de la carpeta "bin" de la descarga ej: C:\apache-maven-3.9.9\bin
    3.añadirla como variable de entorno dentro de PATH
    4.reabrir vs code y colocar en la terminal (estando dentro de la ruta del codigo) mvn spring-boot:run
    5. listo ya estamos corriendo el proyecto en local!

2. darle a "run" o en la doble flecha que aparece al lado de APPS
3. listo, estariamos corriendo nuestra api en local mediante el puerto 8080

Consultas de la API

1. Descargar Postman desde https://www.postman.com/downloads/
2. Abrir postman
3. arrastrar el archivo "Ferremas CRUD.postman_collection.json" hacia la aplicacion de postman
4. Listo, ya importaste todos los metodos para consultas de la API, ahora puedes usar al 100% el CRUD de la api de ferremas (en caso que no deje arrastrar, crear/iniciar sesion en postman)
5. la coleccion de postman trae ejemplos y errores de consultas listas para testear.
