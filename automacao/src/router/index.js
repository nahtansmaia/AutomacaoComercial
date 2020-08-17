import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/About',
    name: 'About',
    component: () => import(/* webpackChunkName: "about" */'../views/About.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
