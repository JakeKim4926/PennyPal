export function usePenny() {
    function toFullNumber(param: number) {
        let localString = String(param).padStart(9, '0');

        let tmpNumber = localString.substring(0, 3);

        for (let i = 1; i < 3; i++) {
            tmpNumber += ',';
            tmpNumber += localString.substring(i * 3, i * 3 + 3);
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

        let answer = 0;
        return answer;
    }

    return {
        toFullNumber: toFullNumber,
    };
}
