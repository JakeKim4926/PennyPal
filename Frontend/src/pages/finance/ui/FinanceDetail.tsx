import { useEffect, useRef, useState } from 'react';
import { CardListUp, StockListUp } from '@/pages/finance/model';
import InfiniteScroll from 'react-infinite-scroll-component';
interface Stock {
    stockId: number;
    crno: string;
    isinCd: string;
    stckIssuCmpyNm: string;
    basDt: number[];
    stckGenrDvdnAmt: number;
}
interface Card {
    cardName: string;
    cardCompany: string;
    startCardRequired: number;
    endCardRequired: number;
    startCardDomestic: number;
    endCardDomestic: number;
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
    useEffect(() => {
        fetchItems();
    }, [page, category]); // category를 의존성 배열에 추가

    return (
        <div className="container">
            {/* 탭 버튼 */}
            <div className="tabButton">
                <button onClick={() => setCategory('card')}>카드</button>
                <button onClick={() => setCategory('stock')}>주식</button>
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
                        value={searchOptions.startPrice}
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
                        value={searchOptions.endPrice}
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
                        value={searchOptions.startCardRequired}
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
                        value={searchOptions.endCardRequired}
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
                        value={searchOptions.startCardDomestic}
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
                        value={searchOptions.endCardDomestic}
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
            {/* 무한 스크롤 */}
            <div id="infiniteScroll">
                <InfiniteScroll
                    dataLength={category === 'stock' ? stockItems.length : cardItems.length}
                    next={() => setPage((prevPage) => prevPage + 1)}
                    hasMore={hasMore}
                    loader={<h4>Loading...</h4>}
                    endMessage={<h2>더 이상 데이터가 없습니다.</h2>}
                    scrollThreshold={0.8}
                    scrollableTarget="infiniteScroll"
                >
                    {category === 'stock' &&
                        stockItems.map((item) => (
                            <div key={item.stockId}>
                                {item.stckIssuCmpyNm} : {item.stckGenrDvdnAmt.toLocaleString()} 원
                            </div>
                        ))}
                    {category === 'card' && cardItems.map((item) => <div key={item.cardName}>{item.cardName}</div>)}
                </InfiniteScroll>
            </div>
        </div>
    );
}
