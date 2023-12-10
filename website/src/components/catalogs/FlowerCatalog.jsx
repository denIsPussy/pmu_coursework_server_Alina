import { useState, useEffect } from "react";
import Catalog from "./Catalog";
import Flower from "../../models/FlowerDTO";
import FlowerBar from "../common/FlowerBar";
export default function Providers(props) {

  const entity_name = 'flower';
  const transformer = (data) => new Flower(data);
  const [data, setData] = useState(new Flower());
  const [providers, setProviders] = useState([]);
  const [selectedItems, setSelectedItems] = useState([]);

  const types = {
    1: "Trees",
    2: "Aquatic",
    3: "Grasses",
    4: "Bulbous",
    5: "Wildflowers",
  };

  useEffect(() => {
    loadProviders();
  }, []);

  useEffect(() => {
    setData({ ...data, "providersId": selectedItems });
  }, [selectedItems]);

  const loadProviders = async function () {
    const requestUrl = "http://localhost:8080" + "/provider";
    const response = await fetch(requestUrl);
    const providers = await response.json();
    setProviders(providers);
    console.log(providers);
  }

  function handleOnAdd() {
    setData(new Flower());
  }

  function handleOnEdit(data) {
    setData(new Flower(data));
  }

  function handleFormChange(event) {
    if (event.target.id == 'type') console.log(event.target.value)
    setData({ ...data, [event.target.id]: event.target.value });
  }

  const [imageURL, setImageURL] = useState();
  const fileReader = new FileReader();

  fileReader.onloadend = () => {
    const tempval = fileReader.result
    setImageURL(tempval);
    setData({ ...data, ['image']: tempval });
  };
  function handleOnChange(event) {
    event.preventDefault();
    const file = event.target.files[0];
    fileReader.readAsDataURL(file);
  }

  const handleCheckboxChange = (event) => {
    const itemId = parseInt(event.target.value);
    if (selectedItems.includes(itemId)) {
      setSelectedItems(selectedItems.filter((id) => id !== itemId));
      console.log("удаление");
    } else {
      setSelectedItems([...selectedItems, itemId]);
      console.log("добавление");
    }
  };

  return (
    <main className="flex-shrink-0">
      <div className="container">
        <h1><strong>Бла бла бла текст про цветы</strong></h1>
        <h3>
          Добавьте новое цветочное изделие
        </h3>
        <Catalog
          entity_name={entity_name}
          transformer={transformer}
          data={data}
          providers={providers}
          onAdd={handleOnAdd}
          onEdit={handleOnEdit}
          barComponent={FlowerBar}
        >
          <div className="mb-3">
            <label className="form-label" htmlFor="image"> Выберите изображение </label>
            <input className="form-control" id="image" type="file" accept="image/jpeg, image/png, image/jpg" value='' onChange={handleOnChange} />
          </div>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Название цветочного изделия
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
            <label htmlFor="price" className="form-label">
              Цена
            </label>
            <input
              type="number"
              id="price"
              className="form-control"
              required
              value={data.price}
              onChange={handleFormChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="type" className="form-label">
              Выберите тип
            </label>
            <select id="type" className="form-select" value={data.type} onChange={handleFormChange}>
              <option disabled value="">  Выберите тип </option>
              {
                Object.keys(types).map((key, index) =>
                  <option key={key} value={Object.values(types)[index]}>
                    {Object.values(types)[index]}
                  </option>
                )
              }
            </select>
          </div>
          <div className="mb-3">
            <label htmlFor="providersId" className="form-label">
              Выберите поставщиков
            </label>
            {providers.map((provider) => (
              <div key={provider.id}>
                <input
                  type="checkbox"
                  id="providersId"
                  value={provider.id}
                  checked={selectedItems.includes(provider.id) || data.providersId?.includes(provider.id)}
                  onChange={handleCheckboxChange}
                />
                <label className="ms-2" htmlFor={provider.id}>{provider.surname} {provider.name} {provider.patronymic}</label>
              </div>
            ))}
          </div>
        </Catalog>
      </div>
    </main>
  );
}
