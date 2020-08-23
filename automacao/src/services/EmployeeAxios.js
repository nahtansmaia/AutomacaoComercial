import { http } from './ConfigAxios'

export default {

    ListEmployee: () => {
        return http.get('employee')
    },

    SaveEmployee: (employee) => {
        return http.post('employee', employee)
    },

    UpdateEmployee: (employee) => {
        return http.put('employee', employee)
    },

    DeleteEmployee: (employee) => {
        return http.delete('employee', { data: employee })
    },

    MaxCode: () => {
        return http.get('maxCodeEmployee')
    }
}