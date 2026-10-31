package com.LibraryManagement.controller;

import com.LibraryManagement.entity.IssueRecord;
import com.LibraryManagement.service.IssueRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issuerecords")
@RequiredArgsConstructor
public class IssueRecordController {
     private final IssueRecordService issueRecordService;



    @PostMapping("/issuethebo0k/{id}")
    public ResponseEntity<IssueRecord> issueTheBook(@PathVariable Long id){
        return ResponseEntity.ok(issueRecordService.issueBook(id));
    }

    @PostMapping("/returnthebook/{issueRecordId}")
    public ResponseEntity<IssueRecord> returnTheBook(@PathVariable Long issueRecordId){
        return ResponseEntity.ok(issueRecordService.returnBook(issueRecordId));
    }
    @GetMapping("/myrecords")
    public ResponseEntity<List<IssueRecord>> getMyRecords() {
        return ResponseEntity.ok(issueRecordService.getMyBorrowedBooks());
    }
}
