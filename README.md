# student-management-system

This is my project work for the course title: Tools and Methods of Software Engineering on Master studies @ the Department of Software Engineering and Computer Science, Faculty of Organisational Sciences, UNIVERSITY OF BELGRADE, SERBIA. 
It also represents my first project experience fully implemented using Clojure Programming Language.

## Summary of README:

1. About - Use of the application
2. Database - Connection parameters
3. Application running
4. Tools and libraries used for project development
5. References



### 1. About - Use of the application

For this subject, my choice was to build an application for dance school which I am member of. In this school I dance Cuban salsa named Timba, but there are several more dances. There are several dancing groups for every dance and each of them has one instructor that teaches students/dancers particular dance.

For dance school, there is no need for complex system, so I develop simple CRUD application.



### 2. Database - Connection parameters

I use mysql - Dbeaver for data storage and manipulation. I created database *dancedb* and attach DDL in project.

* classname:   "com.mysql.cj.jdbc.Driver"
* subprotocol: "mysql"
* subname:     "//localhost:3306/dancedb"
* user:        "root"
* password:    "password"



### 3. Application running

* Installing dependecies from terminal:

```sh
lein deps
```

* Starting the application from terminal:

```sh
lein run
```
http://localhost:3000/swagger
* Testing the application from browser:

****

![Swagger start view](/resources/swagger-start-view.png)

* There are several tests in project that I used in the process of development, and you could run it from terminal:

```sh
lein test
```

![Lein test](/resources/lein-test.png)

### 4. Tools and libraries used for project development

[Compojure-api](https://github.com/metosin/compojure-api) is a Clojure web api library.

[Toucan](https://github.com/metabase/toucan) provides the better parts of an ORM for Clojure, like simple DB queries, flexible custom behavior when inserting or retrieving objects.

[buddy-hashers](https://funcool.github.io/buddy-hashers/latest) is a collection of secure password hashers for Clojure.

[Schema](https://github.com/plumatic/schema) is a Clojure(Script) data structure describing a data shape, which can be used to document and validate functions and data.



### 5. References

* Learning Clojure at [Clojure for the brave and true](https://www.braveclojure.com/clojure-for-the-brave-and-true/)
* Starting project in Clojure at [Clojure project from scratch](https://oli.me.uk/clojure-projects-from-scratch/)
* Relational databases in Clojure at [Rest APIs and relational databases in CLojure](https://fuqua.io/blog/2013/12/rest-apis-and-relational-databases-in-clojure/)
* Learning compojure api at [Getting started with compojure api](https://www.anthony-galea.com/blog/post/getting-started-with-compojure-api/)
* Repl at [Seesaw repl tutorial](https://gist.github.com/daveray/1441520#file-seesaw-repl-tutorial-clj-L33)






## License

Copyright © 2022 ORBANJAMES.
