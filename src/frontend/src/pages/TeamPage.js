import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchCrispCard } from '../components/MatchCrispCard';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { PieChart } from 'react-minimal-pie-chart';
import { Link } from 'react-router-dom';
import './TeamPage.scss';

export const TeamPage = () => {

    const [team, setTeam] = useState({ matches: [] });
    const { teamName } = useParams();
    const endYear = process.env.REACT_APP_DATA_END_YEAR;
    const defaultLabelStyle = {
        fontSize: '15px',
        fontFamily: 'sans-serif',
      };
    useEffect(() => {
        const fetchMatches = async () => {
            const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/team/${teamName}`);
            const data = await response.json();
            setTeam(data);
        };

        fetchMatches();
    }
        , [teamName]);
    if (!team || !team.teamName) {
        return <h1>Team Not Found</h1>
    }
    return (
        <div className='TeamPage'>
            <div className='team-name-section'>
                <h1 className='team-name'>{team.teamName}</h1>
            </div>
            <div className='win-loss-section'>Win/Loss
            <PieChart
                data={[
                    { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#a34d5d' },
                    { title: 'Wins', value: team.totalWins, color: '#4da375' },
                ]}
                label={({ dataEntry }) => dataEntry.value}
                labelStyle={{
                    ...defaultLabelStyle,
                }}
                />         
            </div>
            <div className='match-detail-section'>
                <h3>Latest Matches</h3>
                <MatchDetailCard teamName={team.teamName} match={team.matches[0]} />
            </div>
            {team.matches.slice(1).map(match => <MatchCrispCard key={match.id} teamName={team.teamName} match={match} />)}
            <div className='more-link'>
                <Link to={`/teams/${teamName}/matches/${endYear}`}>More ></Link>
            </div>
        </div>
    );
}