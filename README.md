# Mi Proyecto de Portafolio

Este es un proyecto de Spring Boot desarrollado en Java 11.

encontraras el front de esta aplicacion en el repo -> https://github.com/7honarias/front-profile

## Requisitos Previos

Asegúrate de tener instalado lo siguiente en tu sistema:

- [Java Development Kit (JDK) 11](https://www.oracle.com/java/technologies/javase-downloads.html)

## Configuración del Proyecto

1. Clona este repositorio en tu máquina local:

   ```bash
   git clone git@github.com:7honarias/profile.git
   ```
2. Navega al directorio raíz del proyecto.

3. Abre el proyecto en tu IDE.
4. Ejecuta la clase principal (ProfileApplication)
5. 
   La aplicación se ejecutará en http://localhost:8081 de forma predeterminada.

puedes encontrar una collection de postman en la raiz del proyecto (portfolio.postman_collection.json)

el proyecto cuenta con los sigientes endpoints

```
   create portfolio by id Method POST
   localhost:8081/portfolio/create
   {  
   userName: "",
   githubUserName: "",
   description: "",
   occupation: "",
   }
```
      
```
   update portfolio by id Method PUT
   localhost:8081/portfolio/update/{id}
   {  
   userName: "",
   githubUserName: "",
   description: "",
   occupation: "",
   }
```
      
```
   get all portfolios Method GET
   localhost:8081/portfolio/{id}

```
      
```
   get all portfolios Method GET
   localhost:8081/portfolio/{id}

```
      
```
   get all repositories by githubUserName Method GET
   http://localhost:8081/github/repos/{githubUserName}

```
      
      
#Notas
implemente la api de github para traer el el listado de repositorios publicos que tiene un usuario se debe solicitar el token aqui https://github.com/settings/tokens

documentacion https://docs.github.com/es/rest?apiVersion=2022-11-28
