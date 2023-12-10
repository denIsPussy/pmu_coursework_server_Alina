import { useState, useEffect } from "react";
import Catalog from "./Catalog";
import Order from "../../models/OrderDTO";
import OrderFlower from "../../models/OrderFlowerDTO";
import OrderBar from "../common/OrderBar";
export default function Providers(props) {

    const entity_name = 'order';
    const transformer = (data) => new Order(data);
    const [data, setData] = useState(new Order());
    const [flowers, setFlowers] = useState([]);

    const [modalOrder, setModalOrder] = useState('');
    const [isVisible, setVisible] = useState('');

    const [sum1, setSum] = useState('');
    const [flower, setFlower] = useState('');
    const [quantity, setQuantity] = useState('');
    const [orderItems, setOrderItems] = useState([]);

    useEffect(() => {
        setData({ ...data, "orderFlower": orderItems });
    }, [orderItems]);

    const handleFlowerChange = (e) => {
        const selectedFlowerId = e.target.value;
        const selectedFlower = flowers.find((flower) => flower.id == selectedFlowerId);
        setFlower(selectedFlower);
        setQuantity(orderItems.map((orderItems) => orderItems.flowerId === selectedFlower.id ? orderItems.quantity : null));
    };

    const handleQuantityChange = (e) => {
        setQuantity(e.target.value);
    };

    const handleAddToOrder = () => {
        if (flower && quantity) {
            const listSelectedFlower = orderItems.map(orderItem => orderItem.flowerId);

            if (listSelectedFlower.includes(flower.id)) {
                setOrderItems((prevOrderItems) =>
                    prevOrderItems.map((item) =>
                        item.flowerId === flower.id ? { ...item, quantity: quantity } : item
                    )
                );

            }
            else {
                const newItem = {
                    flowerId: flower.id,
                    quantity: quantity
                };
                setOrderItems([...orderItems, new OrderFlower(newItem)]);
            }
            //const newQuantity = orderItems.map((orderItem) => orderItem.flowerId === flower.Id ? orderItem.quantity : null);
            //const sum = Number(newQuantity) * Number(flower?.price);
            setData((prevData) => ({
                ...prevData,
                sum: prevData.sum + Number(flower?.price * quantity)
            }));
            setSum((prevSum) => prevSum + Number(flower?.price * quantity));
            setFlower('');
            setQuantity('');
        }
    };

    const handleRemoveFromOrder = () => {
        if (flower) {
            setOrderItems((prevOrderItems) =>
                prevOrderItems.filter((item) =>
                    item.flowerId !== flower.id
                )
            );
            setData((prevData) => ({
                ...prevData,
                sum: prevData.sum - Number(flower?.price * quantity)
            }));
            setSum((prevSum) => prevSum - Number(flower?.price * quantity));
            setFlower('');
            setQuantity('');
        }
    };

    useEffect(() => {
        loadFlowers();
    }, []);

    const loadFlowers = async function () {
        const requestUrl = "http://localhost:8080" + "/flower";
        const response = await fetch(requestUrl);
        const flowers = await response.json();
        setFlowers(flowers);
    }

    function handleOnAdd() {
        setData(new Order());
        setFlower('');
        setQuantity('');
        setModalOrder("Добавить в заказ");
        setVisible(false);
    }

    function handleOnEdit(data) {
        setData(new Order(data));
        setOrderItems(data.orderFlower);
        setSum(data.sum);
        setModalOrder("Изменить количество");
        setVisible(true);
    }

    function handleFormChange(event) {
        setData({ ...data, [event.target.id]: event.target.value });
    }

    return (
        <main className="flex-shrink-0">
            <div className="container">
                <h1><strong>Бла бла бла текст про заказ</strong></h1>
                <h3>
                    Добавьте новый заказ
                </h3>
                <Catalog
                    entity_name={entity_name}
                    transformer={transformer}
                    data={data}
                    itemssssssss={flowers}
                    onAdd={handleOnAdd}
                    onEdit={handleOnEdit}
                    barComponent={OrderBar}
                >
                    <div className="d-flex flex-column">
                        <h2>Создание заказа</h2>
                        <label htmlFor="dateCreate" className="form-label">
                            Дата создания
                        </label>
                        <input
                            type="text"
                            id="dateCreate"
                            className="form-control"
                            required
                            value={data.dateCreate}
                            onChange={handleFormChange}
                        />
                        <label htmlFor="sum" className="form-label">
                            Сумма
                        </label>
                        <input
                            type="number"
                            id="sum"
                            className="form-control mb-3"
                            required
                            value={sum1}
                            onChange={handleFormChange}
                            readOnly
                        />
                        <select id="" className="form-select" value={flower.id} onChange={handleFlowerChange}>
                            <option value=''>  Выберите цветок </option>
                            {
                                flowers.map((flower) =>
                                    <option key={flower.id} value={flower.id}>
                                        {flower.id} : {flower.name}
                                    </option>
                                )
                            }
                        </select>
                        <input
                            className="form-control mt-3"
                            type="number"
                            placeholder="Количество"
                            value={quantity}
                            onChange={handleQuantityChange}
                        />
                        <button type="button" className="justify-content-end btn btn-outline-dark mt-3" onClick={handleAddToOrder}>{modalOrder}</button>
                        <button type="button" className="justify-content-end btn btn-outline-dark mt-3" style={{ display: isVisible ? "block" : "none" }} onClick={handleRemoveFromOrder}>Удалить</button>
                        <ul>
                            {orderItems.map((item, index) => (
                                <li key={index}>
                                    {item.flowerId} (Количество: {item.quantity})
                                </li>
                            ))}
                        </ul>
                    </div>
                </Catalog>
            </div>
        </main>
    );
}
