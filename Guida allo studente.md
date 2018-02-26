# Guida allo studente

## Indice
- Passi preliminari
- Accettazione caso di studio e creazione team
- Configurazione Travis CI
- Configurazione locale del progetto
- Lavoro sul codice dell’applicazione
- Controlli di Qualità
- Eseguire immagine docker
- Riferimenti

## Passi preliminari
È necessario effettuare l’iscrizione a diversi servizi che saranno utilizzati durante tutto lo sviluppo del caso di studio. In particolare:

- Iscrizione a [**github.com**](https://github.com)
- Iscrizione a [**education.travis-ci.com**](https://education.travis-ci.com/) (tramite account GitHub) 
- Iscrizione a [**docker.com**](https://www.docker.com)

In aggiunta, occorre installare i seguenti strumenti:

- [**Git**](https://git-scm.com/downloads)
- [ **Docker**](https://www.docker.com/community-edition#/download)
Si suppone che lo studente abbia già installato sulla sua macchina l’ultima versione di **Eclipse  for Java Developers** disponibile.

## Accettazione caso di studio e creazione team
Mediante il canale *Slack* del corso, verrà comunicato un link di *GitHub Classroom* attraverso cui accettare l’assegnazione del caso di studio e creare, o partecipare, ad un team di lavoro. La schermata che apparirà all’apertura del link sarà simile a questa:

![](res/img/guida-studente/Schermata1.png)

Uno dei membri, per ogni gruppo, si prenderà carico di creare un nuovo team, inserendo il nome pattuito preventivamente con il docente. A quel punto gli altri membri potranno aggiungersi tramite il pulsante *Join*.
Questa procedura creerà automaticamente una repository privata nell’organizzazione “Ingegneria del Software, Cdl Informatica, UNIBA” con tutti i membri del gruppo all’interno.

## Configurazione Travis CI
Dopo aver effettuato l’iscrizione e il login su *education.travis-ci.com* ed aver accettato l’assegnazione del caso di studio, occorrerà che uno dei membri del gruppo esegua qualche semplice passo di configurazione.

- Recarsi sulla propria pagina personale (cliccare sul proprio nome e foto di Github in alto a destra)
- Nella parte sinistra dell’interfaccia dovrebbe essere visibile l’organizzazione “Ingegneria del Software, Cdl Informatica, UNIBA”. In caso positivo, selezionarla. In caso negativo provare a premere il bottone *Sync Account*.

![](res/img/guida-studente/OrganizzazioneTravisCI.png)

- Selezionare la repository con il nome del proprio team, all’interno della pagina dell’organizzazione.
- Selezionare quindi *More options* e poi *Settings* 

![](res/img/guida-studente/Schermata2.png)

- Nella sezione *Environment Variables*, tramite il tasto *Add*, definire le seguenti 4 Variabili d’ambiente:

	- **DOCKER\_ORGANIZATION**: il nome dell’organizzazione su *docker.com*
	- **DOCKER\_REPO**: il nome della repository del proprio gruppo su *docker.com*
	- **DOCKER\_USERNAME**: l'username dell’account di gruppo su *docker.com*
	- **DOCKER\_PASSWORD**: la password dell'account di gruppo su *docker.com*

![](res/img/guida-studente/agiove3_SNA4Slack_-_Travis_CI.png)

**N.B.:** è fondamentale che i nomi delle variabili d’ambiente siano scritti esattamente come sono riportati in questa guida.

Si desidera un feedback immediato dello stato della build in Travis CI, consultando direttamente la pagina principale della repository su Github. A questo scopo, è possibile collocare un piccolo *badge* nel file “README.md” che informi se la compilazione e l’esecuzione dei test su Travis CI siano andati a buon fine. Per soddisfare quest’esigenza, è necessario che uno dei membri del gruppo di lavoro svolga le seguenti azioni:

- Cliccare sul *badge* accanto al nome della repository nella pagina del progetto su Travis CI (quello in grigio con su scritto (build|unknown)).
- Selezionare *Markdown*, anziché *Image URL*, nel secondo dropdown.
- Copiare il codice generato in cima al file "README.md" nella cartella di progetto. Questo può essere fatto sia andando a modificare il file direttamente da Github, sia con una *commit* in locale, seguita da una push per aggiornare la repository remota.


## Configurazione locale del progetto
Per rendersi operativi con il progetto in locale, occorre seguire questi passi.

**Clonazione della repository remota**
Come prima attività, è necessario clonare la repository remota sulla propria macchina. Procedere come segue:

- Individuare la cartella nel proprio file system dove posizionare la cartella di progetto;
- Da terminale (Unix) o prompt dei comandi (Windows) spostarsi attraverso il comando *cd* nella cartella scelta al passo precedente;
- Scrivere il comando `git clone <url>` , dove l’url è quello visibile da GitHub premendo il bottone *Clone or Download*, in alto a destra nell’interfaccia. Ad esempio:

![](res/img/guida-studente/agiove3_SNA4Slack__Network_Analysis_and_Visualization_for_Slack_Teams.png)

Se l’operazione è andata a buon fine, siamo quasi pronti per partire… Ma prima, è necessario importare il progetto in Eclipse!

**Importazione del progetto in Eclipse**
Per importare correttamente il progetto in Eclipse, si dovrà seguire solo un semplice accorgimento: anziché creare un progetto Java (scelta di default), si opterà per la creazione di un progetto Gradle. Più nel dettaglio: 

- Da *File* selezionare la voce *Import* per importare il progetto;
- Selezionare sotto la cartella *Gradle*, la voce *Existing Gradle Project*

![](res/img/guida-studente/Import_e_Java_-_Eclipse.png)

- Dopo aver superato l’eventuale *Welcome*, bisognerà specificare come *Project root directory* la cartella di progetto clonata al passo precedente;
- A questo punto terminare l’operazione con *Finish*.


**Modifica della cartella di default per javadoc**
La cartella di default per la generazione di *javadoc* è la cartella **doc**. Per conformità con la struttura della repository di base del progetto, dovremo modificare il percorso e puntare a **doc/javadoc**:

- Premere il tasto destro sulla cartella di progetto di Eclipse. Scegliere quindi l’opzione *Properties*, in coda al menù contestuale;
- Individuare, tra le proprietà, quella denominata *Javadoc Location*;

![](res/img/guida-studente/Properties_for_SNA4Slack.png)

- Tramite il pulsante *Browse*, selezionare il percorso **doc/javadoc** all’interno della cartella di progetto;
- Chiudere la finestra con *Apply and Close*.

## Lavoro sul codice dell’applicazione
Il workflow da utilizzare con git prevede essenzialmente i seguenti passi.

- Per ogni nuova *feature* o *bug fix* occorre creare un nuovo **branch** attraverso il comando `git branch <nome branch> ` ed eseguire `git checkout <nome branch>` per passare dal *master branch* al nuovo branch appena creato. In un comando solo:
	
		git checkout -b <nome-branch> 
	
- Lavorare al codice dell’applicazione. È consigliabile fare piccole **commit** autoconsistenti di volta in volta, con uno scopo ben preciso ed una descrizione dettagliata. *Evitare di fare un’unica grande commit alla fine del lavoro, a meno che la feature o il bug fix non sia davvero di poco conto.*
- Ad ogni nuova **commit** può seguire una **push**. Questo è lasciato alla discrezionalità dello studente; *ovviamente, nel caso in cui ad una feature stiano lavorando più persone, la push diventa necessaria!*
- Quando la modifica è stata correttamente implementata, si consiglia *caldamente* di scrivere adeguati test per validarne la correttezza.
- Dopo l’esecuzione dei test è possibile lanciare gli strumenti di **Quality Assurance** (checkstyle, pmd, findbugs) per assicurarsi di aver scritto codice di qualità. Leggere la sezione *Controlli di Qualità* per ulteriori informazioni.
- Subito prima della fusione del nuovo *branch* con il *master branch*, è opportuno eseguire una `git pull` e lavorare sul codice più aggiornato. Risolvere eventuali conflitti presenti; 
- A questo punto, dunque, si può procedere alla fusione del nuovo *branch* con il *master branch*. Se non lo si è già fatto, spostarsi sul master branch con una `git checkout master` ed eseguire il comando:
	`git merge master`
- Per completare il lavoro, eseguire una push sulla repository remota.
	`git push origin master` o più semplicemente `git push`

## Controlli di Qualità
È possibile operare dei controlli statici sulla qualità del codice Java (QA, quality assurance), grazie a strumenti come *checkstyle*, *pmd*, *findbugs*. Per lanciarli in un colpo solo si può utilizzare *Gradle*.

- Assicurarsi che sia aperta la vista *Gradle Tasks* in Eclipse. In caso negativo, dal menù *Window*, selezionare *Show View* e poi *Other*. La vista si troverà sotto la voce *Gradle*. Nell’eventualità che la vista non compaia, provare a cambiare *perspective* su Eclipse e selezionare *Java EE*: ciò si può fare o premendo Java EE dal bottone in alto a destra o da menù *Window-\>Perspective-\>Open Perspective-\>Other* e poi *Java EE*. 
- Selezionare il nome del progetto e, tra le diverse opzioni, *verification*. 
- Avviare il controllo attraverso l’operazione di **check**, che eseguirà automaticamente sia la build del progetto, sia i test di unità, sia i controlli di qualità.

 ![](res/img/guida-studente/Java_-_SNA4Slack_src_main_java_main_Main_java_-_Eclipse_IDE.png)

- Per verificare gli errori, eventualmente individuati dagli strumenti di QA, si deve aprire la vista *Console*.

**N.B.** Nella configurazione attuale del progetto la presenza di errori non impedisce la corretta compilazione del codice. Si suggerisce, tuttavia, di limitare il più possibile *warnings* ed *errori* segnalati da questi strumenti. 


## Esecuzione immagine docker
Dopo ogni operazione di push sul master branch remoto, Travis-CI tenta di compilare l’applicazione e, in caso di successo, esegue test e controlli di quality assurance. Nel caso in cui la compilazione e i test siano andati a buon fine, Travis-CI  ha il compito di caricare l’immagine del container su docker.com. Per essere certi che il codice non presenti problemi, occorre scaricare l’immagine da docker.com ed eseguire il container mediante l’installazione locale di Docker.

Si svolgano le seguenti operazioni:

- avviare Docker localmente (una volta aperta l’applicazione, bisogna attendere che nel menu di Docker compaia la scritta “Docker is running”)
- digitare nel terminale il seguente comando:

		docker pull <nome_account>/<nome_repository>:<version_number>

Ad esempio, per l’utente registrato su docker.com con l’username `json932` e per la repository denominata `sna4slack` nella sua prima versione, scriviamo:

	docker pull json932/sna4slack:1.0

Attendere che Docker scarichi l’immagine dell’applicazione.

- digitare il comando:

		docker run --rm <nome_account>/<nome_repository>:<version_number>

A questo punto l’applicazione verrà eseguita in un container sul computer locale.
**N.B.:**

1. Il `<version_number>` per l’intera durata del caso di studio si assume essere 1.0.
2. l’opzione `—-rm` serve per far sì che docker fermi l’esecuzione del container nel momento in cui l’applicazione eseguita al suo interno termina.

## Riferimenti

- [Guida](https://chris.beams.io/posts/git-commit/#separate) a come scrivere la descrizione di una commit.
