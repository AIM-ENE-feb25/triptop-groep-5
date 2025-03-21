# ADR: API-keuzes voor reisapplicatie

## Datum: 2025-03-21

## Status
Voorgesteld

## Context
Voor de ontwikkeling van onze reisapplicatie hebben we betrouwbare API's nodig die functionaliteiten bieden voor reisplanning, hotelboekingen, vliegreizen en openbaar vervoer.

## Considered Options
### Kaarten en routeplanning
| Forces              | Google Maps | TomTom |
|---------------------|-------------|--------|
| Wereldwijde dekking | ++          | +      |
| Nauwkeurigheid      | ++          | ++     |
| Documentatie        | ++          | +      |
| Integratie-eenvoud  | ++          | +      |

### Vluchtgegevens
| Forces                     | Skyscanner | FlightStats |
|----------------------------|------------|-------------|
| Aanbod vliegmaatschappijen | ++         | 0           |
| Real-time beschikbaarheid  | +          | ++          |
| Prijsmodel                 | +          | -           |
| Documentatie               | +          | +           |
| Boekingsfunctionaliteit    | 0          | -           | 

### Hotelboekingen
| Forces                  | Booking.com | Hotels.com | Airbnb | 
|-------------------------|-------------|------------|--------|
| Aantal accommodaties    | ++          | +          | +      |
| Boekingsfunctionaliteit | ++          | +          | -      |
| API stabiliteit         | +           | 0          | -      | 
| Integratie-eenvoud      | +           | 0          | -      |

### Openbaar vervoer
| Forces                 | NS | Deutsche Bahn | 
|------------------------|----|---------------|
| Lokale dekking (NL)    | ++ | -             |
| Internationale dekking | -  | ++            | 
| Real-time updates      | ++ | +             | 
| Kosten                 | -  | ++            | 

## Decision

Na zorgvuldige afweging van de verschillende API-opties hebben we de volgende beslissingen genomen voor de reisapplicatie:

### Kaarten en routeplanning
We kiezen voor **Google Maps**. Hoewel de kosten bij schaling een nadeel zijn, wegen de voordelen zwaarder: wereldwijde dekking, uitstekende nauwkeurigheid, uitgebreide documentatie en eenvoudige integratie.

### Vluchtgegevens
We kiezen voor **Skyscanner**. Het brede aanbod van vliegmaatschappijen is cruciaal voor onze doelgroep. Het gebrek aan directe boekingsfunctionaliteit is acceptabel omdat we kunnen doorverwijzen naar de vliegmaatschappijen.

### Hotelboekingen
We kiezen voor **Booking.com**. De combinatie van het grootste aanbod accommodaties, uitgebreide boekingsfunctionaliteit en een stabiele API maakt dit de meest geschikte optie. Hotels.com en Airbnb bieden complementaire voordelen, maar de integratie-uitdagingen van Airbnb en de beperktere API-stabiliteit van Hotels.com maken deze minder geschikt als primaire partner.

### Openbaar vervoer
We kiezen voor een combinatie van **NS** én **Deutsche Bahn**. NS biedt uitstekende lokale dekking en real-time updates voor Nederland, terwijl Deutsche Bahn internationale dekking biedt tegen lagere kosten. De hogere kosten en gebruikslimieten van NS accepteren we omdat lokale reizigers precieze en actuele OV-informatie verwachten. Voor internationale reizen is Deutsche Bahn een kosteneffectieve oplossing met voldoende functionaliteit.

## Consequences

### Voordelen
- Hoogwaardige gebruikerservaring door nauwkeurige kaarten en routeplanning
- Brede dekking van vliegmaatschappijen en accommodaties wereldwijd
- Uitstekende OV-informatievoorziening, zowel nationaal als internationaal
- Betrouwbare en stabiele API's met goede ondersteuning

### Nadelen
- Beperkte directe boekingsfunctionaliteit voor vluchten
- Complexiteit door integratie met meerdere vervoerders (NS en Deutsche Bahn)
- Gebruikslimieten bij NS kunnen bij populariteit een knelpunt vormen
