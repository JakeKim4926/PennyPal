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
        </div>
    );
}
