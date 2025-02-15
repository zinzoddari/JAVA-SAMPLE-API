package com.sample.notice.ui.admin;

import com.sample.notice.dto.NoticeSaveRequest;
import com.sample.notice.dto.NoticeUpdateRequest;
import com.sample.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "[어드민] [공지사항]")
@RequiredArgsConstructor
@RequestMapping("/admin/notice")
class AdminNoticeController {

    private final NoticeService noticeService;

    @PostMapping
    @Operation(summary = "[공지사항] 공지사항 등록", description = "공지사항을 등록합니다.")
    public void register(@Valid @RequestBody final NoticeSaveRequest request) {
        noticeService.register(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "[공지사항] 공지사항 수정", description = "공지사항을 수정합니다.")
    public void modify(@PathVariable final Long id, @Valid @RequestBody final NoticeUpdateRequest request) {
        noticeService.modify(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "[공지사항] 공지사항 삭제", description = "공지사항을 삭제합니다.")
    public void remove(@PathVariable final Long id) {
        noticeService.remove(id);
    }
}
