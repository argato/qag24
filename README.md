# Проект автотестов https://planetazdorovo.ru/

![java](https://user-images.githubusercontent.com/46926736/125160894-01fb9980-e188-11eb-956c-4dbf77339d15.png)
![idea](https://user-images.githubusercontent.com/46926736/125160909-0922a780-e188-11eb-8b82-03b8d48be750.png)
![gradle](https://user-images.githubusercontent.com/46926736/125160914-0aec6b00-e188-11eb-8d78-792dc4fda636.png)
![jUnit5](https://user-images.githubusercontent.com/46926736/125160917-0d4ec500-e188-11eb-9986-790638982928.png)
![rest-assured_logo](https://user-images.githubusercontent.com/46926736/125160921-1049b580-e188-11eb-8465-5b63fbb13d78.png)
![selenide](https://user-images.githubusercontent.com/46926736/125160924-12137900-e188-11eb-91fa-28f828336d5c.png)
![jenkins](https://user-images.githubusercontent.com/46926736/125160927-13dd3c80-e188-11eb-9349-5ab09a4f27ee.png)
![Selenoid](https://user-images.githubusercontent.com/46926736/125160928-150e6980-e188-11eb-9a08-0394fa8bd425.png)
![github](https://user-images.githubusercontent.com/46926736/125160932-18095a00-e188-11eb-9b1a-3ebcf63dab1f.png)
![allure-logo](https://user-images.githubusercontent.com/46926736/125160934-1b9ce100-e188-11eb-8891-5dc8a4ba2308.png)
![allure-testops](https://user-images.githubusercontent.com/46926736/125160973-5d2d8c00-e188-11eb-8537-730ff6ea5e26.png)
![jira-logo](https://user-images.githubusercontent.com/46926736/125160975-63236d00-e188-11eb-8615-6ef6ba29ac9d.png)
![telegram-logo](https://user-images.githubusercontent.com/46926736/125160976-6585c700-e188-11eb-8eae-eb426e581d2f.png)
![slack-logo](https://user-images.githubusercontent.com/46926736/125160979-66b6f400-e188-11eb-98fc-721c06a4c8dd.png)

Java, IntelliJ IDEA, Gradle, JUnit5, Rest-Assured, Selenide, Jenkins, Selenoid, Github, Allure Reports, Allure TestOps, Jira, Telegram (уведомления), Slack (уведомления)

## Тест-план 
Позволяет составить и сразу запустить нужный набор тестов
![testplan](https://user-images.githubusercontent.com/46926736/125159293-3880e680-e17f-11eb-8132-b25bd10b3832.png)

## Результат проведения тестирования
Для каждого теста указаны пройденные шаги в человекочитаемом виде, а так же 

:heavy_check_mark: Скриншот браузера перед завершением прохождения теста

:heavy_check_mark: Код страницы

:heavy_check_mark: Лог консоли браузера

:heavy_check_mark: Видео прохождения теста

Пример теста
![test-steps](https://user-images.githubusercontent.com/46926736/125159663-6f57fc00-e181-11eb-9bb7-c81c181abade.png)

Пример записанного видео
![test](https://user-images.githubusercontent.com/46926736/125159635-4cc5e300-e181-11eb-9214-ceec9616ff0c.gif)

## Список ручных и автоматизированных тестов в Allure TestOps
Тесты можно расписывать в Allure TestOps и экспортировать в код в виде шагов. Тесты уже автоматизированные можно импортировать Allure TestOps в виде тест-кейсов.
![testcases](https://user-images.githubusercontent.com/46926736/125159323-5f3f1d00-e17f-11eb-8600-804755bd6cb8.png)

##  Запуск тестов в 2 потока
Количество потоков задается при запуске
![threads](https://user-images.githubusercontent.com/46926736/125159335-6e25cf80-e17f-11eb-9afa-e1b9bcdb9418.png)

## Отправка оповещений о результатах прогона в Telegram и Slack
![slack](https://user-images.githubusercontent.com/46926736/125159278-2606ad00-e17f-11eb-9de3-62d2cb229e5d.png)
![telegram](https://user-images.githubusercontent.com/46926736/125159286-3159d880-e17f-11eb-9fb9-ced2f844f6bb.png)

## По результатам проведенного тестирования можно провести анализ
Дашборд Allure TestOps 
Представлены графики средней продолжительности, средней успешности тестов по UI и API
![dashboard-automation](https://user-images.githubusercontent.com/46926736/125159705-aa5a2f80-e181-11eb-8640-f544e5c66fcf.png)

Дашборд статистики по команде
![dashboard-team](https://user-images.githubusercontent.com/46926736/125159711-b34b0100-e181-11eb-8342-5e9d74f21ab6.png)

## Пример запуска
Для удаленного запуска необходимо заполнить файл remote.properties или передать параметры в командной строке:

* browser (default chrome)
* browserVersion (default 89.0)
* browserSize (default 1920x1080)
* browserMobileView (mobile device name, for example iPhone X)
* remoteDriverUrl (url address from selenoid or grid)
* videoStorage (url address where you should get video)

Запуск с использованием файла remote.properties:
```bash
gradle clean test
```

Запуск с ручным указанием параметров:
```bash
gradle clean test -Dbrowser=chrome -DbrowserVersion=89.0 -DbrowserSize=1920x1080 -DbrowserMobileView= -DremoteDriverUrl=selenoid.autotests.cloud -DremoteDriverUser=user1 -DremoteDriverPassword=1234 -DvideoStorage=https://selenoid.autotests.cloud/video/ -Dthreads=2
```


