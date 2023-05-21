Инструкция по запуску
=
1. Склонировать репозиторий
```
git clone  https://github.com/TatiLarina/QA_diploma
```
2. Запустить контейнеры docker:
<br> А) Для работы с базой данных mysql выполнить команду:
```
docker-compose -f docker-compose-mysql.yml up -d 
```
После прогона тестов остановить контейнеры:
```
docker-compose -f docker-compose-mysql.yml down
```
B) Для работы с базой данных postgres выполнить команду:
```
docker-compose -f docker-compose-postgres.yml up -d 
```
После прогона тестов остановить контейнеры:
```
docker-compose -f docker-compose-postgres.yml down
```
3. Запустить приложение:
<br> A) Для запуска приложения с базой данных mysql выполнить команду:
```
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
```
B) Для запуска приложения с базой данных postgres выполнить команду:
```
java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
```
