package com.mafn.infra;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
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
