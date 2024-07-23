package com.tekpyramid.supportportal.controller;

import com.tekpyramid.supportportal.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

}
