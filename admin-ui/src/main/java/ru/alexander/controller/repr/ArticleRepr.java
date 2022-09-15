package ru.alexander.controller.repr;

import java.io.Serializable;
import java.util.Objects;
import ru.alexander.persist.model.Article;

public class ArticleRepr implements Serializable {

    private Long id;

    private String name;

    private String description;

    public ArticleRepr() {
    }

    public ArticleRepr(Article article) {
        this.id = article.getId();
        this.name = article.getName();
        this.description = article.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleRepr that = (ArticleRepr) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
