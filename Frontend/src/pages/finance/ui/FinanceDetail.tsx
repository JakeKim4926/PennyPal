import { useEffect, useRef, useState } from 'react';
import { CardListUp, StockListUp } from '@/pages/finance/model';
import InfiniteScroll from 'react-infinite-scroll-component';

interface Card {
    cardId: number;
    cardType: string;
    cardCompany: string;
    cardName: string;
    cardBenefitType: string;
    cardImg: string;
    cardTopCategory: string;
    cardCategory: string;
    cardRequired: number;
    cardDomestic: number;
    cardAbroad: number;
}
interface Stock {
    stockId: number;
    crno: string;
    isinCd: string;
    stckIssuCmpyNm: string;
    basDt: number[];
    stckGenrDvdnAmt: number;
}

export function FinanceDetail() {
    const [category, setCategory] = useState<'card' | 'stock'>('card');
    const [stockItems, setStockItems] = useState<Stock[]>([]);
    const [cardItems, setCardItems] = useState<Card[]>([]);
    const [page, setPage] = useState(0);
    const [hasMore, setHasMore] = useState(true);
    const [searchOptions, setSearchOptions] = useState({
        word: '',
        startPrice: undefined as number | undefined,
        endPrice: undefined as number | undefined,
        cardName: '',
        cardCompany: '',
        startCardRequired: undefined as number | undefined,
        endCardRequired: undefined as number | undefined,
        startCardDomestic: undefined as number | undefined,
        endCardDomestic: undefined as number | undefined,
    });
    const fetchItems = async () => {
        const {
            word,
            startPrice,
            endPrice,
            cardName,
            cardCompany,
            startCardRequired,
            endCardRequired,
            startCardDomestic,
            endCardDomestic,
        } = searchOptions;

        if (category === 'stock') {
            try {
                const response = await StockListUp(0, 10, word, startPrice, endPrice); // page와 size를 고정값으로 사용
                setStockItems((prevItems) => [...prevItems, ...response.data.data.content]);
                if (response.data.data.content.length < 10) setHasMore(false);
            } catch (error) {
                console.error('Fetching stock items failed', error);
            }
        } else if (category === 'card') {
            try {
                const response = await CardListUp(
                    0,
                    10,
                    cardName,
                    cardCompany,
                    startCardRequired,
                    endCardRequired,
                    startCardDomestic,
                    endCardDomestic,
                ); // page와 size를 고정값으로 사용
                setCardItems((prevItems) => [...prevItems, ...response.data.data.content]);
                if (response.data.data.content.length < 10) setHasMore(false);
            } catch (error) {
                console.error('Fetching card items failed', error);
            }
        }
    };
    const handleSearch = () => {
        // 검색 옵션에 따라 데이터를 초기화하고 새로운 데이터를 불러옵니다.
        if (category === 'stock') {
            setStockItems([]);
        } else {
            setCardItems([]);
        }
        setPage(0); // 페이지를 초기화합니다.
        setHasMore(true); // 더 불러올 데이터가 있다고 가정합니다.
        fetchItems(); // 새로운 데이터를 불러오는 함수를 호출합니다.
    };
    const resetSearchOptions = () => {
        setSearchOptions({
            word: '',
            startPrice: undefined,
            endPrice: undefined,
            cardName: '',
            cardCompany: '',
            startCardRequired: undefined,
            endCardRequired: undefined,
            startCardDomestic: undefined,
            endCardDomestic: undefined,
        });
    };
    const adjustImageStyle = (e: React.SyntheticEvent<HTMLImageElement, Event>) => {
        const imgElement = e.currentTarget; // 타입 단언
        if (imgElement.naturalWidth > imgElement.naturalHeight) {
            // 가로가 세로보다 긴 경우
            imgElement.style.width = '150px';
            imgElement.style.height = 'auto';
        } else {
            // 세로가 가로보다 긴 경우 또는 정사각형
            imgElement.style.width = 'auto';
            imgElement.style.height = '150px';
        }
    };
    useEffect(() => {
        fetchItems();
    }, [page, category]); // category를 의존성 배열에 추가

    return (
        <div className="container">
            {/* 탭 버튼 */}
            <div className="tabButton">
                <button onClick={() => setCategory('card')}>|카드</button>||
                <button onClick={() => setCategory('stock')}>주식|</button>
            </div>
            {/* 검색 바 */}
            {category === 'stock' && (
                <div>
                    <input
                        type="text"
                        placeholder="단어 검색..."
                        value={searchOptions.word}
                        onChange={(e) => setSearchOptions({ ...searchOptions, word: e.target.value })}
                    />
                    <input
                        type="number"
                        placeholder="최소 가격..."
                        value={searchOptions.startPrice || ''}
                        onChange={(e) =>
                            setSearchOptions({
                                ...searchOptions,
                                startPrice: e.target.value ? parseInt(e.target.value) : undefined,
                            })
                        }
                    />
                    <input
                        type="number"
                        placeholder="최대 가격..."
                        value={searchOptions.endPrice || ''}
                        onChange={(e) =>
                            setSearchOptions({
                                ...searchOptions,
                                endPrice: e.target.value ? parseInt(e.target.value) : undefined,
                            })
                        }
                    />
                </div>
            )}
            {category === 'card' && (
                <div>
                    <input
                        type="text"
                        placeholder="카드 이름..."
                        value={searchOptions.cardName}
                        onChange={(e) => setSearchOptions({ ...searchOptions, cardName: e.target.value })}
                    />
                    <input
                        type="text"
                        placeholder="카드 회사..."
                        value={searchOptions.cardCompany}
                        onChange={(e) => setSearchOptions({ ...searchOptions, cardCompany: e.target.value })}
                    />
                    <input
                        type="number"
                        placeholder="최소 실적 요구액..."
                        value={searchOptions.startCardRequired || ''}
                        onChange={(e) =>
                            setSearchOptions({
                                ...searchOptions,
                                startCardRequired: e.target.value ? parseInt(e.target.value) : undefined,
                            })
                        }
                    />
                    <input
                        type="number"
                        placeholder="최대 실적 요구액..."
                        value={searchOptions.endCardRequired || ''}
                        onChange={(e) =>
                            setSearchOptions({
                                ...searchOptions,
                                endCardRequired: e.target.value ? parseInt(e.target.value) : undefined,
                            })
                        }
                    />
                    <input
                        type="number"
                        placeholder="최소 연회비..."
                        value={searchOptions.startCardDomestic || ''}
                        onChange={(e) =>
                            setSearchOptions({
                                ...searchOptions,
                                startCardDomestic: e.target.value ? parseInt(e.target.value) : undefined,
                            })
                        }
                    />
                    <input
                        type="number"
                        placeholder="최대 연회비..."
                        value={searchOptions.endCardDomestic || ''}
                        onChange={(e) =>
                            setSearchOptions({
                                ...searchOptions,
                                endCardDomestic: e.target.value ? parseInt(e.target.value) : undefined,
                            })
                        }
                    />
                </div>
            )}
            <button onClick={handleSearch}>검색</button>
            <button onClick={resetSearchOptions}>검색 옵션 초기화</button>
            {/* 무한 스크롤 */}
            <div id="infiniteScroll">
                <InfiniteScroll
                    dataLength={category === 'stock' ? stockItems.length : cardItems.length}
                    next={() => setPage((prevPage) => prevPage + 1)}
                    hasMore={hasMore}
                    loader={<h4>Loading...</h4>}
                    endMessage={<h2>더 표시할 데이터가 없어요ㅠㅠ</h2>}
                    scrollThreshold={0.8}
                    scrollableTarget="infiniteScroll"
                >
                    {category === 'stock' &&
                        stockItems.map((item) => (
                            <div key={item.stockId}>
                                {item.stckIssuCmpyNm} : {item.stckGenrDvdnAmt.toLocaleString()} 원
                            </div>
                        ))}
                    {category === 'card' &&
                        cardItems.map((item) => (
                            <div key={item.cardId}>
                                <div className="item">
                                    <div className="image-container">
                                        <img src={item.cardImg} onLoad={adjustImageStyle} />
                                    </div>
                                    <div>
                                        <h3>{item.cardName}</h3>
                                        <span>{item.cardCompany}</span>
                                    </div>
                                    <div>
                                        {item.cardTopCategory.split(', ').map((category, index) => (
                                            <li key={index}>{category}</li>
                                        ))}
                                    </div>
                                    <div>
                                        <span>전월실적: {item.cardRequired}이상</span>
                                        <br />
                                        연회비 <span>: 국내전용: {item.cardDomestic}원 / </span>
                                        <span>해외겸용: {item.cardAbroad}원</span>
                                    </div>
                                </div>
                            </div>
                        ))}
                </InfiniteScroll>
            </div>
        </div>
    );
}
