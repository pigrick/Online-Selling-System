package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ChanPiseth on 4/24/2018
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllByName(String mainCategoryName);
    List<Category> findAllByParentCategoryId(Integer parentId);
    List<Category> findAllByParentCategoryIsNull();

}
