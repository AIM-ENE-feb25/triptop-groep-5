# ADR: Service niet beschikbaar

## Datum: 2025-04-01

## Status
Voorgesteld

## Context

In onze applicatie zijn we afhankelijk van externe services voor het verkrijgen van bepaalde gegevens.
Er zijn situaties waarin deze services tijdelijk niet beschikbaar zijn, maar waarbij van onze applicatie nog steeds
verwacht wordt dat deze waardevolle output levert aan de eindgebruiker. We moeten een strategie bepalen om met deze
uitvalsituaties om te gaan zonder dat de gebruikerservaring ernstig wordt aangetast.

#### Ontwerpvraag
Hoe ga je om met aanroepen van externe services die niet beschikbaar zijn en toch verwacht wordt dat er waardevolle output gegeven wordt?

## Considered Options

| Forces             | Cache | Error | Meerdere services |
|--------------------|-------|-------|-------------------|
| Betrouwbaarheid    | ++    | -     | +                 |
| Actualiteit        | 0     | ++    | +                 |
| Complexiteit       | 0     | --    | ++                |
| Onderhoudskosten   | +     | -     | +                 |
| Gebruikerservaring | +     | --    | +                 |   

## Decision

Na het bekijken van de geschikte opties hebben we besloten om een caching-strategie te gebruiken om met niet-beschikbare
services om te gaan.
1. Implementeer een eenvoudig caching-mechanisme dat gegevens van succesvolle service-aanroepen bewaart.
2. Gebruik de cache automatisch als fallback wanneer een service niet beschikbaar is.
3. Stel passende vervaltijden in, zodat gegevens niet te verouderd raken.

## Consequences
### Positieve gevolgen
- Verhoogde betrouwbaarheid; de applicatie blijft functioneren, zelfs wanneer de services uitvallen.
- Goede gebruikerservaring; gebruikers zullen minder onderbrekingen ervaren.
- Vrij lage complexiteit; relatief eenvoudig te implementeren en onderhouden.
- Kostenefficiënt; geen extra kosten voor extra services.

### Negatieve gevolgen
- Gegevens kunnen mogelijk verouderd zijn; gebruikers kunnen niet altijd de meest recente informatie zien.
- Cache-beheer; een cache vereist monitoring en periodieke evaluatie van vervaltijden.