Usage:

STEP 1:
SSH into lectura using this command (replace PORTNUMBER with a valid portnumber, and NETID with your netid):
ssh -L PORTNUMBER:localhost:PORTNUMBER NETID@lectura.cs.arizona.edu

STEP 2:
CD into the project root folder

STEP 3 (Do this only once for the first time):
mvn install:install-file -Dfile=/opt/oracle/product/10.2.0/client/jdbc/lib/ojdbc14.jar -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=11.2.0 -Dpackaging=jar

STEP 4:
run the bash script at the root project folder by running the follwoing command:
./run

On your local machine navigate to:
http://localhost:PORTNUMBER

Replace PORTNUMBER with the portnumber you specified when you ssh'ed in step 1.

Workload:

Hazza Alkaabi: DB-Design/ER-Diagram, Frontend/Backend, Documentation,
Isaac Plunkett: DB-Design/ER-Diagram, Backend, Tables creation, Documentation
Reno Valdes: DB-Design/ER-Diagram, Frontend, Documentation
