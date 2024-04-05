# 💸 페니팔 (PennyPal) - 짠돌이 도전 서비스

<img src="/uploads/da522261d32db560e543eb6b2fecea38/image.png" width="800px" />

### ✨ 웹 서비스 소개

<hr>
YOLO족 유행이 줄어들고 FIRE족 유행이 늘어나는 상황 속, 소비 금액을 줄이는 습관을 목표로 하는 방향의 서비스가 필요합니다.

> 여러분의 소비 절약을 재미있게, 똑똑하게 도와주는 PennyPal입니다.

<br/>

### 📆 프로젝트 기간 및 규모

<hr>
삼성 청년 SW 아카데미 10기 공통 프로젝트 팀 B108<br/><br/>

24.02.19 ~ 24.04.05 (6주)<br/><br/>
총 6명

<hr>

### 🎈 팀원 역할 및 소개

### Backend

|이예원(팀장)|김민건(인프라)|김준섭|
|:-:|:-:|:-:|
|<img src="/uploads/e17829be52bfe57f803642a217345f7b/71091FF3-C654-4C9E-AC3B-F7482B731884__1_.jpg" width="150px" />|<img src="/uploads/6e62f322725f948651d27f81eb5ddfac/mingun.png" width="160px" />|<img src="/uploads/96a93b68832dc5201a885f21d5e22dbc/junsub.png" width="150px" />|

### Fullstack

|박시연|
|:-:|
|<img src="/uploads/3136f4c1ca2630ca788fd3a02f62ecd7/siyeon.png" width="150px" />|

### Frontend

|김동학|조한빈|
|:-:|:-:|
|<img src="/uploads/c33e9d3a3053075d935c78d230242114/donghak.png" width="150px" />|<img src="/uploads/c626200acd6fdf4f9e6798828877dfbe/hanbin.png" width="150px" />|

<hr>

## ⚙ 기술스택

<img src="/uploads/5230249d3c3d48a4f67fed9d0654f9ea/기술_스택.png">

<br />

## 💎 API Docs

### [Spring REST Docs](https://mine702.github.io/B108-DOCS/)
<br />

## 💡 주요 기능

| 기능 | 내용 |
| :--------------------------------------------------- | :--------------------------------------------------------------------------------------------|
| 나의 지출 관리 | 유저의 지출 내역을 자동으로 기록하고, 지출 내역을 시각화한 그래프를 통해 직관적으로 지출 현황을 볼 수 있습니다. |
| 그룹 시스템 | 그룹 내 활동과 그룹 간 경쟁을 통해 개인의 소비 절약 습관을 장려하고, 그룹의 목표 달성을 위한 공동체 의식을 높일 수 있습니다. |
| 카드 추천 | 사용자의 최대 지출 카테고리에 알맞는 혜택을 가진 신용 및 체크카드를 추천합니다. |
| 미니 포인트 마켓 | 꾸준한 활동을 통해 모은 포인트를 소소한 생활용품·식료품 등으로 교환 가능한 마켓입니다. |

# 기능 상세 소개

<h3>회원가입</h3>
<img src="/uploads/3478dddda261856141a4d00975d4ddc3/회원가입.gif" >
<h3>마이페이지</h3>
<img src="/uploads/4ddeb226915d468ed92d9895fff8f0e7/마이페이지.gif">
<h3>로그인 및 출석</h3>
<img src="/uploads/7c863a79d650be5ee525981abc53c810/로그인_및_출석.gif">
<h3>개인지출내역 확인 및 카드 추천</h3>
<img src="/uploads/15e3a685574cc334b342f1b71cb93691/개인_지출_내역_확인__카드_추천.gif">
<h3>팀 생성</h3>
<img src="/uploads/af6991941cefe8240d7ea9fe2a6310fd/팀생성.gif">
<h3>팀 참여</h3>
<img src="/uploads/e62053be215a8994518a162ed7f97a72/팀정보.gif">
<h3>팀 정보 확인</h3>
<img src="/uploads/e3f47e538c390bc518ac9d28a7c789b9/팀_정보_확인.gif">
<h3>팀 랭킹</h3>
<img src="/uploads/7b63d3d054b40e2bb5402486bdeb9b4c/팀_랭킹.gif" >
<h3>마켓 구매 및 내역 확인</h3>
<img src="/uploads/d1b9d159e62e887eeb7f7389205487d2/마켓_구매_및_내역_확인.gif" >
<h3>카드 정보 확인 및 검색</h3>
<img src="/uploads/41e98e959641acf8143e39fffde56000/카드_정보_확인_및_검색.gif"  >
<h3>주식 정보 확인</h3>
<img src="/uploads/f8ba6f1db684a04cc8a77fa757159d4f/주식_정보_확인.gif" >

<br />

## 🕹️ ERD

<img src="/uploads/279ae1b26b05913f6b9ba4867952b31d/erd.png" width="900px" >

## 🎨 시스템 아키텍처

<img src="/uploads/f6da2f4b90829994c8602be130f696ee/image.png" width="800px" >

<br />

## 📂 프로젝트 구성도


