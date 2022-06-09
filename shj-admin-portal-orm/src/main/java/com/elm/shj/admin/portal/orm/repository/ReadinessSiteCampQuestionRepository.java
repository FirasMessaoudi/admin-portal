package com.elm.shj.admin.portal.orm.repository;



import com.elm.shj.admin.portal.orm.entity.JpaReadinessSiteCampQuestion;
import com.elm.shj.admin.portal.orm.entity.ReadinessSiteCampQuestionVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReadinessSiteCampQuestionRepository extends JpaRepository<JpaReadinessSiteCampQuestion, Long> {


    @Query("SELECT New com.elm.shj.admin.portal.orm.entity.ReadinessSiteCampQuestionVO(q.questionCode,qlk.label, d.questionDescriptionCode,dlk.label, qlk.questionCategoryCode,clk.label, 0) " +
            "from JpaReadinessSiteCampQuestion q " +
            "join JpaReadinessSurveyQuestionLookup qlk on qlk.code = q.questionCode " +
            "join JpaReadinessSurveyQuestionCategoryLookup clk on clk.code = qlk.questionCategoryCode " +
            "left join JpaReadinessSiteCampQuestionDescription d on q.questionCode = d.questionCode and d.campCategoryCode= :campCategoryCode and d.siteCode= :campSiteCode " +
            "left join JpaReadinessSurveyQuestionDescriptionLookup dlk on dlk.code = d.questionDescriptionCode and dlk.lang =:lang " +
            "where q.campCategoryCode = :campCategoryCode and q.siteCode = :campSiteCode  " +
            "and qlk.lang =:lang and clk.lang =:lang " +
            "order by q.questionOrder")
    List<ReadinessSiteCampQuestionVO> findAllBySiteCodeAndCampCategoryCode(@Param("campSiteCode") String campSiteCode,@Param("campCategoryCode") String campCategoryCode,@Param("lang") String lang);
}
