<<<<<<< HEAD
import React, { useEffect, useState } from 'react';
import CardComponent from '@/pages/finance/ui/CardComponent/CardComponent';
import StockComponent from '@/pages/finance/ui/StockComponent/StockComponent';
import SavingsComponent from '@/pages/finance/ui/SavingsComponent/SavingsComponent';

export function Finance() {
    const [data, setData] = useState({ cards: [], stocks: [], savings: [] });
    const fetchData = async () => {
        // 여기에 API 호출 로직 구현
        return {
            cards: [
                { id: 1, name: '카드1' },
                { id: 2, name: '카드2' },
            ],
            stocks: [
                { id: 1, name: '주식1' },
                { id: 2, name: '주식2' },
            ],
            savings: [
                { id: 1, name: '적금1' },
                { id: 2, name: '적금2' },
            ],
        };
    };
    useEffect(() => {
        fetchData().then();
    }, []);

    return (
        <div className="container">
            <div className="container">
                <div className="contentCard">
                    <div className="contentCard__tile">소비도 투자도 저축도 현명하게</div>
                </div>
                <div>금융</div>
                <div>
                    <CardComponent cards={data.cards} />
                    <StockComponent stocks={data.stocks} />
                    <SavingsComponent savings={data.savings} />
                </div>
            </div>
=======
import { USER_ID, customAxios } from '@/shared';
import { CompatClient, Stomp } from '@stomp/stompjs';
import { useEffect, useRef } from 'react';
import SockJS from 'sockjs-client';

export function Finance() {
    const inputRef = useRef<HTMLInputElement>(null);

    return (
        <div className="container">
            <div>회원가입</div>
            <input ref={inputRef}></input>
            <button
                onClick={async () => {
                    const value = inputRef.current?.value;
                    const dto = {
                        memberEmail: `${value}@test.com`,
                        memberPassword: '1234',
                        memberName: `${value}`,
                        memberNickname: `${value}`,
                        memberBirthDate: '2023-01-18T11:22:33',
                    };

                    const res = await customAxios.post('/member/signup', dto);
                    alert(res.data.message);
                }}
            >
                회원가입
            </button>
>>>>>>> 1cdd804c4ca3b527d070944a90d02396eb5c064e
        </div>
    );
}
