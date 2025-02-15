package com.sample.notice.dto;

import com.sample.notice.domain.Notice;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public record NoticeSaveRequest(
        @NotBlank String title,
        @NotBlank String content,
        @NotBlank String banner) {

    public Notice toEntity() {
        return Notice.from(this.title, this.content, this.banner);
    }
}
