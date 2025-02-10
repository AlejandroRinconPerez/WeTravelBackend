# 🏬 WE Travel 


## 🚀 Descripción del Proyecto
TravelNet es una red social diseñada para que los viajeros compartan sus historias, experiencias y consejos sobre sus aventuras alrededor del mundo. Los usuarios pueden interactuar a través de likes, comentarios y seguir a sus viajeros favoritos, creando una comunidad apasionada por los viajes y la exploración.

## Características
- Registro de usuarios: Permite a los viajeros crear cuentas únicas y validadas por el sistema.
- Publicaciones de historias: Los usuarios pueden compartir sus experiencias de viaje a través de posts interactivos.
- Exploración y recomendaciones: Descubre nuevos destinos y experiencias a través de recomendaciones personalizadas.

## Tecnologías Utilizadas
- **Backend**: Java Spring Boot
- **Base de datos**: MySQL
- **Frontend**: Java Script CSS

## Beneficios
- **Estructura organizada**: Posibilidad de crecimiento y expansión según las necesidades del edificio.
- **Diseño limpio e intuitivo**: Interfaz de usuario amigable y fácil de usar.

## Comenzando 🚀

## 🌐 Instrucciones de Instalación


El proyecto estará disponible en GitHub. Puedes obtener una copia del proyecto de dos formas:

1. **Fork del repositorio**:
   - Ve al repositorio del proyecto en GitHub.
   - Haz clic en el botón "Fork" en la esquina superior derecha.
   - Esto creará una copia del repositorio en tu cuenta de GitHub.

2. **Descargar la copia**:
   - Ve al repositorio del proyecto en GitHub.
   - Haz clic en el botón "Code" y selecciona "Download ZIP".
   - Extrae los archivos del ZIP descargado en tu máquina local.

Una vez que tengas una copia del proyecto, puedes seguir las instrucciones específicas de instalación y configuración incluidas en el repositorio.



## Deployment
Mira **Deployment** para conocer cómo desplegar el proyecto.

### Pre-requisitos 📋

_Que cosas necesitas para instalar el software y cómo instalarlas_

