package ru.alexander.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import ru.alexander.controller.repr.ArticleRepr;
import ru.alexander.error.NotFoundException;
import ru.alexander.persist.model.Article;
import ru.alexander.persist.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    @Transactional
    public List<ArticleRepr> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<ArticleRepr> findById(Long id) {
        return articleRepository.findById(id).map(ArticleRepr::new);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(ArticleRepr articleRepr) throws IOException {
        Article article = (articleRepr.getId() != null) ? articleRepository.findById(articleRepr.getId())
                .orElseThrow(NotFoundException::new) : new Article();
        article.setName(articleRepr.getName());

        articleRepository.save(article);
    }
}
