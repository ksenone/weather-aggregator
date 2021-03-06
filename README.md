# Тестовое задание “Агрегатор погодных сервисов”

### Описание
Приложение имеет архитектуру MVCS и следует принципам REST API.  
WeatherAggregatorController берет на себя работу по использованию API сторонних погодных сервисов, обработке полученных данных и отправлению GET-запросов на определенные endpoint-ы.  
WeatherAggregatorService реализует бизнес-логику приложения, включающую в себя агрегацию данных и ее вывод.  
Модель данных Data определяет структуру получаемых и хранимых данных для каждого из сервисов.

### REST API

<table>
<thead>
  <tr>
    <th>route</th>
    <th>Метод</th>
    <th>Параметры</th>
    <th>Результат</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>/?area=[city]<br>по умолчанию работает просто /</td>
    <td>Вывести информацию <br>о погоде в городе</td>
    <td>Город или название <br>местности</td>
    <td>Список погоды из сервисов <br>с которыми умеет работать<br>приложение</td>
  </tr>
  <tr>
    <td>/services</td>
    <td>Список сервисов, с которыми<br>работает приложение</td>
    <td>Нет</td>
    <td>Список сервисов, с которыми<br>умеет работать приложение, <br>и их статус доступности</td>
  </tr>
</tbody>
</table>

### Как бы я обрабатывала ситуацию с разными названиями городов в каждом сервисе
Имело бы смысл создать еще один сервис, переводящий название местности в координаты, по которым бы и происходил запрос к API погоды.
