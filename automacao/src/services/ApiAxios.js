import axios from 'axios'

export const covid = axios.create({
    baseURL: "https://api.covidtracking.com"
})