<details>
  <summary>
  프론트엔드 디렉토리 구조
  </summary>

        📦public
        ┣ 📂assets
        ┃ ┗ 📂image
        ┃ ┃ ┣ 📂icons_mini
        ┃ ┃ ┃ ┣ 📜BirthDate.svg
        ┃ ┃ ┃ ┣ 📜Email.svg
        ┃ ┃ ┃ ┣ 📜handShake.svg
        ┃ ┃ ┃ ┣ 📜icon_google.svg
        ┃ ┃ ┃ ┣ 📜icon_kakao.svg
        ┃ ┃ ┃ ┣ 📜Name.svg
        ┃ ┃ ┃ ┣ 📜password.svg
        ┃ ┃ ┃ ┗ 📜User.svg
        ┃ ┃ ┣ 📂product
        ┃ ┃ ┃ ┣ 📜abc.jpg
        ┃ ┃ ┃ ┣ 📜banana.jpg
        ┃ ┃ ┃ ┣ 📜binch.jpg
        ┃ ┃ ┃ ┣ 📜chocoemon.jpg
        ┃ ┃ ┃ ┣ 📜cocacola.jpg
        ┃ ┃ ┃ ┣ 📜concho.jpg
        ┃ ┃ ┃ ┣ 📜cornchip.jpg
        ┃ ┃ ┃ ┣ 📜crownchip.jpg
        ┃ ┃ ┃ ┣ 📜crunky.jpg
        ┃ ┃ ┃ ┣ 📜ferrero.jpg
        ┃ ┃ ┃ ┣ 📜free.jpg
        ┃ ┃ ┃ ┣ 📜gana.jpg
        ┃ ┃ ┃ ┣ 📜hersheyhugs.jpg
        ┃ ┃ ┃ ┣ 📜hersheymilk.jpg
        ┃ ┃ ┃ ┣ 📜milkis.jpg
        ┃ ┃ ┃ ┣ 📜pepsi.jpg
        ┃ ┃ ┃ ┣ 📜pocochip.jpg
        ┃ ┃ ┃ ┣ 📜royceb.jpg
        ┃ ┃ ┃ ┣ 📜roycew.jpg
        ┃ ┃ ┃ ┣ 📜shrimpchip.jpg
        ┃ ┃ ┃ ┣ 📜starbucks.jpg
        ┃ ┃ ┃ ┣ 📜strawberry.jpg
        ┃ ┃ ┃ ┣ 📜sunchip.jpg
        ┃ ┃ ┃ ┣ 📜swingchip.jpg
        ┃ ┃ ┃ ┗ 📜top.jpg
        ┃ ┃ ┣ 📜background.svg
        ┃ ┃ ┣ 📜background_Main.svg
        ┃ ┃ ┣ 📜barcode.svg
        ┃ ┃ ┣ 📜calander.svg
        ┃ ┃ ┣ 📜card.svg
        ┃ ┃ ┣ 📜enter.svg
        ┃ ┃ ┣ 📜exchange.svg
        ┃ ┃ ┣ 📜expenditure.png
        ┃ ┃ ┣ 📜expenditure.svg
        ┃ ┃ ┣ 📜finance.svg
        ┃ ┃ ┣ 📜landing1.png
        ┃ ┃ ┣ 📜leader.svg
        ┃ ┃ ┣ 📜logout.svg
        ┃ ┃ ┣ 📜main-logo-colored.svg
        ┃ ┃ ┣ 📜main-logo-full.svg
        ┃ ┃ ┣ 📜market.svg
        ┃ ┃ ┣ 📜menu.svg
        ┃ ┃ ┣ 📜menubar.svg
        ┃ ┃ ┣ 📜mission.svg
        ┃ ┃ ┣ 📜person.svg
        ┃ ┃ ┣ 📜piggybank.svg
        ┃ ┃ ┣ 📜point.svg
        ┃ ┃ ┣ 📜ranking.svg
        ┃ ┃ ┣ 📜receipt.svg
        ┃ ┃ ┣ 📜search.svg
        ┃ ┃ ┣ 📜team.svg
        ┃ ┃ ┣ 📜teamInfo.svg
        ┃ ┃ ┣ 📜uparrow.svg
        ┃ ┃ ┗ 📜user.svg
        ┃ 📜index.html
        📦src
        ┣ 📂app
        ┃ ┣ 📜appProvider.ts
        ┃ ┣ 📜appRoutes.tsx
        ┃ ┗ 📜index.tsx
        ┣ 📂assets
        ┃ ┗ 📂fonts
        ┃ ┃ ┣ 📜Digit.ttf
        ┃ ┃ ┣ 📜Esamanru-Bold.ttf
        ┃ ┃ ┣ 📜Esamanru-Light.ttf
        ┃ ┃ ┣ 📜Esamanru-Medium.ttf
        ┃ ┃ ┣ 📜WantedSans-Black.ttf
        ┃ ┃ ┣ 📜WantedSans-Bold.ttf
        ┃ ┃ ┣ 📜WantedSans-ExtraBlack.ttf
        ┃ ┃ ┣ 📜WantedSans-ExtraBold.ttf
        ┃ ┃ ┣ 📜WantedSans-Medium.ttf
        ┃ ┃ ┣ 📜WantedSans-Regular.ttf
        ┃ ┃ ┗ 📜WantedSans-SemiBold.ttf
        ┣ 📂entities
        ┃ ┣ 📂team
        ┃ ┃ ┗ 📜type.ts
        ┃ ┗ 📜index.ts
        ┣ 📂pages
        ┃ ┣ 📂error
        ┃ ┃ ┣ 📂ui
        ┃ ┃ ┃ ┗ 📜Error.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┣ 📂expenditure
        ┃ ┃ ┣ 📂model
        ┃ ┃ ┃ ┣ 📜card.ts
        ┃ ┃ ┃ ┣ 📜fetchFunctions.ts
        ┃ ┃ ┃ ┗ 📜spending.ts
        ┃ ┃ ┣ 📂ui
        ┃ ┃ ┃ ┣ 📂ExpenditureAnalyzeRecommend
        ┃ ┃ ┃ ┃ ┣ 📂item
        ┃ ┃ ┃ ┃ ┃ ┗ 📜ExpenditureRecommendCard.tsx
        ┃ ┃ ┃ ┃ ┣ 📜ExpenditureAnalyze.tsx
        ┃ ┃ ┃ ┃ ┣ 📜ExpenditureAnalyzeRecommend.tsx
        ┃ ┃ ┃ ┃ ┗ 📜ExpenditureRecommend.tsx
        ┃ ┃ ┃ ┣ 📂ExpenditureGraph
        ┃ ┃ ┃ ┃ ┗ 📜ExpenditureGraph.tsx
        ┃ ┃ ┃ ┣ 📂ExpenditureWeekly
        ┃ ┃ ┃ ┃ ┣ 📂item
        ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenditureWeeklyDaily.tsx
        ┃ ┃ ┃ ┃ ┃ ┗ 📜SpendingItem.tsx
        ┃ ┃ ┃ ┃ ┗ 📜ExpenditureWeekly.tsx
        ┃ ┃ ┃ ┗ 📜Expenditure.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┣ 📂finance
        ┃ ┃ ┣ 📂model
        ┃ ┃ ┃ ┣ 📜CardListUp.ts
        ┃ ┃ ┃ ┣ 📜index.ts
        ┃ ┃ ┃ ┗ 📜StockListUp.ts
        ┃ ┃ ┣ 📂ui
        ┃ ┃ ┃ ┣ 📂CardComponent
        ┃ ┃ ┃ ┃ ┗ 📜CardComponent.tsx
        ┃ ┃ ┃ ┣ 📂SavingsComponent
        ┃ ┃ ┃ ┃ ┗ 📜SavingsComponent.tsx
        ┃ ┃ ┃ ┣ 📂StockComponent
        ┃ ┃ ┃ ┃ ┗ 📜StockComponent.tsx
        ┃ ┃ ┃ ┣ 📜Finance.tsx
        ┃ ┃ ┃ ┗ 📜FinanceDetail.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┣ 📂landing
        ┃ ┃ ┣ 📂ui
        ┃ ┃ ┃ ┗ 📜Landing.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┣ 📂main
        ┃ ┃ ┣ 📂ui
        ┃ ┃ ┃ ┗ 📜Main.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┣ 📂market
        ┃ ┃ ┣ 📂api
        ┃ ┃ ┃ ┣ 📜getMarketItemByCategory.ts
        ┃ ┃ ┃ ┣ 📜getUserPoint.ts
        ┃ ┃ ┃ ┣ 📜purchaseItem.ts
        ┃ ┃ ┃ ┗ 📜searchMarketItemList.ts
        ┃ ┃ ┣ 📂model
        ┃ ┃ ┃ ┣ 📜getMarketItemList.ts
        ┃ ┃ ┃ ┣ 📜index.ts
        ┃ ┃ ┃ ┣ 📜openMarketItemModal.ts
        ┃ ┃ ┃ ┗ 📜setMarketItemList.ts
        ┃ ┃ ┣ 📂ui
        ┃ ┃ ┃ ┣ 📂MarketCategory
        ┃ ┃ ┃ ┃ ┗ 📜MarketCategory.tsx
        ┃ ┃ ┃ ┣ 📂MarketItemModal
        ┃ ┃ ┃ ┃ ┗ 📜MarketItemModal.tsx
        ┃ ┃ ┃ ┣ 📂MarketList
        ┃ ┃ ┃ ┃ ┗ 📜MarketList.tsx
        ┃ ┃ ┃ ┣ 📂MarketListItem
        ┃ ┃ ┃ ┃ ┗ 📜MarketListItem.tsx
        ┃ ┃ ┃ ┣ 📂MarketTop
        ┃ ┃ ┃ ┃ ┗ 📜MarketTop.tsx
        ┃ ┃ ┃ ┗ 📜Market.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┣ 📂marketReceipt
        ┃ ┃ ┣ 📂api
        ┃ ┃ ┃ ┗ 📜getPurchaseHistory.ts
        ┃ ┃ ┣ 📂ui
        ┃ ┃ ┃ ┣ 📂MarketReceiptContent
        ┃ ┃ ┃ ┃ ┗ 📜MarketReceiptContent.tsx
        ┃ ┃ ┃ ┣ 📂MarketReceiptTop
        ┃ ┃ ┃ ┃ ┗ 📜MarketReceiptTop.tsx
        ┃ ┃ ┃ ┗ 📜MarketReceipt.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┣ 📂myPage
        ┃ ┃ ┣ 📂model
        ┃ ┃ ┃ ┗ 📜doSubmit.ts
        ┃ ┃ ┣ 📂ui
        ┃ ┃ ┃ ┗ 📜MyPage.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┣ 📂ranking
        ┃ ┃ ┣ 📂api
        ┃ ┃ ┃ ┣ 📜getRanking.ts
        ┃ ┃ ┃ ┗ 📜getRealtimeRanking.ts
        ┃ ┃ ┣ 📂ui
        ┃ ┃ ┃ ┣ 📂RankingTable
        ┃ ┃ ┃ ┃ ┗ 📜RankingTable.tsx
        ┃ ┃ ┃ ┗ 📜Ranking.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┣ 📂signin
        ┃ ┃ ┣ 📂model
        ┃ ┃ ┃ ┗ 📜doLogin.ts
        ┃ ┃ ┣ 📂ui
        ┃ ┃ ┃ ┣ 📜FindPassword.tsx
        ┃ ┃ ┃ ┗ 📜SignIn.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┣ 📂signup
        ┃ ┃ ┣ 📂model
        ┃ ┃ ┃ ┣ 📜doSignUp.ts
        ┃ ┃ ┃ ┣ 📜signUpStepReducer.ts
        ┃ ┃ ┃ ┗ 📜useSignupFormmodel.ts
        ┃ ┃ ┣ 📂ui
        ┃ ┃ ┃ ┣ 📂Agreement
        ┃ ┃ ┃ ┃ ┗ 📜Agreement.tsx
        ┃ ┃ ┃ ┣ 📂SignUpDone
        ┃ ┃ ┃ ┃ ┗ 📜SignUpDone.tsx
        ┃ ┃ ┃ ┣ 📂SignUpForm
        ┃ ┃ ┃ ┃ ┗ 📜SignUpForm.tsx
        ┃ ┃ ┃ ┗ 📜SignUp.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┣ 📂team
        ┃ ┃ ┣ 📂api
        ┃ ┃ ┃ ┣ 📜checkTeamName.ts
        ┃ ┃ ┃ ┣ 📜createGroup.ts
        ┃ ┃ ┃ ┣ 📜getTeamDetail.ts
        ┃ ┃ ┃ ┣ 📜getTeamList.ts
        ┃ ┃ ┃ ┗ 📜registGroup.ts
        ┃ ┃ ┣ 📂model
        ┃ ┃ ┃ ┣ 📜index.ts
        ┃ ┃ ┃ ┣ 📜openTeamDetailModal.ts
        ┃ ┃ ┃ ┣ 📜registGroup.ts
        ┃ ┃ ┃ ┗ 📜scrollTeamCreateArea.ts
        ┃ ┃ ┣ 📂ui
        ┃ ┃ ┃ ┣ 📂TeamCreateTeam
        ┃ ┃ ┃ ┃ ┗ 📜TeamCreateTeam.tsx
        ┃ ┃ ┃ ┣ 📂TeamDetailModal
        ┃ ┃ ┃ ┃ ┗ 📜TeamDetailModal.tsx
        ┃ ┃ ┃ ┣ 📂TeamTeamList
        ┃ ┃ ┃ ┃ ┗ 📜TeamTeamList.tsx
        ┃ ┃ ┃ ┣ 📂TeamTeamListItem
        ┃ ┃ ┃ ┃ ┗ 📜TeamTeamListItem.tsx
        ┃ ┃ ┃ ┣ 📂TeamTeamListPagenation
        ┃ ┃ ┃ ┃ ┗ 📜TeamTeamListPagenation.tsx
        ┃ ┃ ┃ ┣ 📂TeamTeamSearch
        ┃ ┃ ┃ ┃ ┗ 📜TeamTeamSearch.tsx
        ┃ ┃ ┃ ┗ 📜Team.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┣ 📂teamInfo
        ┃ ┃ ┣ 📂api
        ┃ ┃ ┃ ┣ 📜acceptRegist.ts
        ┃ ┃ ┃ ┣ 📜banTeamMember.ts
        ┃ ┃ ┃ ┣ 📜connectTeamChatRoom.ts
        ┃ ┃ ┃ ┣ 📜deleteTeam.ts
        ┃ ┃ ┃ ┣ 📜denyRegist.ts
        ┃ ┃ ┃ ┣ 📜getTeamChatHistory.ts
        ┃ ┃ ┃ ┣ 📜getTeamWaitingList.ts
        ┃ ┃ ┃ ┣ 📜modifyTeamInfo.ts
        ┃ ┃ ┃ ┗ 📜sendTeamChat.ts
        ┃ ┃ ┣ 📂model
        ┃ ┃ ┃ ┣ 📜openTeamChattingModal.ts
        ┃ ┃ ┃ ┣ 📜openTeamLeaveModal.ts
        ┃ ┃ ┃ ┗ 📜openTeamSettingModal.ts
        ┃ ┃ ┣ 📂ui
        ┃ ┃ ┃ ┣ 📂TeamChattingModal
        ┃ ┃ ┃ ┃ ┗ 📜TeamChattingModal.tsx
        ┃ ┃ ┃ ┣ 📂TeamInfoChatButton
        ┃ ┃ ┃ ┃ ┗ 📜TeamInfoChatButton.tsx
        ┃ ┃ ┃ ┣ 📂TeamInfomation
        ┃ ┃ ┃ ┃ ┗ 📜TeamInformation.tsx
        ┃ ┃ ┃ ┣ 📂TeamInfoMember
        ┃ ┃ ┃ ┃ ┗ 📜TeamInfoMember.tsx
        ┃ ┃ ┃ ┣ 📂TeamInfoMemberItem
        ┃ ┃ ┃ ┃ ┗ 📜TeamInfoMemberItem.tsx
        ┃ ┃ ┃ ┣ 📂TeamInfoTeamExpenditure
        ┃ ┃ ┃ ┃ ┗ 📜TeamInfoTeamExpenditure.tsx
        ┃ ┃ ┃ ┣ 📂TeamLeaveModal
        ┃ ┃ ┃ ┃ ┗ 📜TeamLeavModal.tsx
        ┃ ┃ ┃ ┣ 📂TeamSettingModal
        ┃ ┃ ┃ ┃ ┗ 📜TeamSettingModal.tsx
        ┃ ┃ ┃ ┗ 📜TeamInfo.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┣ 📂teamRouting
        ┃ ┃ ┣ 📂api
        ┃ ┃ ┃ ┗ 📜getTeamInfo.ts
        ┃ ┃ ┣ 📂model
        ┃ ┃ ┃ ┣ 📜forceRender.ts
        ┃ ┃ ┃ ┗ 📜setTeamInfo.ts
        ┃ ┃ ┣ 📂ui
        ┃ ┃ ┃ ┣ 📂TeamRoutingInvitation.tsx
        ┃ ┃ ┃ ┃ ┗ 📜TeamRoutingInvitation.tsx
        ┃ ┃ ┃ ┗ 📜TeamRouting.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┗ 📜index.ts
        ┣ 📂shared
        ┃ ┣ 📂api
        ┃ ┃ ┗ 📜apiCaching.ts
        ┃ ┣ 📂lib
        ┃ ┃ ┣ 📜cookieHelper.ts
        ┃ ┃ ┣ 📜customAxios.ts
        ┃ ┃ ┣ 📜index.ts
        ┃ ┃ ┣ 📜LoginCheck.tsx
        ┃ ┃ ┗ 📜usePenny.tsx
        ┃ ┣ 📂ui
        ┃ ┃ ┣ 📂button
        ┃ ┃ ┃ ┗ 📜Button.tsx
        ┃ ┃ ┣ 📂chartArea
        ┃ ┃ ┃ ┣ 📜ChartArea.tsx
        ┃ ┃ ┃ ┗ 📜PieChart.tsx
        ┃ ┃ ┣ 📂header
        ┃ ┃ ┃ ┣ 📂menubar
        ┃ ┃ ┃ ┃ ┗ 📜Menubar.tsx
        ┃ ┃ ┃ ┗ 📜Header.tsx
        ┃ ┃ ┣ 📂input
        ┃ ┃ ┃ ┗ 📜Input.tsx
        ┃ ┃ ┣ 📂logo
        ┃ ┃ ┃ ┗ 📜Logo.tsx
        ┃ ┃ ┣ 📂modalSpace
        ┃ ┃ ┃ ┗ 📜ModalSpace.tsx
        ┃ ┃ ┣ 📂nav
        ┃ ┃ ┃ ┣ 📂items
        ┃ ┃ ┃ ┃ ┗ 📜NavItems.tsx
        ┃ ┃ ┃ ┗ 📜Nav.tsx
        ┃ ┃ ┣ 📂pageHeader
        ┃ ┃ ┃ ┗ 📜PageHeader.tsx
        ┃ ┃ ┗ 📜index.ts
        ┃ ┗ 📜index.ts
        ┣ 📂style
        ┃ ┣ 📂abstract
        ┃ ┃ ┣ 📜background.svg
        ┃ ┃ ┣ 📜_animation.scss
        ┃ ┃ ┣ 📜_cursor.scss
        ┃ ┃ ┣ 📜_fonts.scss
        ┃ ┃ ┣ 📜_global.scss
        ┃ ┃ ┣ 📜_mixin.scss
        ┃ ┃ ┗ 📜_variables.scss
        ┃ ┣ 📂components
        ┃ ┃ ┣ 📂Expenditure
        ┃ ┃ ┃ ┣ 📜ExpenditureAnalyze.scss
        ┃ ┃ ┃ ┣ 📜ExpenditureRecommend.scss
        ┃ ┃ ┃ ┣ 📜_ExpenditureAnalyze.scss
        ┃ ┃ ┃ ┣ 📜_ExpenditureAnalyzeRecommend.scss
        ┃ ┃ ┃ ┣ 📜_ExpenditureGraph.scss
        ┃ ┃ ┃ ┣ 📜_ExpenditureWeekly.scss
        ┃ ┃ ┃ ┗ 📜_ExpenditureWeeklyDaily.scss
        ┃ ┃ ┣ 📜_Button.scss
        ┃ ┃ ┣ 📜_ChartArea.scss
        ┃ ┃ ┣ 📜_ContentCard.scss
        ┃ ┃ ┣ 📜_Header.scss
        ┃ ┃ ┣ 📜_MarketCategory.scss
        ┃ ┃ ┣ 📜_MarketItemModal.scss
        ┃ ┃ ┣ 📜_MarketList.scss
        ┃ ┃ ┣ 📜_MarketReceipt.scss
        ┃ ┃ ┣ 📜_MarketReceiptContent.scss
        ┃ ┃ ┣ 📜_MarketReceiptTop.scss
        ┃ ┃ ┣ 📜_MarketTop.scss
        ┃ ┃ ┣ 📜_Nav.scss
        ┃ ┃ ┣ 📜_PageHeader.scss
        ┃ ┃ ┣ 📜_RankingTable.scss
        ┃ ┃ ┣ 📜_Signin.scss
        ┃ ┃ ┣ 📜_TeamChattingModal.scss
        ┃ ┃ ┣ 📜_TeamCreateTeam.scss
        ┃ ┃ ┣ 📜_TeamDetailModal.scss
        ┃ ┃ ┣ 📜_TeamInfoChatButton.scss
        ┃ ┃ ┣ 📜_TeamInfoMember.scss
        ┃ ┃ ┣ 📜_TeamInfoMemberItem.scss
        ┃ ┃ ┣ 📜_TeamInfoTeamExpenditure.scss
        ┃ ┃ ┣ 📜_TeamLeaveModal.scss
        ┃ ┃ ┣ 📜_TeamSettingModal.scss
        ┃ ┃ ┣ 📜_TeamTeamInfo.scss
        ┃ ┃ ┣ 📜_TeamTeamList.scss
        ┃ ┃ ┣ 📜_TeamTeamListItem.scss
        ┃ ┃ ┣ 📜_TeamTeamListPagenation.scss
        ┃ ┃ ┗ 📜_TeamTeamSearch.scss
        ┃ ┣ 📂pages
        ┃ ┃ ┣ 📜Expenditure.scss
        ┃ ┃ ┣ 📜FinanceDetail.scss
        ┃ ┃ ┣ 📜Main.scss
        ┃ ┃ ┣ 📜Market.scss
        ┃ ┃ ┣ 📜Ranking.scss
        ┃ ┃ ┣ 📜SignIn.scss
        ┃ ┃ ┣ 📜SignUp.scss
        ┃ ┃ ┣ 📜Team.scss
        ┃ ┃ ┗ 📜TeamInfo.scss
        ┃ ┗ 📜main.scss
        ┣ 📜main.tsx
        ┣ 📜.eslintrc.js
        ┣ 📜.gitignore
        ┣ 📜.prettierrc
        ┣ 📜package-lock.json
        ┣ 📜package.json
        ┣ 📜tsconfig.json
        ┣ 📜webpack.common.js
        ┣ 📜webpack.dev.js
        ┣ 📜webpack.prod.js
        ┗ 📜yarn.lock

 </details>

