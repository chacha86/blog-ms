package com.korea.blog.global.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Setter
@Getter
@Component
@Scope("request")
public class UrlParamManager {
    private String keyword;
    private String isModalOpen;
    private String sortTarget;

    public UrlParamManager() {
        keyword = "";
        isModalOpen = "false";
        sortTarget = "id";
    }

    public String getQueryParam() {
        return "keyword=" + keyword + "&isModalOpen=" + isModalOpen + "&sortTarget=" + sortTarget;
    }

    public String getParamUrl(String url) {
        return url + "?" + getQueryParam();
    }

    public String getBookUrl(long bookId) {
        isModalOpen = "false";
        return getParamUrl("/books/%d".formatted(bookId));
    }
    public String getNoteUrl(long bookId, long noteId, String sortTarget) {
        this.sortTarget = sortTarget;
        return getNoteUrl(bookId, noteId);
    }

    public String getNoteUrl(long bookId, long noteId) {
        isModalOpen = "false";
        return getParamUrl("/books/%d/notes/%d".formatted(bookId, noteId));
    }

    public String getRedirectUrl(String url) {
        return "redirect:" + getParamUrl(url);
    }
}
