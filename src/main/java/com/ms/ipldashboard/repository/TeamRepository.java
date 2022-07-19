package com.ms.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.ms.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long>{
    
    Team findByTeamName(String teamName);
}
