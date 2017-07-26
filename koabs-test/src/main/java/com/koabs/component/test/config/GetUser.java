package com.koabs.component.test.config;

import com.koabs.compentent.logs.api.GetLogUser;
import com.koabs.compentent.logs.api.LogUser;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Author: koabs
 * 2/16/17.
 */
@Service
public class GetUser implements GetLogUser{

    @Override
    public LogUser getLogUser() {
        return null;
    }

}
