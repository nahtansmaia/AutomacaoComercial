import { http } from './ConfigAxios'

export default {

    ListSale: () => {
        return http.get('sale')
    },

    SaveSale: (sale) => {
        return http.post('sale', sale)
    },

    UpdateSale: (sale) => {
        return http.put('sale', sale)
    },

    DeleteSale: (sale) => {
        return http.delete('sale', {data: sale})
    },
    MaxCode: () => {
        return http.get('maxCodeSale')
    }
}