package com.example.demo.designpattern.adapterpattern;

/**
 * 说到适配器，我们可能会想到电脑的适配器，没错，其实作用是一样的，
 * 电脑的适配器在中国可以使用，在美国也可以使用，
 * 它的主要作用是在新接口和老接口之间进行适配。。这就是一个适配的过程，
 *
 * 优点：

 1、将目标类和适配者类解耦

 2、增加了类的透明性和复用性，将具体的实现封装在适配者类中，对于客户端类来说是透明的，而
    且提高了适配者的复用性

 3、灵活性和扩展性都非常好，符合开闭原则

 类适配器还有的优点：

 1、由于适配器类是适配者类的子类，因此可以再适配器类中置换一些适配者的方法，使得适配器的灵活性更强。

 类适配器的缺点：

 1、对于Java、C#等不支持多重继承的语言，一次最多只能适配一个适配者类，而且目标抽象类只能为接口，
    不能为类，其使用有一定的局限性，不能将一个适配者类和他的子类同时适配到目标接口。

 对象适配器还有的优点：

 1、把多个不同的适配者适配到同一个目标，也就是说，同一个适配器可以把适配者类和他的子类都适配到目标接口。

 对象适配器的缺点：

 1、与类适配器模式相比，要想置换适配者类的方法就不容易。

 适配器模式与代理模式的区别：

 适配器模式

 　　　　是把2个不同的东西转换到可以正常使用，适配器是去迎合目标对象与源对象。

 代理模式

 　　　　是2个对象都有相同的功能，2者接口是一样的。


 我所看的书中说适配器模式不适合在详细设计阶段使用它，它是一种补偿模式，
 专用来在系统后期扩展、修改时所用。

 */
public class Notice {
}
