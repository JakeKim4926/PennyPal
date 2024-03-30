export const setCookie = (name: string, value: string) => {
    // document.cookie에 쿠키 이름, 값,  접근 경로를 설정
    document.cookie = name + '=' + (value || '') + '; path=/';
};

export const getCookie = (name: 'memberId' | 'memberToken' | 'memberNickname') => {
    const nameEQ = name + '='; // 쿠키를 찾기 위한 이름과 등호를 결합한 문자열
    const ca = document.cookie.split(';'); // document.cookie를 세미콜론으로 분리하여 배열 생성
    for (let i = 0; i < ca.length; i++) {
        // 배열을 순회
        let c = ca[i];
        while (c.charAt(0) === ' ') c = c.substring(1, c.length); // 쿠키 문자열 앞의 공백 제거
        if (c.indexOf(nameEQ) === 0) {
            if (name === 'memberId') return parseInt(c.substring(nameEQ.length, c.length), 10); // memberId는 숫자로 타입변경해서반환
            return c.substring(nameEQ.length, c.length); // 해당 이름의 쿠키 값 반환
        }
    }
    return null; // 해당 이름의 쿠키가 없으면 null 반환
};

// 쿠키 지우기.
export const deleteCookie = (name: string) => {
    // 쿠키의 만료 시간을 과거로 설정합니다.
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/';
};
