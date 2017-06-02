# rentalcars_test2

REST version of first test
Runs on Spring boot

BUILD
Compile using Gradle: gradle buildDependents

RUNNING 
Using CMD: 
java -jar test2-1.0.0.jar

URLs:

//Print a list of all the cars, in ascending price order.

http://localhost:8080/getCarsByPrice

//Calculate the specification of the vehicles based on their SIPP.

http://localhost:8080/getCarSpecification

//Print out the highest rated supplier per car type, descending order.

http://localhost:8080/getBestSupplierPerCar

//Print out a list of vehicles, ordered by the sum of the scores in descending order.

http://localhost:8080/getCarPerScore
