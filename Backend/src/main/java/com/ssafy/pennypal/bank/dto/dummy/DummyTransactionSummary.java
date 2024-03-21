package com.ssafy.pennypal.bank.dto.dummy;

import java.util.Random;

public enum DummyTransactionSummary {
    DATA1("카카오페이"),
    DATA2("네이버페이"),
    DATA3("토스페이"),
    DATA4("GS25"),
    DATA5("롯데-종진유통"),
    DATA6("다이소가맹점"),
    DATA7("주코리아세븐점"),
    DATA8("요기요-위대한상상"),
    DATA9("29CM"),
    DATA10("신라마라탕"),
    DATA11("메가박스-메가박스(주)"),
    DATA12("쿠팡-쿠페이"),
    DATA13("홈플러스익스프레스"),
    DATA14("티머니-대전버스"),
    DATA15("피자마루"),
    DATA16("주식회사-지마켓"),
    DATA17("E-MART"),
    DATA18("올리브영"),
    DATA19("무신사페이-(주)무신사"),
    DATA20("스타벅스"),
    DATA21("YOUTUBE-PREMIUM"),
    DATA22("도시가스(주)"),
    DATA23("송촌약국"),
    DATA24("삼성화재"),
    DATA25("엔제리너스");

    private final String value;

    DummyTransactionSummary(String value) {
        this.value = value;
    }

    // Enum 값들 중 하나를 랜덤으로 반환하는 메소드
    public static String getRandomData() {
        Random random = new Random();
        DummyTransactionSummary[] values = DummyTransactionSummary.values();
        return String.valueOf(values[random.nextInt(values.length)].getValue());
    }

    public String getValue() {
        return value;
    }
}
