# play-app-basics
A basic web application built with Play framework.

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
