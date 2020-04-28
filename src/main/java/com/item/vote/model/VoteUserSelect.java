package com.item.vote.model;

import lombok.Data;

/**
 * 投票包含用户选择的那个选项
 */
@Data
public class VoteUserSelect {

   private String VoteName;

   private String selectOption;


}
