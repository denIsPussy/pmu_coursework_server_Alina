import { Table } from 'react-bootstrap';
export default function ProviderBar(props) {
    function edit(id) {
        props.onEdit(id);
    }
  
    function remove(id) {
        props.onRemove(id);
    }
    return (
      <>
        <Table>
            <thead>
              <tr>
                <th>#</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Отчество</th>
              </tr>
            </thead>
            <tbody>
              {props.items.map(item => (
                <tr key={item.id}>
                  <td>{item.id}</td>
                  <td>{item.name}</td>
                  <td>{item.surname}</td>
                  <td>{item.patronymic}</td>
                  <td>
                    <button className="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" onClick={(e) => edit(item.id, e)}>Изменить</button>
                    <button className="btn btn-outline-primary ms-2" onClick={(e) => remove(item.id, e)}>Удалить</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
      </>
    );
  }
  