<details>
  <summary>
  백엔드 디렉토리 구조
  </summary>

        📦Backend
        ┣ 📂.gradle
        ┃ ┣ 📂8.6
        ┃ ┃ ┣ 📂checksums
        ┃ ┃ ┃ ┣ 📜checksums.lock
        ┃ ┃ ┃ ┣ 📜md5-checksums.bin
        ┃ ┃ ┃ ┗ 📜sha1-checksums.bin
        ┃ ┃ ┣ 📂dependencies-accessors
        ┃ ┃ ┃ ┗ 📜gc.properties
        ┃ ┃ ┣ 📂executionHistory
        ┃ ┃ ┃ ┣ 📜executionHistory.bin
        ┃ ┃ ┃ ┗ 📜executionHistory.lock
        ┃ ┃ ┣ 📂fileChanges
        ┃ ┃ ┃ ┗ 📜last-build.bin
        ┃ ┃ ┣ 📂fileHashes
        ┃ ┃ ┃ ┣ 📜fileHashes.bin
        ┃ ┃ ┃ ┣ 📜fileHashes.lock
        ┃ ┃ ┃ ┗ 📜resourceHashesCache.bin
        ┃ ┃ ┣ 📂vcsMetadata
        ┃ ┃ ┗ 📜gc.properties
        ┃ ┣ 📂buildOutputCleanup
        ┃ ┃ ┣ 📜buildOutputCleanup.lock
        ┃ ┃ ┣ 📜cache.properties
        ┃ ┃ ┗ 📜outputFiles.bin
        ┃ ┣ 📂vcs-1
        ┃ ┃ ┗ 📜gc.properties
        ┃ ┣ 📜file-system.probe
        ┃ ┣ 📜workspace-id.txt
        ┃ ┗ 📜workspace-id.txt.lock
        ┣ 📂.idea
        ┃ ┣ 📂dataSources
        ┃ ┃ ┣ 📂77f91c2a-002e-4b45-ae69-69902430d610
        ┃ ┃ ┃ ┗ 📂storage_v2
        ┃ ┃ ┃ ┃ ┗ 📂_src_
        ┃ ┃ ┃ ┃ ┃ ┗ 📂schema
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜information_schema.FNRwLQ.meta
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜mysql.osA4Bg.meta
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜pennypal.d-FUEw.meta
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜penny_pal.gMo_Vw.meta
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜performance_schema.kIw0nw.meta
        ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜sys.zb4BAA.meta
        ┃ ┃ ┣ 📂fde1eb67-c3ef-4a7c-ab4f-7f1023bdc11c
        ┃ ┃ ┃ ┗ 📂storage_v2
        ┃ ┃ ┃ ┃ ┗ 📂_src_
        ┃ ┃ ┃ ┃ ┃ ┗ 📂schema
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜information_schema.FNRwLQ.meta
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜mysql.osA4Bg.meta
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜penny_pal.gMo_Vw.meta
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜performance_schema.kIw0nw.meta
        ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜sys.zb4BAA.meta
        ┃ ┃ ┣ 📜77f91c2a-002e-4b45-ae69-69902430d610.xml
        ┃ ┃ ┗ 📜fde1eb67-c3ef-4a7c-ab4f-7f1023bdc11c.xml
        ┃ ┣ 📂modules
        ┃ ┃ ┣ 📜pennypal.main.iml
        ┃ ┃ ┗ 📜pennypal.test.iml
        ┃ ┣ 📜.gitignore
        ┃ ┣ 📜.name
        ┃ ┣ 📜compiler.xml
        ┃ ┣ 📜dataSources.local.xml
        ┃ ┣ 📜dataSources.xml
        ┃ ┣ 📜gradle.xml
        ┃ ┣ 📜jarRepositories.xml
        ┃ ┣ 📜misc.xml
        ┃ ┣ 📜modules.xml
        ┃ ┣ 📜uiDesigner.xml
        ┃ ┣ 📜vcs.xml
        ┃ ┗ 📜workspace.xml
        ┣ 📂gradle
        ┃ ┗ 📂wrapper
        ┃ ┃ ┣ 📜gradle-wrapper.jar
        ┃ ┃ ┗ 📜gradle-wrapper.properties
        ┣ 📂src
        ┃ ┣ 📂docs
        ┃ ┃ ┗ 📂asciidoc
        ┃ ┃ ┃ ┣ 📂bank
        ┃ ┃ ┃ ┃ ┣ 📂api
        ┃ ┃ ┃ ┃ ┃ ┣ 📜createAccount.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜getAccount.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜getAccountDrawingTransfer.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜getAccountTransfer.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜getBankAccount.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜getBankAccountTransaction.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜getBankStockAccount.adoc
        ┃ ┃ ┃ ┃ ┃ ┗ 📜setDummyData.adoc
        ┃ ┃ ┃ ┃ ┗ 📜bank.adoc
        ┃ ┃ ┃ ┣ 📂card
        ┃ ┃ ┃ ┃ ┣ 📂api
        ┃ ┃ ┃ ┃ ┃ ┣ 📜getCards.adoc
        ┃ ┃ ┃ ┃ ┃ ┗ 📜getCards.html
        ┃ ┃ ┃ ┃ ┗ 📜card.adoc
        ┃ ┃ ┃ ┣ 📂chat
        ┃ ┃ ┃ ┃ ┣ 📂api
        ┃ ┃ ┃ ┃ ┃ ┗ 📜enterChatRoom.adoc
        ┃ ┃ ┃ ┃ ┗ 📜chat.adoc
        ┃ ┃ ┃ ┣ 📂member
        ┃ ┃ ┃ ┃ ┣ 📂api
        ┃ ┃ ┃ ┃ ┃ ┣ 📜attend.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜isAttend.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜login.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜signup.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜updateNickname.adoc
        ┃ ┃ ┃ ┃ ┃ ┗ 📜updatePassword.adoc
        ┃ ┃ ┃ ┃ ┗ 📜member.adoc
        ┃ ┃ ┃ ┣ 📂stock
        ┃ ┃ ┃ ┃ ┣ 📂api
        ┃ ┃ ┃ ┃ ┃ ┣ 📜getlist.adoc
        ┃ ┃ ┃ ┃ ┃ ┗ 📜getstocktransaction.adoc
        ┃ ┃ ┃ ┃ ┗ 📜stock.adoc
        ┃ ┃ ┃ ┣ 📂team
        ┃ ┃ ┃ ┃ ┣ 📂api
        ┃ ┃ ┃ ┃ ┃ ┣ 📜approveMember.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜banishMember.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜createTeam.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜deleteTeam.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜detailOtherTeam.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜detailTeam.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜joinTeam.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜leaveTeam.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜modifyTeam.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜rankOfRealtime.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜rankOfWeekly.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜rejectMember.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜searchTeamList.adoc
        ┃ ┃ ┃ ┃ ┃ ┣ 📜validTeamName.adoc
        ┃ ┃ ┃ ┃ ┃ ┗ 📜waitingList.adoc
        ┃ ┃ ┃ ┃ ┗ 📜team.adoc
        ┃ ┃ ┃ ┗ 📜index.adoc
        ┃ ┣ 📂main
        ┃ ┃ ┣ 📂java
        ┃ ┃ ┃ ┗ 📂com
        ┃ ┃ ┃ ┃ ┗ 📂ssafy
        ┃ ┃ ┃ ┃ ┃ ┗ 📂pennypal
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂bank
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BankController.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountDrawingTransferControllerDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountTransactionRequestDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AccountTransferRequestDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountTransactionListResponseDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountTransactionResponseDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommonHeaderResponseControllerDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserAccountListResponseControllerDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserAccountResponseControllerDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserAccountsResponseControllerDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserCreateAccountControllerDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserCreateAccountRECControllerDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDummyAccount.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dummy
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜DummyTransactionSummary.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂common
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommonHeaderRequestDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommonHeaderResponseDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountDepositServiceDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountTransactionRequestServiceDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountTransferServiceRequestDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DrawingTransferRequestServiceDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GetUserAccountListServiceRequestDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserAccountRequestServiceDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserApiKeyRequestDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserBankAccountRequestServiceDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountDepositWithdrawalResponseServiceDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountTransactionListResponseServiceDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountTransactionRecResponseDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountTransactionResponseServiceDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DepositWithdrawalResponseServiceDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserAccountListResponseServiceDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserAccountResponseServiceDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserAccountResponseServicePayLoadDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserBankAccountResponseRECServiceDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserBankAccountResponseServiceDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserBankAccountsResponseServiceDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂model
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserApiKeyException.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BankExceptionHandler.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BankRepositoryImpl.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IBankRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IBankRepositoryCustom.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜IBankRepositoryImpl.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂api
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BankServiceAPIImpl.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜IBankServiceAPI.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂db
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BankServiceDBImpl.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜IBankServiceDB.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂card
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜.gitkeep
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CardController.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SearchCard.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CardResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜.gitkeep
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Card.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Category.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜.gitkeep
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜.gitkeep
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ICardRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ICardRepositoryCustom.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ICardRepositoryImpl.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜.gitkeep
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CardService.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂chat
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatController.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ChattingRequest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜EnterChatRoomRequest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatMessageDto.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatRoomDto.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatMessage.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatRoom.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IChatMessageRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜IChatRoomRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatService.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂market
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MarketController.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderRequestDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderResponseDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProductResponseDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Order.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Product.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜.gitkeep
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IOrderRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜IProductRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MarketService.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂member
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberController.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberAttendRequest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberLoginRequest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberSignupRequest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberUpdateNicknameRequest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberUpdatePasswordRequest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberEmailResponseDto.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberExpensesDetailResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberLastTotalExpenses.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberLoginResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberSignupResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberThisTotalExpenses.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamLastEachTotalResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TeamThisEachTotalResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseDto.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SimpleMemberDto.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Attend.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Expense.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Member.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜.gitkeep
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IAttendRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IExpenseRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜IMemberRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AttendService.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtService.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberService.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂team
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TeamController.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamBanishRequest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamCreateRequest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamCreateServiceRequest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamJoinServiceRequest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamModifyRequest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TeamRequestDTO.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamCreateResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamDetailResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamJoinResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamMemberExpenseResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamModifyResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamOtherDetailResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamRankCalculateResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamRankHistoryResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamRankRealtimeResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamRankWeeklyResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamSearchResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TeamWaitingListResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SimpleTeamDto.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Team.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TeamRankHistory.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AlreadyAppliedJoinException.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BannedMemberJoinException.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ITeamRankHistoryRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ITeamRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TeamService.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂global
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂common
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂api
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ApiResponse.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ApplicationConfig.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CorsConfig.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtAuthenticationFilter.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RestClientConfig.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SecurityConfig.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜StompConfig.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebConfig.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ApiControllerAdvice.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂support
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Querydsl5RepositorySupport.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BaseEntity.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂stock
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜StockController.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SearchStockRequestDto.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜StockWithLatestTransactionDto.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜StockWithTransactionDto.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜StockWithTransactionListDto.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Stock.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜StockTransaction.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜StockExceptionHandler.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂stock
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IStockRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IStockRepositoryCustom.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜IStockRepositoryImpl.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂stocktransaction
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IStockTransactionRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IStockTransactionRepositoryCustom.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜IStockTransactionRepositoryImpl.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IStockService.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜StockService.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PennypalApplication.java
        ┃ ┃ ┗ 📂resources
        ┃ ┃ ┃ ┣ 📂static
        ┃ ┃ ┃ ┣ 📂templates
        ┃ ┃ ┃ ┣ 📜application-dev.yml
        ┃ ┃ ┃ ┣ 📜application-local.yml
        ┃ ┃ ┃ ┣ 📜application-prod.yml
        ┃ ┃ ┃ ┗ 📜application.yml
        ┃ ┗ 📂test
        ┃ ┃ ┣ 📂java
        ┃ ┃ ┃ ┗ 📂com
        ┃ ┃ ┃ ┃ ┗ 📂ssafy
        ┃ ┃ ┃ ┃ ┃ ┗ 📂pennypal
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂bank
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BankControllerTest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂db
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BankServiceDBImplTest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂common
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RestDocsSupport.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂card
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CardControllerTest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂chat
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatControllerTest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂market
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MarketControllerTest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂member
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberControllerTest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AttendServiceTest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtServiceTest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberServiceTest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂team
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TeamControllerTest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TeamTest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TeamRepositoryTest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TeamServiceTest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂stock
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂controller
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜StockControllerTest.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PennypalApplicationTests.java
        ┃ ┃ ┗ 📂resources
        ┃ ┃ ┃ ┣ 📂org
        ┃ ┃ ┃ ┃ ┗ 📂springframework
        ┃ ┃ ┃ ┃ ┃ ┗ 📂restdocs
        ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂templates
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜request-fields.snippet
        ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜response-fields.snippet
        ┃ ┃ ┃ ┗ 📜application.yml
        ┣ 📜.gitignore
        ┣ 📜build.gradle
        ┣ 📜gradle.properties
        ┣ 📜gradlew
        ┣ 📜gradlew.bat
        ┣ 📜HELP.md
        ┗ 📜settings.gradle

 </details>

