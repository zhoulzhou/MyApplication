package com.example.demo.designpattern.responsibilitypattern;

import com.example.demo.common.StringUtils;

/**
 * 类功能说明:   告警规则持久化
 */
public class RulePesistHandler extends AbstractRuleHandler {

    @Override
    public void doHandleReal(AlarmRule rule) throws Exception {
        if (StringUtils.isEmpty(rule.getName())) {
            throw new Exception("Rule name is empty.");
        }
        System.out.println("Persist rule success. Rule name is " + rule.getName());
    }

    @Override
    public void rollBackReal(AlarmRule rule) {
        System.out.println("Roll persist rule. Rule name is " + rule.getName());

    }

}