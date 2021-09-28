# www.belgif.be

Source of the www.belgif.be website

# Building the micronaut web application

Requires at least JDK11 and maven 3.8 to build the application. 

Running the application will process and load the Turtle files (located in the data subdirectory) into memory and create a local webserver.

To create a series of static HTML files, point your favorite webcrawler (wget, curl...) to localhost:8080 and save the files to disk. 
E.g. `wget -r -nc -k localhost:8080`
