<p align="center"><a href="PRIVACY.md">English</a> · <b>Italiano</b></p>

# Privacy

TMPlayer for Apple, per iPhone e iPad. In vigore dal 13 novembre 2026. Fa fede la
[versione inglese](PRIVACY.md).

TMPlayer for Apple è un lettore video non ufficiale per Telegram. Parla con Telegram direttamente
dal tuo iPhone o iPad, con il tuo account Telegram. Non c'è un account TMPlayer, né un server dello
sviluppatore a cui arrivino i tuoi dati. Lo sviluppatore non riceve mai l'accesso al tuo Telegram,
le tue chat, i nomi dei file, cosa guardi né i tuoi video.

È software libero e open source pubblicato dal suo sviluppatore (Killua99x su GitHub), basato su
[TMPlayer](https://github.com/dracu-lah/TMPlayer) per Android, TV e computer. Le domande su questa
pagina vanno nelle [issue](https://github.com/Killua99x/TMPlayer-for-apple/issues) di questo
repository. Le issue sono pubbliche: non scriverci mai il tuo numero di telefono, un codice di
accesso o altri dati personali.

## Cosa resta sul tuo dispositivo

TDLib di Telegram conserva la sessione con cui sei entrato, le informazioni delle chat in cache, le
miniature e le parti dei video che hai guardato. TMPlayer conserva le impostazioni, i preferiti, i
punti di Continua a guardare e i video che scarichi. Tutto sta nello spazio dell'app sul tuo iPhone
o iPad, che le altre app non possono leggere, ed eliminando l'app si elimina tutto. Un video che
salvi altrove, nell'app File o in una cartella che scegli, è una copia che resta lì.

Mentre un video è in riproduzione, il suo titolo compare nei controlli In riproduzione di iOS, quindi
la schermata di blocco, il Centro di Controllo e un dispositivo collegato possono mostrarlo.

## Riprendi sugli altri dispositivi

Spento finché non lo accendi in Impostazioni. Acceso, TMPlayer tiene i tuoi punti di Continua a
guardare in un messaggio nei tuoi Messaggi salvati di Telegram, così un altro dispositivo con lo
stesso account può riprendere da dove ti sei fermato. Allo sviluppatore non arriva nulla: il
messaggio è nel tuo account come qualsiasi altro. Contiene, per un massimo di 40 video recenti, la
chat e il messaggio da cui viene il video, dove ti sei fermato, la durata, quando l'hai guardato
l'ultima volta, il titolo, il nome della chat, il nome del file e la dimensione. Spegnendo
l'impostazione si ferma subito. Il messaggio resta nei Messaggi salvati finché non lo elimini lì.

## Proxy

Se Telegram è bloccato dove ti trovi, puoi aggiungere un proxy in Impostazioni (MTProto, SOCKS5 o
HTTP). Viene salvato nel database di TDLib sul tuo dispositivo e usato per la connessione a
Telegram. TMPlayer non gestisce proxy e non ne fornisce elenchi. Quello che scrivi va solo al proxy
che hai scelto, che vede il tuo indirizzo IP.

## Con chi parla TMPlayer

Ognuno di questi vede il tuo indirizzo IP, come per qualsiasi connessione. A nessuno viene inviato
il tuo account Telegram.

- **Telegram**, per l'accesso, le chat e i canali, i video e le loro miniature, secondo
  l'[informativa di Telegram](https://telegram.org/privacy). Dove Telegram mostra un messaggio
  sponsorizzato, TMPlayer comunica a Telegram quando è stato visto e quando è stato toccato, come
  ogni app di Telegram deve fare.
- **TMDB, TVmaze e AniList**, per locandine e trame. Attivo di default; si spegne in Impostazioni,
  Locandine e dettagli. Una ricerca invia il titolo ripulito del video, e l'anno, la stagione e
  l'episodio letti dal nome, mai il file. Le risposte restano sul dispositivo al massimo sei mesi, e
  le Impostazioni possono cancellarle.
- **OMDb e Wikidata**, per i punteggi IMDb, Rotten Tomatoes e Metacritic di un titolo, quando ne
  apri la pagina con queste ricerche attive. Una richiesta invia solo l'id IMDb del titolo trovato.
- **TheIntroDB, IntroDB.app e AniSkip**, per sapere dove sono la sigla, il riassunto e i titoli di
  coda di un episodio, per il pulsante che li salta, quando le ricerche e i Pulsanti per saltare
  l'intro sono entrambi attivi (lo sono di default). Una richiesta invia l'id del titolo trovato, la
  stagione e l'episodio.
- **OpenSubtitles.com**, solo quando scegli Cerca online fra i sottotitoli. Una ricerca invia il
  nome del file del video o il suo titolo, stagione ed episodio, il suo hash OpenSubtitles (un
  numero ricavato dalla dimensione e dai primi e ultimi 64 KB, mai il file) e le lingue che vuoi.
  Accedere in Impostazioni, Sottotitoli online, invia a OpenSubtitles il tuo nome utente e la tua
  password di OpenSubtitles.
- **SubDL**, lo stesso tipo di ricerca, solo con una tua chiave SubDL, se l'hai inserita.
- **MSone**, sottotitoli in malayalam, solo quando scegli MSone fra i sottotitoli, attraverso il
  gateway subs.tmplayer.org che lo sviluppatore di TMPlayer gestisce su Cloudflare. Una ricerca
  invia l'id IMDb o TMDB del titolo, oppure il titolo ripulito e l'anno, e la stagione e
  l'episodio.
- **tmplayer.org e api.github.com**, solo quando Telegram non risponde da un po': TMPlayer apre una
  connessione verso ciascuno per capire se internet funziona, e non invia nulla.

TMPlayer non cerca aggiornamenti da solo. AltStore o SideStore leggono il file sorgente di questo
repository su GitHub per proporre ogni nuova versione, secondo le loro regole e quelle di GitHub.

Un link che apri dall'app (Buy Me a Coffee, PayPal, GitHub, una pagina come questa) si apre nel
browser, e vale l'informativa di quel sito. L'app non invia nulla insieme al link.

## Cosa TMPlayer non fa

Niente analisi, niente SDK pubblicitari, nessun tracciamento fra app e nessun rapporto sugli
arresti anomali. Segnala un problema, in Impostazioni, apre nel browser una nuova issue su GitHub
con la versione dell'app, il modello del dispositivo, la versione di iOS e la lingua dell'app già
compilati. Non parte nulla finché non la invii, e prima puoi cambiare o togliere qualsiasi cosa.

## Eliminare i tuoi dati

- Esci da Telegram, in Impostazioni, scollega Telegram su questo dispositivo e cancella il database
  di TDLib, la cache dei video guardati, le impostazioni, i preferiti, Continua a guardare e
  l'elenco dei video visti. I download restano, a meno che tu non spunti Elimina anche i miei
  download.
- Eliminando l'app si elimina tutto ciò che ha salvato sul dispositivo.
- Il tuo account Telegram è di Telegram, non di TMPlayer. Per eliminarlo usa
  [telegram.org/deactivate](https://telegram.org/deactivate).

## I tuoi diritti

Dove ti si applica una legge come il GDPR, puoi chiedere quali dati personali ha lo sviluppatore,
farli correggere o cancellare, e presentare reclamo all'autorità per la protezione dei dati del tuo
paese. Lo sviluppatore non conserva dati personali di chi usa l'app: nessun account, nessun
rapporto, nessun log.

## Modifiche

Una modifica a ciò che dice questa pagina aggiorna la data in alto ed è elencata qui.

- 13 novembre 2026: prima versione.
