## Zuhlke CSV Coding Challenge

You are given the task of writing a simple program which reads from a given CSV file and saves the data in 
a database. This challenge can be completed in about 2 hours, however there is no time limit as such.

##### Attachments

- `sales.csv`: The csv file which your program needs to parse. It is a small list of sales made in a store.
- `schema.sql`: The DDL for the database your program needs to use. This DDL is written for a MySQL DB, 
however you may use any DB of your choosing as long as the constraints are the same.

##### Guidelines

- The program needs to be written in Java or .Net (if your application is for a .Net role).
- Create your solution as if you were in a real-life project and it would have to be shipped into production after you are done 
(read that again, and REALLY consider it in your approach).
- For the purpose of this exercise you can assume that we already have a database containing the necessary tables; you don't need
to include the database itself in your solution.
- Please include a short readme when returning your solution describing decisions and assumptions you made during development.
- Please submit as a ZIP file over either email or DropBox/Google Drive/etc - do NOT push to a public repository.
- For .Net submissions we require: Use .Net Core and it must be possible to compile and run on Linux

##### What we will be looking at

- Readability
- Adherence to best practices
- Extensibility (what if we wanted to add an other source later, or a different database?)
- Exception handling


how insert to mysql

C:\Users\iden.teo\Downloads\zuhlke-coding-challenge\challenge>mvn spring-boot:run

watch spring boot tutorial video ( make sure with jpa)
watch spring jpa tutorial

https://www.pixeltrice.com/import-the-csv-file-into-mysql-database-using-spring-boot-application/

https://www.youtube.com/watch?v=vtPkZShrvXQ
https://attacomsian.com/blog/read-write-csv-files-opencsv#reading-a-csv-file-with-each-record-as-a-java-object-using-annotations

http://opencsv.sourceforge.net/#csvparser

https://spring.io/guides/gs/testing-web/

https://www.petrikainulainen.net/programming/spring-framework/integration-testing-of-spring-mvc-applications-write-clean-assertions-with-jsonpath/

https://www.baeldung.com/guide-to-jayway-jsonpath

https://www.petrikainulainen.net/programming/spring-framework/integration-testing-of-spring-mvc-applications-write-clean-assertions-with-jsonpath/

https://stackoverflow.com/questions/15371022/springmvc-mockmvc-jsonpath-compare-list-of-strings

https://stackoverflow.com/questions/42135114/how-does-spring-jpa-hibernate-ddl-auto-property-exactly-work-in-spring
https://junit.org/junit5/docs/snapshot/user-guide/