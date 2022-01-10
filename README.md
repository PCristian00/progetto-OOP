<div id="top"></div>

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

-->

<!-- PROJECT LOGO -->
<br />
<div align="center">
<a href="https://github.com/PCristian00/progetto-OOP">
<img src="images/logo.png" alt="Logo" width="80" height="80">
</a>

<h3 align="center">PressureStats</h3>

<p align="center">
Applicazione Java che calcola statistiche di visibilità e pressione di una città data.
<br />
<a href="https://github.com/PCristian00/progetto-OOP/"><strong>Explore the docs »</strong></a>
<br />
<br />
<a href="https://github.com/PCristian00/progetto-OOP">View Demo</a>
·
<a href="https://github.com/PCristian00/progetto-OOP/issues">Report Bug</a>
·
<a href="https://github.com/PCristian00/progetto-OOP/issues">Request Feature</a>
</p>
</div>


<!-- TABLE OF CONTENTS -->
<details>
<summary>Table of Contents</summary>
<ol>   
<li><a href="#about-the-project">About The Project</a></li>
<ul>
	<li><a href="#built-with">Built With</a></li>
	</ul>  
<li><a href="#uml">UML</a></li>
<ul>
	<li><a href="#use-case">Use Case</a></li>
	</ul>
<ul>
	<li><a href="#package-controller">Package Controller</a></li>
	</ul>
<ul>
	<li><a href="#package-model">Package Model</a></li>
	</ul>
<ul>
	<li><a href="#package-service">Package Service</a></li>
	</ul>
<ul>
	<li><a href="#package-statistics">Package Statistics</a></li>
	</ul>
<ul>
	<li><a href="#package-exception">Package Exception</a></li>
	</ul>  
<li><a href="#getting-started">Getting Started</a></li>
<ul>
<!-- <li><a href="#prerequisites">Prerequisites</a></li>-->
	<li><a href="#installation">Installation</a></li>
</ul>  
	<li><a href="#usage">Usage</a></li>
<ul>
	<li><a href="#rotte">Rotte</a></li>
	</ul>
<ul>
	<li><a href="#test">Test</a></li>
	</ul>
<li><a href="#documentazione">Documentazione</a></li>
<!--<li><a href="#roadmap">Roadmap</a></li>-->
<!-- <li><a href="#contributing">Contributing</a></li>-->
<li><a href="#license">License</a></li>
<!--<li><a href="#contact">Contact</a></li>-->
<li><a href="#acknowledgments">Acknowledgments</a></li>
	</ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

