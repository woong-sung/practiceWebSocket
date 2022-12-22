package com.practice.jojoPractice.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Controller
// index.html에서 웹소켓을 연결하기위한 주소
@ServerEndpoint("/websocket")
public class MessageController extends Socket {
    // 사용자 정보를 담을 List
    private static final List<Session> session = new ArrayList<>();

    @GetMapping("/")
    public String index() {
        // 사용자 입장 시 리턴
        return "index.html";
    }

    // 사용자 접속시 실행
    @OnOpen
    public void open(Session newUser) {
        System.out.println("[connected]");
        session.add(newUser);
        System.out.println("접속중인 유저 수: " + newUser.getId());
    }

    // 사용자로부터 메세지를 받으면 실행
    @OnMessage
    public void getMsg(Session receiveSession, String msg){
        for (int i = 0; i < session.size(); i++) {
            if (!receiveSession.getId().equals(session.get(i).getId())) {
                try {
                    session.get(i).getBasicRemote().sendText("상대: " + msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    session.get(i).getBasicRemote().sendText("나: "+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
