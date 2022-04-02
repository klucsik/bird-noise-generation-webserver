package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.frontend.services.MarkdownToHtmlService;
import com.github.klucsik.birdnoiseserver.frontend.services.ResourceReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/docs")
@Controller
@RequiredArgsConstructor
public class DocsController {

    private final  MarkdownToHtmlService toHtmlService;
    private final ResourceReader resourceReader;

    @GetMapping("/readme")
    public String getReadme(Model model) {
        model.addAttribute("title","Developer Readme");
        model.addAttribute("htmlContent",toHtmlService.markdownToHtml(resourceReader.readFileToString("markdown/README.md")));
        return "markdownView";
    }
    @GetMapping("/changenotes")
    public String getChangeNotes(Model model) {
        model.addAttribute("title","Change Notes");
        model.addAttribute("htmlContent",toHtmlService.markdownToHtml(resourceReader.readFileToString("markdown/CHANGE_NOTES.md")));
        return "markdownView";
    }
    @GetMapping("/usermanual")
    public String getUserManual(Model model) {
        model.addAttribute("title","User Manual");
        model.addAttribute("htmlContent",toHtmlService.markdownToHtml(resourceReader.readFileToString("markdown/USER_MANUAL.md")));
        return "markdownView";
    }
}
