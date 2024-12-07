package space.kkho.chatroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import space.kkho.chatroom.domain.ChatMessage;

public class ChatServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel());
        System.out.println("客户端连接: " + ctx.channel().remoteAddress());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channels.remove(ctx.channel());
        System.out.println("客户端断开连接: " + ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        try {
            ChatMessage chatMessage = objectMapper.readValue(msg.text(), ChatMessage.class);
            System.out.println("收到消息: " + chatMessage.getContent());
            ChatMessage msg_ = new ChatMessage(chatMessage.getContent(), chatMessage.getSender(), chatMessage.getType());
            ChatMessage msg_re = new ChatMessage(chatMessage.getContent(), chatMessage.getSender(), chatMessage.getType());
            if (msg_re.getType() == ChatMessage.MessageType.CHAT) {
                msg_re.setType(ChatMessage.MessageType.CONFIRM);
            }
            for (var channel : channels) {
                if (channel != ctx.channel()) {
                    channel.writeAndFlush( new TextWebSocketFrame(objectMapper.writeValueAsString(msg_)) );
                    System.out.println("转发消息: " + msg_.getContent());
                } else {
                    channel.writeAndFlush(new TextWebSocketFrame(objectMapper.writeValueAsString(msg_re)));
                    System.out.println("回复消息: " + msg_re.getContent());
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to parse message: " + msg.text());
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}