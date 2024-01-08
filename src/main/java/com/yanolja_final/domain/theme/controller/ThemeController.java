package com.yanolja_final.domain.theme.controller;

import com.yanolja_final.domain.theme.dto.response.ThemeResponse;
import com.yanolja_final.domain.theme.facade.ThemeFacade;
import com.yanolja_final.global.util.ResponseDTO;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/themes")
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeFacade themeFacade;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ThemeResponse>>> getAllThemes() {
        List<ThemeResponse> themes = themeFacade.getAllThemes();
        return ResponseEntity.ok(ResponseDTO.okWithData(themes));
    }
}
