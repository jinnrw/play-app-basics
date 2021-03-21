# play-app-basics
A basic web application built with Play framework.  
See the demo in [Heroku](https://play-app-basics.herokuapp.com/).

## Routes
- `/` -> Home page
- `/ping` -> Check status
- `/names/all` -> Show all names
- `/names/:id` -> Show specific name
- `/post` -> Add name

## Controllers
- HomeController
  - index()
  - ping()
  - getAllName()
  - getName(id:Long)
  - addName()

## Models
- Name: `final case class Name(id: String, name: String)`

## Documentation
My notes while learning how to deploy were documented here:
- [Play Framework: deploy to Heroku](https://app.gitbook.com/@jinn-wang/s/web-development/scala-java/play-framework)
