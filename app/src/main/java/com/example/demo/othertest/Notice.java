package com.example.demo.othertest;

/**
 * fail-fast 机制，即快速失败机制，是java集合(Collection)中的一种错误检测机制。
 * 当在迭代集合的过程中该集合在结构上发生改变的时候，就有可能会发生fail-fast，
 * 即抛出ConcurrentModificationException异常。fail-fast机制并不保证在不同步的修改下一定会抛出异常，
 * 它只是尽最大努力去抛出，所以这种机制一般仅用于检测bug。
 *
 * 使用java并发包(java.util.concurrent)中的类来代替ArrayList 和hashMap。
 比如使用 CopyOnWriterArrayList代替ArrayList，CopyOnWriterArrayList在是使用上跟ArrayList几乎一样，
 CopyOnWriter是写时复制的容器(COW)，在读写时是线程安全的。该容器在对add和remove等操作时，
 并不是在原数组上进行修改，而是将原数组拷贝一份，在新数组上进行修改，待完成后，
 才将指向旧数组的引用指向新数组，所以对于CopyOnWriterArrayList在迭代过程并不会发生fail-fast现象。
 但 CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性。
 对于HashMap，可以使用ConcurrentHashMap，ConcurrentHashMap采用了锁机制，是线程安全的。
 在迭代方面，ConcurrentHashMap使用了一种不同的迭代方式。在这种迭代方式中，
 当iterator被创建后集合再发生改变就不再是抛出ConcurrentModificationException，
 取而代之的是在改变时new新的数据从而不影响原有的数据 ，iterator完成后再将头指针替换为新的数据 ，
 这样iterator线程可以使用原来老的数据，而写线程也可以并发的完成改变。即迭代不会发生fail-fast，
 但不保证获取的是最新的数据。


 */
public class Notice {
}
