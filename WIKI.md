# Wiki - Overview

This project is a simple Quarkus application that demonstrates REST and server-side templating.

## REST endpoints

- `GET /hello` returns the plain text message `Hello from Quarkus REST`.
- `POST /hello` accepts JSON with a `name` field and responds with a JSON greeting. Invalid names result in an error.
- `GET /greeting` serves an HTML page with a form to submit a name. The page displays the greeting or an error message.

## Core components

- `GreetingService` validates the provided name, determines the part of the day, and composes the greeting.
- `GreetingResource` exposes the `/hello` REST endpoints.
- `GreetingPageResource` renders the `/greeting` HTML page using a Qute template.
- `GreetingRequest` and `GreetingResponse` are simple records used to transfer data.

The service computes whether it is morning, afternoon, or evening based on the server time and includes that in the greeting.
