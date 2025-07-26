package org.example.betting.controller;


import org.example.betting.model.Bet;
import org.example.betting.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bets")
public class BetController {
    @Autowired
    private BetService betService;

    @PostMapping("/add")
    public ResponseEntity<?> addBet(@RequestBody Bet bet) {
        betService.addBet(bet);
        return ResponseEntity.ok("Bet added");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getBets() {
        return ResponseEntity.ok(betService.getAllBets());
    }

}
