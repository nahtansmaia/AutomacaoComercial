import { http } from './ConfigAxios'

export default {

    ListProduct: () => {
        return http.get('product')
    },

    SaveProduct: (product) => {
        return http.post('product', product)
    },

    UpdateProduct: (product) => {
        return http.put('product', product)
    },

    DeleteProduct: (product) => {
        return http.delete('product', {data: product})
    },
    SaveAll: ([product]) => {
        return http.post('productSaveAll', [product]) 
    },
    MaxCode: () => {
        return http.get('maxCodeProduct')
    }
}