# SpringFinalProject

# System for Submission of Reports to the Tax.
An individual / legal entity (hereinafter referred to as the User) is registered. Submits a report (XML / JSON / Form). The Tax Inspector accepts / rejects the report (indicating the reason for the refusal). The user can view all submitted reports, the reasons for the refusal and change them if requested by the Inspector. The user can send a request to replace the Inspector in case of dissatisfaction.

# (RU) Система Подачи Отчетов в Налоговую. Вариант 5
Физ./Юр.лицо (далее Пользователь) регистрируется. Подает отчет (XML/JSON/Форма). Налоговый Инспектор принимает/отклоняет отчет (указывая причину отказа). Пользователь может просмотреть все поданные отчеты, причины отказа и изменять их если того потребовал Инспектор. Пользователь может отправлять запрос на замену Инспектора в случае неудовлетворения. 

# Installation Instructions 
```
git clone https://github.com/blacksempai/SpringFinalProject.git
```

# Deployment Instruction

1)Config database in [application.properties](src/main/resources/application.properties)

2)Install database from [schema.sql](src/main/resources/schema.sql)

3)Insert accounts from [db.sql](src/main/resources/db.sql)

4)Build and deploy the project

5)Go to url localhost:8080/



You can test User logic via 

```
Tomas Anderson account:
login: neo
password: root
```

You can test Inspector logic via 

```
Inspector Gadget account:
login: inspector01
password: Inspector01
```

You can test Admin logic via 

```
root account:
login: root
password: root
```
