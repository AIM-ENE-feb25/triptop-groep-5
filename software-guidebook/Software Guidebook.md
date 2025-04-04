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

#### Hoe ga je om met aanroepen van externe services die niet beschikbaar zijn en toch verwacht wordt dat er waardevolle output gegeven wordt?
Het principle wat het beste bij deze vraag past, is het Open/Closed Principle. Dit principle stelt dat bestaande code niet gewijzigd hoeft te worden 
om nieuwe functionaliteit toe te voegen. <br>
In de context van de ADR bij deze vraag:
- De caching-strategie laat het systeem uitbreidbaar zijn voor verschillende soorten gegevens en verschillende vervaltijden, zonder de kernfunctionaliteit te verwijderen.
- Het systeem kan nieuwe caching-regels toevoegen zonder bestaande implementaties te verstoren.
- Wanneer nieuwe externe services worden toegevoegd, kan het caching-mechanisme hierop worden toegepast zonder dat de basisarchitectuur verandert.

Bovendien maakt dit principe het mogelijk om in de toekomst eenvoudig andere fallback-strategieën toe te voegen, zonder dat de kernfunctionaliteit van de applicatie
hoeft te worden aangepast. Het systeem blijft gesloten voor wijzigingen in bestaande componenten, maar open voor uitbreiding met nieuwe caching-regels of strategieën.

#### Hoe kunnen we verschillende betalingssystemen integreren voor de verschillende bouwstenen?
Het principle wat het beste bij deze vraag past, is het open-closed principle. Dit principe stelt dat een systeem open moet zijn voor uitbreiding, maar gesloten voor aanpassing. je moet nieuwe functionaliteit kunnen toevoegen zonder bestaande code te hoeven wijzigen. . <br>

In de context van de ADR bij deze vraag:

- Dankzij het Strategy Pattern kunnen nieuwe betaalstrategieën eenvoudig worden toegevoegd zonder dat bestaande strategieën of de controller aangepast hoeven te worden

- Het systeem blijft stabiel en betrouwbaar ook wanneer nieuwe betaalmethodes worden toegevoegd

- De basisarchitectuur blijft ongewijzigd omdat uitbreiding plaatsvindt door toevoeging van losse strategieklassen.

#### Hoe zorg je dat een wijziging in een of meerdere APIs niet leidt tot een grote wijziging in de applicatie? Specifieker: hoe zorg je ervoor dat een wijziging in de API van een externe service niet leidt tot een wijziging in de front-end maar flexibel kan worden opgevangen door de back-end?

_Encapsulate what varies_ is het best passende design principle voor deze vraag.
Door een adapter te gebruiken die eventuele veranderingen kan opvangen scherm ik het programma zelf effectief af van eventuele externe veranderingen.
Eventueel met een verdere uitwerking dan ik hier gedaan heb, zou je zelfs een 2e service kunnen aansluiten die als fallback gebruikt kan worden mocht de huidige wegvallen. Maar dat is buiten de scope van deze opdracht

Het idee achter _encapsulate what varies_ is het afschermen van delen van code die vaak veranderen van de rest van je code (de delen die (grotendeels) hetzelfde blijven).
In dit geval hoef je in principe alleen de adapter aan te passen als er een grote verandering is van buitenaf.

Het pattern Adapter sluit perfect aan bij _Encapsulate what varies_ door die afscherming te zijn voor het hoofdprogramma
 waarin de aanpassingen kunnen worden gedaan en eventuele onverwachtheden in kunnen worden opgevangen.

## 7. Software Architecture

###     7.1. Containers

> [!IMPORTANT]
> Voeg toe: Container Diagram plus een Dynamic Diagram van een aantal scenario's inclusief begeleidende tekst.

![Container Diagram Triptop](./images/containerdiagram.png)

#### Boeken van een reis
![Container Diagram Triptop reis boeken](./images/boekreiscontainerdiagram.png)

#### Inloggen
![Container Diagram Triptop inlog](images/logincontainerdiagram.png)
#### Betalingssystemen
![Container Diagram Triptop betaalsystemen](images/betaalsysteemcontainerdiagram.png)
Dit diagram toont de interactie tussen de webapplicatie, API Gateway, backend service en externe services voor het betalen van een reis. Het laat ook zien hoe de applicatie omgaat met verschillende betalingssystemen.

