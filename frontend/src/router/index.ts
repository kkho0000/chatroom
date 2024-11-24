import { createRouter, createWebHistory } from 'vue-router';
import { defineAsyncComponent  } from 'vue';

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: defineAsyncComponent(() => import('../components/Login.vue'))
  },
  {
    path: '/chat',
    name: 'Chat',
    component: defineAsyncComponent(() => import('../components/Chat.vue'))
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;