<template>
  <div class="login-container">
    <h2>登录</h2>
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="username">用户名</label>
        <input type="text" id="username" v-model="username" required />
      </div>
      <div class="form-group">
        <label for="password">密码</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <div class="form-actions">
        <button type="submit" @click="login">登录</button>
        <button type="button" @click="register">注册</button>
      </div>
    </form>
    <div v-if="message" :class="{ 'alert-success': isSuccess, 'alert-error': !isSuccess }">
      {{ message }}
    </div>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'Login',
  data() {
    return {
      username: '',
      password: '',
      message: '',
      isSuccess: false
    };
  },
  methods: {
    async login() {
      // 登录逻辑
      try {
        const response = await axios.post('http://localhost:8080/login', {
          username: this.username,
          password: this.password
        });
        console.log(response);
        if (response.data.code === 200) {
          this.message = '登录成功';
          this.isSuccess = true;
          this.$router.push({ path: '/chat', query: { username: this.username } });
        }
        else if (response.data.code === 400) {
          this.message = '登录失败: ' + response.data.message;
          this.isSuccess = false;
        }
      } 
      catch (error: any) {
        this.message = '登录失败: 未知错误';
        this.isSuccess = false;
      }
    },
    async register() {
      try {
        const response = await axios.post('http://localhost:8080/user', {
          username: this.username,
          password: this.password
        });
        if (response.data.code === 200) {
          this.message = '注册成功';
          this.isSuccess = true;
          this.$router.push('/chat');
        }
        else if (response.data.code === 400) {
          this.message = '注册失败: ' + response.data.message;
          this.isSuccess = false;
        }
      } 
      catch (error: any) {
        this.message = '注册失败: 未知错误';
        this.isSuccess = false;
      }
    }
  }
});
</script>

<style scoped>

.alert-success {
  color: green;
}

.alert-error {
  color: red;
}

.login-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}

.form-actions {
  display: flex;
  justify-content: space-between;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>