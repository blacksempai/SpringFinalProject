# JavaExternalFinalProject

# Система Подачи Отчетов в Налоговую.
Физ./Юр.лицо (далее Пользователь) регистрируется. Подает отчет (XML/JSON/Форма). Налоговый Инспектор принимает/отклоняет отчет (указывая причину отказа). Пользователь может просмотреть все поданные отчеты, причины отказа и изменять их если того потребовал Инспектор. Пользователь может отправлять запрос на замену Инспектора в случае неудовлетворения. 

# Инструкция по установке 
```
git clone https://github.com/blacksempai/SpringFinalProject.git
```

# Инструкция по запуску приложения

1)Install database from [schema.sql](database/schema.sql)

2)Insert accounts from [db.sql](database/db.sql)

3)Build and deploy the project

4)Go to url localhost:8080/

You can test User logic via 

```
Tomas Anderson account:
login: neo
password: Matrix1999
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
