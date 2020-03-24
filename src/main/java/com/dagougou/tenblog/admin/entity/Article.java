package com.dagougou.tenblog.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Long articleId;

    private Long userId;

    private Long articleViews;

    private Long articleCommentCount;

    private String articleTitle;

    private String articleHtmlContent;

    private String articleMdContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date articleDate;

    private Long articleLikeCount;

    private Integer articleStatus;

    private Integer articleAllowComment;


}