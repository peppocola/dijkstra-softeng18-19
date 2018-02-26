[![Build Status][image-1]][1]

# SNA4Slack
La struttura della repository si presenta nel seguente modo:
```
|–– config
|    |–– checkstyle
|–– doc
|    |–– drawings
|    |–– javadoc 
|    |–– Report.md
|–– gradle
|–– lib
|–– res
|–– src
|    |–– main
|    |–– test
|–– .gitignore
|–– .travis.yml
|–– build.gradle
|–– Caso di studio.md
|–– Guida per lo studente.md
|–– gradlew
|–– gradle.bat
|–– README.md
|–– settings.gradle
|–– Workflow - diagramma.xml
```

Nel seguito si dettagliano i ruoli dei diversi componenti:
- **config**: ospita i file di configurazione. L’unica configurazione di base richiesta è quella per il tool checkstyle;
- **doc**: in questa cartella deve essere inserita tutta la documentazione relativa al caso di studio. In particolare, in *drawings* dovranno essere salvati i diagrammi UML e *javadoc* ospiterà la documentazione generata automaticamente per il codice Java. Il file *Report.md* rappresenta la relazione finale del caso di studio, da stilare prima della consegna;
- **gradle**: contiene il jar per il sistema di gestione delle dipendenze *Gradle*.
- **lib**: creata per includere eventuali *jar* di librerie esterne utilizzate dal progetto.
- **res**: la cartella deve contenere tutte le risorse usate dal sistema (immagini, testi ecc.)
- **src**: la cartella principale del progetto, in cui scrivere tutto il codice dell’applicazione. In *main* ci saranno i file sorgente e *test* conterrà i test di unità previsti.
- **.gitignore**: specifica tutti i file che devono essere esclusi dal sistema di controllo versione.
- **.travis.yml**: dettaglia le direttive per assicurare la *continuous integration* attraverso l’uso di Travis CI;
- **build.gradle**: esplicita le direttive e la configurazione per *Gradle*. 
- **Caso di studio.md**: fare riferimento a questo file per la descrizione dettagliata del progetto;
- **Guida per lo studente.pdf:**: elenca e descrive tutti i passi di configurazione necessari per attivare l’intero flusso di lavoro dietro lo sviluppo del caso di studio;
- **gradlew & gradlew.bat**: sono i file eseguibili di *Gradle*, rispettivamente per Unix e per Windows. Vengono generati automaticamente da Eclipse;
- **settings.gradle**: file di configurazione di *Gradle*. Anche quest’ultimo viene generato automaticamente da Eclipse.
- **Workflow.xml**: descrive il flusso di lavoro attraverso il formalismo UML. In particolare, viene usato un *diagramma di attività*.

In alcune cartelle è possibile notare la presenza di un unico file nascosto “.keep”: questo ha il solo scopo di richiedere a Git l’inclusione delle cartelle in cui è contenuto (Git esclude dal *versioning* le cartelle vuote). Pertanto, il file può essere ignorato o eventualmente cancellato nel momento in cui si inserisca almeno un altro file all’interno della cartella.

[1]:	https://travis-ci.org/agiove3/SNA4Slack

[image-1]:	https://travis-ci.org/agiove3/SNA4Slack.svg?branch=master
