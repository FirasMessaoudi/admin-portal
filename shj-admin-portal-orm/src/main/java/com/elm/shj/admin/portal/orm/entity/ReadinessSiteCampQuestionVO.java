/*
 *  Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadinessSiteCampQuestionVO {

    private String questionCode;
    private String questionLabel;
    private String questionDescriptionCode;
    private String questionDescriptionLabel;
    private String questionCategoryCode;
    private String questionCategoryLabel;
    private int rate;
}
