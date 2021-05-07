# Grooming Card Interface

## CSC 365 SP 2021 Group Project

_Developers: Jackie Morrison & Robert Stonner_

## Startup Instructions

- Must have [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- Must have [PostgreSQL](https://www.postgresqltutorial.com/install-postgresql/) installed (I use 12). __THIS PROJECT REQUIRES SETUP OF AN EXTERNAL POSTGRES DATABASE. IF YOU DO NOT DO THIS IT WILL NOT WORK.__
- Must have role 'postgres' with password 'password'. I believe these are the default settings in Postgres (password and database name can be changed in application.properties, just make sure that you have a database set up with that name).
- Must have database 'postgres' running on localhost:5432 (a lot of these are PostgreSQL defaults, so this shouldn't be difficultto set up except for the password change).
- Currently, just use `./gradlew bootRun` within the project directory. You can add `--args="drop"` to drop and recreate (or just create) all the necessary relations in the postgres database.
- Runs on `localhost:8080`. Try `localhost:8080/demo/` and `localhost:8080/demo/object/` to test basic functionality. Go to `localhost:8080` to see the actual page.

## Usage

- The main page on `localhost:8080` is where the list of clients are viewable in a table.
- You can run `curl -H "Content-Type: application/json" -d @gci-test.json localhost:8080/api/addclient` from the console within the project directory a few times to add some dummy client/appointment/pet data to view on the main page.
- To add a new client, click the "Add Client" button at the bottom of the home page client table. This will produce a modal where you can input basic client information, like name, phone numbers, and addresses. Click submit to save the information to the database. The page will refresh and the new client entry should be visible on the main table.
- Once a client is added, you can hit the button under the "Pets" column for that client to view and add pets. Enter the information into the bottom row of the table on the modal that will appear and click the submit button to add a pet.
- Similarly, you can hit the button under the "Appointments" column for that client to view and add appointments. Again, just enter the information into the bottom row of the table on the modal that will appear and click the submit button to add an appointment.
- If you click the button under the "Card" column for a client entry, you will see a modal display that recreates a paper grooming card look with all the client information, including pets and appointments for the client.

## Stuff I Didn't Finish

- You can only create new information, not update and delete it.
- There is no Vet information.
- You can't search or sort the tables by field.
