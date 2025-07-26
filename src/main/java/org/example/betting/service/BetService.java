package org.example.betting.service;

import jakarta.annotation.PostConstruct;
import org.example.betting.model.Bet;
import org.example.betting.model.EventOutcome;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BetService {

    private final Map<String, Bet> bets = new HashMap<>(); // In-memory storage for bets

    @PostConstruct
    public void initDummyBets() {
        addBet(
                Bet.builder()
                        .betId("bet1").userId("user1")
                        .eventId("match123").eventMarketId("market1").eventWinnerId("teamIndia")
                        .betAmount(100.0)
                        .settled(false)
                        .won(false)
                        .build()
        );
        addBet(
                Bet.builder()
                        .betId("bet2").userId("user2")
                        .eventId("match123").eventMarketId("market1").eventWinnerId("teamAustralia")
                        .betAmount(150.0)
                        .settled(false)
                        .won(false)
                        .build()
        );
        addBet(
                Bet.builder()
                        .betId("bet3").userId("user3")
                        .eventId("match456").eventMarketId("market3").eventWinnerId("teamIndia")
                        .betAmount(200.0)
                        .settled(false)
                        .won(false)
                        .build()
        );
        System.out.println("Initialized dummy bets.");
    }

    /**
     * Add a new bet to the in memory storage.
     */
    public void addBet(Bet bet) {
        bets.put(bet.getBetId(), bet);
    }

    /**
     * Get all bets for an event.
     */
    public List<Bet> getBetsForEvent(String eventId) {
        return bets.values().stream()
                .filter(each -> each.getEventId().equalsIgnoreCase(eventId))
                .collect(Collectors.toList());
    }

    public List<Bet> getAllBets() {
        return new ArrayList<>(bets.values());
    }

    /**
     * Settle bets for an event based on the event outcome.
     */
    public void settleBets(EventOutcome eventOutcome) {
        List<Bet> betsForEvent = getBetsForEvent(eventOutcome.getEventId());
        betsForEvent.forEach(bet -> {
            bet.settleBet(eventOutcome);
            System.out.printf("Settling bet %s: %s\n", bet.getBetId(), bet.isWon() ? "WIN" : "LOSE");
        });
        System.out.println("Bets: " + bets);
        System.out.println("Bets for event: " + betsForEvent);
    }

}
