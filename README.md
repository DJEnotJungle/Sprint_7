# Sprint_7

В финальном проекте 7 спринта "Тестирование API" были разработаны авто тесты учебного сервиса **Яндекс.Самокат**.

Были выделены в константы "ручки" сервиса по документации Ez-scooter.

В пакете "courier" выделены

* POJO модель класса Курьер;
* Рандомизированный генератор входных данных для нового курьера
* Класс для проверок кодов ошибок в ответах от сервера
* Класс для общих шагов разных тестов

В пакете "order" выделены

* POJO модель класса Заказ с тестовыми данными для задания
* Класс шагов для выполнения тестов

Тесты разделены по пакетам в соответствие с заданием и направлением тестов:

* Тесты для модели Курьера
* Тесты для модели Заказа

По результатам тестов сформируем Allure отчет с помощью команд

```
mvn clean test
mvn allure:serve
```

# Задание

Тебе предстоит протестировать API учебного сервиса [Яндекс.Самокат](http://qa-scooter.praktikum-services.ru/).

Его документация: [Ez-scooter](https://qa-scooter.praktikum-services.ru/docs/).

## Подготовка

Перед тем как приступить к заданиям:

1. Создай Maven-проект;
2. Назови проект Sprint7;
3. Подключи JUnit 4, RestAssured, Allure.

## Протестируй ручки.

Проверь, что они корректно работают и выдают нужные ошибки.

1. Создание курьера.

   ### Проверь:
    * курьера можно создать;
    * нельзя создать двух одинаковых курьеров;
    * чтобы создать курьера, нужно передать в ручку все обязательные поля;
    * запрос возвращает правильный код ответа;
    * успешный запрос возвращает `ok: true`;
    * если одного из полей нет, запрос возвращает ошибку;
    * если создать пользователя с логином, который уже есть, возвращается ошибка.

2. Логин курьера.

   ### Проверь:
    * курьер может авторизоваться;
    * для авторизации нужно передать все обязательные поля;
    * система вернёт ошибку, если неправильно указать логин или пароль;
    * если какого-то поля нет, запрос возвращает ошибку;
    * если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;
    * успешный запрос возвращает `id`.

3. Создание заказа.

   ### Проверь, что когда создаёшь заказ:
    * можно указать один из цветов — BLACK или GREY;
    * можно указать оба цвета;
    * можно совсем не указывать цвет;
    * тело ответа содержит `track`.

   Чтобы протестировать создание заказа, нужно использовать параметризацию.

4. Список заказов.

   Проверь, что в тело ответа возвращается список заказов.

5. Отчёт Allure.

   Сгенерируй его и запушь в репозиторий.

**Обрати внимание**: всю папку target коммитить не нужно.

Чтобы добавить в коммит только отчёт, можно перейти в папку проекта в консоли и выполнить команды:

```
# добавляем папку с отчётом Allure к файлам. Ключ -f пригодится, если папка target указана в .gitignore
git add -f .\target\allure-results\.
# выполняем коммит
git commit -m "add allure report"
# так отправишь файлы в удалённый репозиторий
git push
```

**Не забудь**: тесты должны быть независимыми.

Все данные нужно удалять после того, как тест выполнится.

Если для проверки нужен пользователь, создай его перед тестом и удали после. 