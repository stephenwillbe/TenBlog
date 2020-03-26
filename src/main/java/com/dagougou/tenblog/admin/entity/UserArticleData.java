package com.dagougou.tenblog.admin.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Description: 用户的文章数据
 * @Author stephen
 * @Date 2020/3/26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserArticleData {

    private Long userId;
    //文章数
    private Long articles;
    //点击量
    private Long articleViews;
    //点赞数
    private Long articleLikeCount;
    //评论数
    private Long articleCommentCount;
}
