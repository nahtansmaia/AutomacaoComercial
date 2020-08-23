import { http } from './ConfigAxios'

export default {

    ListCategory: () => {
        return http.get('category')
    },

    SaveCategory: (category) => {
        return http.post('category', category)
    },

    UpdateCategory: (category) => {
        return http.put('category', category)
    },

    DeleteCategory: (category) => {
        return http.delete('category', {data: category})
    },
    MaxCode: () => {
        return http.get('maxCodeCategory')
    }
}