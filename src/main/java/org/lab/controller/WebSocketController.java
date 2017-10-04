package org.lab.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.lab.dto.BatsmanDTO;
import org.lab.dto.ChatMessageDTO;
import org.lab.service.impl.LiveScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    
    @Autowired
    private LiveScoreService service;
    
    // ppt : https://os.alfajango.com/websockets-slides/#/
    
    @MessageMapping("/score")	// $stomp.send('/app/score', '');
    @SendTo("/topic/myscores")	// $stomp.subscribe('/topic/myscores',
    public List<BatsmanDTO> getScores() {
        
        List<BatsmanDTO> scoresList = service.getScore();
        return scoresList;
    }
    
    /*// UI does not sends message on this channel
    @MessageMapping("/twitter")	
    @SendTo("/topic/tweets")	
    public List<BatsmanDTO> getTweets() {
        
        List<BatsmanDTO> scoresList = service.getScore();
        return scoresList;
    }*/
	
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessageDTO send(ChatMessageDTO message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new ChatMessageDTO(message.getFrom(), message.getText(), new Date().toString());
    }
	
	@MessageMapping("/chat/{topic}")
	@SendTo("/topic/messages")
	public ChatMessageDTO sendTopic(
	        @DestinationVariable("topic") String topic, ChatMessageDTO message)
	        throws Exception {
	    return new ChatMessageDTO(message.getFrom(), message.getText(), topic);
	}
	
}