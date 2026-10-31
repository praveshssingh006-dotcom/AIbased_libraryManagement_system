package com.LibraryManagement.controller;

import com.LibraryManagement.service.Aiservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {
    private final Aiservice aiservice;

    @PostMapping("/queryaboutthebook")
    public ResponseEntity<String> queryAboutTheBookPost(
            @RequestBody(required = false) Object body,
            @RequestParam(value = "question", required = false) String paramQuestion) {
        String prompt = resolveQuestion(body, paramQuestion);
        return ResponseEntity.ok(aiservice.askGemini(prompt));
    }

    @GetMapping("/queryaboutthebook")
    public ResponseEntity<String> queryAboutTheBookGet(
            @RequestParam(value = "question", required = false) String question,
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "title", required = false) String title) {
        String prompt = question;
        if (prompt == null || prompt.isBlank()) prompt = query;
        if (prompt == null || prompt.isBlank()) prompt = title;
        if (prompt == null || prompt.isBlank()) prompt = "General book recommendations";
        return ResponseEntity.ok(aiservice.askGemini(prompt));
    }

    private String resolveQuestion(Object body, String paramQuestion) {
        if (body instanceof String str && !str.isBlank()) {
            if (str.trim().startsWith("{") && str.contains("\"question\"")) {
                try {
                    int start = str.indexOf("\"question\"") + 10;
                    int colon = str.indexOf(":", start);
                    int quoteStart = str.indexOf("\"", colon + 1);
                    int quoteEnd = str.indexOf("\"", quoteStart + 1);
                    if (quoteStart != -1 && quoteEnd != -1) {
                        return str.substring(quoteStart + 1, quoteEnd);
                    }
                } catch (Exception ignored) {}
            }
            return str;
        }
        if (body instanceof Map<?, ?> map) {
            Object q = map.get("question");
            if (q != null && !q.toString().isBlank()) return q.toString();
            Object query = map.get("query");
            if (query != null && !query.toString().isBlank()) return query.toString();
            Object title = map.get("title");
            if (title != null && !title.toString().isBlank()) return title.toString();
        }
        if (paramQuestion != null && !paramQuestion.isBlank()) {
            return paramQuestion;
        }
        return "General book recommendations";
    }
}