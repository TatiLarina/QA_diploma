[![Build status](https://ci.appveyor.com/api/projects/status/oxjrs7in96lmkcoy?svg=true)](https://ci.appveyor.com/project/TatiLarina/qa-diploma)
# Дипломный проект "Тестировщик ПО"
[План автоматизации](https://github.com/TatiLarina/QA_diploma/blob/main/docs/Plan.md)
[Отчет по итогам тестирования] (https://github.com/TatiLarina/QA_diploma/blob/main/docs/Report.md)
<br> Отчет по итогам автоматизации

## Инструкция по запуску

1. Склонировать репозиторий
```
git clone  https://github.com/TatiLarina/QA_diploma
```
2. Запустить контейнеры docker:
```
docker-compose up -d 
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
4. Запустить тесты:
<br> А) Для запуска тестов с базой данных mysql выполнить команду:
```
./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
```
B) Для запуска тестов с базой данных postgres выполнить команду:
```
./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
```
5. Сформировать и открыть в браузере отчеты:
```
./gradlew allureServe
```
6. Закрыть отчет Ctrl+C
7. Закрыть приложение Ctrl+C
8. Остановить docker
```
docker-compose down
```
