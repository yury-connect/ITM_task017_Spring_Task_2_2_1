# Задача: **PP 2.2.1.**

---

#### Тема: _10 Spring_
#### № **ITMPJ-2766**
#### Наименование: _Spring_
#### Дедлайн: _27/10/2024_
#### Код модуля: _PP2_

---
# Spring

## Условие:

Скачайте/склонируйте заготовку проекта по [ссылке](https://github.com/VanderDT/Task-5).

С работой ядра _Спринг_ мы разобрались, теперь самое время подключить к нему 
пару модулей для комфортной работы.

Начнем с _ORM_.

Для работы с `hibernate` нам понадобится зависимость `{{ hibernate-core}}`, корректным взаимодействием 
со _Спрингом_ озаботится зависимость `spring-orm`.

Как вы можете видеть, зависимость `spring-core` пропала, это произошло из-за того, 
что она является транзитной для всех модулей _Спринга_ и дублировать ее смысла нет.
У нас появились пакеты `model`, `service`, теперь _сервисы_ и _DAO_ объявлены _бинами_ 
с помощью аннотаций `@Repository` и `@Service`.

В методе `main` будет происходить тестирование работоспособности нашего приложения. 
Класс `Car` аннотирован как стандартная сущность `hibernate`. 
В `AppConfig` теперь присутствует базовая настройка `hibernate`, берущая данные из файла `db.properties`. 
Обратите внимание, что для ее работы используется аннотация `@PropertySource("classpath:db.properties")`, 
обращающаяся к папке ресурсов.

На этом настройка приложения окончена.

## Задание:

1. Создайте соединение к своей _базе_ данных и схему. Запустите приложение. 
Проверьте, что оно полностью работает.
2. Создайте сущность `Car` с полями `String model` и `int series`, на которую 
будет ссылаться `User` с помощью связи `one-to-one`.
3. Добавьте этот класс в настройки `hibernate`.
4. Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.
5. В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, 
владеющего машиной по ее модели и серии.

![Step1. Подключение модулей](/imgs/1.png)
![Step2. Добавление зависимостей](/imgs/2.png)
![Step3. Перейти в конфигурирование запуска](/imgs/3.png)
![Step4. Настроить конфигурацию запуска](/imgs/4.png)

[Ссылка на оригинальную страницу](http://jira.it-mentor.tech/browse/ITMPJ-2766)