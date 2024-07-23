package com.tekpyramid.supportportal.dao.impl;

import com.tekpyramid.supportportal.dao.FaqDao;
import com.tekpyramid.supportportal.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FaqDaoImpl implements FaqDao {

    private final FaqRepository faqRepository;
}
