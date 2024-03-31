package com.ssafy.pennypal.domain.chat.dto.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingRequest {

    private Long senderId;
    private String content;

}
