package com.example.demo.designpattern.responsibilitypattern;

/**
 * 类功能说明:   告警流规则生成
 */
public class StreamGenerateHandler extends AbstractRuleHandler
{

    @Override
    public void doHandleReal(AlarmRule rule) throws Exception
    {
        System.out.println("Generate stream success.");
    }

    @Override
    public void rollBackReal(AlarmRule rule)
    {
        System.out.println("Roll Generate stream.");
    }

}
