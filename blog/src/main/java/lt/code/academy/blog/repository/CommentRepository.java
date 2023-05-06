package lt.code.academy.blog.repository;

import lt.code.academy.blog.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
    List<CommentEntity> findAllByArticleId(UUID articleId);
}
