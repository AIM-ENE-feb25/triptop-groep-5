# ADR: Adapter keuzes voor reisapplicatie

## Datum: 2025-03-26

## Status
Voorgesteld

## Context
Onze Triptop applicatie moet naadloos kunnen integreren met verschillende
externe diensten voor bouwstenen als accommodaties en activiteiten. De applicatie
moet flexibel zijn om:
- Verschillende accommodatiediensten (Zoals AirBnB) te ondersteunen.
- Verschillende activiteitenleveranciers (Zoals TripAdvisor) te integreren.
- Toekomstige wijzigingen in de API's op te kunnen vangen.

Specifieke uitdagingen (Ontwerpvragen)
- Hoe maken we de applicatie uitbreidbaar met nieuwe bouwstenen?
- Hoe zorgen we ervoor dat we makkelijk een nieuwe externe service kunnen toevoegen?
- Hoe voorkomen we grote wijzigingen in de applicatie bij API-veranderingen?

## Considered Options

| Forces                     | Directe API-integratie | Adapter Pattern |
|----------------------------|------------------------|-----------------|
| Uitbreidbaarheid           | -                      | ++              |
| Externe Service Toevoeging | Moeilijk               | Eenvoudig       |
| API Wijzigings-impact      | Groot                  | Klein           |
| Systeemcomplexiteit        | Laag                   | Gemiddeld       |
| Front-end Stabiliteit      | Laag                   | Hoog            |
| Onderhoudbaarheid          | -                      | ++              |

## Decision
We kiezen voor Adapter pattern (met service adapters).
Doordat we een abstractielaag tussen de service en onze applicatie wat directe 
afhankelijkheden voorkomt en het systeem flexibeler maakt. 
Ook krijgt elke externe service zijn eigen adapter.

## Consequences
#### Positieve effecten
* Minimale impact wanneer externe API's wijzigen
* Voorkomt wijzigingen in de frontend bij backend aanpassingen
* Maximale flexibiliteit bij het toevoegen van nieuwe externe services

#### Risico's
* Toename in systeemcomplexiteit
* Initieel is er meer ontwikkelingstijd nodig