- Reiziger → Vraagt gegevens op via de Web Applicatie (Angular)
- Web applicatie -> verstuurt verzoeken naar Spring Boot webapp (HTTP)
- Spring boot webapp -> Verstuurt verzoeken door naar de controller.
- Betaalcontroller -> Verwerkt verzoeken en roept de juiste adapter aan.
- BetaalStrategyFactory -> Maakt de juiste adapter aan op basis van de gekozen betaalmethode.
![Container Diagram Triptop betaalsystemen]

#### Vervangen externe API
![Container Diagram Triptop API Vervangen](./images/VervangenContainerDiagram.png)

#### Caching
![Container Diagram Triptop caching](images/cachingContainerDiagram.png)
Dit diagram toont hoe een webapplicatie gegevens opvraagt via een API Gateway, die verzoeken doorstuurt naar een backend service. De backend haalt gegevens op van een externe service of gebruikt MongoDB als cache om beschikbaarheid te garanderen bij storingen.

- Gebruiker → Vraagt gegevens op via de Web Applicatie (Angular) 
- API Gateway → Routeert verzoeken naar de Backend Service (Spring Boot)
- Backend Service → Behandelt verzoeken, haalt gegevens op en implementeert caching 
- MongoDB → Slaat data en cache op met TTL-indexen 
- Externe Service → Bronnen van originele gegevens 
- Monitoring (Prometheus/Grafana) → Houdt systeemprestaties en servicebeschikbaarheid bij

Caching garandeert dat data beschikbaar blijft, zelfs als de externe service uitvalt.

###     7.2. Components

> [!IMPORTANT]
> Voeg toe: Component Diagram plus een Dynamic Diagram van een aantal scenario's inclusief begeleidende tekst.

#### Backend (dynamic)
![Component Diagram Triptop backend](./images/backendcomponentdiagram.png)

#### Frontend (dynamic)
![Component Diagram Triptop frontend](images/frontendcomponentdiagram.png)

#### Betalingssystemen
![Component Diagram Triptop betaalsystemen](images/betaalsysteemcomponentdiagram.png)
Dit diagram toont de interactie tussen de webapplicatie, API Gateway, backend service en externe services voor het betalen van een reis. Het laat ook zien hoe de applicatie omgaat met verschillende betalingssystemen.
- Reiziger → Vraagt gegevens op via de Web Applicatie (Angular).
- Betaalcontroller → Verwerkt inkomende HTTP-verzoeken en start het betalingsproces.
- BetaalStrategyFactory → Bepaalt en instantieert de juiste strategie op basis van de gekozen betaalmethode.
- BetaalStrategy → Interface die de gemeenschappelijke logica definieert voor alle betaalstrategieën.
- paypalBetaling → Concrete implementatie van BetaalStrategie, verwerkt betaling via de PayPal API.
- iDealBetaling → Concrete implementatie van BetaalStrategie, verwerkt betaling via de iDEAL API.
- creditcardBetaling → Concrete implementatie van BetaalStrategie, verwerkt betaling via een creditcarddienst.

#### Caching
![Component Diagram Triptop caching](images/cachingComponentDiagram.png)
Dit diagram toont hoe de applicatie gegevens ophaalt en caching toepast bij service-onbeschikbaarheid.

- Eindgebruiker → Vraagt gegevens op via de API-Gateway. 
- Data Service → Bepaalt via de Strategy Selector of data direct wordt opgehaald of uit de cache komt. 
- Service Client → Haalt data op bij de Externe Service als deze beschikbaar is. 
- Cache Manager → Gebruikt de Cache Database (MongoDB) als back-up bij service-uitval. 
- Monitoring Service → Houdt beschikbaarheid en cache-efficiëntie bij.

###     7.3. Design & Code

> [!IMPORTANT]
> Voeg toe: Per ontwerpvraag een Class Diagram plus een Sequence Diagram van een aantal scenario's inclusief begeleidende tekst.

Maak van de DB class een facade.

