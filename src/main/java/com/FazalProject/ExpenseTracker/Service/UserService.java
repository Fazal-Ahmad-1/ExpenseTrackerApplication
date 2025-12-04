package com.FazalProject.ExpenseTracker.Service;

import com.FazalProject.ExpenseTracker.Entity.Entry;
import com.FazalProject.ExpenseTracker.Entity.LoginDTO;
import com.FazalProject.ExpenseTracker.Entity.User;
import com.FazalProject.ExpenseTracker.Entity.UserStats;
import com.FazalProject.ExpenseTracker.Repository.EntryRepository;
import com.FazalProject.ExpenseTracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntryRepository entryRepository;

    public ResponseEntity<?>login(LoginDTO loginDTO){
        try{
            User user=userRepository.findByUsername(loginDTO.getUsername());
            if(user==null)return new ResponseEntity<>("Invalid Username",HttpStatus.NOT_FOUND);
            boolean passwordMatches = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
            if(!passwordMatches)return new ResponseEntity<>("Invalid Password!",HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>("Login Successfull",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> createUser(User user){
        try{
            String encodedPassword=passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteUser(String username){
        try{
            User user=userRepository.findByUsername(username);
            if(user==null)return new ResponseEntity<>("Invalid username",HttpStatus.BAD_REQUEST);
            userRepository.deleteById(user.getUid());
            return new ResponseEntity<>("User Deleted",HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getUserEntries(String username){
        try{
            User user=userRepository.findByUsername(username);
            if(user==null)return new ResponseEntity<>("Invalid Username",HttpStatus.NOT_FOUND);
            if(user.getEntryList().isEmpty())return new ResponseEntity<>("No Entries!",HttpStatus.OK);
            return new ResponseEntity<>(user.getEntryList(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> getUserStats(String username, int month, int year){
        try{
            User user=userRepository.findByUsername(username);
            if(user==null)return new ResponseEntity<>("Invalid username!",HttpStatus.BAD_REQUEST);
            UserStats userStat=new UserStats();

            YearMonth yearMonth=YearMonth.of(year,month);
            LocalDate startDate=yearMonth.atDay(1);
            LocalDate endDate=yearMonth.atEndOfMonth();

            List<Entry> userEntries=entryRepository.findByUserAndDateBetween(user,startDate,endDate);

             double highestExpense=userEntries.stream().mapToDouble(e->e.getPrice()*e.getQuantity()).max().orElse(0.0);
             double totalAmount=userEntries.stream().mapToDouble(e->e.getQuantity()*e.getPrice()).sum();
             long totaldays=yearMonth.lengthOfMonth();
             double avgSpent=totalAmount/totaldays;

             userStat.setYear(year);
             userStat.setMonth(month);
             userStat.setTotalSpent(totalAmount);
             userStat.setAverageDailySpent(avgSpent);
             userStat.setHighestExpense(highestExpense);
             return new ResponseEntity<>(userStat,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

