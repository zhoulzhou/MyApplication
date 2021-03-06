package com.example.demo.designpattern.decoratorpattern;

/**
 *
 *装饰者模式
 1、意图： 动态地给一个对象添加一些额外的职责。就增加功能来说， Decorator模式相比生成子类更为灵活。该模式以对客 户端透明的方式扩展对象的功能。

 2、适用环境

 （1）在不影响其他对象的情况下，以动态、透明的方式给单个对象添加职责。

 （2）处理那些可以撤消的职责。

 （3）当不能采用生成子类的方法进行扩充时。一种情况是，可能有大量独立的扩展，为支持每一种组合将产生大量的 子类，使得子类数目呈爆炸性增长。另一种情况可能是因为类定义被隐藏，或类定义不能用于生成子类。

 要实现装饰者模式,注意一下几点内容:
 1.装饰者类要实现真实类同样的接口
 2.装饰者类内有一个真实对象的引用(可以通过装饰者类的构造器传入)
 3.装饰类对象在主类中接受请求,将请求发送给真实的对象(相当于已经将引用传递到了装饰类的真实对象)
 4.装饰者可以在传入真实对象后,增加一些附加功能(因为装饰对象和真实对象都有同样的方法,装饰对象可以添加一定操作在调用真实对象的方法,或者先调用真实对象的方法,再添加自己的方法)
 5.不用继承,
 *
 * 需要使用旧的方法 然后再旧的方法上增加新的方法时使用
 */
public class Notice {

}
