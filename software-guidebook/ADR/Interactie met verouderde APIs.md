# ADR: Interactie met verouderde externe API's


###### Datum: 2025-03-31

## Status
Geaccepteerd

## Context
Wij zijn de volgende onderzoeksvraag tegengekomen: Hoe beheer je veilig de interactie met verouderde externe APIs die geen moderne beveiligingsprotocollen ondersteunen?

In deze ADR onderzoeken wij meerdere potentiële oplossingen voor dit probleem en kiezen wij de meest passende oplossing.

## Considered Options


### Vervangen:
Het vervangen van de verouderde API door een API die wél moderne beveiligingsprotocollen ondersteunt. Dit zal niet altijd een optie kunnen zijn, aangezien wij niet kunnen garanderen dat er daadwerkelijk een up-to-date API beschikbaar zal zijn.

#### Voordelen:
- Hoogste veiligheidsgraad, ondersteuning voor moderne encryptie en authenticatie.
- Betrouwbaar, geen afhankelijkheid van tussenlagen of 'tijdelijke oplossingen'.
- **De juiste manier**, Lost het probleem op in plaats van het te omzeilen.

#### Nadelen:
- Lastig te implementeren, of herschrijven of migreren naar nieuwe API.
- Langdurige implementatie


### API Gateway Security Wrappers:
Is een 'tussenpersoon' die de communicatie met de API afhandelt en zelf wél beveiligingsprotocollen kan gebruiken.

#### Voordelen:
- Ondersteunt encryptie.
- Ondersteunt Authenticatie.
- Beschermt tegen aanvallen
- Geen aanpassingen aan de API nodig (sneller en makkelijker te implementeren dan de API vervangen)


#### Nadelen:
- Voegt een extra laag toe, heeft bijna gegarandeerd een prestatie impact.
- Voegt onderhoud toe
- Pakt het probleem zelf niet aan, maar omzeilt het.


### VPN/Tunneling:
Hiermee gaat het verkeer door een versleutelde verbinding. De API zelf blijft onveilig, maar de communicatie ermee is versleuteld en alleen toegankelijk voor vertrouwde apparaten.

#### Voordelen:
- Verbergt API-verkeer
- Geen aanpassingen aan de API nodig (snel en makkelijk te implementeren)
- Beperkt toegang tot vertrouwde apparaten

#### Nadelen:
- Voegt een extra stap toe, heeft bijna gegarandeerd een prestatie impact.
- Geen bescherming tegen injectie-aanvallen (elders af te vangen)
- Pakt het probleem zelf niet aan, maar omzeilt het.
- Minder schaalbaar

| Opties                  | Vervanging | API Gateway Security Wrappers | VPN/Tunneling |
|-------------------------|------------|-------------------------------|---------------|
| Veiligheid              | ++         | +                             | 0             |
| Gemak van implementatie | -          | 0                             | +             |
| 'future proofing'*      | ++         | +                             | 0             |
| Kosten                  | -          | 0                             | +             |
| Onderhoud               | 0          | -                             | +             |
| Schaalbaarheid          | 0          | -                             | -             |
|                         |            |                               |               |
| Totale score            | 2          | 1                             | 2             |


###### *In hoeverre is dit een 'permanente' oplossing?


## Decision

Er komen uit de afweging 2 opties _Vpn/tunneling_ en _Vervanging_, beide scoren even hoog dus hak ik hier de knoop door en kies ik _Vervanging_ omdat schaalbaarheid voor de opdrachtgever belangrijk is.
Met deze keuze zijn we meer tijd kwijt met implementatie en zal het team proactief moeten zijn met het implementeren van nieuwe api's wanneer deze worden uitgebracht. 
Dit gaat ons echter wel de beste prestaties, veiligheid en schaalbaarheid bieden omdat er geen tussenstappen of omwegen worden gebruikt.


## Consequences

### Voordelen
- Veilig
- Snel
- Schaalbaar
- Lage problematiek later omdat er geen complexiteit wordt toegevoegd aan het systeem (geen ophoping aan 'tijdelijke oplossingen')

### Nadelen
- Lange implementatietijd en kosten
- proactief werk nodig


### Bronnen:

Checkpoint. _Point-to-Point Tunneling Protocol_. (z.d.). Geraadpleegd op 01-04-2025, van: https://www.checkpoint.com/cyber-hub/network-security/point-to-point-tunneling-protocol-pptp/
