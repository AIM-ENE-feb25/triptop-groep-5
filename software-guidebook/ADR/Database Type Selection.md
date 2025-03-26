### Databasebeslissing

We hebben een database nodig voor een applicatie die structured data verwerkt met complexe queries, sterke consistentie vereist en schaalbaar moet zijn.
Wij gaan er van uit dat TripTop uitgroeit tot een applicatie die ver buiten Nederland en uiteindelijk ook wereldwijd gebruikt gaat worden.

### Overwogen Opties:

| **Criteria**             | **MySQL** | **MongoDB** | **CouchDB** |  
|--------------------------|-----------|-------------|-------------|  
| **Schema Flexibiliteit** | +         | ++          | +           |  
| **Query Complexiteit**   | ++        | +           | -           |
| **Schaalbaarheid**       | -         | ++          | +           |
| **Betrouwbaarheid**      | /         | +           | ++          |
| **Totaal**               | 2         | 6           | 3           |  

Omdat de schaalbaarheid erg belangrijk is voor deze applicatie weegt deze meer bij het overwegen van welk systeem wij gaan gebruiken.

### Decision: MangoDB

**Reasoning:**

1. **Schaalbaarheid**: MongoDB en CouchDB zijn als NoSQL systemen beide beter schaalbaar dan MySQL, met MongoDB als winnaar tussen de twee NoSQL systemen.
2. **Betrouwbaarheid**: CouchDB is in haar kern opgezet als een systeem dat goed kan omgaan met onbetrouwbare / wegvallende hardware en daardoor het meest betrouwbare systeem in deze vergelijking. MongoDB presteert echter beter, met nog steeds ruim voldoende betrouwbaarheid.


**Conclusion**: **MongoDB** is de beste keuze vanwege de sterke schaalbaarheid, goede prestaties en prima betrouwbaarheid voor een applicatie die gemaakt is om sterk te groeien en een veeltal aan gebruikers gaat hebben.


