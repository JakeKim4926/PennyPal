import React, { useState, useCallback, useRef } from 'react';
import { TeamTeamList } from '@/pages/team/ui/TeamTeamList/TeamTeamList';

export function TeamTeamSearch() {
    const [searchedPage, setSearchedPage] = useState(1);
    const [keyword, setKeyWord] = useState('');
    const inputRef = useRef<HTMLInputElement>(null);

    return (
        <div className="teamTeamSearch contentCard">
            <div className="contentCard__title">
                <div className="contentCard__title-text">TEAM SEARCH</div>
            </div>
            <div className="teamTeamSearch__searchBar">
                <input className="teamTeamSearch__searchBar-input" type="text" ref={inputRef} />
                <button
                    className="teamTeamSearch__searchBar-submit"
                    onClick={() => {
                        setKeyWord(inputRef.current!.value);
                    }}
                >
                    검색
                </button>
            </div>
            <div className="teamTeamSearch__teamList">
                <TeamTeamList searchedPage={searchedPage} keyword={keyword} />
            </div>
        </div>
    );
}