- **Java**: Necesitas tener Java instalado en tu máquina. Puedes descargarlo e instalarlo desde [aquí](https://www.oracle.com/java/technologies/javase-downloads.html).
- **Spring Boot Security**: Nuestro proyecto utiliza Spring Boot Security. Asegúrate de seguir la configuración específica en la documentación de Spring Boot.
- **Navegador**: Un navegador web moderno para acceder a la interfaz del proyecto.
- **NetBeans**: Utiliza NetBeans IDE para el desarrollo. Puedes descargarlo desde [aquí](https://netbeans.apache.org/download/index.html).
- **React**: Necesitas tener Node.js y npm instalados para trabajar con React. Puedes descargarlos e instalarlos desde [aquí](https://nodejs.org/).



## Instalación

### Paso 1: Clonar el repositorio
Clona el repositorio del proyecto desde GitHub a tu máquina local.


### Paso 2: Instalar Java
Asegúrate de tener Java instalado en tu máquina. 

### Paso 3: Configurar Spring Boot

Nuestro proyecto utiliza Spring Boot Security.

### Paso 4: Configurar NetBeans

### Paso 5: Instalar Node.jsy npm

### Paso 6: Instalar dependencias de React



## Construido con 🛠️

_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - El framework web usado
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [ROME](https://rometools.github.io/rome/) - Usado para generar RSS
* [Java Spring Boot](https://spring.io/projects/spring-boot) - Framework para el backend
* [MySQL](https://www.mysql.com/) - Sistema de gestión de bases de datos
* [React](https://reactjs.org/) - Librería de JavaScript para construir interfaces de usuario
* [Tailwind CSS](https://tailwindcss.com/) - Framework de CSS para diseño

## Contribuyendo 🖇️
- https://github.com/AlejandroRinconPerez
- https://github.com/laura2ndrea
- https://github.com/JaimePrada11
 
 
 el proceso para enviarnos pull requests.

## Wiki 📖

Puedes encontrar mucho más de cómo utilizar este proyecto en nuestra [Wiki](https://github.com/tu/proyecto/wiki)

## Versionado 📌

Usamos [SemVer](http://semver.org/) para el versionado. Para todas las versiones disponibles, mira los [tags en este repositorio](https://github.com/tu/proyecto/tags).

## Autores ✒️



## Licencia 📄

Este proyecto está bajo la Licencia (Tu Licencia) - mira el archivo [LICENSE.md](LICENSE.md) para detalles

## Expresiones de Gratitud 🎁

* Comenta a otros sobre este proyecto 📢
* Invita una cerveza 🍺 o un café ☕ a alguien del equipo. 
* Da las gracias públicamente 🤓.
* Dona con cripto a esta dirección: `0xf253fc233333078436d111175e5a76a649890000`
* etc.

# Documentación de la API

## Descripción General
Esta API proporciona endpoints para la gestión de usuarios, porteros, personas, empresas, carnets, control de acceso, membresías y facturas en un sistema de gestión de instalaciones.

## URL Base
```
http://localhost:3000
```

## Autenticación
Los detalles de autenticación no están especificados en la documentación de la API.
# API Documentation

## Table of Contents
- [Authentication](#authentication)
- [Users](#users)
- [Posts](#posts)
- [Comments](#comments)
- [Likes](#likes)
- [Follow](#follow)
- [Tags](#tags)
- [Notifications](#notifications)
- [Photos](#photos)
- [Search](#search)
- [Data Models](#data-models)

## Authentication

### Register
```http
POST /register
```
**Request Body**
```json
{
  "name": "string",
  "userName": "string", 
  "email": "string",
  "password": "string"
}
```
**Response**: Object

### Login
```http
POST /login?userName={userName}&password={password}
```
**Response**: Object

## Users

### Get All Users
```http
GET /api/users
```
**Response**: Array of UsersDTO
```json
[{
  "name": "string",
  "email": "string",
  "userName": "string",
  "biography": "string",
  "photo": "string",
  "creationDate": "date-time",
  "lastLogin": "date-time",
  "editionDate": "date-time"
}]
```

### Create User
```http
POST /api/users
```
**Request Body**
```json
{
  "name": "string",
  "userName": "string",
  "email": "string",
  "password": "string"
}
```
**Response**: UsersDTO

### Get User by Email
```http
GET /api/users/{email}
```
**Response**: UsersDTO

### Update User
```http
PUT /api/users/update/{email}
```
**Request Body**
```json
{
  "idUser": "integer",
  "name": "string",
  "userName": "string",
  "email": "string",
  "password": "string",
  "biography": "string",
  "photo": "string",
  "active": "boolean",
  "creationDate": "date-time",
  "lastLogin": "date-time",
  "editionDate": "date-time",
  "followerList": [],
  "followedList": [],
  "postList": [],
  "likeList": [],
  "commentList": [],
  "notifications": []
}
```
**Response**: UsersDTO

### Delete User
```http
DELETE /api/users/{id}
```

### Get User by Username
```http
GET /api/users/username/{userName}
```
**Response**: UsersDTO

### Get Users Not Following
```http
GET /api/users/notfolloging/{email}
```
**Response**: Array of UsersDTO

### Update User Status
```http
PUT /api/users/status/{email}
```
**Response**: UsersDTO

## Posts

### Get All Posts
```http
GET /api/post
```
**Response**: Array of CombinePostDTO
```json
[{
  "showPostDTO": {
    "postid": "integer",
    "creationDate": "date-time",
    "user": {
      "name": "string",
      "email": "string",
      "userName": "string",
      "biography": "string",
      "photo": "string",
      "creationDate": "date-time",
      "lastLogin": "date-time",
      "editionDate": "date-time"
    },
    "updatedDate": "date-time",
    "description": "string"
  },
  "photoDTOurl": [{
    "url": "string",
    "idPost": "integer"
  }],
  "tagDTO": [{
    "idTag": "integer",
    "tagContent": "string"
  }],
  "likePostDTO": [{
    "idLike": "integer",
    "idUser": "integer",
    "idPost": "integer",
    "reactionDate": "date-time",
    "userName": "string",
    "userProfilePhoto": "string"
  }],
  "commentDTO": [{
    "idComment": "integer",
    "content": "string",
    "createDate": "date-time",
    "userName": "string",
    "name": "string",
    "userProfilePhoto": "string",
    "likes": [{
      "idLike": "integer",
      "idUser": "integer",
      "idComment": "integer",
      "reactionDate": "date-time",
      "userName": "string",
      "userProfilePhoto": "string"
    }]
  }]
}]
```

### Create Post
```http
POST /api/post/{email}
```
**Request Body**
```json
{
  "description": "string"

