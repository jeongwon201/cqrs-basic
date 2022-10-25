package com.cqrsv1.ui;

import com.cqrsv1.command.application.AccountRequest;
import com.cqrsv1.command.application.AccountService;
import com.cqrsv1.command.domain.AccountId;
import com.cqrsv1.query.application.AccountQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private AccountService commandService;
    private AccountQueryService queryService;

    public AccountController(AccountService commandService, AccountQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping("/account")
    public ResponseEntity register(@RequestBody AccountRequest request) {
        commandService.save(request);
        return ResponseEntity.ok("Your account has been created.");
    }

    @PutMapping("/account/{id}")
    public ResponseEntity change(@PathVariable("id") String id, @RequestBody AccountRequest request) {
        request.setId(id);
        commandService.change(request);
        return ResponseEntity.ok("Your account has been modified.");
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        commandService.delete(new AccountId(id));
        return ResponseEntity.ok("Your account has been deleted.");
    }

    @GetMapping("/account/{id}")
    public ResponseEntity find(@PathVariable("id") String id) {
        return ResponseEntity.ok(queryService.find(id));
    }

    @GetMapping("/accounts")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(queryService.findAll());
    }
}
