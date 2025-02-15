package com.sample.notice.ui.admin;

import com.sample.global.domain.ListRequest;
import com.sample.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/view/notice")
@RequiredArgsConstructor
class AdminNoticeViewController {

    private final NoticeService noticeService;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("result", noticeService.getList(new ListRequest()));
        return "notice/notices";
    }

    @GetMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("mode", "create");
        return "notice/notice";
    }

    @GetMapping("/{id}")
    public String viewDetail(@PathVariable final Long id, Model model) {
        model.addAttribute("mode", "view");
        model.addAttribute("result", noticeService.getDetail(id));

        return "notice/notice";
    }

    @GetMapping("/{id}/edit")
    public String viewEdit(@PathVariable final Long id, Model model) {
        model.addAttribute("mode", "edit");
        model.addAttribute("result", noticeService.getDetail(id));

        return "notice/notice";
    }
}
