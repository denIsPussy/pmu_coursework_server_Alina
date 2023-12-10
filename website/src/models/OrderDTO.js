export default class OrderDTO {
    constructor(data) {
        this.id = data && data.id ? data.id : null;
        this.dateCreate = data && data.dateCreate ? data.dateCreate : " ";
        this.sum = data && data.sum ? data.sum : 0;
        this.orderFlower = data && data.orderFlower ? data.orderFlower : null;
    }
}