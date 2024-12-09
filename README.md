**TABLE OF CONTENTS**

- [1. PostgreSQL](#1-postgresql)
- [2. Backend (Spring Boot)](#2-backend-spring-boot)
  - [Spring Initializr.](#spring-initializr)
    - [Dépendances :](#dépendances-)
- [3. Frontend (Angular)](#3-frontend-angular)
  - [Créer un projet Angular](#créer-un-projet-angular)

# 1. PostgreSQL
Se connecter a la base de donnée PostgreSQL
```bash
psql -h localhost -U didierbd -d ocp13poc
```
pour creer la table "chat" depuis un fichier sql
```bash
psql -h localhost -U didierbd -d ocp13poc -f chat.sql
```
pour inserer des données dans la table "chat"
```bash
INSERT INTO Chat (sender_id, content, status) 
VALUES (1, 'Hello World!', 'sent');
```
pour creer la table "utilisateur" depuis un fichier sql
```bash
psql -h localhost -U didierbd -d ocp13poc -f utilisateur.sql
```
pour inserer des données dans la table "utilisateur"
```bash
INSERT INTO Utilisateur (nom, email, mot_de_passe, prénom) 
VALUES ('Doe', 'john@example.com', 'password123', 'John');
```
Pour lister les tables une fois connecté
```
\dt
```
pour voir les données de la table "chat"
```
SELECT * FROM chat;
```
pour voir les données de la table "utilisateur"
```
SELECT * FROM utilisateur;
```
pour voir les champs de la table "chat"
```
\d chat
```
pour voir les champs de la table "utilisateur"
```
\d utilisateur
```
pour quitter la base de donnée
```
\q
```
# 2. Backend (Spring Boot)
## Spring Initializr.
### Dépendances :
- Web
- WebSocket
- Spring Security
- PostgreSQL

# 3. Frontend (Angular)
## Créer un projet Angular
```bash
ng new chat-poc
cd chat-poc
ng generate component chat
npm install @stomp/rx-stomp
npm install sockjs-client
```