### Hoe zorg je dat een wijziging in een of meerdere APIs niet leidt tot een grote wijziging in de applicatie? 

De adapter is zo geschreven dat hij de data die van een externe service afkomt eerst vergelijkt mat wat
de tripService verwacht door checkDataFormat(). Is die niet gelijk aan het verwachtte patroon dan wordt er geen data doorgestuurd. 
Als oplossing voor dit probleem bestaat translateDataFormat(), daarmee zetten we de data om naar het juiste patroon, dit wordt wel doorgestuurd.

![API Wijzigingen Opvangen](images/classDiagramTripTopAPIWijzigingOpvangen.png)


### Hoe maak je de applicatie uitbreidbaar met nieuwe bouwstenen?

Door TripService() uit te breiden met TripServiceV2 kan er gemakkelijk extra functionaliteit toegevoegd worden aan de applicatie zonder al te veel te hoeven friemelen met de code die in gebruik is.

![Uitbreidbaarheid](images/classDiagramTripTopUitbreidbaarheid.png)

### Hoe zorg je ervoor dat je makkelijk een nieuwe externe service kan toevoegen?

Door 'simpelweg' een nieuwe adapter aan de TripService te koppelen kan er een nieuwe externe service gekoppeld worden aan de applicatie

![Service Toevoegen](images/classDiagramTripTopServiceToevoegen.png)

### Hoe ga je om met aanroepen van externe services die niet beschikbaar zijn en toch verwacht wordt dat er waardevolle output gegeven wordt?
#### Design pattern
Het <b>Strategy Pattern</b> is ideaal in dit scenario, omdat:
1. Het scheidt de beslissing over wanneer en hoe te cachen van de daadwerkelijke implementatie van de caching.
2. Het maakt het mogelijk om verschillende caching-strategieën te definiëren en deze dynamisch te wisselen, zonder de kern van de applicatie te wijzigen.
3. Het kan verschillende caching-algoritmes encapsuleren voor verschillende gegevenstypen en deze configureerbaar maken.
4. Als in de toekomst toch besloten wordt om alsnog meerdere services als fallback toe te voegen, kan dit als een nieuwe strategie worden geïmplementeerd zonder de bestaande code te veranderen.
5. Het stelt het systeem in staat om eenvoudig te schakelen tussen het halen van gegevens uit de cache of van de externe service.

In de context van de ADR zou je een interface kunnen maken voor het ophalen van gegevens, met concrete implementaties voor directe service-aanroepen en cache-gebaseerde ophaalmethoden. Het systeem kan dan
dynamisch wisselen tussen deze strategieën, afhankelijk van de beschikbaarheid van de externe services.

#### Class Diagram
![Class diagram caching](./images/ClassdiagramCaching.png)

Key components van dit diagram:
1. `DataFetchStrategy` Interface: Dit is de kern van het Strategy Pattern. Het definieert een gemeenschappelijke interface voor alle strategieën om data op te halen, met de methode ```fetchData()```.
2. Concrete strategieën:
- `DirectServiceStrategy`: Haalt gegevens rechtstreeks op via de externe service wanneer deze beschikbaar is.
- `CacheStrategy`: Haalt gegevens uit de cache en valt terug op een alternatieve strategie wanneer er geen cache-data beschikbaar is.
3. `DataFetchContext`: Gebruikt de huidige strategie om gegevens op te halen. Dit stelt de applicatie in staat om tijdens runtime te wisselen tussen verschillende strategieën.
4. `StrategySelector`: Beslist welke strategie gebruikt moet worden op basis van de beschikbaarheid van de service.
5. Ondersteunende klassen:
- `ServiceClient`: Verzorgt de communicatie met externe services.
- `CacheProvider`: Beheert de cache-operaties (opslaan, ophalen, invalideren).
- `RequestParams`: Bevat de parameters voor het verzoek, inclusief het type data dat wordt opgevraagd.
- `Datatype` <b>Enum</b>: categoriseert verschillende typen gegevens voor de juiste TTL-configuratie.

#### Sequence Diagram
![Sequence diagram caching](images/sequenceDiagramCaching.png)

Stap voor stap uitleg:
1. De Client vraagt data op bij DataFetchContext met de methode fetchData(requestParams).

