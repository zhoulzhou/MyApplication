package com.example.demo.designpattern.responsibilitypattern;

import com.example.demo.common.StringUtils;

/**
 * 类功能说明:   阈值解析
 */
public class ThresholdParseHandler extends AbstractRuleHandler {

    @Override
    public void doHandleReal(AlarmRule rule) throws Exception {
        if (StringUtils.isEmpty(rule.getThreshold())) {
            throw new Exception("Threshold is empty.");
        }
        System.out.println("Parse threshold success. Threshold is " + rule.getThreshold());
    }

    @Override
    public void rollBackReal(AlarmRule rule) {
        System.out.println("Roll parse threshold. Threshold is " + rule.getThreshold());
    }

}