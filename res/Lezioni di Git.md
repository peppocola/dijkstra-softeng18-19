# Lezioni di Git

## Lezione 1
Obiettivo della lezione è introdurre brevemente il contesto dei sistemi di controllo versione e approfondire direttamente git e i suoi comandi principali, facendo familiarizzare gli studenti con lo strumento attraverso il tutorial interattivo di GitHub.

- **Introduzione a git**: architettura e comandi principali (Fonte: *slide Sistemi di collaborazione in rete* fino a "git sheet cheats" e tradotte in italiano)
- **Esercitazione** con tutorial interattivo GitHub: [fonte][1]
	1) Initializing (`git init`)
	2) Checking the status (`git status`)
	3) Adding & Committing 
	4) Adding Changes (`git add octocat.txt)`
	5)  Checking for Changes (`git status`)
	6) Committing (`git commit`)
	7) Adding all changes (wildcards con `git add '*.txt'`)
	8) Commit all changes
	9) History (`git log`)
	10) Remote repository (`git remote add`)
	11) Pushing remotely (`git push`)
	12) Pulling remotely (`git pull`)
	13) Differences (`git diff HEAD`)
	14-15) Staged Differences (`git diff --staged`)
	16) Resetting the stage (`git reset`)
	17) Undo (`git checkout -- <target>`)
	18) Branching Out (`git branch`)
	19) Switching Branches (`git checkout <branch>`)
	20) Removing All The Things (`git rm`)
	21) Commiting Branch Changes
	22) Switching Back to master (`git checkout master`)
	23) Preparing to Merge (`git merge`)
	24) Keeping Things Clean (`git branch -d <branch>`)
	25) The Final Push
- [Eventuale] Istruzioni installazione e configurazione git (Fare riferimento a slide dott. Calefato da 25 a 29 o da [qui][2])
  - Installazione 
  - Configurazione *user* e *email* tramite `git config`
  - Aiuto attraverso `git help`
  - Esplicitare come avviare un progetto da riga di comando:
     	cd YOUR_PROJECT_FOLDER
     	git init
- [Eventuale] Presentazione sintetica strumenti grafici
	- *GitKraken* (con un anno gratuito di funzionalità pro con lo Student Developer Pack, disponibile per tutti i sistemi operativi): [fonte][3]
	- *GitHub Desktop*: [fonte][4] (Windows e Mac)
	- *SourceTree* da [fonte][5] (Windows e Mac)
	- *EGit* estensione per Eclipse  [fonte][6]

\**Compiti a casa:* Suggerire agli studenti di ripetere il tutorial GitHub a casa guardando più attentamente i *ProTip*, installare git e fare alcune prove sulla propria macchina.


### Lezione 2
Obiettivo della lezione è approfondire alcune funzionalità più avanzate di git attraverso lo studio di alcuni possibili workflow a complessità crescente. La fonte di riferimento è [questa][7].
- **Centralized Workflow**: struttura generale, spiegazione tramite esempio (John e Mary). Argomenti correlati:
	- Gestione dei conflitti
	- Merging vs Rebasing (Fare riferimento a slide 39 dott. Calefato)
- **Feature Branch Workflow/GitHub Flow**: struttura generale, spiegazione tramite esempio (John, Mary e Bill). Argomenti correlati:
	- Pull Requests [fonte][8]
- **Gitflow Workflow**: struttura generale. Argomenti correlati:
	- Tagging in git [qui][9] (versione web del libro *ProGit*)
- **Forking Workflow**: struttura generale. Argomenti correlati:
	- Forking vs Cloning
- **GitHub**: introduzione e panoramica (Fare riferimento a slide *Ambienti e sviluppo collaborativo* di Sistemi di Collaborazione in rete)
- **Esercitazione**:
	-  *Hello World GitHub* [fonte][10]
	- *Gestione dei conflitti*: questo esercizio mira ad evitare che gli studenti vadano nel panico nel momento in cui si verificano dei conflitti. Un’idea potrebbe essere quella di dividere gli studenti in coppie in cui entrambi modificano uno stesso file: il primo fa una commit/push e l’altro fa una pull e corregge i conflitti. Poi si scambiano i ruoli.
	- *Merging vs Rebasing*: dopo una piccolo esercizio per far sperimentare i comandi principali per la creazione e gestione dei branch, si prova a utilizzare prima un normale merge e poi una rebase.

\**Compiti a casa:* leggere come scrivere i commenti di una commit ([Fonte][11])


[1]:	https://try.github.io/levels/1/challenges/1
[2]:	https://git-scm.com/book/en/v2/Getting-Started-Installing-Git
[3]:	https://www.gitkraken.com
[4]:	https://desktop.github.com
[5]:	https://www.sourcetreeapp.com
[6]:	http://www.eclipse.org/egit/
[7]:	https://www.atlassian.com/git/tutorials/comparing-workflows
[8]:	http://collab.di.uniba.it/tesi-di-laurea/come-sviluppare-un-progetto-di-tesi/
[9]:	https://git-scm.com/book/en/v2/Git-Basics-Tagging
[10]:	https://guides.github.com/activities/hello-world/
[11]:	http://collab.di.uniba.it/tesi-di-laurea/come-sviluppare-un-progetto-di-tesi/