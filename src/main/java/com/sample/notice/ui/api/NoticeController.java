package com.sample.notice.ui.api;

import com.sample.global.domain.ListRequest;
import com.sample.global.domain.ListResponse;
import com.sample.notice.dto.NoticeListResponse;
import com.sample.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    @Operation(summary = "공지사항 리스트 조회", description = "공지사항 리스트를 조회합니다.")
    public ListResponse<NoticeListResponse> getList(@ParameterObject final ListRequest request) {
        return noticeService.getList(request);
    }
}
