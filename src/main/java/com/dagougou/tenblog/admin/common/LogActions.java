package com.dagougou.tenblog.admin.common;
/**
 * @Description //日志行为枚举
 **/
public enum LogActions {

    LOGIN_BACK("登陆后台"),LOGOUT_BACK("退出后台"),LOGIN_FRONT("登陆博客"),
    DEL_ARTICLE("删除文章"),DEL_COMMENT("删除评论"),LOGOUT_FRONT("退出博客"),
    DEL_LABEL("删除标签"),DEL_SORT("删除分类"),DEL_USER("删除用户"),
    INSERT_LABEL("新增标签"),INSERT_SORT("新增分类"),POST_ARTICLE("发布文章"),
    UPT_USER("编辑用户"),UPT_INFO("修改个人信息"),UPT_ARTICLE("编辑文章");

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    LogActions(String action){
        this.action = action;
    }
}
