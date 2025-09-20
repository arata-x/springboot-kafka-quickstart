# Spring Boot Kafka Quickstart

A demo Spring Boot application that uses Apache Kafka for messaging. It provides a REST endpoint to receive orders, publishes them to a Kafka topic, and consumes them with a message listener.

## Components

- **Spring Boot App**: A web application with a REST API for order creation (`/orders`), a Kafka producer, and a Kafka consumer (`OrderEventsListener`).
- **Kafka**: A single-node Kafka broker running in a Docker container.
- **Test Script**: A shell script (`test/send-task.sh`) to send a sample order to the REST API.

## Prerequisites

- Docker
- Java 21
- Maven
- `curl` (or any HTTP client)

## Running the Application (Local)

This method runs the Kafka broker in Docker and the Spring Boot application locally on your machine.

1.  **Start Kafka**

    Navigate to the `docker` directory and start the Kafka service:

    ```bash
    cd docker
    docker-compose up
    ```

    The Kafka broker will be available at `localhost:9092`.
    To stop and remove the containers, press `Ctrl+C` in the terminal where `docker-compose` is running, or run `docker-compose down` from the project root.

2.  **Run the Spring Boot App**

    In a new terminal, navigate to the `springboot` directory and run the application using Maven:

    ```bash
    cd springboot
    mvn spring-boot:run -Dmaven.test.skip=true
    ```

    The application will start on `http://localhost:8080`. The Kafka consumer log output will appear in this terminal.

3.  **Send an Order**

    In a third terminal, run the test script to send a sample order:

    ```bash
    cd test
    ./send-order.sh
    ```

4.  **Observe the Output**

    Check the terminal where you ran the `mvn spring-boot:run` command. You should see log output from `OrderEventsListener` confirming that the order was received from the Kafka topic.
