# Wiki - Overzicht

Dit project is een eenvoudige Quarkus-applicatie die REST-endpoints en server-side templating demonstreert.

## REST-endpoints

- `GET /hello` geeft de tekst `Hello from Quarkus REST` terug.
- `POST /hello` verwacht JSON met een `name`-veld en reageert met een JSON-groet. Ongeldige namen leveren een foutmelding op.
- `GET /greeting` levert een HTML-pagina met een formulier om een naam in te voeren. De pagina toont de groet of een foutmelding.

## Kernonderdelen

- `GreetingService` valideert de opgegeven naam, bepaalt het deel van de dag en stelt de groet samen.
- `GreetingResource` stelt de `/hello` REST-endpoints beschikbaar.
- `GreetingPageResource` rendert de `/greeting` HTML-pagina via een Qute-template.
- `GreetingRequest` en `GreetingResponse` zijn eenvoudige records om data uit te wisselen.

De service berekent of het ochtend, middag of avond is op basis van de servertijd en verwerkt dat in de groet.
