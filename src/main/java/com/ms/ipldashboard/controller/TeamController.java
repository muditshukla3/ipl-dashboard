package com.ms.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.ipldashboard.model.Match;
import com.ms.ipldashboard.model.Team;
import com.ms.ipldashboard.repository.MatchRepository;
import com.ms.ipldashboard.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {
    
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;
    
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository){
        this.teamRepository=teamRepository;
        this.matchRepository=matchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        // write logic if team name not found and return and error response with not found error code
        Team team = teamRepository.findByTeamName(teamName);
        
        team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, 4));

        return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesByYear(@PathVariable String teamName, @RequestParam int year){
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year+1, 1, 1);
        return matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
    }

    @GetMapping("/team")
    public Iterable<Team> getAllTeams(){
        return teamRepository.findAll();
    }
}
