# Projet OC-P13-Chat-PoC

Ce projet est une preuve de concept (PoC) pour une application de chat en temps réel utilisant Spring Boot pour le backend et Angular pour le frontend. L'application permet aux utilisateurs de se connecter et d'échanger des messages via WebSocket.

## Sommaire

- [Projet OC-P13-Chat-PoC](#projet-oc-p13-chat-poc)
  - [Sommaire](#sommaire)
  - [Prérequis](#prérequis)
  - [Installation](#installation)
    - [Backend](#backend)
    - [Frontend](#frontend)
  - [Configuration](#configuration)
    - [Backend](#backend-1)
    - [Frontend](#frontend-1)
  - [Lancement de l'application](#lancement-de-lapplication)
    - [Backend](#backend-2)
    - [Frontend](#frontend-2)
  - [Architecture du projet](#architecture-du-projet)
    - [Backend](#backend-3)
    - [Frontend](#frontend-3)
  - [Fonctionnalités](#fonctionnalités)
  - [Technologies utilisées](#technologies-utilisées)
  - [Documentation Javadoc](#documentation-javadoc)
  - [Contributeurs](#contributeurs)

## Prérequis

- Java 17
- Node.js et npm
- Maven
- PostgreSQL

## Installation

### Backend

1. Clonez le dépôt :
  ```bash
  git clone <URL_DU_DEPOT>
  cd <NOM_DU_DEPOT>
  ```

2. Installez les dépendances Maven :
  ```bash
  mvn clean install
  ```

### Frontend

1. Accédez au répertoire Angular :
  ```bash
  cd frontend
  ```

2. Installez les dépendances npm :
  ```bash
  npm install
  ```

## Configuration

### Backend

1. Configurez les variables d'environnement pour PostgreSQL :
  ```bash
  export PGDB_USERNAME=<votre_nom_utilisateur>
  export PGDB_PASSWORD=<votre_mot_de_passe>
  ```

2. Modifiez le fichier `application.properties` si nécessaire :
  ```properties
  spring.datasource.url=jdbc:postgresql://localhost:5432/ocp13poc
  spring.datasource.username=${PGDB_USERNAME}
  spring.datasource.password=${PGDB_PASSWORD}
  ```

### Frontend

1. Modifiez le fichier `environment.ts` si nécessaire pour pointer vers le backend.

## Lancement de l'application

### Backend

1. Démarrez le serveur Spring Boot :
  ```bash
  mvn spring-boot:run
  ```

### Frontend

1. Démarrez le serveur Angular :
  ```bash
  ng serve
  ```

2. Accédez à l'application via `http://localhost:4200`.

## Architecture du projet

### Backend

- `src/main/java/com/openclassrooms/ycyw/config` : Configuration de sécurité et CORS.
- `src/main/java/com/openclassrooms/ycyw/controllers` : Contrôleurs pour gérer les requêtes HTTP et WebSocket.
- `src/main/java/com/openclassrooms/ycyw/services` : Services pour la logique métier.
- `src/main/java/com/openclassrooms/ycyw/models` : Modèles de données.
- `src/main/java/com/openclassrooms/ycyw/dto` : Objets de transfert de données.
- `src/main/java/com/openclassrooms/ycyw/mapper` : Mappers pour convertir entre DTOs et entités.

### Frontend

- `src/app/components` : Composants Angular pour l'interface utilisateur.
- `src/app/services` : Services Angular pour les appels API et WebSocket.
- `src/app/routes` : Configuration des routes de l'application.

## Fonctionnalités

- Connexion utilisateur
- Envoi et réception de messages en temps réel via WebSocket
- Gestion des utilisateurs

## Technologies utilisées

- Backend : Spring Boot, Spring Security, WebSocket, PostgreSQL
- Frontend : Angular, RxJS, TypeScript
- Outils : Maven, npm

## Documentation Javadoc
Pour génerer la documentation Javadoc, exécutez la commande suivante depuis le dossier racine du projet (backend):
```mvn javadoc:javadoc ```

La documentation Javadoc pour le backend de ce projet est disponible dans le dossier:  back/target/reports/apidocs/


## Contributeurs

- Didier BD
