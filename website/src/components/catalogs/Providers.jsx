import { useState, useEffect } from "react";
import Catalog from "./Catalog";
import Provider from "../../models/ProviderDTO";
import ProviderBar from "../common/ProviderBar";
export default function Providers(props) {

  const entity_name = 'provider';
  const transformer = (data) => new Provider(data);
  const [data, setData] = useState(new Provider());

  function handleOnAdd() {
    setData(new Provider());
  }

  function handleOnEdit(data) {
    setData(new Provider(data));
  }

  function handleFormChange(event) {
    setData({ ...data, [event.target.id]: event.target.value });
  }
  return (
    <main className="flex-shrink-0">
      <div className="container">
        <h1><strong>Ищем надежных поставщиков для сотрудничества</strong></h1>
        <h3>
          Добавьте нового поставщика
        </h3>
        <Catalog
          entity_name={entity_name}
          transformer={transformer}
          data={data}
          onAdd={handleOnAdd}
          onEdit={handleOnEdit}
          barComponent={ProviderBar}
        >
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Имя поставщика
            </label>
            <input
              type="text"
              id="name"
              className="form-control"
              required
              value={data.name}
              onChange={handleFormChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="surname" className="form-label">
              Фамилия поставщика
            </label>
            <input
              type="text"
              id="surname"
              className="form-control"
              required
              value={data.surname}
              onChange={handleFormChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="patronymic" className="form-label">
              Отчество поставщика
            </label>
            <input
              type="text"
              id="patronymic"
              className="form-control"
              required
              value={data.patronymic}
              onChange={handleFormChange}
            />
          </div>
        </Catalog>
      </div>
    </main>
  );
}
