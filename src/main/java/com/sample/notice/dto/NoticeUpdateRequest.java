package com.sample.notice.dto;

import jakarta.validation.constraints.NotBlank;

public record NoticeUpdateRequest(
        @NotBlank String title,
        @NotBlank String content,
        @NotBlank String banner) {
}
