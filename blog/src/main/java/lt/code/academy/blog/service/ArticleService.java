package lt.code.academy.blog.service;

import lt.code.academy.blog.dto.Article;
import lt.code.academy.blog.entity.ArticleEntity;
import lt.code.academy.blog.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
