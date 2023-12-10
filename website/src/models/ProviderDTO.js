export default class ProviderDTO {
    constructor(data) {
        this.id = data && data.id ? data.id : null;
        this.name = data && data.name ? data.name : " ";
        this.surname = data && data.surname ? data.surname : " ";
        this.patronymic = data && data.patronymic ? data.patronymic : " ";
    }
}