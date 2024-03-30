export function RankingTable() {
    const ranking = [
        { id: 0, rank: 1, name: '팀이름', score: '123' },
        { id: 1, rank: 1, name: '팀이름', score: '123' },
        { id: 2, rank: 1, name: '팀이름', score: '123' },
        { id: 3, rank: 1, name: '팀이름', score: '123' },
        { id: 4, rank: 1, name: '팀이름', score: '123' },
        { id: 5, rank: 1, name: '팀이름', score: '123' },
    ];
    const myRanking = { id: 20, rank: 23, name: '팀이름', score: '85' };
    return (
        <div className="rankingTable">
            <div className="rankingTable__header">
                <div>순위</div>
                <div>팀명</div>
                <div>점수</div>
                <div>보상 포인트</div>
            </div>
            <div className="rankingTable__content">
                <ul className="rankingTable__content-list">
                    {ranking.map((it) => (
                        <li className="rankingTable__content-list-item">
                            <div>{it.rank}</div>
                            <div>{it.name}</div>
                            <div>{it.score}</div>
                            <div>P 5,000</div>
                        </li>
                    ))}
                </ul>
            </div>
            <div className="rankingTable__mine">
                <div className="rankingTable__mine-rank">{myRanking.rank}</div>
                <div className="rankingTable__mine-name">{myRanking.name}</div>
                <div className="rankingTable__mine-score">{myRanking.score}</div>
                <div>보상 포인트</div>
            </div>
            <div className="rankingTable__pagenation">
                <div className="rankingTable__pagenation-box">
                    <div>1</div>
                    <div>2</div>
                    <div>3</div>
                    <div>4</div>
                    <div>5</div>
                </div>
            </div>
        </div>
    );
}
