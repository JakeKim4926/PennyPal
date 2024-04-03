import { useEffect, useRef, useState } from 'react';
import { StockListUp } from '@/pages/finance/model';
import InfiniteScroll from 'react-infinite-scroll-component';
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
    const [items, setItems] = useState<Stock[]>([]);
    const [page, setPage] = useState(0);
    const [hasMore, setHasMore] = useState(true);
    const fetchItems = async (page: number, size: number = 5): Promise<Stock[]> => {
        try {
            const response = await StockListUp(page, size);
            return response.data.data.content; // API 응답 구조에 맞게 조정
        } catch (error) {
            throw new Error('Fetching items failed');
        }
    };
    useEffect(() => {
        const loadItems = async () => {
            const newItems = await fetchItems(page);
            console.log(newItems); // 데이터 확인
            setItems((prevItems) => [...prevItems, ...newItems]);
            if (newItems.length < 5) {
                setHasMore(false);
            } else {
                setHasMore(true);
            }
        };

        loadItems();
    }, [page]);

    return (
        <div className="container">
            <div className="tabButton">
                <button>카드</button>
                <button>주식</button>
            </div>
            <div className="searchBar">
                <input type="text" placeholder="검색..." />
            </div>
            <h1>무한스크롤이 문제가 있음. x축 스크롤 (=shift+스크롤)을 할때 무한스크롤이 동작을 함. </h1>
            <div>
                <InfiniteScroll
                    dataLength={items.length}
                    next={() => setPage((prevPage) => prevPage + 1)}
                    hasMore={hasMore}
                    loader={<h4>Loading...</h4>}
                    endMessage={<p style={{ textAlign: 'center' }}>더 이상 데이터가 없습니다.</p>}
                >
                    {items.map((item) => (
                        <div key={item.stockId}>
                            {item.stckIssuCmpyNm} : {item.stckGenrDvdnAmt.toLocaleString()} 원
                        </div>
                    ))}
                </InfiniteScroll>
            </div>
        </div>
    );
}
