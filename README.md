Gestore di Inventario Prodotti

Applicazione CRUD per la gestione di un inventario prodotti tramite Spring Boot e Thymeleaf.
Permette di aggiungere, visualizzare, modificare ed eliminare prodotti presenti in magazzino.

Funzionalità principali

CRUD completo sui prodotti.

Regola di business: il prezzo viene sempre salvato come valore positivo (gestito nel Service).

Interfaccia HTML con form per creare/modificare prodotti e tabella di visualizzazione.

Struttura del dominio (Entity Prodotto)

id – Long, chiave primaria autogenerata

nome – String, nome del prodotto

quantita – Integer, quantità disponibile

prezzo – Double, prezzo unitario (positivo)

 Componenti implementate

Prodotto.java – Entity del modello

ProdottoRepository.java – Interfaccia Repository

ProdottoService.java / ProdottoServiceImpl.java – Logica di business

ProdottoController.java – Gestione delle rotte (endpoint principali su /inventario)

Template Thymeleaf: prodotto.html per gestione e visualizzazione prodotti

🌐 Template HTML

Il template prodotto.html (in src/main/resources/templates/) fornisce:

Form per aggiungere/modificare un prodotto (nuovoProdotto)

Tabella dei prodotti disponibili (listaProdotti)
