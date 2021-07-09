# Проект автотестов https://planetazdorovo.ru/


IntelliJ IDEA, Java, Gradle, JUnit5, Rest-Assured, Selenide, Jenkins, Selenoid, Github, Allure TestOps, Jira, Telegram (notifications), Slack (notifications)


# Пример для запуска

### Для удаленного запуска необходимо заполнить файл remote.properties или передать параметры в командной строке:

* browser (default chrome)
* browserVersion (default 89.0)
* browserSize (default 1920x1080)
* browserMobileView (mobile device name, for example iPhone X)
* remoteDriverUrl (url address from selenoid or grid)
* videoStorage (url address where you should get video)

Run tests with filled remote.properties:
```bash
gradle clean test
```

Run tests with not filled remote.properties:
```bash
gradle clean test -Dbrowser=chrome -DbrowserVersion=89.0 -DbrowserSize=1920x1080 -DbrowserMobileView= -DremoteDriverUrl=selenoid.autotests.cloud -DremoteDriverUser=user1 -DremoteDriverPassword=1234 -DvideoStorage=https://selenoid.autotests.cloud/video/ -Dthreads=2
```


