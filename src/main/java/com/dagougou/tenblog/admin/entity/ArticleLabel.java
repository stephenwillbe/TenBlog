package com.dagougou.tenblog.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleLabel {
    private Integer tId;

    private Long articleId;

    private Long labelId;

}