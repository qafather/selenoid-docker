# Распараллеливание тестов с помощью Selenoid
Selenoid — это легковесный сервер, запускающий изолированные браузеры в Docker контейнерах. 
> Selenium Grid в Docker образах. (с) Habr
## Структура репозитория
Для запуска Selenoid и Selenoid-UI нужны только два файла: browsers.json и docker-compose.yml. 
Но дополнительно в репозитории есть Maven проект с тестами и docker-compose.tests.yml для запуска тестов в контейнере. 
```
.
├── config
│   └── selenoid
│       └── browsers.json
├── docker-compose.tests.yml
├── docker-compose.yml
├── pom.xml
└── src
    └── test
        └── java
            └── SimpleTest.java
```
### Конфигурация
Selenoid конфигурируется посредством внесения изменений в файл browsers.json. В нём указывается какие браузеры может поднять Selenoid. 
```json
{
  "chrome": {
    "default": "109.0",
    "versions": {
      "109.0": {
        "image": "selenoid/chrome:109.0",
        "port": "4444",
        "path": "/"
      },
      "106.0": {
        "image": "selenoid/chrome:106.0",
        "port": "4444",
        "path": "/"
      }
    }
  }
}
```
## Запуск
Чтобыз поднять Selenoid сначала нам нужно выгрузить из Docker Hub образы браузеров, который мы указали в конфигурации:
```console
docker pull selenoid/chrome:109.0
docker pull selenoid/chrome:106.0
```
А вот теперь уже можно запускать Selenoid:
```console
docker compose up -d
```
Чтобы остановить и удалить контейнеры выполните следующую команду:
```console
docker compose down
```
А для того чтобы одной командой запусить и Selenoid и тесты воспользуемся командой:
```console
docker compose -f docker-compose.yml -f docker-compose.tests.yml up -d
```
