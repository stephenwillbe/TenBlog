package com.dagougou.tenblog.admin.service;

import com.dagougou.tenblog.admin.entity.Sorts;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * @Description: todo
 * @Author stephen
 * @Date 2020/3/12
 **/
public interface SortsService {

    Map<String,Object> getAllSortsTree();

    List<Sorts> getArticleSorts();

    int insertBySorts(Sorts sorts);

    int uptSortsName(Sorts sorts);

    Sorts getSortByArticleId(Integer articleId);
}
