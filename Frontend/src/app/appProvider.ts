import { combineReducers } from 'redux';
import { openTeamDetailModalReducer } from '@/pages/team/index';
import { signUpStepReducer } from '@/pages/signup/index';
import { openMarketItem } from '@/pages/market/index';
import { setTeamInfoReducer } from '@/pages/teamRouting/model/setTeamInfo';
import { openTeamLeaveModalReducer } from '@/pages/teamInfo/index';
import { openTeamSettingModalReducer } from '@/pages/teamInfo/index';
import { openTeamChattingModalReducer } from '@/pages/teamInfo/index';
import { forceRenderReducer } from '@/pages/teamRouting';
import { setMarketItemListReducer } from '@/pages/market/index';
import { setIsInitPageReducer } from '@/shared';

export const rootReducer = combineReducers({
    openTeamDetailModalReducer,
    openMarketItem,
    signUpStep: signUpStepReducer,
    setTeamInfoReducer,
    openTeamLeaveModalReducer,
    openTeamSettingModalReducer,
    openTeamChattingModalReducer,
    forceRenderReducer,
    setMarketItemListReducer,
    setIsInitPageReducer,
});

export type RootState = ReturnType<typeof rootReducer>;