2. DataFetchContext bepaalt welke strategie gebruikt moet worden door StrategySelector aan te roepen.

3. StrategySelector controleert of de externe service beschikbaar is door isServiceAvailable() aan ServiceClient te vragen.

4. Als de service beschikbaar is:
- StrategySelector kiest DirectServiceStrategy.
- DirectServiceStrategy roept callService(requestParams) aan op ServiceClient.
- ServiceClient levert de gevraagde data terug.
- DirectServiceStrategy stuurt de data terug naar DataFetchContext, die het vervolgens naar de Client retourneert.

5. Als de service niet beschikbaar is:
- StrategySelector kiest CacheStrategy.
- CacheStrategy controleert bij CacheProvider of de data al in de cache zit met get(key).
- Als de data in de cache zit (cache hit):
  - CacheProvider retourneert de cached data.
  - CacheStrategy stuurt deze data door naar DataFetchContext, die het teruggeeft aan de Client.
- Als de data niet in de cache zit (cache miss):
  - CacheStrategy schakelt over naar DirectServiceStrategy om alsnog de data op te halen.
  - DirectServiceStrategy roept callService(requestParams) aan op ServiceClient.
  - ServiceClient retourneert de data.
  - DirectServiceStrategy stuurt de data door naar CacheStrategy.
  - CacheStrategy slaat de data op in CacheProvider met een bepaalde tijd-tot-leven (TTL).
  - CacheStrategy retourneert de data naar DataFetchContext, die het terugstuurt naar de Client.
  
### Hoe kunnen we verschillende betalingssystemen integreren voor de verschillende bouwstenen?
#### Design pattern
Het <b>Strategy pattern + factory pattern</b> is ideaal in dit scenario, omdat:
1. Het zorgt voor een duidelijke scheiding van verantwoordelijkheden tussen betaalstrategieën, objectcreatie en controlelogica.
2. Het systeem flexibel uitbreidbaar blijft zonder bestaande code te hoeven aanpassen (OCP).
3. Nieuwe betaalmethodes eenvoudig kunnen worden toegevoegd met minimale impact op de rest van de applicatie.

In de context van de ADR zou je een interface kunnen maken voor de betaalstrategieën, met concrete implementaties voor elke betaalmethode. De factory kan dan de juiste strategie instantiëren op basis van de gebruikerskeuze of configuratie.
#### Class Diagram
![Class diagram betalingssystemen](images/betaalsysteemclassdiagram.png)

Key components van dit diagram:
1. `BetaalStrategy` Interface: Dit is de kern van het Strategy Pattern. Het definieert een gemeenschappelijke interface voor alle betaalstrategieën, met de methode ```verwerkBetaling()```.
2. `Betaalcontainer` Klasse: Dit is de controller die verantwoordelijk is voor het afhandelen van de REST aanvragen van de betalingen. Het maakt gebruik van de `BetaalStrategyFactory` om de juiste strategie te kiezen.
3. `BetaalStrategyFactory`: Bevat de logica om op basis van gebruikersinput (zoals "paypal") de juiste implementatie van BetaalStrategie terug te geven.
4. `BetalingsRequest` Klasse: Een DTO (Data Transfer Object) die de gegevens bevat die nodig zijn voor een betaling.
5. `idealBetaling`, `creditcardBetaling`, `paypalBetaling`: Dit zijn concrete implementaties van de `BetaalStrategy` interface. Elke klasse implementeert de specifieke logica voor het verwerken van betalingen via de betreffende betaalmethode.

