package com.FazalProject.ExpenseTracker.Controller;

import com.FazalProject.ExpenseTracker.Entity.Entry;
import com.FazalProject.ExpenseTracker.Service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("entry")
@CrossOrigin(origins = "https://expense-tracker-frontend-black.vercel.app")
public class EntryController {
    @Autowired
    EntryService entryService;

    @PostMapping("{username}/create")
    public ResponseEntity<?> createEntry(@PathVariable String username,@RequestBody Entry entry){
        return entryService.create(username,entry);
    }

    @DeleteMapping("{eid}/delete")
    public ResponseEntity<?> deleteEntry(@PathVariable int eid){
        return entryService.delete(eid);
    }

    @PutMapping("{eid}/update")
    public ResponseEntity<?> updateEntry(@PathVariable int eid, @RequestBody Entry entry){
        return entryService.update(eid,entry);
    }
}
