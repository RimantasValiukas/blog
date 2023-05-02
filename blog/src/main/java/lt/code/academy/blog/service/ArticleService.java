package lt.code.academy.blog.service;

import lt.code.academy.blog.dto.Article;
import lt.code.academy.blog.entity.ArticleEntity;
import lt.code.academy.blog.exception.ArticleNotExistRuntimeException;
import lt.code.academy.blog.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void createArticle(Article article) {
        articleRepository.save(ArticleEntity.convert(article));
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll()
                .stream()
                .map(Article::convert)
                .toList();
    }

    public Article getArticle(UUID id) {
        return articleRepository.findById(id)
                .map(Article::convert)
                .orElseThrow(() -> new ArticleNotExistRuntimeException(id));
    }

    public void updateArticle(Article article) {
        articleRepository.save(ArticleEntity.convert(article));
    }

    public void deleteArticle(UUID id) {
        articleRepository.deleteById(id);
    }

}
