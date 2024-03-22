import { Dispatch, SetStateAction, useCallback } from 'react';

/**
 *
 * @param state
 * @param setState
 * @returns
 */
export function increaseCount(state: number, setState: Dispatch<SetStateAction<number>>) {
    // 함수 파라미터에 다 타입 지정해주기
    return useCallback(() => setState((prevState) => prevState + 1), [setState]);
}
