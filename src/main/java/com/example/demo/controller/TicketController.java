package com.example.demo.controller;

import com.example.demo.config.JwtUtil;
import com.example.demo.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class TicketController {
    private final TicketService ticketService;
    private final JwtUtil jwtUtil;
    private final ConcurrentHashMap<Integer, BlockingQueue<String>> ticketQueueMap = new ConcurrentHashMap<>();

    @Autowired
    public TicketController(TicketService ticketService, JwtUtil jwtUtil) {
        this.ticketService = ticketService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/purchase/{ticketId}/{availCount}")
    public String ticketPurchase(@PathVariable("ticketId") Integer ticketId, @PathVariable("availCount") Integer availCount, HttpSession session) {

        String jwtToken = (String) session.getAttribute("jwtToken");
            if (jwtToken != null) {
                String userEmail = jwtUtil.extractUserEmail(jwtToken);
                ticketQueueMap.computeIfAbsent(ticketId, id -> new ArrayBlockingQueue<>(availCount - ticketService.currentCount(ticketId)));
                BlockingQueue<String> ticketQueue = ticketQueueMap.get(ticketId);
                    if (ticketQueue.offer(userEmail)) {
                        ticketService.purchaseTicket(ticketId, userEmail);
                        return "purchaseSuccess";
                    } else {
                        return "redirect:/failedTicket";
                    }
            } else {
                return "redirect:/login";
            }
    }

    //클릭 테스트용 컨트롤러, 큐가 가득 차면 failedTicket으로 보낸다. test 완료 from k6
//    @PostMapping("/purchase/{ticketId}/{availCount}")
//    public String ticketPurchase(@PathVariable("ticketId") Integer ticketId, @PathVariable("availCount") Integer availCount) {
//        System.out.println("Received purchase request for ticketId" + ticketId + " availCount:" + availCount);
//        ticketQueueMap.computeIfAbsent(ticketId, id -> new ArrayBlockingQueue<>(availCount - ticketService.currentCount(ticketId)));
//        BlockingQueue<String> ticketQueue = ticketQueueMap.get(ticketId);
//        String testUser = "testUser@example.com";
//        if (ticketQueue.offer(testUser)) {
//                ticketService.purchaseTicketTest(ticketId);
//                return "purchaseSuccess";
//            } else {
//                System.out.println("queue full");
//                return "redirect:/failedTicket";
//            }
//    }

}