<details>
  <summary>
  데이터 디렉토리 구조
  </summary>

        📦flask
        ┣ 📂.idea
        ┃ ┣ 📂inspectionProfiles
        ┃ ┃ ┗ 📜profiles_settings.xml
        ┃ ┣ 📜encodings.xml
        ┃ ┣ 📜flaskProject.iml
        ┃ ┣ 📜misc.xml
        ┃ ┣ 📜vcs.xml
        ┃ ┗ 📜workspace.xml
        ┣ 📂handledata
        ┃ ┗ 📂category
        ┃ ┃ ┣ 📂cardcategory
        ┃ ┃ ┃ ┣ 📜CalculateCardCategory.py
        ┃ ┃ ┃ ┗ 📜SaveCardCategory.py
        ┃ ┃ ┣ 📂dao
        ┃ ┃ ┃ ┗ 📜Repository.py
        ┃ ┃ ┣ 📂json
        ┃ ┃ ┃ ┗ 📜JsonToCsv.py
        ┃ ┃ ┗ 📂service
        ┃ ┃ ┃ ┣ 📜AI.py
        ┃ ┃ ┃ ┣ 📜CardService.py
        ┃ ┃ ┃ ┣ 📜MatrixFactorization.py
        ┃ ┃ ┃ ┣ 📜MemberService.py
        ┃ ┃ ┃ ┣ 📜Run.py
        ┃ ┃ ┃ ┗ 📜Similarity.py
        ┣ 📂static
        ┃ ┗ 📂data
        ┃ ┃ ┗ 📂result
        ┃ ┃ ┃ ┣ 📜card_data.xlsx
        ┃ ┃ ┃ ┣ 📜categories.csv
        ┃ ┃ ┃ ┣ 📜categories.xlsx
        ┃ ┃ ┃ ┣ 📜extract_card_category.csv
        ┃ ┃ ┃ ┣ 📜extract_card_category_view.xlsx
        ┃ ┃ ┃ ┣ 📜final_card_data.csv
        ┃ ┃ ┃ ┣ 📜final_card_data_view.xlsx
        ┃ ┃ ┃ ┣ 📜max_len.json
        ┃ ┃ ┃ ┣ 📜model_path.keras
        ┃ ┃ ┃ ┣ 📜sample.csv
        ┃ ┃ ┃ ┗ 📜tokenizer.json
        ┣ 📜.gitignore
        ┣ 📜app.py
        ┣ 📜base.yaml
        ┗ 📜requirment.txt

 </details>


