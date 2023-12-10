export default class DataService {
    static dataUrlPrefix = 'http://localhost:8080/';

    static async createOrUpdate (entity, data, isEdit) {
        const requestParams = {
            method: `${!isEdit ? "POST" : "PUT"}`,
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data),
        };
        console.log('ГОТОВЫЙ', Object.values(data));
        const requestUrl = "http://localhost:8080" +
            `/${entity}${!isEdit ? '' : '/' + data.id}`;
        const response = await fetch(requestUrl, requestParams);
        const result = await response.json();
        return result;
    }

    static async delete (id, entity) {
        const requestParams = {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
            }
        };
        console.log('ГОТОВЫЙ', id);
        const requestUrl = "http://localhost:8080" +
            `/${entity}/${id}`;
        const response = await fetch(requestUrl, requestParams);
        const result = await response.json();
        return result;
    }

    static async getAll (entity) {
        const requestUrl = "http://localhost:8080" + `/${entity}`;
        const response = await fetch(requestUrl);
        const result = await response.json();
        return result;
    }

    static async get (id, entity, transformer) {
        const requestUrl = "http://localhost:8080" + `/${entity}/${id}`;
        const response = await fetch(requestUrl);
        const result = await response.json();
        return transformer(result);
    }
}