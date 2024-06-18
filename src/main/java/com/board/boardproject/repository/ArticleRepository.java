package com.board.boardproject.repository;

import com.board.boardproject.domain.Article;
import com.board.boardproject.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.DateTimeException;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        /**
         * PredicateExecutor는 기본적으로 이 Article안에 있는 모든 검색기능을 추가해준다.
         * 만약 제목을 검색으로 한다할 때 제목이 '경제는 차갑다.' 라면 '경제는 차갑다.'를 전부 입력하여 검색해줘야 한다. 부분 검색은 안된다.
         * 예를 들어 '경제는' 만 검색하면 검색이 안된다.
         * 이를 해결하기 위해 BinderCustomizer를 사용한다!!
         */
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle>
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        /**
         * StringExpression::containsIgnoreCase는 like '%${v}%'
         * StringExpression::likeIgnoreCase는 like '${v}'
         */
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
