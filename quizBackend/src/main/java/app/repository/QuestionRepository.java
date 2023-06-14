package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.Question;

/**
 * @implNote Repository interface to manage Questions
 * @author usardar
 *
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

}
