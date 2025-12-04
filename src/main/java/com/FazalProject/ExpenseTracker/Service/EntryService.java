package com.FazalProject.ExpenseTracker.Service;

import com.FazalProject.ExpenseTracker.Entity.Entry;
import com.FazalProject.ExpenseTracker.Entity.User;
import com.FazalProject.ExpenseTracker.Repository.EntryRepository;
import com.FazalProject.ExpenseTracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EntryService {
    @Autowired
    EntryRepository entryRepository;
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> create(String username,Entry entry){
        try{

            User user=userRepository.findByUsername(username);
            if(user==null)return new ResponseEntity<>("Invalid Username!",HttpStatus.BAD_REQUEST);
            entry.setDate(LocalDate.now());
            //user.getEntryList().add(entry);
            entry.setUser(user);
            //userRepository.save(user);
            entryRepository.save(entry);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> delete(int eid){
        try {
            Entry entry=entryRepository.findById(eid).orElse(null);
            if(entry==null)return new ResponseEntity<>("Invalid Id",HttpStatus.BAD_REQUEST);
            entryRepository.deleteById(eid);
            return new ResponseEntity<>("Entry deleted successfully",HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> update(int eid,Entry entry){
        try {
            Entry oldentry=entryRepository.findById(eid).orElse(null);
            if(oldentry==null)return new ResponseEntity<>("Invalid Id",HttpStatus.BAD_REQUEST);
            entryRepository.save(entry);
            return new ResponseEntity<>("Entry updated successfully",HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
