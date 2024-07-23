package com.tekpyramid.supportportal.service.impl;

import com.tekpyramid.supportportal.dao.FaqDao;
import com.tekpyramid.supportportal.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

    private final FaqDao faqDao;
}
