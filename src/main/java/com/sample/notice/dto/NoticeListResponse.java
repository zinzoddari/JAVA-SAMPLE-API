package com.sample.notice.dto;

import com.sample.notice.domain.Notice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Schema(title = "공지사항 리스트 조회 응답 DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeListResponse {

    @Schema(description = "공지사항 고유 ID", example = "1")
    private long id;

    @Schema(description = "제목", example = "공지사항 입니다.")
    private String title;

    @Schema(description = "배너 이미지")
    private String banner;

    @Schema(description = "생성 일자", example = "2025-02-15T23:28:57")
    private LocalDateTime createdDate;

    public static NoticeListResponse from(final Notice notice) {
        NoticeListResponse response = new NoticeListResponse();

        response.id = notice.getId();
        response.title = notice.getTitle();
        response.banner = notice.getBanner();
        response.createdDate = notice.getCreatedDate();

        return response;
    }
}
