# INDICE
1. [Introduzione](#Introduzione)
2. [Modello di dominio](#Modello-di-dominio)
3. [Requisiti specifici](#Requisiti-specifici)
4. [System Design](#System-Design)
    - [Stile architetturale adottato](#Stile-architetturale-adottato)
    - [Diagramma dei package](#Diagramma-dei-package)
    - [Diagramma dei componenti](#Diagramma-dei-componenti)
    - [Commenti](#Commenti-SD)
5. [OO Design](#OO-Design)
    - [Diagrammi delle classi e diagrammi di sequenza](#Diagrammi-delle-classi-e-diagrammi-di-sequenza)
    - [Design pattern utilizzati](#Design-pattern-utilizzati)
    - [Commenti](#Commenti-OO)
6. [Riepilogo del test](#Riepilogo-del-test)
7. [Manuale utente](#Manuale-utente)
8. [Processo di sviluppo e organizzazione del lavoro](#Processo-di-sviluppo-e-organizzazione-del-lavoro)
9. [Analisi retrospettiva](#Analisi-retrospettiva)
    - [Soddisfazioni](#Soddisfazioni)
    - [Insoddisfazioni](#Insoddisfazioni)
    - [Cosa ci ha fatto impazzire](#Cosa-ci-ha-fatto-impazzire)


# Introduzione
**SNA4SO** è una applicazione di ***social network analysis*** (*SNA*) applicata a [**Stack Overflow**](https://stackoverflow.com/) (*SO*), con interfaccia a linea di comando.

[***Social network analysis***](https://it.wikipedia.org/wiki/Analisi_delle_reti_sociali) è il processo di investigazione delle strutture sociali attraverso l'uso di reti e teoria dei grafi.

[***Stack Overflow***](https://it.wikipedia.org/wiki/Stack_Overflow_%28sito%29) è un sito web che fa parte della rete Stack Exchange in cui si possono porre domande riguardo a vasti argomenti di programmazione.

Il modello utilizzato per l'applicazione **SNA4SO** è un grafo orientato:
- I **nodi** sono gli utenti registrati sulla piattaforma **Stack Overflow**.
- Gli **archi** diretti partono dall’utente che risponde verso l'utente che ha posto una domanda.

L'applicazione opera su una *base di dati* a cui è possibile accedere in modalità *sola lettura* attraverso le *API* di **Google Big Query**. <br>
Il dump dei dati di Stack Overflow utilizzato risale al *2016/06*.

L'esecuzione avviene a linea di comando via [Docker](https://hub.docker.com/).

L'output dell'esecuzione di ***SNA4SO*** è un link ad uno *spreadsheet* creato attraverso le *API* di **Google API Services Sheets** e **Google API Services Drive**.

Tramite le opzioni inserite da riga di comando si possono analizzare le attività degli utenti (es. pone una domanda in *Maggio 2016*) e le loro interazioni con altri utenti (*es. utente 86 risponde alla domanda di utente 74560*). <br>
Le opzioni permettono di analizzare le domande/risposte relative ad un dato tag (*es. utenti che hanno risposto a domande con tag `matplotlib`*), una data specifica o anche visualizzare quanto spesso un utente specificato interagisce con altri utenti. <br>
È possibile decidere il limite massimo di risultati mostrati nello *spreadsheet*.

**SNA4SO** è stata realizzata durante il corso di [*INGEGNERIA DEL SOFTWARE*](http://www.di.uniba.it/~lanubile/ingsw/) dal gruppo ***dijkstra*** composto da:
- [Giuseppe Colavito](https://github.com/peppocola)
- [Antonio Fiore](https://github.com/AntoFlow)
- [Paolo Gasparro](https://github.com/paologas91)
- [Antonio Grisulli](https://github.com/GreenSully)
- [Lorenzo Loconte](https://github.com/loreloc)
- [Gianluca Losciale](https://github.com/LuzDeGea) <br>

Nell'anno accademico 2018/2019.

<br> <br>
[Torna all'indice...](#Indice)

# Modello di dominio

<center>
<img width="700" alt="Modello_di_dominio.jpg" src="./images/Modello_di_dominio.jpg">
</center>

<br> <br>
[Torna all'indice...](#Indice)

# Requisiti specifici

* Visualizzare la lista dei primi 100 id utente (User) che hanno fatto almeno una domanda (Question) in un dato anno, mese e giorno

  Story points: 8

  Criteri di accettazione:
  Eseguendo il comando

  `docker run --rm softeng1819infuniba/dijkstra yyyy=2016 mm=02 dd=11 type=question limit=100`

  il risultato è una lista di owner_user_id univoci e non nulli, ordinati in modo crescente come da esempio in figura:

  ![sprint1-query1](./images/UserStories/sprint1-query1.png)

* Visualizzare la lista dei primi 100 id utente (User) che hanno dato almeno una risposta (Answer) in un dato anno, mese e giorno.

  Story points: 5

  Criteri di accettazione:

  Eseguendo il comando

  `docker run --rm softeng1819infuniba/dijkstra yyyy=2016 mm=02 dd=11 type=answer limit=100`

  il risultato è una lista di owner_user_id univoci e non nulli, ordinati in modo crescente come da esempio in figura:

  ![sprint1-query2](./images/UserStories/sprint1-query2.png)

* Visualizzare la lista dei primi 100 id utente (User) che hanno fatto almeno un Post in un dato mese, anno, giorno (un Post può essere una domanda o una risposta).

  Story points: 2

  Criteri di accettazione:

  Eseguendo il comando

  `docker run --rm softeng1819infuniba/dijkstra yyyy=2016 mm=02 dd=11 type=post limit=100`

  il risultato è una lista di owner_user_id univoci e non nulli, ordinati in modo crescente come da esempio in figura:

  ![sprint1-query3](./images/UserStories/sprint1-query3.png)

* Visualizzare la lista dei primi 100 id utente (User) che hanno fatto almeno una domanda (Question) su un dato argomento (Tag) in un dato mese e anno.

  Story points: 3

  Criteri di accettazione:

  Eseguendo il comando

  `docker run --rm softeng1819infuniba/dijkstra yyyy=2016 mm=02 type=question taglike=java limit=100`

  il risultato è una lista di owner_user_id univoci e non nulli, ordinati in modo crescente come da esempio in figura:

  ![sprint1-query4](./images/UserStories/sprint1-query4.png)

* Visualizzare la lista dei primi 100 id utente (User) che hanno dato almeno una risposta (Answer) su un dato argomento (Tag) in un dato mese e anno

  Story points: 8

  Criteri di accettazione:

  Eseguendo il comando

  `docker run --rm softeng1819infuniba/dijkstra yyyy=2016 mm=02 type=answer taglike=java limit=100`

  il risultato è una lista di owner_user_id univoci e non nulli, ordinati in modo crescente come da esempio in figura:

  <img src="./images/UserStories/sprint1-query5.png" alt="sprint1-query5" width="100"/>

* Visualizzare la lista dei primi 100 id utente (User) che hanno fatto almeno un Post su un dato argomento (Tag) in un dato mese e anno (un Post può essere una domanda o una risposta)

  Story points: 5

  Criteri di accettazione:

  Eseguendo il comando

  `docker run --rm softeng1819infuniba/dijkstra yyyy=2016 mm=02 type=post taglike=java limit=100`

  il risultato è una lista di owner_user_id univoci e non nulli, ordinati in modo crescente come da esempio in figura:

  ![sprint1-query6](./images/UserStories/sprint1-query6.png)

* Visualizzare la lista delle prime 100 coppie (from, to) relative a domande (Question) poste in un dato anno, mese e giorno

  Story points: 5

  Criteri di accettazione:

  Eseguendo il comando

  `docker run --rm softeng1819infuniba/dijkstra yyyy=2016 mm=02 dd=11 type=question edge=yes limit=100`

  il risultato è una lista come da esempio in figura:

  <img width="94" alt="sprint2-query1" src="./images/UserStories/sprint2-query1.png">


  - Verificare che per ogni domanda e risposta sia visualizzata una riga con la coppia (from, to) dove to è l'id dell'utente che ha posto la domanda e from è l'id dell'utente che ha risposto.
  - Verificare che le coppie (from, to) non contengano valori nulli
  - Verificare che le coppie (from, to) siano ordinate in modo crescente
  - Verificare che le coppie (from, to) non siano ripetute.

* Visualizzare la lista delle prime 100 coppie (from, to) relative a domande (Question) poste da un determinato utente.

  Story points: 3

  Criteri di accettazione:

  Eseguendo il comando

  `docker run --rm softeng1819infuniba/dijkstra type=question user=1109 edge=yes limit=100`

  il risultato è una lista come da esempio in figura:

  <img width="94" alt="sprint2-query2" src="./images/UserStories/sprint2-query2.png">


  - Verificare che per ogni domanda e risposta sia visualizzata una riga con la coppia (from, to) dove to è l'id dell'utente che ha posto la domanda, specificato come valore di user, e from è l'id dell'utente che ha risposto.
  - Verificare che le coppie (from, to) non contengano valori nulli
  - Verificare che le coppie (from, to) siano ordinate in modo crescente
  - Verificare che le coppie (from, to) non siano ripetute.

* Visualizzare la lista delle prime 100 coppie (from, to) relative a risposte (Answer) date da un determinato utente.

  Story points: 3

  Criteri di accettazione:

  Eseguendo il comando

  `docker run --rm softeng1819infuniba/dijkstra type=answer user=86 edge=yes limit=100`

  il risultato è una lista come da esempio in figura:

  <img width="94" alt="sprint2-query3" src="./images/UserStories/sprint2-query3.png">



  - Verificare che per ogni domanda e risposta sia visualizzata una riga con la coppia (from, to) dove to è l'id dell'utente che ha posto la domanda e from è l'id dell'utente che ha risposto, specificato come valore di user
  - Verificare che le coppie (from, to) non contengano valori nulli
  - Verificare che le coppie (from, to) siano ordinate in modo crescente
  - Verificare che le coppie (from, to) non siano ripetute.

* Visualizzare la lista delle prime 100 triple (from, to, weight) relative a domande (Question) poste in un dato anno, mese e giorno

  Story points: 3

  Criteri di accettazione:

  Eseguendo il comando

  `docker run --rm softeng1819infuniba/dijkstra yyyy=2016 mm=02 dd=11 type=question edge=yes weight=yes limit=100`

  il risultato è una lista come da esempio in figura:

  <img width="131" alt="sprint2-query4" src="./images/UserStories/sprint2-query4.png">


  - Verificare che per ogni domanda e risposta sia visualizzata una riga con la tripla (from, to, weight) dove to è l'id dell'utente che ha posto la domanda, from è l'id dell'utente che ha risposto e weight è il peso associato al numero di volte che l'utente from ha risposto all'utente to.
  - Verificare che le triple (from, to, weight) non contengano valori nulli,
  - Verificare che le triple (from, to, weight) siano ordinate in modo crescente
  - Verificare che non ci siano triple con (from, to) ripetuti.

* Visualizzare la lista delle prime 100 triple (from, to, weight) relative a domande (Question) poste da un determinato utente.

  Story points: 3

  Criteri di accettazione:

  Eseguendo il comando

  `docker run --rm softeng1819infuniba/dijkstra type=question user=1109 edge=yes weight=yes limit=100`

  il risultato è una lista come da esempio in figura:

  <img width="124" alt="sprint2-query5" src="./images/UserStories/sprint2-query5.png">


  - Verificare che per ogni domanda e risposta sia visualizzata una riga con la tripla (from, to, weight) dove to è l'id dell'utente che ha posto la domanda, specificato come valore di user, from è l'id dell'utente che ha risposto e weight è il peso associato al numero di volte che l'utente from ha risposto all'utente to.
  - Verificare che le triple (from, to, weight) non contengano valori nulli,
  - Verificare che le triple (from, to, weight) siano ordinate in modo crescente
  - Verificare che non ci siano triple con (from, to) ripetuti.

* Visualizzare la lista delle prime 100 triple (from, to, weight) relative a risposte (Answer) date da un determinato utente.

  Story points: 3

  Criteri di accettazione:

  Eseguendo il comando

  `docker run --rm softeng1819infuniba/dijkstra type=answer user=86 edge=yes weight=yes limit=100`

  il risultato è una lista come da esempio in figura:

  <img width="124" alt="sprint2-query6" src="./images/UserStories/sprint2-query6.png">


  - Verificare che per ogni domanda e risposta sia visualizzata una riga con la tripla (from, to, weight) dove to è l'id dell'utente che ha posto la domanda, from è l'id dell'utente che ha risposto, specificato come valore di user, e weight è il peso associato al numero di volte che l'utente from ha risposto all'utente to.
  - Verificare che le triple (from, to, weight) non contengano valori nulli,
  - Verificare che le triple (from, to, weight) siano ordinate in modo crescente
  - Verificare che non ci siano triple con (from, to) ripetuti.

<br> <br>
[Torna all'indice...](#Indice)

# System Design
<br><br>

# Stile architetturale adottato


Lo stile architetturale adottato segue il pattern del *Model View Presenter*.

Infatti lo stile MVP è uno stile adatto per i sistemi interattivi, in cui c'è una netta separazione tra la logica di presentazione dei dati *(View)*  e la logica di business *(Model)*.<br>
Di conseguenza si nota un'indipendenza tra le tre grandi componenti che sono alla base di questo pattern.

I tre componenti principali sono:
* **Model** : Gestisce i metodi per l'accesso ai dati. Il model nel *MVP* non comunica direttamente con *view* e non si preoccupa di come i dati vengono rappresentati, ma comunica direttamente con il *presenter*.<br>
Nella nostra applicazione SOQuery e QueryResult lavorano sul *model*.<br>
Nello specifico *SOQuery* si occupa del recupero dei dati tramite l'interazione con l' API GoogleBigQuery, mentre *QueryResult* specifica come i dati vengono rappresentati all'interno dell'applicazione.

* **View** : Permette la rappresentazione visuale dei dati (sono previste viste multiple) e fornisce una prima interfaccia con l'utente. La view non ha informazione sulla gestione dei dati, ma si occupa solo di rappresentarli. Infatti non ha una comunicazione diretta con il *model* ma ottiene le informazioni necessarie sui dati direttamente del *presenter*.<br>
Nel nostra caso l'interazione tra l'utente e l'applicazione è caratterizzata dall'utilizzo di un interfaccia di tipo *CLI* (Command Line Interface).<br>
I dati vengono visualizzati su un foglio elettronico generato da *Google API Services Sheets* e condiviso da *Google API Services Drive*.

* **Presenter** : Gestisce la sequenza delle interazioni tra l'applicazione e l'utente, facendo controlli sull'input e fornendo comandi per il *modello* o la *vista*.
A differenza del *MVC* dove è presente il *controller*, nel *MVP* la presenza del *Presenter* garantisce la totale separazione tra il modello e le viste.<br>
Infatti il Model non notifica eventi alle viste.
Nell'applicazione le componenti che si occupano di gestire le sequenze di interazioni sono *Parser, Arguments, Query* e *GoogleDocsUtils.*<br>
*Parser* si occupa di analizzare sintatticamente il comando fornito in input dall'utente e inserisce i dati un oggetto di tipo "Arguments".
<br>*Arguments* contiene i parametri scritti dall'utente per costruire la query.
<br>*Query* in base ai parametri forniti da Arguments genera la query associata al comando.
<br>*GoogleDocsUtils* scrive i risultati delle query nel foglio elettronico che poi verrà visualizzato dell'utente.

<br>
  <img width="600" src="images/SystemDesign/MVP.PNG">
<br><br>

Il nostro MVP non è da ritenersi completamente puro, poichè non è presente una *GUI* e quindi in questo caso le componenti view hanno un ruolo minimale.


# Diagramma dei package


<br><br>
  <img width="600" src="images/SystemDesign/diagramma-package.png">
<br><br>


# Diagramma dei componenti

<br>
<br><br>
  <img width="600" height="" src="images/SystemDesign/Diagramma-componenti.PNG">
<br><br>

# <a name="Commenti-SD"></a>Commenti delle decisioni prese


Inizialmente avevamo pensato di adottare come stile architetturale il *"Pipe and filter"*, ma notando che c'era un interazione frequente con le API di Google e che più volte si comunicava con gli stessi sottosistemi esterni abbiamo optato per utilizzare il *Model-View-Presenter* (**MVP**).

Infatti lo stile MVP è adatto per i sistemi interattivi, in cui c'è una netta separazione tra le varie componenti del sistema che però comunicano frequentemente tra di loro.



<br> <br>
[Torna all'indice](#Indice)


# Manuale utente

* Esecuzione via Docker attraverso il comando:
```bash
docker run --rm softeng1819infuniba/dijkstra <options>
```
* `options` include i seguenti parametri di input:
   - `type`: il tipo di post cercato in Stack Overflow, definito in `{question,answer,post}` (es., `type=post`)
   - `yyyy`: l'anno in cui un post è stato creato (es., `yyyy=2012`)
   - `mm`: il mese in cui un post è stato creato (es., `mm=01`)
   - `dd`: il giorno in cui un post è stato creato (es., `dd=07`)
   - `taglike`: la sottostringa da cercare nei tag applicati alle domande (es., `taglike=java` varrà per le domande taggate con `java`, `java9`, etc.)
   - `limit`: il limite al numero di risultati da restituire in una query (es., `limit=1000`)
   - `edge`: l'opzione per indicare che l'output deve includere gli archi (es., `edge=yes`); il default è output di soli nodi
   - `weight`: l'opzione per indicare che l'output relativo agli archi deve includere anche i pesi (es. `weight=yes`); il default è output di archi senza pesi
   - `user`: l'id dell'utente che ha creato un post (es. `user=86`)

* Al termine di ogni esecuzione di sna4so (output):
  - i risultati di una query sono salvati in un Google Spreedsheet attraverso le relative API
  - l'applicazione stampa a console l'url per acceddere via web a tale foglio di calcolo
  - il foglio di calcolo è accessibile in lettura a chiunque abbia il link
  - il foglio di calcolo può essere salvato in locale in formato CSV

<br> <br>
[Torna all'indice...](#Indice)

# Processo di sviluppo e organizzazione del lavoro
Il processo di sviluppo di **SNA4SO** è avvenuto seguendo un approccio *simil-* [***SCRUM***](https://it.wikipedia.org/wiki/Scrum_%28informatica%29).
Tale approccio ha previsto quattro sprint di durata variabile. così composti:
  - I professori hanno "interpretato" il ruolo del **Product Owner**.
  Per ogni sprint, in aula e via [*Slack*](https://slack.com/intl/en-it/) ci sono stati comunicati i requisiti e le priorità sotto forma di *Definition of Done* e *User Stories*.
  Abbiamo realizzato la *board* del progetto e, dallo Sprint 2, abbiamo anche stimato gli *Story Points* delle varie *User Story* (**Sprint Backlog**).
  <br> <br>
  - La *Definition of Done* definiva lo **Sprint Goal**, l'obiettivo da raggiugnere dal Team alla fine dello sprint.
  <br> <br>
  - La ripartizione del lavoro è avvenuta in modo flessibile. Non c'erano dei ruoli prestabiliti e ogni componente si è proposto autonomamente per lo svolgimento di un compito specifico.
  <br> <br>
  - La lista dei compiti da svolgere è stata realizzata tramite una lavagna virtuale fornita da [**GitHub**](https://github.com/) (*Project Board*).
  La lavagna è stata organizzata in stile [*Kanban*](https://it.wikipedia.org/wiki/Kanban), con *4+1* colonne:
  <br> <br>
    - **TO DO**, compiti non ancora svolti
    - **IN PROGRESS**, compiti in svolgimento
    - **REVIEW**, compiti in attesa di *build* di *Travis-CI* e revisione di almeno un componente del team
    - **READY**, compiti svolti ma in attesa di revisione da parte dei Professori
    - **DONE**, compiti svolti che non necessitano di ulteriore revisione
  <br> <br>
  - Le varie attività sono state gestite seguendo il **GitHub Flow**: <br>
    ogni *Issue* è stata assegnata ad uno o due membri del team che hanno lavorato su un *branch* per implementare cambiamenti nel software.
    Una volta completata la fase di sviluppo e superata la fase di testing, il *branch* è stato *pushato* in remoto per permettere ad uno o più componenti del team di effettuare la fase di review e per attendere l'esito della *build* di *Travis-CI*.
    Superate queste due fasi, venivano risolti i conflitti se presenti e poi effettuato il *merge* sul *master*.
  <br> <br>
  - Non ci sono stati dei veri e propri **Daily Meeting** con orario e durata specifici da rispettare. Il *Daily Meeting* è avvenuto all'inizio di ogni incontro (virtuale o fisico) del team, aveva durata variabile in base all'argomento di discussione, precedeva la ripartizione dei compiti e l'inizio del lavoro vero e proprio.
  <br> <br>
  - Ci sono stati degli ulteriori momenti di discussione sul lavoro svolto e da svolgere, avvenute in aula tra una lezione e l'altra, non sempre immediatamente seguiti da una fase di sviluppo.
  <br> <br>
  - Sia per la fase di sviluppo, sia per i *Daily Meeting*, nella maggior parte dei casi abbiamo comunicato via [**Skype**](https://www.skype.com/it/), essendo i vari componenti del team per la maggior parte pendolari e provenienti da luoghi distanti tra loro. Per lo sviluppo in *Pair-Programming* abbiamo spesso utilizzato lo strumento *condivisione schermo* di [*Skype*](https://www.skype.com/it/) che ci ha permesso di avere maggiore possibilità di aiutarci a vicenda.

<br> <br>
[Torna all'indice...](#Indice)
