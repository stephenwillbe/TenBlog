package com.dagougou.tenblog.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sorts {
    private Long sortId;

    private String sortName;

    private Long parentId;

    private Integer sortState;

    private String sortDescription;


}