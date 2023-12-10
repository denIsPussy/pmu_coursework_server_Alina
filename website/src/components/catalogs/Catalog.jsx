import { useState, useEffect } from "react";
import Toolbar from "../common/Toolbar";
import Modal from "../common/Modal";
import DataService from '../../services/DataService';

export default function Catalog(props) {
    const [items, setItems] = useState([]);
    const [modalHeader, setModalHeader] = useState('');
    const [modalConfirm, setModalConfirm] = useState('');
    const [modalVisible, setModalVisible] = useState(false);
    const [isEdit, setEdit] = useState(false);
    const { barComponent: BarComponent } = props;

    useEffect(() => {
        loadItems();
    }, []);

    function loadItems() {
        DataService.getAll(props.entity_name).then((data) =>
            setItems(data)
        );
        
    }

    function saveItem() {
        if (props.entity_name == 'order') console.log(props.data);
        DataService.createOrUpdate(props.entity_name, props.data, isEdit).then(() => loadItems());
    }

    function editItems(id) {
        DataService.get(id, props.entity_name, props.transformer)
            .then(data => {
                setEdit(true);
                setModalHeader('Редактирование элемента');
                setModalConfirm('Сохранить');
                setModalVisible(true);
                props.onEdit(data);
            });
    }

    function deleteItem(id) {
        DataService.delete(id, props.entity_name).then(() => loadItems());
    }

    function handleAdd() {
        setEdit(false);
        setModalHeader('Добавление элемента');
        setModalConfirm('Добавить');
        setModalVisible(true);
        props.onAdd();
    }

    function handleEdit(id) {
        editItems(id);
    }

    function handleRemove(id, e) {
        if (confirm('Удалить выбранные элементы?')) {
            deleteItem(id);
        }
    }

    function handleModalHide() {
        setModalVisible(false);
    }

    function handleModalDone() {
        saveItem();
    }

    return (
        <>
            <Toolbar
                onAdd={handleAdd}
            />
            <BarComponent
                items={items}
                itemssssssss={props.itemssssssss} 
                onEdit={handleEdit}
                onRemove={handleRemove} />
            <Modal
                header={modalHeader}
                confirm={modalConfirm}
                visible={modalVisible}
                onHide={handleModalHide}
                onDone={handleModalDone}>
                {props.children}
            </Modal>
        </>
    );
}