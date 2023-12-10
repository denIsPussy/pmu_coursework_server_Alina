export default class FlowerDTO {
    constructor(data) {
        this.id = data && data.id ? data.id : null;
        this.name = data && data.name ? data.name : " ";
        this.price = data && data.price ? data.price : 0;
        this.type = data && data.type ? data.type : " ";
        this.image = data && data.image ? data.image : null;
        this.providersId = data && data.providersId ? data.providersId : null;
    }
}