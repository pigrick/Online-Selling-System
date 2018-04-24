package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ChanPiseth on 4/24/2018
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {


    List<Category> findAllByMainCategoryName(String mainCategoryName);

    List<Category> findAllBySubCategoryName(String subCategoryName);

    @Query(value = "SELECT DISTINCT sub_category_name from category" , nativeQuery = true)
    List<String> findAllSubCategoryName();

    @Query(value = "SELECT DISTINCT main_category_name from category" , nativeQuery = true)
    List<String> findAllMainCategoryName();


}
