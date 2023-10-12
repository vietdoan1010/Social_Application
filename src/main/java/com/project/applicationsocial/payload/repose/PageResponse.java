package com.project.applicationsocial.payload.repose;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T>{
    private int page;
    private int totalElement;
    private int pageSize;
    private List<T> data;
}
