package com.mafn.infra;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiErrors {
    private LocalDate timestamp;
    private int status;
    private String error;
    private String path;

    
}
