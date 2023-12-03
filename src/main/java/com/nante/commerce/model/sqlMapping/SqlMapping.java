package com.nante.commerce.model.sqlMapping;

import jakarta.persistence.Embeddable;

import com.nante.commerce.model.stock.etatStock.ResteEntre;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.SqlResultSetMappings;

// @SqlResultSetMapping(name = "ArticleStock", classes = {
//                 @ConstructorResult(targetClass = ArticleStock.class, columns = {
//                                 @ColumnResult(name = "refArticle", type = String.class),
//                                 @ColumnResult(name = "refMagasin", type = String.class),
//                                 @ColumnResult(name = "qteInitial", type = double.class),
//                                 @ColumnResult(name = "montantInitial", type = double.class),
//                                 @ColumnResult(name = "qteEntre", type = double.class),
//                                 @ColumnResult(name = "montantEntre", type = double.class),
//                                 @ColumnResult(name = "qteSortie", type = double.class),
//                                 @ColumnResult(name = "montantSortie", type = double.class)
//                 })
// })
@SqlResultSetMapping(name = "ResteEntre", classes = {
        @ConstructorResult(targetClass = ResteEntre.class, columns = {
                @ColumnResult(name = "idEntree", type = int.class),
                @ColumnResult(name = "reste", type = double.class),
                @ColumnResult(name = "pu", type = double.class)
        })
})
@Embeddable
public class SqlMapping {
}