#### Sequence Diagram
![Sequence diagram betalingssystemen](images/betaalsysteemsequencediagram.png)
Stap voor stap uitleg:
1. De reiziger start een betaling via de webinterface. De frontend stuurt een POST /betalen verzoek naar de BetaalController met o.a. de gekozen betaalmethode en het bedrag.
2. BetaalController ontvangt het verzoek en roept de methode maakStrategie(methode) aan op BetaalStrategieFactory om te bepalen welke strategie gebruikt moet worden.
3. BetaalStrategieFactory bepaalt op basis van de methode-parameter (bijvoorbeeld "paypal") welke implementatie van BetaalStrategie moet worden teruggegeven:
- Bij "paypal" wordt PaypalBetaling gekozen.
- Bij "ideal" wordt IdealBetaling gekozen.
- Bij "creditcard" wordt CreditcardBetaling gekozen.
4. De gekozen concrete strategie (PaypalBetaling, IdealBetaling of CreditcardBetaling) wordt teruggegeven aan de controller.
5. BetaalController voert vervolgens de methode verwerkBetaling(BetalingsRequest) uit op de gekozen strategie. Deze methode implementeert de concrete logica voor het uitvoeren van de betaling.
6. De strategie maakt een API-aanroep naar de externe betaaldienst (zoals PayPal API of iDEAL API) om de betaling uit te voeren.
7. De externe betaaldienst retourneert een antwoord
8. De concrete strategie stuurt het resultaat van de betaling terug naar de BetaalController.
9. BetaalController retourneert de uiteindelijke HTTP-response of foutmelding naar de frontend, die deze toont aan de gebruiker als betaalbevestiging of foutmelding.

### Hoe zorg je dat een wijziging in een of meerdere APIs niet leidt tot een grote wijziging in de applicatie? Specifieker: hoe zorg je ervoor dat een wijziging in de API van een externe service niet leidt tot een wijziging in de front-end maar flexibel kan worden opgevangen door de back-end?

In mijn ADR (8.8) heb ik onderzoek gedaan naar een aantal mogelijke opties om deze ontwerpvraag te beantwoorden,
zoals daar ook staat ben ik uitgekomen op 2 opties: De API vervangen en VPN/Tunneling.
Uiteindelijk heb ik gekozen voor Vervanging mede omdat deze oplossing het probleem daadwerkelijk verhelpt in plaats van omzeilt.
Verdere beredenering is in het ADR te vinden. 

#### Design Pattern
Ik heb gekozen voor het Pattern: Adapter
De adapter is een goede keuze omdat het een tussenlaag is tussen de backend en de buitenwereld, hierin kunnen wij een vertaallaag stoppen om zo het vervangen van een api te vergemakkelijken.
In deze adapter kunnen wij zodra een nieuwe versie van een api wordt aangekondigd functionaliteit inbouwen voor de vernieuwde variant zonder de oude te hoeven laten vallen.
Daarna houden wij de functionaliteit om met de versie te communiceren die wij op dat moment het beste achtten (aan de hand van bvb behoefte vanuit de opdrachtgever of evt. problemen met de nieuwe versie).
In principe kunnen wij dit volhouden tot de oude api 'ge-sunset' wordt, maar ivm de veiligheidsrisico's die in de onderzoeksvraag staan raden wij dit af.
Het idee van de Adapter hier is een soepele overgang bieden tussen API versies zonder onnodig downtime te hoeven ervaren.

#### Class Diagram

![Class Diagram Vervangen API](./images/VervangenClassDiagram.png)

In deze class diagram staat aangegeven hoe de Adapter te werk moet gaan. Je geeft mee welke API er gebruikt moet worden in de vorm van een Boolean, 
waarna de Adapter die api gebruikt en (als nodig) omzet naar wat TripService verwacht qua data.


#### Sequence Diagram 

![Sequence Diagram Vervangen API](./images/VervangenSequenceDiagram.png)

##### Stap voor stap

##### Start (oude API)
Stap 1. **Customer** start de 'Applicatie'.

Stap 2. De applicatie haalt accommodatiegegevens op via de Oude API

Stap 3. TripService vraagt om de gegevens aan de Adapter

Stap 4. De adapter vraagt de gegevens aan de oude api

Stap 5. De oude Booking.com api geeft informatie terug

Stap 6. TransformDataToOutput krijgt data volgens de bekende standaard binnen en hoeft de informatie niet te vertalen.

Stap 7. Deze data gaat terug naar TripService

Stap 8. De data wordt in de 'Applicatie weergegeven'

Stap 9. **Customer** krijgt de informatie te zien op zijn scherm


##### Start (Nieuwe API)
###### Nummering is apart door dubbele sequence diagram

Stap 1. **Customer** Start de 'Applicatie'

