package ru.alexander.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import ru.alexander.controller.repr.ArticleRepr;

public interface ArticleService {

    List<ArticleRepr> findAll();

    Optional<ArticleRepr> findById(Long id);

    void deleteById(Long id);

    void save(ArticleRepr article) throws IOException;
}
