package org.example.betting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bet {
    
    private String betId;
    private String userId;
    private String eventId;
    private String eventMarketId;
    private String eventWinnerId; // The expected winner for this bet
    private Double betAmount;

    private boolean settled;
    private boolean won;

    public void settleBet(EventOutcome eventOutcome) {
        boolean hasWon = eventWinnerId.equalsIgnoreCase(eventOutcome.getWinnerId()); // same winner
        this.settled = true;
        this.won = hasWon;
    }

}
