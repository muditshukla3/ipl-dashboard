package com.ms.ipldashboard.processor;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import com.ms.ipldashboard.data.MatchInput;
import com.ms.ipldashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {
  
    @Override
    public Match process(MatchInput matchInput) throws Exception {
        Match match = new Match();
        match.setId(Long.valueOf(matchInput.getId()));
        match.setCity(matchInput.getCity());

        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());

        String firstInningTeam, secondInningTeam;
        if("bat".equals(matchInput.getToss_decision())){
            firstInningTeam = matchInput.getToss_winner();
            secondInningTeam = matchInput.getToss_winner().
                                equalsIgnoreCase(matchInput.getTeam1())
                                ?matchInput.getTeam2() : matchInput.getTeam1();
        }else{
            secondInningTeam = matchInput.getToss_winner();
            firstInningTeam = matchInput.getToss_winner().
                                equalsIgnoreCase(matchInput.getTeam1())
                                ?matchInput.getTeam2() : matchInput.getTeam1();
        }

        match.setTeam1(firstInningTeam);
        match.setTeam2(secondInningTeam);
        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());
        match.setMatchWinner(matchInput.getWinner());

        return match;
    }

}
