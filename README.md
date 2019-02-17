# JMS Test - Playground

Simple application to setup a complete JMS end to end test within wildfly.

The application is run using a simple REST endpoint allowing messages to be posted via a JMS Queue into a LIFO (Last-in-First-out) message store.

## Build / Run

### App
The application is already built and the latest .ear file availble in `/docker/files`.

If you wish to make changes then you can update the .ear with the following command:

```bash
./gradlew clean build dist
```

### Docker
The docker file provided in `/docker` uses wildfly 13.0.0-Latest and adds the .ear and a configuration of the standalone.xml to enable activemq and create a queue that is used within the app.

You can then run the build as a normal docker build from within the `docker` folder:

```bash
docker build -t c19/jmstest .
```

Then run with the following command:

```bash
docker run -dt -p8080:8080 -p9990:9990 -p5555:5555 c19/jmstest
```

## Usage

To retrieve messages just perform a HTTP GET on the `/message` endpoint.

```http request
GET /message HTTP1.1
```

If the  message store is empty then the string "EMPTY" will be returned. Otherwise the most recently sent message will be removed and returned.

To add a message perform an HTTP POST with an HTTP body containing the "message" on the `/message` endpoint.

```http request
POST /message HTTP1.1

{
    "hello" : "world!"
}
```