import { React } from 'react'
import { Link } from 'react-router-dom';
import './YearSelector.scss';
export const YearSelector = ({teamName}) => {
    let year = [];
    const startYear = process.env.REACT_APP_DATA_START_YEAR;
    const endYear = process.env.REACT_APP_DATA_END_YEAR;

    for (let i = startYear; i <= endYear; i++) {
        year.push(i);
    }

    return (
        <ol className='YearSelector'>
            {
                year.map(year =>

                    <li key={year}>
                       <Link to={`/teams/${teamName}/matches/${year}`}>{year}</Link>
                    </li>
                )
            }
        </ol>


    );
}