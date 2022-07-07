package tech.surfer.examserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.surfer.examserver.entity.exam.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
