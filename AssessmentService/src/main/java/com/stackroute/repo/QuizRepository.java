package com.stackroute.repo;

import com.stackroute.model.exam.Category;
import com.stackroute.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    public List<Quiz> findBycategory(Category category);

    public List<Quiz> findByActive(Boolean b);

    public List<Quiz> findByCategoryAndActive(Category c, Boolean b);

    @Query(value = "select * from quiz where match(title,description) against (?1)", nativeQuery = true)
    public List<Quiz> findbycategory(String keyword);

}
