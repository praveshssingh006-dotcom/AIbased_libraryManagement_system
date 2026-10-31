package com.LibraryManagement.repo;

import com.LibraryManagement.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueReportRepo extends JpaRepository<IssueRecord,Long> {
    List<IssueRecord> findByUserUserName(String userName);
}
