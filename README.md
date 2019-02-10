# Rides 
## Sample use of a Taxis application with external APIs

## Setup

Code was developed in IntelliJ IDEA using Apache Maven 3

To build the package run 'mvn clean install'

The code is packaged already in `rides-api-1.0.0.jar` and is enough to to be ran under the command:

`java -jar rides-api-1.0.0.jar`


## Part 1

### Console application to print the search results for Dave's Taxis

To run the application with the required parameters run in console:

`java -jar rides-api-1.0.0.jar latitude longitude supplierName`

where latitude and longitude are parameters such as: 
latitude = 51.00000,1.0000
longitude = 51.00000,1.0000
and the supplier name should be specified for which cases we have now dave, eric and jeff

example: `java -jar rides-api-1.0.0.jar 51.00000,1.0000 51.00000,1.0000 dave`

### Console application to filter by number of passengers

To run the application with the required parameters for this use case run in console:

`java -jar rides-api-1.0.0.jar latitude longitude maximumPassengers`

where maximumPassengers represents the total number of passengers a car type returned can support

example1: `java -jar rides-api-1.0.0.jar 51.00000,1.0000 51.00000,1.0000 8`

example2: `java -jar rides-api-1.0.0.jar 51.00000,1.0000 51.00000,1.0000`

In second example if the passengers parameter is omitted it will go to default highest 16 and if value is under minimum 4 it will get updated automatically.


## Part 2

To run the application as a spring boot app it's enough to run it under: 

`java -jar rides-api-1.0.0.jar`

from here we can simply call the controller with following examples:

For Dave's Api:

`http://localhost:8080/getSupplierCarOptions?pickup=51.00000,1.0000&dropoff=51.00000,1.0000`

`http://localhost:8080/getSupplierCarOptions?pickup=51.00000,1.0000&dropoff=51.00000,1.0000&supplier=dave`

By not specifying a supplier name it will default to dave's api.

For all suppliers:

`http://localhost:8080/getCarOptions?pickup=51.00000,1.0000&dropoff=51.00000,1.0000&maximumPassengers=6`
