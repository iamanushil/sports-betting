package org.example.betting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventOutcome {
    
    private String eventId;
    private String eventName;
    private String winnerId;

}
