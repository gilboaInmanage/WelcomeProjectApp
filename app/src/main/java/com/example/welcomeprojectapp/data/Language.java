package com.example.welcomeprojectapp.data;

import il.co.inmanage.parser.Parser;
import il.co.inmanage.server_responses.SortResponse;
import il.co.inmanage.utils.NumberUtils;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Objects;

public class Language extends SortResponse implements Serializable {

    private String id;
    private String title;
    private String description;
    private LanguageDirectionEnum direction = LanguageDirectionEnum.RTL;
    private String constHtml;
    private boolean active = false;
    private boolean isSelected = false;
    private String contentPageHtmlSuffix;

    public Language() {
        // Default constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LanguageDirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(LanguageDirectionEnum direction) {
        this.direction = direction;
    }

    public String getConstHtml() {
        return constHtml;
    }

    public void setConstHtml(String constHtml) {
        this.constHtml = constHtml;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getContentPageHtmlSuffix() {
        return contentPageHtmlSuffix;
    }

    public void setContentPageHtmlSuffix(String contentPageHtmlSuffix) {
        this.contentPageHtmlSuffix = contentPageHtmlSuffix;
    }

    @Override
    public SortResponse createSortResponse(JSONObject jsonObject) {
        Language language = new Language();
        if (jsonObject != null) {
            language.setId(Parser.jsonParse(jsonObject, "id", Parser.createTempString()));
            language.setTitle(Parser.jsonParse(jsonObject, "title", Parser.createTempString()));
            language.setDescription(Parser.jsonParse(jsonObject, "description", Parser.createTempString()));
            String directionString = Parser.jsonParse(jsonObject, "direction", "-1");
            language.setDirection(LanguageDirectionEnum.fromDirection(directionString));
            language.setConstHtml(Parser.jsonParse(jsonObject, "const_html", Parser.createTempString()));
            language.setActive(Parser.jsonParse(jsonObject, "active", false));
            language.setContentPageHtmlSuffix(Parser.jsonParse(jsonObject, "const_html", ""));
        }
        return language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(id, language.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int compareTo(Language other) {
        return NumberUtils.getIntegerFromString(this.id == null ? "0" : this.id)
                - NumberUtils.getIntegerFromString(other.id == null ? "0" : other.id);
    }

    public enum LanguageEnum {
        EN("en"), HE("he"), AR("ar");

        private final String title;

        LanguageEnum(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum LanguageDirectionEnum {
        LTR("1"), RTL("2");

        private final String direction;

        LanguageDirectionEnum(String direction) {
            this.direction = direction;
        }

        public String getDirection() {
            return direction;
        }

        public static LanguageDirectionEnum fromDirection(String direction) {
            for (LanguageDirectionEnum value : values()) {
                if (value.getDirection().equals(direction)) {
                    return value;
                }
            }
            return RTL;
        }
    }

    @Override
    public String toString() {
        return "Language{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", direction=" + direction +
                ", constHtml='" + constHtml + '\'' +
                ", active=" + active +
                ", isSelected=" + isSelected +
                ", contentPageHtmlSuffix='" + contentPageHtmlSuffix + '\'' +
                '}';
    }
}

