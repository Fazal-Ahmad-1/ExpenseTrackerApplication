package com.FazalProject.ExpenseTracker.Repository;

import com.FazalProject.ExpenseTracker.Entity.Entry;
import com.FazalProject.ExpenseTracker.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry,Integer> {

    List<Entry> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
}
