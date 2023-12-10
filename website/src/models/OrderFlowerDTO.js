export default class OrderFlowerDTO {
    constructor(data) {
        this.orderId = data && data.orderId ? data.orderId : " ";
        this.flowerId = data && data.flowerId ? data.flowerId : " ";
        this.quantity = data && data.quantity ? data.quantity : 0;
    }
}