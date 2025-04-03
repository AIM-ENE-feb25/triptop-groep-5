# ADR Betalings systemen
## Datum: 2025-03-28

## Status
Voorgesteld

## Context
Om de gebruikers van de Triptop-applicatie vrijheid te geven in hun betalingskeuze moeten er verschillende betalingssystemen worden ondersteund. Om dat ze makkelijk moggelijk te maken willen dat er verschillende betaalmethoden als bouwstenen kunnen toegevoegd worden.
Het systeen moet:
- Flexibel zijn om verschillende betalingssystemen te ondersteunen.
- Herbruikbaar en onderhoudbaar blijven.
- Betalingslogica gescheiden houden van de rest van de applicatie.

## Considered Options
### Betalingssystemen
| Forces                       | PayPal | iDEAL | Creditcard | Apple Pay | Google Pay | Alipay |
|------------------------------|--------|-------|------------|-----------|-----------|--------|
| Beschikbaarheid in Nederland | ++     | ++    | ++         | ++        | ++        | --     |
| Gebruikers Nederland         | ++     |++| +          | 0         |-| --     |
| Wereldwijde dekking          |+|--|++|+|+| 0      |
| Gebruiksgemak                |++|++|++|++|++| -      |
| Kosten                       |+|++|0|0|0| -      |

### Design Patterns
| Forces                              | Strategy + Factory | Template method | Command | Decorator | Abstract Factory |
|-------------------------------------|--------------------|----------------|---------|-----------|------------------|
| Uitbreidbaarheid                    | ++                 | -              | 0       | -         | +                |
| Herbruikbaarheid                    | ++                 | +              | +       | 0         | +                |
| Scheiding van verantwoordelijkheden | ++                 | -              | 0       | 0         | +                |
| Implementatiecomplexiteit           | ++                 |+| 0       |0| --               |


## Decision
### Betalingssystemen
We kiezen voor **PayPal**, **iDEAL** en **Creditcard** als betalingssystemen voor de Triptop-applicatie. Deze drie opties bieden een goede dekking voor zowel Nederlandse als internationale gebruikers.
### Design Patterns
We kiezen voor **Strategy** in combinatie met **Factory** als design pattern voor de implementatie van de betalingssystemen. Dit biedt de meeste flexibiliteit en maakt het eenvoudig om nieuwe betalingssystemen toe te voegen zonder bestaande code te wijzigen. Door gebruik te maken van een factory kunnen we verschillende implementaties van betalingssystemen creëren op basis van de gebruikersvoorkeuren.

## Consequences
### Positieve consequenties:
- Gebruikers kunnen kiezen uit verschillende betalingssystemen die aansluiten bij hun voorkeuren.
- De applicatie is flexibel en kan gemakkelijk worden aangepast om nieuwe betaalmethoden toe te voegen.
- De ontwikkelingstijd wordt verkort doordat bestaande code hergebruikt kan worden.
- Duidelijke structuur: strategieën, factory en controller zijn logisch gescheiden (SoC).
- Minder foutgevoelig: geen lange if/else-constructies in de controller.
### Risico's en beperkingen:
- Het toevoegen van nieuwe betaalmethoden kan extra ontwikkelingstijd vereisen.
- Het onderhouden van meerdere betaalmethoden kan complexiteit toevoegen aan de applicatie.
- Iets meer code-infrastructuur nodig: extra klassen per betaalmethode en een factory
- Factory is gevoelig voor typfouten (bijv. "paypal" vs "PayPal") tenzij met enum of mapping gewerkt wordt



