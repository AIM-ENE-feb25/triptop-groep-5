#ADR: Design patterns voor Triptop
## Datum: 2025-03-28

## Status
Voorgesteld

## Context
Voor de ontwikkeling van de **Triptop**-applicatie makkelijker te maken willen we gebruik maken van design patterns.
## Hoe maak je de applicatie uitbreidbaar met nieuwe bouwstenen?
## Considered Options 
| Forces                | Stategy | Factory | Template Method Pattern |Decorator Pattern|Chain of Responsibility|State Pattern|Command Pattern|
|-----------------------|---|---------|------------------------|---|---|---|---|
| Ondersteund extends   |++|+|++|+|+|+|0| 
| flexibiliteit         |++| +|0|+|+|+|+|
| Uitbreidbaarheid      |++|-|+|0|0|0|-|
| Eenvoud implementatie |+|++|0|-|-|-|-|


## Decision
We kiezen ervoor om voor deze ontwerpvraag de design methode **Strategy** te gebruiken. Met het gebruik van de design patern Strategy kunnen we de applicatie uitbreidbaar maken met nieuwe bouwstenen. Deze aanpak biedt de meeste flexibiliteit en maakt het mogelijk om nieuwe bouwstenen toe te voegen zonder bestaande code te wijzigen. Door gebruik te maken van Strategy kunnen we nieuwe functionaliteit toevoegen en bestaande code hergebruiken.
## Consequences
### Positieve consequenties:
- De applicatie is eenvoudig uitbreidbaar met nieuwe bouwstenen
- De ontwikkelingstijd wordt verkort doordat bestaande code hergebruikt kan worden
- De applicatie is flexibel en kan gemakkelijk worden aangepast aan veranderende eisen
- De codebase wordt eenvoudiger en beter onderhoudbaar

### Risico's en beperkingen:
- Het gebruik van Strategy kan leiden tot een complexere codebase als het niet zorgvuldig wordt toegepast
- Het kan lastig zijn om de juiste strategie te kiezen voor een specifieke situatie
- Het kan moeilijk zijn om te begrijpen hoe de verschillende strategieën samenwerken in de applicatie


## Hoe zorg je ervoor dat je makkelijk een nieuwe externe service kan toevoegen? (Adapters goed schrijven)

## Considered Options

| Forces                | Adapter | Facade | Proxy | Bridge |Decorator |
|-----------------------|---|---------|------------------------|---|---|

## Decision

## Consequences
### Positieve consequenties:
### Risico's en beperkingen:

## Hoe zorg je dat een wijziging in een of meerdere APIs niet leidt tot een grote wijziging in de applicatie?

## Considered Options
