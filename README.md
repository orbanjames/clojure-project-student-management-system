# STUDENT-MANAGEMENT-SYSTEM

This is project work for the course title: Tools and Methods of Software Engineering on Master studies 
@ the Department of Software Engineering and Computer Science, Faculty of Organisational Sciences,
UNIVERSITY OF BELGRADE, SERBIA. It also represents my first project experience implemented
using Clojure Programming Language. 
The application is a CRUD application that allows the users/students to log in with username and 
password after registration from the user account page and can see the list of students on scholarship,
view the list of universities, faculties and courses available in various universities of their choice.

# DATABASE - Connection parameters
I use postgresql, for data storage and manipulation, and I have created a database *student_management_system*,
and you can find the connection in the "dev-config.edn"

* classname:   "com.postgresql.cj.jdbc.Driver"
* subprotocol: "postgres"
* subname:     "//localhost:5433/student_management_system"
* user:        "postgres"
* password:    "postgres"
* I used PgAdmin to run a postgreSQL Server on my localhost.


# Starting the application from terminal:
terminal 1:
terminal 2
 run "npx shadow-cljs watch app" for clojurescript. 
http://localhost:3000, navigate to the browser for the application home page  and clojurescript handle.


#Testing the applicaction
Lein test

## License

Copyright © 2022 ORBANJAMES.
