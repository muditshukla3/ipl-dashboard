package com.ms.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ms.ipldashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long>{
    
    public List<Match> findByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName)" 
            +" and m.date between :startDate and :endDate"
            +" order by date desc")
    public List<Match> getMatchesByTeamBetweenDates(@Param("teamName") String teamName, 
                                                    @Param("startDate") LocalDate startDate,
                                                    @Param("endDate")  LocalDate endDate);
    // public List<Match> findByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
    //                     String teamName1,LocalDate date1, LocalDate date2, 
    //                     String teamName2,LocalDate startDate, LocalDate endDate);

    default List<Match> findLatestMatchesByTeam(String teamName, int count){
        return findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }
}
