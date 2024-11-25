package com.korea.blog.global.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Setter
@Getter
@Component
@RequestScope
public class UrlBuilder {
    private String keyword;
    private String isModalOpen;
    private String sortTarget;

    public UrlBuilder() {
        keyword = "";
        isModalOpen = "false";
        sortTarget = "id";
    }

    public String getQueryParam() {
        return "keyword=" + keyword + "&isModalOpen=" + isModalOpen + "&sortTarget=" + sortTarget;
    }

    public String getRedirectUrl(String url) {
        return "redirect:" + url + "?" + getQueryParam();
    }

    public String getParamUrl(String url) {
        return url + "?" + getQueryParam();
    }
    public String getBooksUrl(long bookId) {
        isModalOpen = "false";
        return getParamUrl("/books/" + bookId);
    }
    public String getNotesUrl(long bookId, long noteId) {
        isModalOpen = "false";
        return getParamUrl("/books/" + bookId + "/notes/" + noteId);
    }
    public String getNotesTitleAscUrl(long bookId, long noteId) {
        sortTarget = "title";
        return getNotesUrl(bookId, noteId);
    }

    public String getNotesIdDescUrl(long bookId, long noteId) {
        sortTarget = "id";
        return getNotesUrl(bookId, noteId);
    }

}
