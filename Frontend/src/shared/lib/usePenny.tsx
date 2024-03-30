export function usePenny() {
    function toFullNumber(param: number) {
        let localString = String(param).padStart(9, '0');

        let tmpNumber = localString.substring(0, 3);

        for (let i = 1; i < 3; i++) {
            tmpNumber += ',';
            tmpNumber += localString.substring(i * 3, i * 3 + 3);
        }

        // if: 매개변수가 0일 경우 예외처리
        if (param === 0) {
            return (
                <>
                    <span className="spend spend-prev">000,000,00</span>
                    <span className="spend spend-post">0</span>
                    <span className="unit">원</span>
                </>
            );
        }

        const tmpArr = [];

        let prev = '';
        let post = '';

        let prevCheck = true;
        for (let i = 0; i < tmpNumber.length; i++) {
            if (prevCheck) {
                if (tmpNumber[i] === '0' || tmpNumber[i] === ',') {
                    prev += tmpNumber[i];
                } else {
                    prevCheck = !prevCheck;
                    post += tmpNumber[i];
                }
            } else {
                post += tmpNumber[i];
            }
        }

        return (
            <>
                <span className="spend spend-prev">{prev}</span>
                <span className="spend spend-post">{post}</span>
                <span className="unit">원</span>
            </>
        );
    }

    return {
        toFullNumber: toFullNumber,
    };
}
