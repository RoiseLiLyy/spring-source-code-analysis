package com.xuanhui.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author IanGuo
 * @date 2021/10/20
 */
@Service
@Slf4j
public class PanelUserServiceImpl implements PanelUserService {


    @Override
    public String testPanel(Integer size) {
        log.info("aaaaaaaaaaaaaa");
        Integer s = 12 * 56;
        return s.toString();
    }
}
