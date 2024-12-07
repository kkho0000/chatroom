import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'root',
    component: () => import('../components/Login.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../components/Login.vue')
  },
  {
    path: '/chat',
    name: 'Chat',
    component: () => import('../components/Chat.vue')
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;