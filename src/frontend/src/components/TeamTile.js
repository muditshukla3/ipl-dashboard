import { React } from 'react';
import { Link } from 'react-router-dom';
import './TeamTile.scss';

// Logos in src/assets are named after the team name returned by the backend
const logos = require.context('../assets', false, /\.(png|jpe?g|svg)$/);

const getLogo = (teamName) => {
    const key = logos.keys().find(file => file.replace(/^\.\//, '').replace(/\.[^.]+$/, '') === teamName);
    return key ? logos(key) : null;
};

export const TeamTile = ({teamName}) => {
    const logo = getLogo(teamName);

    return (
        <div className='TeamTile'>
            <Link to={"/teams/"+teamName}>
                <div className='logo-box'>
                    {logo && <img src={logo} alt={teamName + ' logo'} />}
                </div>
                <h1>{teamName}</h1>
            </Link>
        </div>
    );
}
