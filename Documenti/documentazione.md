1. [Introduzione](#introduzione)

  - [Informazioni sul progetto](#informazioni-sul-progetto)

  - [Abstract](#abstract)

  - [Scopo](#scopo)

1. [Analisi](#analisi)

  - [Analisi del dominio](#analisi-del-dominio)
  
  - [Analisi dei mezzi](#analisi-dei-mezzi)

  - [Analisi e specifica dei requisiti](#analisi-e-specifica-dei-requisiti)

  - [Use case](#use-case)

  - [Pianificazione](#pianificazione)

1. [Progettazione](#progettazione)

  - [Design dell’architettura del sistema](#design-dell’architettura-del-sistema)

  - [Design dei dati e database](#design-dei-dati-e-database)

1. [Implementazione](#implementazione)

1. [Test](#test)

  - [Protocollo di test](#protocollo-di-test)

  - [Risultati test](#risultati-test)

  - [Mancanze/limitazioni conosciute](#mancanze/limitazioni-conosciute)

1. [Consuntivo](#consuntivo)

1. [Conclusioni](#conclusioni)

  - [Sviluppi futuri](#sviluppi-futuri)

  - [Considerazioni personali](#considerazioni-personali)

1. [Sitografia](#sitografia)

1. [Allegati](#allegati)


## Introduzione

### Informazioni sul progetto

  - <b>Allievi coinvolti</b>: Matteo Arena, Luca Mazza, Bruno Gomes e Jonas Bertossa
  - <b>Docenti coinvolti</b>: Geo Petrini, Luca Muggiasca
  - <b>Scuola</b>: Scuola Arti e Mestieri (SAM) Trevano, sezione informatica
  - <b>Materia</b>: Modulo 306
  - <b>Durata progetto</b>: 17.01.2020 - xx.xx.2020

### Abstract

  E’ una breve e accurata rappresentazione dei contenuti di un documento,
  senza notazioni critiche o valutazioni. Lo scopo di un abstract efficace
  dovrebbe essere quello di far conoscere all’utente il contenuto di base
  di un documento e metterlo nella condizione di decidere se risponde ai
  suoi interessi e se è opportuno il ricorso al documento originale.

  Può contenere alcuni o tutti gli elementi seguenti:

  -   **Background/Situazione iniziale**

  -   **Descrizione del problema e motivazione**: Che problema ho cercato
      di risolvere? Questa sezione dovrebbe includere l'importanza del
      vostro lavoro, la difficoltà dell'area e l'effetto che potrebbe
      avere se portato a termine con successo.

  -   **Approccio/Metodi**: Come ho ottenuto dei progressi? Come ho
      risolto il problema (tecniche…)? Quale è stata l’entità del mio
      lavoro? Che fattori importanti controllo, ignoro o misuro?

  -   **Risultati**: Quale è la risposta? Quali sono i risultati? Quanto è
      più veloce, più sicuro, più economico o in qualche altro aspetto
      migliore di altri prodotti/soluzioni?

  Esempio di abstract:

  > *As the size and complexity of today’s most modern computer chips
  > increase, new techniques must be developed to effectively design and
  > create Very Large Scale Integration chips quickly. For this project, a
  > new type of hardware compiler is created. This hardware compiler will
  > read a C++ program, and physically design a suitable microprocessor
  > intended for running that specific program. With this new and powerful
  > compiler, it is possible to design anything from a small adder, to a
  > microprocessor with millions of transistors. Designing new computer
  > chips, such as the Pentium 4, can require dozens of engineers and
  > months of time. With the help of this compiler, a single person could
  > design such a large-scale microprocessor in just weeks.*

### Scopo

  Questo progetto ci offre l'opportunità di provare per la prima volta a gestire un progetto in team. I punti importanti, oltre al programma effettivo, sono la documentazione e i diari che devono essere completi ed esaustivi.


## Analisi

### Analisi del dominio

  Questo capitolo dovrebbe descrivere il contesto in cui il prodotto verrà
  utilizzato, da questa analisi dovrebbero scaturire le risposte a quesiti
  quali ad esempio:

  -   Background/Situazione iniziale

  -   Quale è e come è organizzato il contesto in cui il prodotto dovrà
      funzionare?

  -   Come viene risolto attualmente il problema? Esiste già un prodotto
      simile?

  -   Chi sono gli utenti? Che bisogni hanno? Come e dove lavorano?

  -   Che competenze/conoscenze/cultura posseggono gli utenti in relazione
      con il problema?

  -   Esistono convenzioni/standard applicati nel dominio?

  -   Che conoscenze teoriche bisogna avere/acquisire per poter operare
      efficacemente nel dominio?

  -   …

### Analisi e specifica dei requisiti


  |**ID**	|**Nome**			|**Descrizione**|**Importanza**|**Versione**|
  |----|------------|--------|----|------|--------|
  |0|Riconoscere Volto|Capire quando qualcuno si trova davanti alla webcam|Alta|1.0|
  |1|Registrare utente|L'utente deve poter registrare il proprio volto|Alta|1.0|
  |2|Associare volto all'utente|Fare in modo che in base al volto visto dalla webcam il software capisca di che utente si tratta|Alta|1.0|
  |3|Intervallo di blocco|Dopo x tempo che non è presente il volto il PC si deve bloccare|Alta|1.0|
  |4|Interfaccia grafica|Tutte le impostazioni devono essere modificate da un'interfaccia grafica (electron o java) desktop|Alta|1.0|
  |5|Bloccare PC|Il PC si deve bloccare quando non viene riconosciuto un volto|Alta|1.0|
  |6|Scelta intervallo di blocco|L'utente può scegliere dopo quanto tempo il PC si deve bloccare|Media|1.0|
  |7|Modifica impostazioni in modo sicuro|La modifica delle impostazioni richiede una password|Alta|1.0|
  |8|Aggiornamento progressivo volto|I volti vengono sovrascritti ogni giorno per fare in modo che sia il più duraturo possibile|Bassa|1.0|
  |9|Registrazione di più volti|Più utenti (sulla macchina) possono aggiungere il proprio volto|Media|1.0|
  |10|Utente corrente|L'utente da riconoscere deve essere quello dell'account|Bassa|1.0|
  |11|Multipiattaformità|Il software deve poter essere eseguito su i sistemi operativi più usati (Windows, macOs, Linux)|Alta|1.0|
  |12|Cifratura dati volto|I dati del volto sono cifrati|Bassa|1.0|
  |13|Countdown|Quando non viene riconosciuto un volto parte il countdown e poi si spegne|Alta|1.0|
  |14|Shortcut|Tramite una shortcut deve essere possibile attivare e disabilitare le funzionalità del software|Bassa|1.0|
  |15|Utenti aggiuntivi|Dentro ad un account ci possono essere anche degli utenti con il permesso di usare il PC|Media|1.0|
  |16|Mail/notifica di blocco|Quando il PC viene bloccato ci arriverà una mail/notifica|Bassa|1.0|
  |17|Mail/Notifica utilizzo|Quando qualcuno usa il PC e non sei tu arriva una notifica/mail|Bassa|1.0|
  


**Spiegazione elementi tabella dei requisiti:**

**ID**: identificativo univoco del requisito

**Nome**: breve descrizione del requisito

**Descrizione**: descrizione molto riassuntiva che permette di capire il requisito senza ambiguità.

**Importanza**: indica l’importanza di un requisito nell’insieme del
progetto, definita assieme al committente. Ad esempio poter disporre di
report con colonne di colori diversi ha priorità minore rispetto al
fatto di avere un database con gli elementi al suo interno. Solitamente
si definiscono al massimo di 2-3 livelli di priorità.

**Versione**: indica la versione del requisito. Ogni modifica del
requisito avrà una versione aggiornata.

Sulla documentazione apparirà solamente l’ultima versione, mentre le
vecchie dovranno essere inserite nei diari.

### Use case

![use case](UseCase.jpg)

### Pianificazione

Prima di stabilire una pianificazione bisogna avere almeno una vaga idea
del modello di sviluppo che si intende adottare. In questa sezione
bisognerà inserire il modello concettuale di sviluppo che si seguirà
durante il progetto. Gli elementi di riferimento per una buona
pianificazione derivano da una scomposizione top-down della problematica
del progetto.

La pianificazione può essere rappresentata mediante un diagramma di
Gantt.

Se si usano altri metodi di pianificazione (es scrum), dovranno apparire
in questo capitolo.

### Analisi dei mezzi
#### Hardware
|**Nome**|**Proprietario**|**Sistema operativo**|**RAM**|**Storage**|**Scheda grafica**|**CPU**|**Risoluzione**|
|-----|-----|-----|-----|-----|-----|-----|-----|
|Acer Aspire 5|Matteo|Windows 10 1909|16GB|128GB SSD 1TB HDD|nvidia 940mx|i7 7500U|1920x1080|
|MacBook Pro (15-inch, 2017)|Luca|macOS Catalina 10.15.4|16GB|256GB SSD|Radeon Pro 555|i7|2880x1800|
|Acer Predator Orion 3000|Matteo|Windows 10 1903|16GB|500GB SSD 1TB HDD|nvidia Geforce RTX 2060 super|i7 9700|1920x1080 (monitor esterno)|
|Asus|Bruno|Windows 10 1909|4GB|128GB|-|i3 7020U|1366x768|

#### Software

|**Nome**|**Versione**|
|--|--|
|Python|3.6.6|
|cmake|3.16.3|
|dlib|19.7.0|
|face-recognition|1.3.0|
|face-recognition-models|0.3.0|
|numpy|1.18.2|
|opencv-python|4.1.0.25|
|pip|20.0.2|
|psutil|5.7.0|
|netbeans|11.1|
|JRE|12.0.2|
|JDK|12.0.2|
|NSIS|3.05|

## Progettazione

### Design dell’architettura del sistema

Lo script principale (quello che è sempre eseguito) si chiama faceCheck.py e si può trovare nella directory 'C:\Program Files (x86)\FaceLock\Source\faceCheck.py' su windows, ***METTI DIRECTORY MAC*** su macOS e ***METTI DIRECTORY LINUX*** su linux. Una volta avviato lo script viene eseguito in background sulla macchina ospite e ogni secondo accende la webcam per controllare se il proprietario è davanti al computer. Dentro allo script sono presenti molte funzioni per fare in modo che il main possa richiamarle più volte avendo in questa maniera un codice estremamente pulito e molto facile da leggere.

### Design dei dati e database

I dati dei volti sono codificati tramite degli encodings di face-recognition (libreria di python), per leggere i dati del volto o crearne uno nuovo bisogna passare tramite la funzione dedicata della libreria.

### Design delle interfacce

#### Interrfaccia grafica (con le impostazioni)
<img src="FaceLockUI.png" width="400" height="500" style="float: left;"/>
L'interfaccia grafica (in questa schermata ideata per macOS ma praticamente invariata per Windows e Linux) è pensata per essere molto minimale ma che comprende tutte le informazioni indispensabili. Come si può notare infatti le prime informazioni che si notano sono i nomi delle facce da riconcere (John Zillo, Pier Telo e Georgre Bo) con a fianco una x per eliminarli. Subito sotto troviamo la categoria General contenente le preferenze dell'utente, più specificatamente:
 
- *Scelta dei secondi dopo il quale bloccare il computer*
- *Notifica quando il computer viene spento (sul telefono)*
- *Shortcut per abilitare o disattivare lo script che controlla se qualcuno è davanti al computer*
- *Dark mode del programma*

Infine è presente un checkbox per scegliere se notificare (sempre sul telefono) se qualcuno che non è il proprietario sta utilizzando il computer.
<br>
<br>
<br>
<br>
<br>
<br>

#### Countdown

<img src="Countdown.png" width="350" height="250" style="float: left;" hspace="10"/>

In questa immagine è rappresentata come il countdown una volta che non vedrà uno degli utenti definiti in precedenza, ovviamente dovrà essere completamente funzionante e dovrà essere visualizzato unicamente quando lo script è sicuro che davanti alla webcam non ci sia nessuno.
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

### Design procedurale

Descrive i concetti dettagliati dell’architettura/sviluppo utilizzando
ad esempio:

-   Diagrammi di flusso e Nassi.

-   Tabelle.

-   Classi e metodi.

-   Tabelle di routing

-   Diritti di accesso a condivisioni …

Questi documenti permetteranno di rappresentare i dettagli procedurali
per la realizzazione del prodotto.

## Implementazione

In questo capitolo dovrà essere mostrato come è stato realizzato il
lavoro. Questa parte può differenziarsi dalla progettazione in quanto il
risultato ottenuto non per forza può essere come era stato progettato.

Sulla base di queste informazioni il lavoro svolto dovrà essere
riproducibile.

In questa parte è richiesto l’inserimento di codice sorgente/print
screen di maschere solamente per quei passaggi particolarmente
significativi e/o critici.

Inoltre dovranno essere descritte eventuali varianti di soluzione o
scelte di prodotti con motivazione delle scelte.

Non deve apparire nessuna forma di guida d’uso di librerie o di
componenti utilizzati. Eventualmente questa va allegata.

Per eventuali dettagli si possono inserire riferimenti ai diari.

## Test

### Protocollo di test

Definire in modo accurato tutti i test che devono essere realizzati per
garantire l’adempimento delle richieste formulate nei requisiti. I test
fungono da garanzia di qualità del prodotto. Ogni test deve essere
ripetibile alle stesse condizioni.


|Test Case      | TC-001                               |
|---------------|--------------------------------------|
|**Nome**       |Import a card, but not shown with the GUI |
|**Riferimento**|REQ-012                               |
|**Descrizione**|Import a card with KIC, KID and KIK keys with no obfuscation, but not shown with the GUI |
|**Prerequisiti**|Store on local PC: Profile\_1.2.001.xml (appendix n\_n) and Cards\_1.2.001.txt (appendix n\_n) |
|**Procedura**     | - Go to “Cards manager” menu, in main page click “Import Profiles” link, Select the “1.2.001.xml” file, Import the Profile - Go to “Cards manager” menu, in main page click “Import Cards” link, Select the “1.2.001.txt” file, Delete the cards, Select the “1.2.001.txt” file, Import the cards |
|**Risultati attesi** |Keys visible in the DB (OtaCardKey) but not visible in the GUI (Card details) |


### Risultati test

Tabella riassuntiva in cui si inseriscono i test riusciti e non del
prodotto finale. Se un test non riesce e viene corretto l’errore, questo
dovrà risultare nel documento finale come riuscito (la procedura della
correzione apparirà nel diario), altrimenti dovrà essere descritto
l’errore con eventuali ipotesi di correzione.

### Mancanze/limitazioni conosciute

Descrizione con motivazione di eventuali elementi mancanti o non
completamente implementati, al di fuori dei test case. Non devono essere
riportati gli errori e i problemi riscontrati e poi risolti durante il
progetto.

## Consuntivo

Consuntivo del tempo di lavoro effettivo e considerazioni riguardo le
differenze rispetto alla pianificazione (cap 1.7) (ad esempio Gannt
consuntivo).

## Conclusioni

Quali sono le implicazioni della mia soluzione? Che impatto avrà?
Cambierà il mondo? È un successo importante? È solo un’aggiunta
marginale o è semplicemente servita per scoprire che questo percorso è
stato una perdita di tempo? I risultati ottenuti sono generali,
facilmente generalizzabili o sono specifici di un caso particolare? ecc

### Sviluppi futuri
  Migliorie o estensioni che possono essere sviluppate sul prodotto.

### Considerazioni personali
  Cosa ho imparato in questo progetto? ecc

## Bibliografia

### Bibliografia per articoli di riviste
1.  Cognome e nome (o iniziali) dell’autore o degli autori, o nome
    dell’organizzazione,

2.  Titolo dell’articolo (tra virgolette),

3.  Titolo della rivista (in italico),

4.  Anno e numero

5.  Pagina iniziale dell’articolo,

### Bibliografia per libri


1.  Cognome e nome (o iniziali) dell’autore o degli autori, o nome
    dell’organizzazione,

2.  Titolo del libro (in italico),

3.  ev. Numero di edizione,

4.  Nome dell’editore,

5.  Anno di pubblicazione,

6.  ISBN.

### Sitografia

1.  URL del sito (se troppo lungo solo dominio, evt completo nel
    diario),

2.  Eventuale titolo della pagina (in italico),

3.  Data di consultazione (GG-MM-AAAA).

**Esempio:**

-   http://standards.ieee.org/guides/style/section7.html, *IEEE
    Standards Style Manual*, 07-06-2008.

## Allegati

Elenco degli allegati, esempio:

-   Diari di lavoro

-   Codici sorgente/documentazione macchine virtuali

-   Istruzioni di installazione del prodotto (con credenziali
    di accesso) e/o di eventuali prodotti terzi

-   Documentazione di prodotti di terzi

-   Eventuali guide utente / Manuali di utilizzo

-   Mandato e/o Qdc

-   Prodotto

-   …