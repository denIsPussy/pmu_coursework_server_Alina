import { Table } from 'react-bootstrap';
export default function OrderBar(props) {
  function edit(id) {
    props.onEdit(id);
  }

  function remove(id) {
    props.onRemove(id);
  }

  const findFlowerById = (flowers, id) => {
    const flower = flowers.find((flower1) => flower1.id === id);
    return flower ? flower.id + ". Название: " + flower.name + " | Цена: " + flower.price + " | Тип:" + flower.type : null;
  };

  return (
    <Table>
      <thead>
        <tr>
          <th>Номер заказа  </th>
          <th style={{width:"400px"}}>Цветы</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        {props.items.map(item => (
          <tr key={item.id}>
            <td>{item.id}</td>
            <td>
              <select id="" className="form-select" value=''>
                <option disabled value=''></option>
                {
                  item.orderFlower.map((flower) =>
                    <option key={flower.flowerId} value={flower.flowerId}>
                      {findFlowerById(props.itemssssssss, flower.flowerId)}
                    </option>
                  )
                }
              </select>
            </td>
            <td>
              <button className="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" onClick={(e) => edit(item.id, e)}>Изменить</button>
              <button className="btn btn-outline-primary ms-2" onClick={(e) => remove(item.id, e)}>Удалить</button>
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
}
