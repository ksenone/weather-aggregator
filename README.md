# Тестовое задание “Агрегатор погодных сервисов”
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