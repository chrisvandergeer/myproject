# Wiki - Overzicht

Dit project is een eenvoudige Quarkus-applicatie die server-side templating demonstreert.

## Endpoints

- `GET /greeting` levert een HTML-pagina met een formulier om een naam in te voeren. De pagina toont de groet of een foutmelding.

## Kernonderdelen

- `GreetingService` valideert de opgegeven naam, bepaalt het deel van de dag en stelt de groet samen.
- `GreetingPageResource` rendert de `/greeting` HTML-pagina via een Qute-template.
- `GreetingResponse` is een eenvoudig record om data uit te wisselen.

De service berekent of het ochtend, middag of avond is op basis van de servertijd en verwerkt dat in de groet.
