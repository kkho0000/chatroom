# space chatroom
实时聊天室应用，允许用户注册、登陆和实时发送与接收消息。

### 项目结构

该仓库包含应用的前端和后端：

- 后端：应用Java Spring Boot处理用户认证、消息以及数据库操作。
- 前端：应用Vue搭建UI界面与后端进行交互。

### 项目特性

- 基于Netty框架实现消息的接收与转发。

---

### 后端安装

#### 克隆仓库

```bash
git clone https://github.com/kkho0000/chatroom
cd ./chatroom/backend
```

#### 搭建数据库

创建名为`chat_room_db`的MySQL数据库。然后配置 `src/main/resources/application.properties`文件。

```properties
spring:
  application:
    name: chatroom
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/chat_room_db
    username: your_username
    password: your_password
```

#### 构建并运行项目

```bash
mvn clean install
mvn spring-boot:run
```

---

### 前端安装

前往前端目录下安装依赖：

```bash
cd ./frontend
npm install
```

运行项目：

```bash
npm run dev
```

---

### 消息模型

```json
{
  "content": "Hello, how are you?",
  "sender": "user1",
  "type": "CHAT", // CHAT, JOIN, LEAVE, CONFIRM
  "timestamp": "2024-12-07T12:34:56Z"
}
```
