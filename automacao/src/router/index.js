import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/About',
    name: 'About',
    component: () => import(/* webpackChunkName: "about" */'../views/About.vue')
  },
  {
    path: '/Product',
    name: 'Product',
    component: () => import(/* webpackChunkName: "product" */'../views/Product.vue')
  }, 
  {
    path: '/DashBoard',
    name: 'DashBoard',
    component: () => import(/* webpackChunkName: "dashboard" */'../views/DashBoard.vue')
  },
  {
    path: '/Employee',
    name: 'Employee',
    component: () => import(/* webpackChunkName: "costumers" */'../views/Employee.vue')
  },
  {
    path: '/Category',
    name: 'Category',
    component: () => import(/* webpackChunkName: "costumers" */'../views/Category.vue')
  },
  {
    path: '/Sales',
    name: 'Sales',
    component: () => import(/* webpackChunkName: "sales" */'../views/Sales.vue')
  },
]

const router = new VueRouter({
  routes
})

export default router
