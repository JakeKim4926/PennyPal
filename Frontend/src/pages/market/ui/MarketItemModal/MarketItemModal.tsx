import { useDispatch } from 'react-redux';
import { closeMarketItemModal } from '../../model';
import { useEffect, useCallback } from 'react';

export function MarketItemModal() {
    const dispatch = useDispatch();

    useEffect(() => {
        function handleClick(e: MouseEvent) {
            e.stopPropagation();
            if (e.target instanceof Element) {
                if ([...e.target.classList].some((it) => it === 'modalContainer')) {
                    dispatch(closeMarketItemModal());
                }
            }
        }
        window.addEventListener('click', handleClick);

        return () => {
            window.removeEventListener('click', handleClick);
        };
    });

    return (
        <div className="modalContainer">
            <div className="marketItemModal contentCard">
                <div className="marketItemModal__top">
                    <div className="marketItemModal__top-image">이미지</div>
                </div>
                <div className="marketItemModal__middle">
                    <div className="marketItemModal__middle-left">
                        <div className="marketItemModal__middle-left-brand">GS25</div>
                        <div className="marketItemModal__middle-left-name">빙그레) 바나나맛 우유</div>
                    </div>

                    <div className="marketItemModal__middle-right">[P] 1,700</div>
                </div>
                <div className="marketItemModal__bottom">
                    <div className="marketItemModal__bottom-type">유형 모바일교환권(기프티콘)</div>
                    <div className="marketItemModal__bottom-exp">유효기간</div>
                </div>
                <button className="marketItemModal__button button">PURCHASE</button>
            </div>
        </div>
    );
}
