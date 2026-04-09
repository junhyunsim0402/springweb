package springweb.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller // RestController 사용하지 않는 이유 : HTTP 응답이 아니라서
public class ChatController {
    // @GetMapping // HTTP 요청이 아니라서 쓰지 않는다
    @MessageMapping("/chat") // 클라이언트가 서버에게 메세지를 보낸 주소 매핑,            클라이언트 --> 서버
    // @ResponseBody   // HTTP 응답이 아니라서 쓰지 않는다
    @SendTo("/topic/message") // 서버가 연동(구독)된 클라이언트에게 메세지 응답 주소 매핑, 클라이언트 <--- 서버
    public Map<String,Object> sendMessage(Map<String,Object> message){
        System.out.println("message = " + message);
        System.out.println("ChatController.sendMessage");
        return message;
    }
}
