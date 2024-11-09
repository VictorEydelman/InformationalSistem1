import Vue from 'vue'
import App from './App.vue'
import First from "./First.vue"
import Main from "./Main.vue"
import Form from "./Form.vue"
import Add from "./Add.vue"
import Update from "./Update.vue"
import FilterByIndex from "./FilterByIndexResult.vue"
import FilterDescription from "./FilterByDesciplation.vue"
Vue.component("app-first",First)
Vue.component("app-main",Main)
Vue.component("app-form",Form)
Vue.component("app-add",Add)
Vue.component("app-update",Update)
Vue.component("app-filterbyindexresult",FilterByIndex)
Vue.component("app-filterbydescription",FilterDescription)
Vue.use(VueRouter)
const routes = [
  {path: '/', name: "/", component: First},
  {path: '/first', name: "first", component: First},
  {path: '/main', name: "main", component: Main, beforeEnter:(to,from,next)=>{
    if (localStorage.getItem("jwt")!=="") next();
    else next ({name: "/"})
    }},
  {path: '/main/form',name: "form",component: Form},
  {path: '/main/add',name: "add",component: Add},
  {path: '/main/update',name: "update",component: Update},
  {path: '/main/index',name: "index", component: FilterByIndex},
  {path: '/main/description', name: "description", component: FilterDescription}
]
const router = new VueRouter({
  routes
})

new Vue({
  el: '#app',
  render: h => h(App),
  router
})
