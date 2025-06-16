# Wiki - Overzicht

Dit project is een eenvoudige Quarkus-applicatie die server-side templating demonstreert.

## Endpoints

- `GET /greeting` levert een HTML-pagina met een formulier om een naam in te voeren. De pagina toont de groet of een foutmelding.
- `GET /tasks` toont een pagina waarop taken kunnen worden toegevoegd en weergegeven.
- `POST /tasks` voegt een taak toe met naam, omschrijving en einddatum.

## Kernonderdelen

- `GreetingService` valideert de opgegeven naam, bepaalt het deel van de dag en stelt de groet samen.
- `GreetingPageResource` rendert de `/greeting` HTML-pagina via een Qute-template.
- `TaskService` beheert een eenvoudige in-memory lijst van taken.
- `TaskPageResource` verzorgt het tonen en toevoegen van taken via `/tasks`.
- `GreetingResponse` is een eenvoudig record om data uit te wisselen.

De service berekent of het ochtend, middag of avond is op basis van de servertijd en verwerkt dat in de groet.
