# ADR Betalings systemen
## Datum: 2025-03-28

## Status
Voorgesteld

## Context
Om de gebruikers van de Triptop-applicatie vrijheid te geven in hun betalingskeuze moeten er verschillende betalingssystemen worden ondersteund. Om dat ze makkelijk moggelijk te maken willen dat er verschillende betaalmethoden als bouwstenen kunnen toegevoegd worden.

## Considered Options
| **Kenmerken**                    | **PayPal** | **iDEAL** | **Creditcard** | **Apple Pay** | **Google Pay** | **Alipay** |
|----------------------------------|------------|-----------|----------------|---------------|----------------|------------|
| **Beschikbaarheid in Nederland** | ++         | ++        | ++             | ++            | ++             | --         |
| **Wereldwijde beschikbaarheid**  | ++         | --        | ++             | +             | +              | ++         |
| **Gemak van integratie**         | ++         | ++        | ++             | ++            | ++             | ++         |
| **Kosten voor de handelaar**     | +          | ++        | +              | ++            | ++             | ++         |
| **Betaalsnelheid**               | ++         | ++        | ++             | ++            | ++             | ++         |

## Decision
We kiezen ervoor om Paypal , ideal, en creditcard te gebruiken als betaalsystemen.