Stap 10. De applicatie haalt accommodatiegegevens op via de Nieuwe API

Stap 11. TripService vraagt om de gegevens aan de Adapter

Stap 12. De adapter vraagt de gegevens aan de nieuwe api

Stap 13. De nieuwe Booking.com api geeft informatie teru

Stap 14. TransformDataToOutput krijgt data volgens een bekende maar verkeerde standaard binnen en vertaalt de informatie naar iets waar TripService iets mee kan

Stap 15. Deze data gaat terug naar TripService

Stap 16. De data wordt in de 'Applicatie weergegeven'

Stap 17. **Customer** krijgt de informatie te zien op zijn scherm



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

#### Consequences 
Schaalbaar systeem voor groeiende gebruikersbasis

- MongoDB maakt horizontale schaalbaarheid mogelijk, waardoor de applicatie goed kan meegroeien met de verwachte internationale uitbreiding.

Flexibele dataopslag

- Door het schema-loze ontwerp kunnen we eenvoudig nieuwe features en datatypes toevoegen zonder grote migraties uit te voeren.

Complexe queries vereisen extra optimalisatie

- In vergelijking met relationele databases kunnen complexe queries lastiger zijn. Indexering en aggregaties moeten goed worden geoptimaliseerd om prestaties te garanderen.

Consistentiemanagement vereist aandacht

- MongoDB biedt configuraties voor strong consistency, maar standaard werkt het met een eventually consistent model. Dit moet goed worden afgestemd op de eisen van de applicatie.

Mogelijke leercurve voor het team

- Als het team meer ervaring heeft met SQL-gebaseerde systemen, kan extra training nodig zijn om MongoDB effectief te gebruiken.

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
- Unit testing kan uitdagender worden bij veel gedeeld gedrag in de basisklass

### 8.5. ADR-005 Design patterns voor Triptop

#### Datum: 2025-03-28

#### Status
Voorgesteld

#### Context
Om de ontwikkeling van de **Triptop**-applicatie makkelijker te maken willen we gebruik maken van design patterns.

##### Considered Options
##### Hoe maak je de applicatie uitbreidbaar met nieuwe bouwstenen?

| Forces                | Stategy | Factory | Template Method Pattern |Decorator Pattern|Chain of Responsibility|State Pattern|Command Pattern|
|-----------------------|---|---------|------------------------|---|---|---|---|
| Ondersteund extends   |++|+|++|+|+|+|0| 
| flexibiliteit         |++| +|0|+|+|+|+|
| Uitbreidbaarheid      |++|-|+|0|0|0|-|
| Eenvoud implementatie |+|++|0|-|-|-|-|

#### Hoe zorg je ervoor dat je makkelijk een nieuwe externe service kan toevoegen? (Adapters goed schrijven)
| Forces                | Adapter | Facade | Proxy | Bridge | Decorator |
|-----------------------|---|---------|-------|--------|-----------|
| Interface afscherming |++|+| +     | +      | -         |
| API Ondersteuning     |++|+| 0     | +      | -         |
| Makkelijk toevoegen   |++|+| 0     | +      | 0         |
|Testbaarheid           |++|+| +     | 0      | 0         |

#### Hoe zorg je dat een wijziging in een of meerdere APIs niet leidt tot een grote wijziging in de applicatie?
| Forces               |Facade|Anti-corruption layer|Proxy|Decorator|
|----------------------|------|---------------------|-----|---------|
| Schermt front-end af |+|++|+|-|

#### Decision

##### Hoe maak je de applicatie uitbreidbaar met nieuwe bouwstenen?
We kiezen ervoor om voor deze ontwerpvraag de design patern **Strategy** te gebruiken. Met het gebruik van de design patern Strategy kunnen we de applicatie uitbreidbaar maken met nieuwe bouwstenen. Deze aanpak biedt de meeste flexibiliteit en maakt het mogelijk om nieuwe bouwstenen toe te voegen zonder bestaande code te wijzigen. Door gebruik te maken van Strategy kunnen we nieuwe functionaliteit toevoegen en bestaande code hergebruiken.

