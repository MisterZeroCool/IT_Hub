package ru.alexander.persist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexander.persist.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
