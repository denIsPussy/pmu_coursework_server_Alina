export default function Card(props) {
  function edit(id) {
    props.onEdit(id);
  }

  function remove(id) {
    props.onRemove(id);
  }

  function findProvider(id, providersId) {
    const provider = props.providers.find((prd) => providersId.includes(id) && prd.id == id);
    return provider.id + ". " + provider.surname + " " + provider.name + " " + provider.patronymic;
  }

  return (
    <div className="row row-cols-1 row-cols-md-3 mt-5">
        {props.items.map((flower) => (
          <div className="col-xl-2 col-lg-3 col-md-4 col-sm-6">
            <div className="card">
              <img className="card-img-top" src={flower.image} alt="Flower" />
              <div className="card-body">
                <h5 className="card-title">{flower.name}</h5>
                <p className="card-text">Тип: {flower.type}</p>
                <p className="card-text">Цена: {flower.price} руб/шт</p>
                {/* <p>
                  {props.providers.map((provider) =>
                    <div key={provider.id}>{findProvider(provider.id, flower.providersId)}</div>
                  )}
                </p> */}
                {/* <div className="d-flex flex-column text-center">
                  <button className="btn btn-outline-dark" type="button" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    Подробнее
                  </button>
                </div> */}
                <div className="d-flex flex-column justify-content-between mt-2">
                  <a href="#" className="btn btn-outline-dark" data-bs-toggle="modal" data-bs-target="#staticBackdrop" onClick={(e) => edit(flower.id, e)}>Изменить</a>
                  <a href="#" className="btn btn-outline-dark mt-2" onClick={(e) => remove(flower.id, e)}>Удалить</a>
                </div>
              </div>
            </div>
          </div>
        ))}
    </div>
  );
}
