# ADR: `extends` voor uitbreidbaarheid met nieuwe bouwstenen
## Datum: 2025-03-21

## Status
Voorgesteld

## Context
Onze **Triptop**-applicatie is opgebouwd uit verschillende bouwstenen, zoals vervoer, verblijf en activiteiten. We willen de applicatie uitbreiden met nieuwe bouwstenen, zoals een helikoptertour of een nieuwe activiteit. Om dit te kunnen doen, moet de applicatie flexibel zijn om:

- Nieuwe typen bouwstenen toe te voegen zonder bestaande code te wijzigen.

### Specifieke ontwerpvraag:
- Hoe maak je de applicatie uitbreidbaar met nieuwe bouwstenen? (Reisservice zelf aanpassen met `extends`)

## Considered Options

| Forces               | Switch-statement  | Interface + Compositie | `extends` (Inheritance) |
|----------------------|-------------------|--------------------------|--------------------------|
| Uitbreidbaarheid     | +                 | ++                       | ++                       |
| Onderhoudbaarheid    | +                 | ++                       | ++                       |
| Ontwikkelingstijd    | ++                | +                        | ++                       |

## Decision
We kiezen voor het gebruik van **`extends`** om de applicatie uitbreidbaar te maken met nieuwe bouwstenen. Deze aanpak biedt de meeste flexibiliteit en maakt het mogelijk om nieuwe bouwstenen toe te voegen zonder bestaande code te wijzigen. Door gebruik te maken van `extends` kunnen we nieuwe bouwstenen implementeren door een nieuwe klasse te maken die de bestaande basisklasse uitbreidt. Dit maakt het eenvoudig om nieuwe functionaliteit toe te voegen en bestaande code te hergebruiken.

## Consequences

### Positieve consequenties:
- De applicatie is eenvoudig uitbreidbaar met nieuwe bouwstenen
- De ontwikkelingstijd wordt verkort doordat bestaande code hergebruikt kan worden

### Risico's en beperkingen:
- Het gebruik van `extends` kan leiden tot een complexere codebase als het niet zorgvuldig wordt toegepast
- Unit testing kan uitdagender worden bij veel gedeeld gedrag in de basisklasse  
