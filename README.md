# Paper rock scissor

simple paper rock scissor game with angular and spring boot.
## Getting Started

To run the app locally, you'll need to have Docker, Java, Node.js and the Angular CLI installed on your machine. 

### Prerequisites

- [Node.js](https://nodejs.org/en/)
- [Angular CLI](https://angular.io/cli)
- [Docker](https://docker.com)

### Installing
To run this application locally there's two options(using docker-compose or run front-end and back-end servers separately)

#### Running the App with Docker

1. Open a terminal window and navigate to the root directory of the project.
2. Run the command `cd back-end/game/ && docker build -t spring-boot-game .` to build the Docker image.
3. Run the command `cd cd front-end/game/angular-game && npm install && ng build && docker build -t angular-game-app .` to build the Docker image.
4. Go back to root directory and run the command `docker-compose up` to build and start the container.
5. Open a web browser and navigate to `http://localhost:5656` to view the app.

### Running the App without Docker

1. Clone the repository to your local machine.
2. Open a terminal window and navigate to the root directory of the project.
3. Run the command `npm install` to install the app's dependencies.
4. Open a terminal window and navigate to the root directory of the project.
5. Run the command `ng serve` to start the app.
6. Open a web browser and navigate to `http://localhost:4200` to view the app.
7. Open the back-end project in Intellij and change the application port to `5432` and run the application

## Built With

- [Angular](https://angular.io/)
- [SpringBoot](https://spring.io/)
