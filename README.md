# DungeonSQL
Project repository for  2025/2026 Database's course @ I.S.I. Cesena

## Come avviare il programma

Prima di tutto è necessario avere le credenziali per accedere al DBMS (Postgres scaricabile su https://www.postgresql.org/download/), che dovranno essere passate tramite l'environment

```bash 
export DB_URL="jdbc:postgresql://localhost:5432/dungeonsql"
export DB_USER="postgres"
export DB_PASSWORD=""
``` 

e Java 21, scaricabile a questo link https://www.oracle.com/it/java/technologies/downloads/#java21 

Poi l'applicativo può essere avviato con 
```bash
./gradlew run
``` 