package com.LibraryManagement.service;

import com.LibraryManagement.entity.Book;
import com.LibraryManagement.entity.IssueRecord;
import com.LibraryManagement.entity.Users;
import com.LibraryManagement.repo.BookRepo;
import com.LibraryManagement.repo.IssueReportRepo;
import com.LibraryManagement.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssueRecordService {
    private final IssueReportRepo issueReportRepo;
    private final UserRepo userRepo;
    private final BookRepo bookRepo;

    public IssueRecord returnBook(Long issueRecordId) {
        IssueRecord issueRecord = issueReportRepo.findById(issueRecordId).orElseThrow(() ->
                new RuntimeException("issueRecordId not found"));
        if(issueRecord.getIsReturned()){
            throw new RuntimeException("issueRecord is already returned");
        }
        Book book = issueRecord.getBook();
        book.setQuantity(book.getQuantity() + 1);
        book.setIsAvailable(true);
        bookRepo.save(book);
        issueRecord.setReturnDate(LocalDate.now());
        issueRecord.setIsReturned(true);
        issueReportRepo.save(issueRecord);
        return issueRecord;

    }

    public IssueRecord issueBook(Long id) {
        Book book = bookRepo.findById(id).orElseThrow(()->new RuntimeException("Book not found!"));
        if(book.getQuantity() == 0 || !book.getIsAvailable())  throw new RuntimeException("Book is not available");
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> user = userRepo.findByUserName(userName);
        if(user.isPresent()){
            IssueRecord issueRecord = new IssueRecord();
            issueRecord.setBook(book);
            issueRecord.setIssueDate(LocalDate.now());
            issueRecord.setDueDate(LocalDate.now().plusDays(14));
            issueRecord.setIsReturned(false);
            issueRecord.setUser(user.get());
            book.setQuantity(book.getQuantity() - 1);
            if(book.getQuantity() == 0)  book.setIsAvailable(false);
            bookRepo.save(book);
            issueReportRepo.save(issueRecord);
            return issueRecord;

        }
        throw new RuntimeException("User not found");
    }
    public List<IssueRecord> getMyBorrowedBooks() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return issueReportRepo.findByUserUserName(userName);
    }
}
