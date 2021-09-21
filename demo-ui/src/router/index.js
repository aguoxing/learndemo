import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: '/websocket',
    component: (resolve) => require(['@/views/websocket/index'], resolve),
    name: 'websocket',
    meta: { title: 'websocket', icon: 'dashboard', noCache: true, affix: true }
  },
];

const router = new VueRouter({
  routes,
});

export default router;
