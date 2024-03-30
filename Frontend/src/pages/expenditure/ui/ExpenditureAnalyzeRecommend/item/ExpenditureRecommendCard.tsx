// const exampleData = {
//   favCategory1: '식료품',
//   favCategory2: '외식',
//   items: [
//       {
//           id: 1,
//           stats: [
//               {
//                   icon: '/path/to/icon1.png',
//                   label: '혜택',
//                   value: '최대 5%',
//               },
//               {
//                   icon: '/path/to/icon2.png',
//                   label: '연회비',
//                   value: '무료',
//               },
//           ],
//       },
//       {
//           id: 2,
//           stats: [
//               {
//                   icon: '/path/to/icon3.png',
//                   label: '캐시백',
//                   value: '2%',
//               },
//               {
//                   icon: '/path/to/icon4.png',
//                   label: '특별 할인',
//                   value: '영화관 30% 할인',
//               },
//           ],
//       },
//       {
//           id: 2,
//           stats: [
//               {
//                   icon: '/path/to/icon3.png',
//                   label: '캐시백',
//                   value: '2%',
//               },
//               {
//                   icon: '/path/to/icon4.png',
//                   label: '특별 할인',
//                   value: '영화관 30% 할인',
//               },
//           ],
//       },
//       {
//           id: 2,
//           stats: [
//               {
//                   icon: '/path/to/icon3.png',
//                   label: '캐시백',
//                   value: '2%',
//               },
//               {
//                   icon: '/path/to/icon4.png',
//                   label: '특별 할인',
//                   value: '영화관 30% 할인',
//               },
//           ],
//       },
//   ],
// };

// <ExpenditureRecommend {...exampleData} />

import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faChevronLeft, faChevronRight } from '@fortawesome/free-solid-svg-icons';

interface DataStat {
    icon: string;
    label: string;
    value: string;
}

interface CarouselItem {
    id: number;
    stats: DataStat[];
}

interface IBigDataRecommendingProps {
    favCategory1: String;
    favCategory2: String;
    items: CarouselItem[];
}

export function ExpenditureRecommend({ favCategory1, favCategory2, items }: IBigDataRecommendingProps) {
    const [carouselIndex, setCarouselIndex] = useState(0);

    const goToNext = () => {
        setCarouselIndex((prevIndex) => (prevIndex + 1) % items.length);
    };

    const goToPrevious = () => {
        setCarouselIndex((prevIndex) => (prevIndex - 1 + items.length) % items.length);
    };

    const renderStats = (stats: DataStat[]) => {
        return stats.map((stat, index) => (
            <div key={index} className="stat">
                <img src={stat.icon} alt={stat.label} />
                <span>{stat.label}</span>
                <strong>{stat.value}</strong>
            </div>
        ));
    };

    return (
        <div className="big-data-recommending contentCard">
            <div className="contentCard__title">
                <div className="contentCard__title-text">BIGDATA RECOMMENDING</div>
            </div>

            <div className="carousel">
                <button onClick={goToPrevious} aria-label="Previous">
                    <FontAwesomeIcon icon={faChevronLeft} size="xl" />
                </button>
                <div className="carousel-item">
                    <div className="play-button-icon"></div>
                    <div className="stats">{renderStats(items[carouselIndex].stats)}</div>
                </div>
                <button onClick={goToNext} aria-label="Next">
                    <FontAwesomeIcon icon={faChevronRight} size="xl" />
                </button>
            </div>
            <div className="carousel-indicator">
                {items.map((item, index) => (
                    <span
                        key={item.id}
                        className={`dot ${index === carouselIndex ? 'active' : ''}`}
                        onClick={() => setCarouselIndex(index)}
                    ></span>
                ))}
            </div>
            <p className="caption">
                {favCategory1},{favCategory2}
            </p>
        </div>
    );
}
