package tech.surfer.examserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.surfer.examserver.entity.exam.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
