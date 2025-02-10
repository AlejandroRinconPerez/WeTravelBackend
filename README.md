API Documentation
Login Controller
Register

POST /register
Request Body (CreateUserDTO):

jsonCopy{
  "name": "string",
  "userName": "string", 
  "email": "string",
  "password": "string"
}

Response: Object

Login

POST /login
Query Parameters:

userName (required): string
password (required): string


Response: Object

User Controller
Get All Users

GET /api/users
Response: Array of UsersDTO:

jsonCopy[{
  "name": "string",
  "email": "string",
  "userName": "string",
  "biography": "string",
  "photo": "string",
  "creationDate": "date-time",
  "lastLogin": "date-time",
  "editionDate": "date-time"
}]
Create User

POST /api/users
Request Body (CreateUserDTO):

jsonCopy{
  "name": "string",
  "userName": "string",
  "email": "string",
  "password": "string"
}

Response: UsersDTO

Update User

PUT /api/users/update/{email}
Request Body (Users):

jsonCopy{
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

Response: UsersDTO

Post Controller
Create Post

POST /api/post/{email}
Request Body (CreatePostDTO):

jsonCopy{
  "description": "string",
  "creationDate": "date-time",
  "updatedDate": "date-time",
  "listTag": ["string"],
  "listPhoto": ["string"]
}

Response (ShowPostDTO):

jsonCopy{
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
}
Get All Posts

GET /api/post
Response (Array of CombinePostDTO):

jsonCopy[{
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
Comment Controller
Create Comment

POST /api/comment/{email}/{idPost}
Request Body (Comment):

jsonCopy{
  "idComment": "integer",
  "content": "string",
  "createDate": "date-time",
  "upDatedAt": "date-time",
  "user": {}, // Users object
  "post": {}, // Post object
  "likeList": [], // Array of Likes
  "notification": {} // Notification object
}

Response (CommentDTO):

jsonCopy{
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
}
Like Controller
Like Post Response (LikePostDTO)
jsonCopy{
  "idLike": "integer",
  "idUser": "integer",
  "idPost": "integer",
  "reactionDate": "date-time",
  "userName": "string",
  "userProfilePhoto": "string"
}
Like Comment Response (LikeCommentDTO)
jsonCopy{
  "idLike": "integer",
  "idUser": "integer",
  "idComment": "integer",
  "reactionDate": "date-time",
  "userName": "string",
  "userProfilePhoto": "string"
}
Notification Controller
Notification Response (NotificationDTO)
jsonCopy{
  "idNotification": "integer",
  "email": "string",
  "userPhoto": "string",
  "status": "boolean",
  "name": "string",
  "username": "string",
  "date": "date-time",
  "hora": "date-time",
  "tipo": "string",
  "idFollow": "integer",
  "idLike": "integer",
  "idComment": "integer",
  "emailRecipient": "string",
  "idPost": "integer"
}
Tag Controller
Tag Object
jsonCopy{
  "idTag": "integer",
  "tagContent": "string"
}
TagDTO Response
jsonCopy{
  "content": "string",
  "count": "integer"
}
Search Controller
Search Response (SearchClass)
jsonCopy{
  "usersDTOList": [{
    "name": "string",
    "email": "string",
    "userName": "string",
    "biography": "string",
    "photo": "string",
    "creationDate": "date-time",
    "lastLogin": "date-time",
    "editionDate": "date-time"
  }],
  "combinePostDTOList": [] // Array of CombinePostDTO
}
Photo Controller
PhotoDTOurl Response
jsonCopy{
  "url": "string",
  "idPost": "integer"
}
