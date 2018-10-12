package com.example.demo.designpattern.responsibilitypattern;

/**
 * 生产一个产品，需要依次执行多个步骤，才能完成，那么是使用责任链模式则是极好的。

 在性能告警模块开发过程中，创建一条告警规则需要执行阈值解析，中间表生成，流任务生成，规则入库，告警事件入库等诸多操作。如果把这些步骤糅合在一个类中，代码可读性及复杂度往往是灾难的，特别对于这么多步骤的事务性操作，更是力不从心。使用责任链模式，上述问题迎刃而解。

 以告警规则创建为例子，简化流程如下

 阈值解析 ---> 流任务生成 ---> 规则入库

 回滚流程如下

 1、 阈值解析失败：回滚阈值解析。

 2、 流任务生产失败：回滚流任务生成，阈值解析。

 3、 规则入库失败：回滚规则入库，流任务生成，阈值解析。



 采用责任链模式编码，思路如下：

 1、 编写阈值解析处理器，流任务生成处理器，规则入库处理器，每个处理器包含业务处理方法和回滚方法；

 2、 一个处理器业务代码执行完成后主动调用下一个处理器业务方法；

 3、 一个处理器业务代码执行失败主动调用本处理器回滚方法，本处理器回滚完成后主动调用上一个处理器回滚方法。
 */
public class Notice {
}
