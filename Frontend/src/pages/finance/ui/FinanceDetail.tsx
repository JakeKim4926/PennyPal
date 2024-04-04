import { useEffect, useRef, useState } from 'react';
import { CardListUp, StockListUp, StockSubDetail } from '@/pages/finance/model';
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
interface SubDetail {
    basDt: string; // 기준일
    srtnCd: string; // 단축코드
    isinCd: string; // 국제증권식별번호
    itmsNm: string; // 항목명, 예: "한국앤컴퍼니"
    mrktCtg: string; // 시장 카테고리, 예: "KOSPI"
    clpr: string; // 종가
    vs: string; // 변동
    fltRt: string; // 변동률
    mkp: string; // 시가
    hipr: string; // 고가
    lopr: string; // 저가
    trqu: string; // 거래량
    trPrc: string; // 거래 가격
    lstgStCnt: string; // 상장 주식 수
    mrktTotAmt: string; // 시장 총액
}

interface StockWithDetail {
    stock: Stock;
    subDetail?: SubDetail; // 세부 정보는 선택적으로 존재할 수 있습니다.
}
export function FinanceDetail() {
    const [category, setCategory] = useState<'card' | 'stock'>('card');
    const [stockItems, setStockItems] = useState<StockWithDetail[]>([]);
    const [cardItems, setCardItems] = useState<Card[]>([]);
    const [loading, setLoading] = useState(true); // 로딩 상태 추가
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
                const res = await StockListUp(page, 20, word, startPrice, endPrice);
                if (res.data.code === 200) {
                    let stocks: StockWithDetail[] = res.data.data.content.map((stock: Stock) => ({
                        stock: stock,
                    }));
                    // stocks 배열의 각 요소에 대해 비동기 요청을 수행
                    for (let i = 0; i < stocks.length; i++) {
                        const res2 = await StockSubDetail(stocks[i].stock.isinCd);
                        if (res2.data && res2.data.item.length > 0) {
                            // 구조분해 할당을 사용해 stocks 배열의 i번째 요소의 subDetail 프로퍼티에 할당
                            stocks[i].subDetail = res2.data.item[0];
                        }
                    }
                    // 모든 요청이 완료된 후, 업데이트된 stocks 배열로 상태를 업데이트
                    setStockItems((prevItems) => [...prevItems, ...stocks]);
                }
            } catch (err) {
                console.error(err);
            }
        } else if (category === 'card') {
            try {
                const response = await CardListUp(
                    page,
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
        <div className="container financeDetail__container">
            <div className="financeDetail">
                {/* 탭 버튼 */}
                <div className="financeDetail__tabButton">
                    <button onClick={() => setCategory('card')}>혜택 좋은 카드 추천</button>
                    <button onClick={() => setCategory('stock')}>배당률 좋은 주식 추천</button>
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
                    <div className="financeDetail__inputList">
                        <div className="financeDetail__inputList-main">
                            <input
                                className="financeDetail__inputList-main-input"
                                type="text"
                                placeholder="카드명 · 카드사 · 혜택 등을 검색해보세요!"
                                value={searchOptions.cardName}
                                onChange={(e) => setSearchOptions({ ...searchOptions, cardName: e.target.value })}
                            />
                        </div>
                        <div className="financeDetail__inputList-sub">
                            <div className="financeDetail__inputList-sub-company">
                                <div className="financeDetail__inputList-sub-company-title">카드사</div>
                                <input
                                    type="text"
                                    placeholder="카드 회사..."
                                    value={searchOptions.cardCompany}
                                    onChange={(e) =>
                                        setSearchOptions({ ...searchOptions, cardCompany: e.target.value })
                                    }
                                />
                            </div>
                            <div className="financeDetail__inputList-sub-req">
                                <div className="financeDetail__inputList-sub-req-title">전월 실적</div>
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
                            </div>
                            <div className="financeDetail__inputList-sub-fee">
                                <div className="financeDetail__inputList-sub-fee-title">연회비</div>
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
                        </div>
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
                        scrollThreshold={category === 'card' ? 0.8 : 0.6}
                        scrollableTarget="infiniteScroll"
                    >
                        {category === 'stock' && (
                            <div className="stock__content">
                                {stockItems.map((stock, index) => (
                                    <div key={index} className="stock__content--item">
                                        <div className="stock__content--companyName">{stock.stock.stckIssuCmpyNm}</div>
                                        <div className="stock__content--info">
                                            {stock.stock.stckGenrDvdnAmt.toLocaleString()} 원
                                        </div>
                                        <div className="stock__content--info">
                                            {parseInt(stock.subDetail?.mkp ?? '0', 10).toLocaleString()}원
                                        </div>
                                        <div className="stock__content--info">
                                            {parseInt(stock.subDetail?.mrktTotAmt ?? '0', 10).toLocaleString()}원
                                        </div>
                                    </div>
                                ))}
                            </div>
                        )}
                        {category === 'card' &&
                            cardItems.map((item, index) => (
                                <div key={index}>
                                    <div className="item">
                                        <div className="image-container">
                                            <img src={item.cardImg} onLoad={adjustImageStyle} />
                                        </div>
                                        <div className="cardItem__content">
                                            <div className="cardItem__content-title">
                                                <h3>{item.cardName}</h3>
                                                <span>{item.cardCompany}</span>
                                            </div>
                                            <div className='className="cardItem__content"'>
                                                {item.cardTopCategory.split(', ').map((category, index) => (
                                                    <div key={index}>{category}</div>
                                                ))}
                                            </div>
                                            <div className='className="cardItem__bottom"'>
                                                <span>전월실적: {item.cardRequired}이상</span>
                                                <br />
                                                연회비 <span>: 국내전용: {item.cardDomestic}원 / </span>
                                                <span>해외겸용: {item.cardAbroad}원</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))}
                    </InfiniteScroll>
                </div>
            </div>
        </div>
    );
}