##### Hoe zorg je ervoor dat je makkelijk een nieuwe externe service kan toevoegen? (Adapters goed schrijven)
We kiezen er voor om voor deze ontwerpvraag de design patern **Adapter** te gebruiken. Met het gebruik van de design patern Adapter kunnen we makkelijk een nieuwe externe service toevoegen. Deze aanpak biedt de meeste flexibiliteit en maakt het mogelijk om nieuwe externe services toe te voegen zonder bestaande code te wijzigen. Door gebruik te maken van Adapter kunnen we gemakkelijk nieuwe API services toevoegen zonder extra code te schrijven ook is het makkelijk om de code met unit tests te testen.

##### Hoe zorg je dat een wijziging in een of meerdere APIs niet leidt tot een grote wijziging in de applicatie?

#### Consequences

##### Positieve consequenties:
- De applicatie is eenvoudig uitbreidbaar met nieuwe bouwstenen
- De ontwikkelingstijd wordt verkort doordat bestaande code hergebruikt kan worden
- De applicatie is flexibel en kan gemakkelijk worden aangepast aan veranderende eisen
- De codebase wordt eenvoudiger en beter onderhoudbaar
- De applicatie is eenvoudig te testen en te onderhouden

##### Risico's en beperkingen:
- Het gebruik van Strategy kan leiden tot een complexere codebase als het niet zorgvuldig wordt toegepast
- Het kan lastig zijn om de juiste strategie te kiezen voor een specifieke situatie
- Het kan moeilijk zijn om te begrijpen hoe de verschillende strategieën samenwerken in de applicatie
- Het gebruik van Adapter kan leiden tot een complexere codebase met veel boiler code
- Het kan voorkomen dat de API verkeerd vertaald wordt naar de intere interface
- Als de API veranderd moet de adapter ook aangepast worden


### 8.6. ADR-006 Service niet beschikbaar

## Datum: 2025-04-01

## Status
Geaccepteerd

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

### 8.7 ADR Betalings systemen en de design patterns van de implementatie
## Datum: 2025-03-28

## Status
Geaccepteerd

## Context
Om de gebruikers van de Triptop-applicatie vrijheid te geven in hun betalingskeuze moeten er verschillende betalingssystemen worden ondersteund. Om dat zo makkelijk moggelijk te maken willen dat er verschillende betaalmethoden als bouwstenen kunnen toegevoegd worden.
Het systeen moet:
- Flexibel zijn om verschillende betalingssystemen te ondersteunen.
- Herbruikbaar en onderhoudbaar blijven.
- Betalingslogica gescheiden houden van de rest van de applicatie.

## Considered Options
### Betalingssystemen
| Forces                       | PayPal | iDEAL | Creditcard | Apple Pay | Google Pay | Alipay |
|------------------------------|--------|-------|------------|-----------|------------|--------|
| Beschikbaarheid in Nederland | ++     | ++    | ++         | ++        | ++         | --     |
| Gebruikers Nederland         | ++     | ++    | +          | 0         | -          | --     |
| Wereldwijde dekking          | +      | --    | ++         | +         | +          | 0      |
| Gebruiksgemak                | ++     | ++    | ++         | ++        | ++         | -      |
| Kosten                       | +      | ++    | 0          | 0         | 0          | -      |

### Design Patterns
| Forces                              | Strategy + Factory | Template method | Command | Decorator | Abstract Factory |
|-------------------------------------|--------------------|-----------------|---------|-----------|------------------|
| Uitbreidbaarheid                    | ++                 | -               | 0       | -         | +                |
| Herbruikbaarheid                    | ++                 | +               | +       | 0         | +                |
| Scheiding van verantwoordelijkheden | ++                 | -               | 0       | 0         | +                |
| Implementatiecomplexiteit           | ++                 | +               | 0       | 0         | --               |


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
- Iets meer code-infrastructuur nodig: extra klassen per betaalmethode en een factory.
- Factory is gevoelig voor typfouten (bijv. "paypal" vs "PayPal") tenzij er met enum of mapping gewerkt wordt.

## 8.8 ADR: Interactie met verouderde externe API's

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


## 9. Deployment, Operation and Support

> [!TIP]
> Zelf beschrijven van wat je moet doen om de software te installeren en te kunnen runnen.