### PressureStats
Progetto Programmazione ad Oggetti
di [Settimi Diego](https://github.com/Diego7imi) e [Pietroniro Cristian](https://github.com/PCristian00)

PressureStats permette di salvare dati su <b>pressione</b> e <b>visibilità</b> di una città su un file di testo.

Tale salvataggio viene eseguito una sola volta oppure in automatico ogni tot ore (frequenza scelta dall'utente).

I dati raccolti possono poi essere elaborati per ricavare <b>statistiche</b> su <b>pressione</b> e <b>visibilità</b> della città:
* Pressione massima, minima, media
* Visibilità massima, minima, media
* Varianza Pressione
* Varianza Visibilità

Le <b>statistiche</b> possono quindi essere mostrate in vari <b>filtri</b>:
* <b>Giornaliere</b>: Mostra le statistiche di un intero giorno scelto dall'utente
* <b>Più giorni</b>: Mostra le statistiche per una fascia di giorno scelti dall'utente
* <b>Orarie</b>: Mostra le statistiche comprese tra due ore (di un giorno) scelte dall'utente.

<!--
Here's a blank template to get started: To avoid retyping too much info. Do a search and replace with your text editor for the following: `PCristian00`, `progetto-OOP`, `twitter_handle`, `linkedin_username`, `email`, `email_client`, `PressureStats`, `Applicazione Java che calcola statistiche di visibilità e pressione di una città data.`
-->
<p align="right">(<a href="#top">back to top</a>)</p>

### Built With

* [Spring Initializr](https://start.spring.io/)
* [Eclipse IDE](https://www.eclipse.org/)
* [Postman](https://www.postman.com/)

<p align="right">(<a href="#top">back to top</a>)</p>


## UML
### Use Case

<img src="images/use_case.svg" alt="use_case">

Diagramma dei casi d'uso.
  
<p align="right">(<a href="#top">back to top</a>)</p>

### Package Controller

<img src="images/controller_pkg.svg" alt="controller_pkg">

Contiene il <b>Controller</b>, necessario per gestire le richieste degli utenti.
  
<p align="right">(<a href="#top">back to top</a>)</p>

### Package Model
<img src="images/model_pkg.svg" alt="model_pkg">

Contiene tutta la parte di modellazione delle classi.
	
La classe <b>City</b> contiene le proprietà della città e le relative previsioni.
    
La classe <b>Forecast</b> contiene i dati di pressione, visibilità e la data della loro misurazione.
      
<p align="right">(<a href="#top">back to top</a>)</p>

### Package Service
<img src="images/service_pkg.svg" alt="service_pkg">
	
  Contiene i servizi e le operazioni usate per raccogliere, salvare e mostrare i dati.
	  
  L'interfaccia Service contiene i metodi implementati da ServiceImpl e richiamati dal Controller.
	  
<p align="right">(<a href="#top">back to top</a>)</p>

### Package Statistics
<img src="images/statistics_pkg.svg" alt="statistics_pkg">
	
Contiene le statistiche e metodi per filtrarle e presentarle.
  
<b>Statistics</b> permette di calcolare e salvare le statistiche.

<b>Filters</b> filtra le statistiche calcolate, mostrando statistiche del giorno (intero o solo alcune ore) o di più giorni.

<p align="right">(<a href="#top">back to top</a>)</p>

### Package Exception
<img src="images/exception_pkg.svg" alt="exception_pkg">

Contiene le eccezioni personalizzate di PressureStats.
  
<b>CityStatisticsNotFoundException</b>: Eccezione lanciata se le statistiche della città non sono state trovate.

<b>DayNotFoundException</b>: Eccezione lanciata se il giorno non è stato trovato.

<b>ItalianCityNotFoundException</b>: Eccezione lanciata se la città non è italiana.

<b>WrongHoursPeriodException</b>: Eccezione lanciata se il range orario non è corretto.

<b>WrongMultiplyException</b>: Eccezione lanciata se il moltiplicatore non è ammesso (moltiplicatore minore o uguale a 0.02).

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

<!--
This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.
-->

<!--### Prerequisites
This is an example of how to list things you need to use the software and how to install them.
* npm
```sh
npm install npm@latest -g
```
-->
### Installation

1. Get a free API Key at [https://openweathermap.org/api](https://openweathermap.org/api)
2. Clone the repo
```sh
git clone https://github.com/PCristian00/progetto-OOP.git
```
3. Enter your API in `ServiceImpl.java`
```java
private String apiKey = 'ENTER YOUR API';
```

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage
Per eseguire correttamente le varie richieste, è consigliabile l'utilizzo di [Postman](https://www.postman.com/) o applicazioni simili.
1. Avviare il programma come applicazione SpringBoot
2. Da Postman, fare una chiamata di tipo get con la rotta scelta (vedi tabella)
3. Consultare i dati salvati in /src/main/resources/
4. Terminare il programma
<p align="right">(<a href="#top">back to top</a>)</p>

### Rotte
Le rotte definite sono le seguenti:

N° | Tipo | Rotta | Descrizione
----- | ------------ | -------------------- | ----------------------
[1](#1) | ` GET ` | `/current?id=6542126` | *Restituisce e salva su un file i dati attuali di pressione e visibilità di una città data*
[2](#2) | ` GET ` | `/hourlySave?id=6542126&multiplier=1` | *Finché è in esecuzione, salva ogni tot ore su un file i  dati attuali di pressione e visibilità di una città data.*
[3](#3) | ` GET ` | `/multiSave?multiplier=1` | *Finché è in esecuzione, salva ogni tot ore su dei file i  dati attuali di pressione e visibilità di alcune città selezionate.*
[4](#4) | ` GET ` | `/oneDay?city=Ancona&?date=05-01-2022` | *Restituisce le statistiche di pressione e visibilità di una città data, filtrate per il giorno scelto.*
[5](#5) | ` GET ` | `/moreDays?city=Ancona&?days=3` | *Restituisce le statistiche di pressione e visibilità di una città data, filtrate per i giorni scelti.*
[6](#6) | ` GET ` | `/hourly?city=Ancona&?date=05-01-2022&from=10&to=13` | *Restituisce le statistiche di pressione e visibilità di una città data, filtrate per la fascia oraria scelta.*

<p align="right">(<a href="#top">back to top</a>)</p>

<a name="1"></a>
### /current

```java
@GetMapping("/current")
ResponseEntity<Object> getForecast(@RequestParam(name="id",defaultValue="3169070") String id)
throws ItalianCityNotFoundException 
 ```
Se la città è italiana, restituisce su schermo la misurazione attuale di pressione e visibilità della città scelta, oltre ad alcuni dati come nazione, nome città e posizione geografica.

  ```json
  {
    "id": 3169070,
    "name": "Rome",
    "country": "IT",
    "lat": 41.8947,
    "lon": 12.4839,
    "weather": [
        {
            "pressure": 999,
            "visibility": 10000,
            "dt": 1641804066,
            "date": "10-01-2022 09:41:06"
        }
    ]
}
  ```
La misurazione viene inoltre salvata su un file chiamato `CITYNAME_data.txt`

<p align="right">(<a href="#top">back to top</a>)</p>

<a name="2"></a>
### /hourlySave

```java
@GetMapping(value = "/hourlySave")
public ResponseEntity<Object> saveToFileHourly(@RequestParam(name = "id", defaultValue = "3169070") String id,
  @RequestParam(name = "multiplier", defaultValue = "1") double multiplier)
  throws WrongMultiplyException
 ```
Se la città è italiana e il [Multiplier](#m) è di un valore maggiore di 0.02, restituisce su schermo un messaggio di riepilogo e la misurazione attuale di pressione e visibilità della città scelta, oltre ad alcuni dati come nazione, nome città e posizione geografica.

  ```txt
Il salvataggio avverrà ogni ora
id=3169070, name=Rome, country=IT, lat=41.8947, lon=12.4839, weather=[pressure=999, visibility=10000, dt=1641805793, date=10-01-2022 10:09:53]
  ```
La misurazione viene inoltre salvata automaticamente con frequenza scelta dall'utente tramite il [Multiplier](#m) su un file chiamato `CITYNAME_data.txt`
<p align="right">(<a href="#top">back to top</a>)</p>

<a name="3"></a>
### /multiSave
```java
@GetMapping(value = "/multiSave")
public ResponseEntity<Object> saveToFileHourly(@RequestParam(name = "multiplier", defaultValue = "1") double multiplier)
  throws ItalianCityNotFoundException, WrongMultiplyException
 ```
Se la città è italiana e il [Multiplier](#m) è di un valore maggiore di 0.02, restituisce su schermo un messaggio di riepilogo con la frequenza scelta.

  ```txt
Il salvataggio avverrà ogni ora
Ricontrollare file finali, lasciare in esecuzione applicazione.
  ```
La misurazione viene inoltre salvata automaticamente con frequenza scelta dall'utente tramite il [Multiplier](#m) su 5 file diversi (uno per ogni città) chiamato `CITYNAME_data.txt`.

 Questa funzione è stata usata durante lo sviluppo per scaricare dati per più città contemporaneamente (Ancona, Milano, Napoli, Palermo, Roma).
<p align="right">(<a href="#top">back to top</a>)</p>

<a name="m"></a>
### Utilizzo del Multiplier
Per le rotte [2](#2) e [3](#3) è possibile modificare la frequenza di salvataggio attraverso la variabile <b>Multiplier</b>.

Il valore assegnato a Multiplier viene moltiplicato ad una costante Hour che contiene il valore di 1 ora espresso in millisecondi.

Sono ammessi tutti i valori superiori a `0.02` ma è consigliabile inserire un valore di almeno `0.17` (corrispondente a circa 10 minuti) in quanto OpenWeather aggiorna i propri dati con questa frequenza con un'API gratuita.

Di seguito sono riportati esempi di modifiche di frequenza:
  
Valore | Frequenza di salvataggio |
:----: | :----------: |
  `0.02` | ogni minuto circa (Valore non ammesso, lancia WrongMultiplyException) |
 `0.1` | ogni 6 minuti | 
`0.17` | ogni 10 minuti circa | 
`0.5` | ogni 30 minuti |
`1` | ogni ora (valore default) |
`1.5` | ogni ora e 30 minuti |
`3` | ogni 3 ore |
`10` | ogni 10 ore |
<p align="right">(<a href="#top">back to top</a>)</p>

<a name="4"></a>
### /oneDay
```java
@GetMapping(value="/oneDay")
public ResponseEntity<Object> getStatisticsOneDay(@RequestParam(name = "city", defaultValue = "Rome") String city,
	@RequestParam(name = "date") String date)
 ```
Se esistono dati a riguardo,generati da [1](#1),[2](#2) o [3](#3), restituisce su schermo le statistiche di pressione e visibilità della città scelta nella data scelta.

  ```json
  {
    "date": "09-01-2022",
    "maxMin": {
        "visibilityMin": 3500,
        "visibilityMax": 10000,
        "pressureMax": 1010,
        "pressureMin": 998
    },
    "average": {
        "visibilityAvg": 9065,
        "pressureAvg": 1003
    },
    "city": "Rome",
    "variance": {
        "visibilityVariance": 3528355,
        "pressureVariance": 24
    }
}
  ```
La misurazione viene inoltre salvata su un file chiamato `CITYNAME_stats_DATE.txt`

<p align="right">(<a href="#top">back to top</a>)</p>

<a name="5"></a>
### /moreDays
```java
@GetMapping(value="/moreDays")
public ResponseEntity<Object> getStatisticsMoreDays(@RequestParam(name = "city", defaultValue = "Rome") String city,
			@RequestParam(name = "days") int days)
 ```
Se esistono dati a riguardo,generati da [1](#1),[2](#2) o [3](#3), restituisce su schermo le statistiche di pressione e visibilità della città scelta basate su un numero di giorni scelto dall'utente.

  ERRATO / INCOMPLETO. MODIFICARE
  
  ```json
  {
    "date": null,
    "maxMin": {
        "visibilityMin": 3500,
        "visibilityMax": 10000,
        "pressureMax": 1014,
        "pressureMin": 995
    },
    "average": {
        "visibilityAvg": 9688,
        "pressureAvg": 1007
    },
    "city": "Rome",
    "variance": {
        "visibilityVariance": 1370300,
        "pressureVariance": 33
    }
}
  ```
La misurazione viene inoltre salvata su un file chiamato `CITYNAME_stats_DATE.txt`

<p align="right">(<a href="#top">back to top</a>)</p>

<a name="6"></a>
### /hourly
```java
@GetMapping(value = "/hourly")
public ResponseEntity<Object> getStatisticsHourly(@RequestParam(name = "city", defaultValue = "Rome") String city,
			@RequestParam(name = "date") String date, @RequestParam(name = "from") int from,
			@RequestParam(name = "to") int to) {
 ```
Se esistono dati a riguardo,generati da [1](#1),[2](#2) o [3](#3), restituisce su schermo le statistiche di pressione e visibilità della città scelta basate sulla fascia oraria scelta di un giorno.

  ERRATO / INCOMPLETO. MODIFICARE
  
  ```json
  {
    "date": "09-01-2022",
    "maxMin": {
        "visibilityMin": 10000,
        "visibilityMax": 10000,
        "pressureMax": 1004,
        "pressureMin": 1000
    },
    "average": {
        "visibilityAvg": 10000,
        "pressureAvg": 1002
    },
    "city": "Rome",
    "variance": {
        "visibilityVariance": 0,
        "pressureVariance": 2
    }
}
  ```
La misurazione viene inoltre salvata su un file chiamato `CITYNAME_stats_DATE.txt`
<p align="right">(<a href="#top">back to top</a>)</p>

### Test
Inserire qui test con immagini.

Riga 2

Riga 3
<p align="right">(<a href="#top">back to top</a>)</p>

### Documentazione
Il programma è completamente documentato in Javadoc [qui](/pressurestats/doc).

Ulteriore documentazione con diagrammi UML è disponibile [qui](https://github.com/PCristian00/progetto-OOP/tree/main/UML%20Exports).
<p align="right">(<a href="#top">back to top</a>)</p>
<!-- ROADMAP -->
<!--## Roadmap
<!--
- [] Feature 1
- [] Feature 2
- [] Feature 3
- [] Nested Feature

See the [open issues](https://github.com/PCristian00/progetto-OOP/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTRIBUTING -->
<!--## Contributing
<!--
Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distribuito con licenza MIT. Vedi `LICENSE.txt` per maggiori informazioni.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTACT -->
<!--## Contact
<!--
Your Name - [@twitter_handle](https://twitter.com/twitter_handle) - email@email_client.com

Project Link: [https://github.com/PCristian00/progetto-OOP](https://github.com/PCristian00/progetto-OOP)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments


* [Best README Template](https://github.com/othneildrew/Best-README-Template)


<p align="right">(<a href="#top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/PCristian00/progetto-OOP.svg?style=for-the-badge
[contributors-url]: https://github.com/PCristian00/progetto-OOP/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/PCristian00/progetto-OOP.svg?style=for-the-badge
[forks-url]: https://github.com/PCristian00/progetto-OOP/network/members
[stars-shield]: https://img.shields.io/github/stars/PCristian00/progetto-OOP.svg?style=for-the-badge
[stars-url]: https://github.com/PCristian00/progetto-OOP/stargazers
[issues-shield]: https://img.shields.io/github/issues/PCristian00/progetto-OOP.svg?style=for-the-badge
[issues-url]: https://github.com/PCristian00/progetto-OOP/issues
[license-shield]: https://img.shields.io/github/license/PCristian00/progetto-OOP.svg?style=for-the-badge
[license-url]: https://github.com/PCristian00/progetto-OOP/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[product-screenshot]: images/screenshot.png

