package com.dagougou.tenblog.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Labels {
    private Long labelId;

    private String labelName;

    private Integer disabled;


}