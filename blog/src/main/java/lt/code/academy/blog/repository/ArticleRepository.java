package lt.code.academy.blog.repository;


import lt.code.academy.blog.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {

}
