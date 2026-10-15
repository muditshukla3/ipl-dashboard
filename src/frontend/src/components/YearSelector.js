import { React } from 'react'
import { Link } from 'react-router-dom';
import './YearSelector.scss';
export const YearSelector = ({teamName}) => {
    let year = [];
    const startYear = 2008;
    const endYear = 2020;

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