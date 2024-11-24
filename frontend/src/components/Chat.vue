<template>
  <div class="chat-container">
    <div class="messages">
      <div v-for="(message, index) in messages" :key="index" class="message" :style="getReverse(message)">
        <div class="username-container">
          <span class="username">{{ message.sender }}</span>
        </div>
        <div class="text-container">
          <span class="text">{{ message.content }}</span>
        </div>
      </div>
    </div>
    <div class="input-container">
      <input
        type="text"
        v-model="newMessage"
        @keyup.enter="sendMessage"
        placeholder="输入消息..."
      />
      <button @click="sendMessage">发送</button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref, onUnmounted } from 'vue';
import { useRoute } from 'vue-router';

enum MessageType {
  CHAT = 'CHAT',
  JOIN = 'JOIN',
  LEAVE = 'LEAVE',
  CONFIRM = 'CONFIRM'
}

class Message {
  content: string;
  sender: string;
  type: MessageType;
  timestamp: string;

  constructor(content: string, sender: string, type: MessageType) {
    this.content = content;
    this.sender = sender;
    this.type = type;
    this.timestamp = new Date().toISOString();
  }
}

  export default defineComponent({
    name: 'ChatRoom',
    methods: {
      getReverse (msg: Message) {
        if ( msg.type === MessageType.CONFIRM ) {
          return "flex-direction: row-reverse";
        } 
        else {
          return "flex-direction: row";
        }
      }
    },
    setup() {
      const route = useRoute();
      const messages = ref<Message[]>([]);
      const newMessage = ref('');
      const username = ref(route.query.username as string);
      let ws: WebSocket;
      const isConnected = ref(false);

      const sendMessage = () => {
      if (newMessage.value.trim() !== '') {
        if (ws.readyState === WebSocket.OPEN) {
          const message = new Message(newMessage.value, username.value, MessageType.CHAT);
          ws.send( JSON.stringify(message) ); // 发送消息到服务器
          console.log('Message sent:', message);
          console.log('WebSocket state:', ws.readyState);
          console.log('WebSocket URL:', ws.url);
          newMessage.value = '';
        } else {
          console.error('WebSocket is not open. Current state:', ws.readyState);
        }
      }
    };

      onMounted(() => {
        ws = new WebSocket('ws://localhost:9000/ws');

        ws.onopen = () => {
          console.log('Connected to server');
          isConnected.value = true;
          const joinMessage = new Message('加入聊天室', username.value, MessageType.JOIN);
          ws.send(JSON.stringify(joinMessage));
          console.log('Sent message:', joinMessage);
        };

        ws.onmessage = (event) => {
          console.log('Message received:', event.data);
          const message: Message = JSON.parse(event.data);
          messages.value.push(message);
        };

        ws.onclose = () => {
          console.log('Disconnected from server');
          isConnected.value = false;
        };

        ws.onerror = (error) => {
          console.error(error);
          isConnected.value = false;
        };
      });

      onUnmounted(() => {
        ws.close();
      });

      return {
        messages,
        newMessage,
        sendMessage
      };
    },
  });
</script>

<style>
.chat-container {
  display: flex;
  flex-direction: column;
  position: absolute;
  bottom: 0;
  top: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 100%;
  max-width: 1000px;
  border: 1px solid #ccc;
  border-top: 0px;
  overflow: hidden;
}

.messages {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
  background-color: #2e2e2e;
}

.message {
  display: flex;
  flex-direction: row;
  margin-bottom: 10px;
  font-size: large;
  color: white;
}

.username-container {
  display: flex;
  justify-content: center;
  max-width: 8%;
  min-width: 8%;
  margin: 10px;
}

.username {
  font-weight: bold;
}

.text-container {
  padding: 10px;
  border-radius: 12px;
  background-color: #0091ff;
  border: #0091ff 0px solid;
  max-width: 60%;
}

.input-container {
  display: flex;
  padding: 20px;
  border-top: 0px solid #ccc;
  background-color: #fff;
}

input[type='text'] {
  flex: 1;
  padding: 15px;
  border: 1px solid #ccc;
  border-radius: 12px;
  margin-right: 10px;
  font-size: medium;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 12px;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>