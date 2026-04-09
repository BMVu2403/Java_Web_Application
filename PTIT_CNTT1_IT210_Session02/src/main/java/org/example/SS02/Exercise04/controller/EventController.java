package org.example.SS02.Exercise04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

@Controller
public class EventController {

    @GetMapping("/search")
    public String searchEvents(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, Model model) {
        // Giả lập danh sách sự kiện
        List<Map<String, Object>> events = new ArrayList<>();

        // Thêm dữ liệu mẫu để test các điều kiện (Miễn phí, Hết vé, Sắp hết vé)
        events.add(createEvent(1, "Spring MVC", 0.0, 15, "15/04/2026"));
        events.add(createEvent(2, "Java Web ", 500000.0, 5, "20/04/2026"));
        events.add(createEvent(3, "Github Pro", 200000.0, 0, "25/04/2026"));

        // Lọc sơ bộ theo keyword (không phân biệt hoa thường)
        List<Map<String, Object>> filteredEvents = new ArrayList<>();
        for (Map<String, Object> e : events) {
            if (e.get("name").toString().toLowerCase().contains(keyword.toLowerCase())) {
                filteredEvents.add(e);
            }
        }

        model.addAttribute("events", filteredEvents);
        model.addAttribute("keyword", keyword);

        // Trỏ đến file trong thư mục: views/events/search.jsp
        return "Bai4/search";
    }

    private Map<String, Object> createEvent(int id, String name, double price, int tickets, String date) {
        Map<String, Object> event = new HashMap<>();
        event.put("id", id);
        event.put("name", name);
        event.put("price", price);
        event.put("remainingTickets", tickets);
        event.put("date", date);
        return event;
    }
}