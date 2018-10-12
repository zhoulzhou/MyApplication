package com.example.demo.designpattern.responsibilitypattern;

/**
 * <p>
 * 类功能说明:   告警规则创建
 * <p>
 */
public class AlarmRuleCreator {
    private AbstractRuleHandler alarmRuleHandler;

    public AlarmRuleCreator() {
        alarmRuleHandler = new ThresholdParseHandler();
        alarmRuleHandler.setNextHandler(new StreamGenerateHandler())
                .setNextHandler(new RulePesistHandler());
    }

    public void create(AlarmRule rule) {
        alarmRuleHandler.doHandle(rule);
    }

}
