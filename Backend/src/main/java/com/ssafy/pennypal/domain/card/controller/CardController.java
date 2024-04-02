package com.ssafy.pennypal.domain.card.controller;


import com.ssafy.pennypal.domain.card.dto.request.SearchCard;
import com.ssafy.pennypal.domain.card.dto.response.CardResponse;
import com.ssafy.pennypal.domain.card.service.CardService;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/card")
@RestController
public class CardController {
    private final CardService cardService;

    @GetMapping
    public ApiResponse<Page<CardResponse>> getCards(
            @ModelAttribute SearchCard searchCard,
            @PageableDefault(page = 0, size = 5) Pageable pageable
    ) {
        return cardService.getCards(pageable, searchCard);
    }
}
