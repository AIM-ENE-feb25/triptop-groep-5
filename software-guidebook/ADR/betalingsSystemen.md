# ADR Betalings systemen
## Datum: 2025-03-28

## Status
Voorgesteld

## Context
Om de gebruikers van de Triptop-applicatie vrijheid te geven in hun betalingskeuze moeten er verschillende betalingssystemen worden ondersteund. Om dat ze makkelijk moggelijk te maken willen dat er verschillende betaalmethoden als bouwstenen kunnen toegevoegd worden.

## Considered Options
| Forces                       | PayPal | iDEAL | Creditcard | Apple Pay | Google Pay | Alipay |
|------------------------------|--------|-------|------------|-----------|-----------|--------|
| Beschikbaarheid in Nederland | ++     | ++    | ++         | ++        | ++        | --     |
| Gebruikers Nederland         | ++     |++| +          | 0         |-| --     |
| Wereldwijde dekking          |+|--|++|+|+| 0      |
| Gebruiksgemak                |++|++|++|++|++| -      |
| Kosten                       |+|++|0|0|0| -      |

## Decision
We kiezen voor **PayPal**, **iDEAL** en **Creditcard** als betalingssystemen voor de Triptop-applicatie. Deze drie opties bieden een goede dekking voor zowel Nederlandse als internationale gebruikers.

## Consequences
### Positieve consequenties:

- Gebruikers kunnen kiezen uit verschillende betalingssystemen die aansluiten bij hun voorkeuren.
- De applicatie is flexibel en kan gemakkelijk worden aangepast om nieuwe betaalmethoden toe te voegen.

### Risico's en beperkingen:

- Het toevoegen van nieuwe betaalmethoden kan extra ontwikkelingstijd vereisen.
- Het onderhouden van meerdere betaalmethoden kan complexiteit toevoegen aan de applicatie.
