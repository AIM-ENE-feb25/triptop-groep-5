# Software Guidebook Triptop

## 1. Introduction
Dit software guidebook geeft een overzicht van de Triptop-applicatie. Het bevat een samenvatting van het volgende: 
1. De vereisten, beperkingen en principes. 
1. De software-architectuur, met inbegrip van de technologiekeuzes op hoog niveau en de structuur van de software. 
1. De ontwerp- en codebeslissingen die zijn genomen om de software te realiseren.
1. De architectuur van de infrastructuur en hoe de software kan worden geinstalleerd. 

## 2. Context

> [!IMPORTANT]
> Werk zelf dit hoofdstuk uit met context diagrammen en een beschrijving van de context van de software.

Toelichting op de context van de software inclusief System Context Diagram:
* Functionaliteit
* Gebruikers
* Externe systemen

## 3. Functional Overview

Om de belangrijkste features toe te lichten zijn er user stories en twee domain stories gemaakt en een overzicht van het domein in de vorm van een domeinmodel. Op deze plek staat typisch een user story map maar die ontbreekt in dit voorbeeld.

### 3.1 User Stories

#### 3.1.1 User Story 1: Reis plannen

Als gebruiker wil ik een zelfstandig op basis van diverse variabelen (bouwstenen) een reis kunnen plannen op basis van mijn reisvoorkeuren (wel/niet duurzaam reizen, budget/prijsklasse, 's nachts reizen of overdag etc.) zodat ik op vakantie kan gaan zonder dat hiervoor een reisbureau benodigd is.

#### 3.1.2 User Story 2: Reis boeken

Als gebruiker wil ik een geplande reis als geheel of per variabele (bouwsteen) boeken en betalen zodat ik op vakantie kan gaan zonder dat hiervoor een reisbureau benodigd is.

#### 3.1.3 User Story 3: Reis cancelen

Als gebruiker wil ik een geboekte reis, of delen daarvan, kunnen annuleren zodat ik mijn geld terug kan krijgen zonder inmenging van een intermediair zoals een reisbureau.

#### 3.1.4 User Story 4: Reisstatus bewaren 

Als gebruiker wil ik mijn reisstatus kunnen bewaren zonder dat ik een extra account hoef aan te maken zodat ik mijn reis kan volgen zonder dat ik daarvoor extra handelingen moet verrichten.

#### 3.1.5 User Story 5: Bouwstenen flexibel uitbreiden

Als gebruiker wil ik de bouwstenen van mijn reis flexibel kunnen uitbreiden met een zelf te managen stap (bijv. met providers die niet standaard worden aangeboden zoals een andere reisorganisatie, hotelketen etc.) zodat ik mijn reis helemaal kan aanpassen aan mijn wensen.

### 3.2 Domain Story Reis Boeken (AS IS)

![Domain Story Reis Boeken AS IS](../opdracht-diagrammen/reis-boeken-asis-coursegrained_2024-06-11.egn.svg)

### 3.3 Domain Story Reis Boeken (TO BE)

![Domain Story Reis Boeken TO BE](../opdracht-diagrammen/reis-boeken-tobe-coursegrained_2024-06-11.egn.svg)

### 3.4 Domain Model

![Domain Model](../opdracht-diagrammen/Domain%20Model.png)

## 4. Quality Attributes

Voordat deze casusomschrijving tot stand kwam, heeft de opdrachtgever de volgende ISO 25010 kwaliteitsattributen benoemd als belangrijk:
* Compatibility -> Interoperability (Degree to which a system, product or component can exchange information with other products and mutually use the information that has been exchanged)
* Reliability -> Fault Tolerance (Degree to which a system or component operates as intended despite the presence of hardware or software faults)
* Maintainability -> Modularity (Degree to which a system or computer program is composed of discrete components such that a change to one component has minimal impact on other components)
* Maintainability -> Modifiability (Degree to which a product or system can be effectively and efficiently modified without introducing defects or degrading existing product quality)
* Security -> Integrity (Degree to which a system, product or component ensures that the state of its system and data are protected from unauthorized modification or deletion either by malicious action or computer error)
* Security -> Confidentiality (Degree to which a system, product or component ensures that data are accessible only to those authorized to have access)

## 5. Constraints

> [!IMPORTANT]
> Beschrijf zelf de beperkingen die op voorhand bekend zijn die invloed hebben op keuzes die wel of niet gemaakt kunnen of mogen worden.

## 6. Principles

> [!IMPORTANT]
> Beschrijf zelf de belangrijkste architecturele en design principes die zijn toegepast in de software.

## 7. Software Architecture

###     7.1. Containers

> [!IMPORTANT]
> Voeg toe: Container Diagram plus een Dynamic Diagram van een aantal scenario's inclusief begeleidende tekst.

![Container Diagram Triptop](./images/containerdiagram.png)
![Container Diagram Triptop reis boeken](./images/boekreiscontainerdiagram.png)
![Container Diagram Triptop inlog](./images/logincontainerdiagram.png)

###     7.2. Components

> [!IMPORTANT]
> Voeg toe: Component Diagram plus een Dynamic Diagram van een aantal scenario's inclusief begeleidende tekst.

![Component Diagram Triptop backend](./images/backendcomponentdiagram.png)
![Component Diagram Triptop frontend](./images/frontendcomponentdiagram.png)

###     7.3. Design & Code

> [!IMPORTANT]
> Voeg toe: Per ontwerpvraag een Class Diagram plus een Sequence Diagram van een aantal scenario's inclusief begeleidende tekst.

## 8. Architectural Decision Records
### 8.1. ADR-001 API-keuzes voor reisapplicatie

#### Datum: 2025-03-21

#### Status
Voorgesteld

#### Context
Voor de ontwikkeling van onze reisapplicatie hebben we betrouwbare API's nodig die functionaliteiten bieden voor reisplanning, hotelboekingen, vliegreizen en openbaar vervoer.

#### Considered Options
##### Kaarten en routeplanning
| Forces              | Google Maps | TomTom |
|---------------------|-------------|--------|
| Wereldwijde dekking | ++          | +      |
| Nauwkeurigheid      | ++          | ++     |
| Documentatie        | ++          | +      |
| Integratie-eenvoud  | ++          | +      |

##### Vluchtgegevens
| Forces                     | Skyscanner | FlightStats |
|----------------------------|------------|-------------|
| Aanbod vliegmaatschappijen | ++         | 0           |
| Real-time beschikbaarheid  | +          | ++          |
| Prijsmodel                 | +          | -           |
| Documentatie               | +          | +           |
| Boekingsfunctionaliteit    | 0          | -           | 

##### Hotelboekingen
| Forces                  | Booking.com | Hotels.com | Airbnb | 
|-------------------------|-------------|------------|--------|
| Aantal accommodaties    | ++          | +          | +      |
| Boekingsfunctionaliteit | ++          | +          | -      |
| API stabiliteit         | +           | 0          | -      | 
| Integratie-eenvoud      | +           | 0          | -      |

##### Openbaar vervoer
| Forces                 | NS | Deutsche Bahn | 
|------------------------|----|---------------|
| Lokale dekking (NL)    | ++ | -             |
| Internationale dekking | -  | ++            | 
| Real-time updates      | ++ | +             | 
| Kosten                 | -  | ++            | 

#### Decision

Na zorgvuldige afweging van de verschillende API-opties hebben we de volgende beslissingen genomen voor de reisapplicatie:

##### Kaarten en routeplanning
We kiezen voor **Google Maps**. Hoewel de kosten bij schaling een nadeel zijn, wegen de voordelen zwaarder: wereldwijde dekking, uitstekende nauwkeurigheid, uitgebreide documentatie en eenvoudige integratie.

##### Vluchtgegevens
We kiezen voor **Skyscanner**. Het brede aanbod van vliegmaatschappijen is cruciaal voor onze doelgroep. Het gebrek aan directe boekingsfunctionaliteit is acceptabel omdat we kunnen doorverwijzen naar de vliegmaatschappijen.

##### Hotelboekingen
We kiezen voor **Booking.com**. De combinatie van het grootste aanbod accommodaties, uitgebreide boekingsfunctionaliteit en een stabiele API maakt dit de meest geschikte optie. Hotels.com en Airbnb bieden complementaire voordelen, maar de integratie-uitdagingen van Airbnb en de beperktere API-stabiliteit van Hotels.com maken deze minder geschikt als primaire partner.

##### Openbaar vervoer
We kiezen voor een combinatie van **NS** én **Deutsche Bahn**. NS biedt uitstekende lokale dekking en real-time updates voor Nederland, terwijl Deutsche Bahn internationale dekking biedt tegen lagere kosten. De hogere kosten en gebruikslimieten van NS accepteren we omdat lokale reizigers precieze en actuele OV-informatie verwachten. Voor internationale reizen is Deutsche Bahn een kosteneffectieve oplossing met voldoende functionaliteit.

#### Consequences

##### Voordelen
- Hoogwaardige gebruikerservaring door nauwkeurige kaarten en routeplanning
- Brede dekking van vliegmaatschappijen en accommodaties wereldwijd
- Uitstekende OV-informatievoorziening, zowel nationaal als internationaal
- Betrouwbare en stabiele API's met goede ondersteuning

##### Nadelen
- Beperkte directe boekingsfunctionaliteit voor vluchten
- Complexiteit door integratie met meerdere vervoerders (NS en Deutsche Bahn)
- Gebruikslimieten bij NS kunnen bij populariteit een knelpunt vormen

### 8.2. ADR-002 Adapter keuzes voor reisapplicatie

#### Datum: 2025-03-26

#### Status
Voorgesteld

#### Context
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

#### Considered Options

| Forces                     | Directe API-integratie | Adapter Pattern |
|----------------------------|------------------------|-----------------|
| Uitbreidbaarheid           | -                      | ++              |
| Externe Service Toevoeging | Moeilijk               | Eenvoudig       |
| API Wijzigings-impact      | Groot                  | Klein           |
| Systeemcomplexiteit        | Laag                   | Gemiddeld       |
| Front-end Stabiliteit      | Laag                   | Hoog            |
| Onderhoudbaarheid          | -                      | ++              |

#### Decision
We kiezen voor Adapter pattern (met service adapters).
Doordat we een abstractielaag tussen de service en onze applicatie wat directe
afhankelijkheden voorkomt en het systeem flexibeler maakt.
Ook krijgt elke externe service zijn eigen adapter.

#### Consequences
##### Positieve effecten
* Minimale impact wanneer externe API's wijzigen
* Voorkomt wijzigingen in de frontend bij backend aanpassingen
* Maximale flexibiliteit bij het toevoegen van nieuwe externe services

##### Risico's
* Toename in systeemcomplexiteit
* Initieel is er meer ontwikkelingstijd nodig

### 8.3. ADR-003 Databasebeslissing

#### Datum: 2025-03-21

#### Status
Voorgesteld

#### Context
We hebben een database nodig voor een applicatie die structured data verwerkt met complexe queries, sterke consistentie vereist en schaalbaar moet zijn.
Wij gaan er van uit dat TripTop uitgroeit tot een applicatie die ver buiten Nederland en uiteindelijk ook wereldwijd gebruikt gaat worden.

#### Considered Options

| **Criteria**             | **MySQL** | **MongoDB** | **CouchDB** |  
|--------------------------|-----------|-------------|-------------|  
| **Schema Flexibiliteit** | +         | ++          | +           |  
| **Query Complexiteit**   | ++        | +           | -           |
| **Schaalbaarheid**       | -         | ++          | +           |
| **Betrouwbaarheid**      | /         | +           | ++          |

Omdat de schaalbaarheid erg belangrijk is voor deze applicatie weegt deze meer bij het overwegen van welk systeem wij gaan gebruiken.

#### Decision
**MongoDB** is de beste keuze vanwege de sterke schaalbaarheid, goede prestaties en prima betrouwbaarheid voor een applicatie die gemaakt is om sterk te groeien en een veeltal aan gebruikers gaat hebben.
1. **Schaalbaarheid**: MongoDB en CouchDB zijn als NoSQL systemen beide beter schaalbaar dan MySQL, met MongoDB als winnaar tussen de twee NoSQL systemen.
2. **Betrouwbaarheid**: CouchDB is in haar kern opgezet als een systeem dat goed kan omgaan met onbetrouwbare / wegvallende hardware en daardoor het meest betrouwbare systeem in deze vergelijking. MongoDB presteert echter beter, met nog steeds ruim voldoende betrouwbaarheid.

#### Consequences TO DO

### 8.4. ADR-004 `extends` voor uitbreidbaarheid met nieuwe bouwstenen

#### Datum: 2025-03-28

#### Status
Voorgesteld

#### Context
Onze **Triptop**-applicatie is opgebouwd uit verschillende bouwstenen, zoals vervoer, verblijf en activiteiten. We willen de applicatie uitbreiden met nieuwe bouwstenen, zoals een helikoptertour of een nieuwe activiteit. Om dit te kunnen doen, moet de applicatie flexibel zijn om:

- Nieuwe typen bouwstenen toe te voegen zonder bestaande code te wijzigen.

##### Specifieke ontwerpvraag:
- Hoe maak je de applicatie uitbreidbaar met nieuwe bouwstenen? (Reisservice zelf aanpassen met `extends`)

#### Considered Options

| Forces               | Switch-statement  | Interface + Compositie | `extends` (Inheritance) |
|----------------------|-------------------|--------------------------|--------------------------|
| Uitbreidbaarheid     | +                 | ++                       | ++                       |
| Onderhoudbaarheid    | +                 | ++                       | ++                       |
| Ontwikkelingstijd    | ++                | +                        | ++                       |

#### Decision
We kiezen voor het gebruik van **`extends`** om de applicatie uitbreidbaar te maken met nieuwe bouwstenen. Deze aanpak biedt de meeste flexibiliteit en maakt het mogelijk om nieuwe bouwstenen toe te voegen zonder bestaande code te wijzigen. Door gebruik te maken van `extends` kunnen we nieuwe bouwstenen implementeren door een nieuwe klasse te maken die de bestaande basisklasse uitbreidt. Dit maakt het eenvoudig om nieuwe functionaliteit toe te voegen en bestaande code te hergebruiken.

#### Consequences

##### Positieve consequenties:
- De applicatie is eenvoudig uitbreidbaar met nieuwe bouwstenen
- De ontwikkelingstijd wordt verkort doordat bestaande code hergebruikt kan worden

##### Risico's en beperkingen:
- Het gebruik van `extends` kan leiden tot een complexere codebase als het niet zorgvuldig wordt toegepast
- Unit testing kan uitdagender worden bij veel gedeeld gedrag in de basisklasse

### 8.5. ADR-005 TITLE

> [!TIP]
> These documents have names that are short noun phrases. For example, "ADR 1: Deployment on Ruby on Rails 3.0.10" or "ADR 9: LDAP for Multitenant Integration". The whole ADR should be one or two pages long. We will write each ADR as if it is a conversation with a future developer. This requires good writing style, with full sentences organized into paragraphs. Bullets are acceptable only for visual style, not as an excuse for writing sentence fragments. (Bullets kill people, even PowerPoint bullets.)

#### Context

> [!TIP]
> This section describes the forces at play, including technological, political, social, and project local. These forces are probably in tension, and should be called out as such. The language in this section is value-neutral. It is simply describing facts about the problem we're facing and points out factors to take into account or to weigh when making the final decision.

#### Considered Options

> [!TIP]
> This section describes the options that were considered, and gives some indication as to why the chosen option was selected.

#### Decision

> [!TIP]
> This section describes our response to the forces/problem. It is stated in full sentences, with active voice. "We will …"

#### Status

> [!TIP]
> A decision may be "proposed" if the project stakeholders haven't agreed with it yet, or "accepted" once it is agreed. If a later ADR changes or reverses a decision, it may be marked as "deprecated" or "superseded" with a reference to its replacement.

#### Consequences

> [!TIP]
> This section describes the resulting context, after applying the decision. All consequences should be listed here, not just the "positive" ones. A particular decision may have positive, negative, and neutral consequences, but all of them affect the team and project in the future.

## 9. Deployment, Operation and Support

> [!TIP]
> Zelf beschrijven van wat je moet doen om de software te installeren en te kunnen runnen.