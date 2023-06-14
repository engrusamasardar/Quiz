package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.model.AttemtsQuiz;

/**
 * @implNote Repository interface to manage Quiz
 * @author usardar
 *
 */
@Repository
public interface QuizRepository extends JpaRepository<AttemtsQuiz, Integer> {
	
	//Both @Transactional and @Modifying annotation used for Data manipulation
	//Query Stores the attemp Quiz in database
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO quiz.attempquiz(uId, qId, realAnswer, userAnswer) VALUES (:uId, :qId, :realAnswer, :userAnswer)", nativeQuery = true)
	public void insertInToAttempQuiz(@Param("uId") int uId, @Param("qId") int qId,
			@Param("realAnswer") String realAnswer, @Param("userAnswer") String userAnswer);

}
