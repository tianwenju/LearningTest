系列文章

“一盘沙拉”带你入门Dagger2（一）之HelloWorld

“一盘沙拉”带你入门Dagger2（二）之带参数怎么办

“一盘沙拉”带你入门Dagger2（三）之@Qualifier

“一盘沙拉”带你入门Dagger2（四）之 @Singleton

“一盘沙拉”带你入门Dagger2（五）之 @Scope

引子

使用Dagger2这个框架，可以高效的在Android（Java）中实现“依赖注入”。

什么是依赖注入？

简单理解就是：

往往一个类（目标类）里面，为了使用其他类（被依赖类）的一些方法，需要先引入其他类的对象（称之为目标类依赖其他被依赖类），这时，你就需要new 其他类的对象，有时候后new的这个对象需要准备很多参数，你在new之前就要一个一个地准备这些参数，这样就使得现在的这个目标类，越来越臃肿。

从目标类的角度去想，我就是使用一个对象而已，而你却让为为此做了那么多工作，如果使用依赖注入的话，一个注解搞定所有，而具体的准备这个依赖的所有参数的工作，交给这个依赖自身去处理，目标类不需要知道这些依赖是如何准备的，这就充分体现了解耦。

通俗地讲，你要吃一个苹果，你只需一个依赖注入，一个洗干净、削好皮、切好块、扎好牙签的苹果就呈现在你面前了，至于这个苹果是怎么洗的、怎么削皮的、怎么切的、如何买的牙签，你统统不需要知道，这些工作都交给了另一个类，充分地解耦！！！
如果你使用了很多被依赖类，你就需要new很多次，同时为这些被依赖类准备所有需要的参数，而依赖注入可以很方便地帮助你管理和注入其他类的对象，不用你再一个一个地new，不用再准备所有的参数。

当然以上只是一个很简单的理解，具体依赖注入的其他好处，请自行Google。

友情提示：要想学会Dagger2，必须对“依赖注入”有一定了解，因为Dagger2是实现“依赖注入”的一种框架，不了解“依赖注入”怎么能行。
本文宗旨不是概念，是实际的代码举例，相信大家把这些代码运行过后，对Dagger2有了一个大概了解后，再去看进阶内容，会更理解其中的深意，任何东西入门是最难的，入门之后，再往深挖，会简单一些。

入门之后，就easy了。
开始正题

本文要制作一盘美味的水果沙拉Salad。
Salad需要什么原料呢？
需要梨（Pear）、香蕉（Banana）和沙拉酱 （SaladSauce）
我们就说Salad依赖Pear、Banana、 SaladSauce

我们先按照一般的方式做这盘水果沙拉

首先定义好三个类、分别是Pear、Banana、SaladSauce
定义Salad类，Salad类有一个方法叫做makeSalad（Pear，Banana， SaladSauce），这个方法需要传入三个参数，分别是Pear、Banana、 SaladSauce。
所以此时必须在Salad类内部创建出Pear、Banana、 SaladSauce的对象，然后传参调用makeSalad（Pear，Banana， SaladSauce）方法来制作好沙拉
public class Pear {
    public Pear(){
        Log.e("TAG", "我是一个梨");

    }
}

public class Banana {
    public Banana(){
        Log.e("TAG", "我是一个香蕉");

    }
}

public class SaladSauce {
    public SaladSauce(){
        Log.e("TAG", "我是沙拉酱");

    }
}

public class Salad {
    private Pear pear;
    private Banana banana;
    private SaladSauce saladSauce;

    public Salad() {
//        这里new了三个水果对象（依赖）
        pear = new Pear();
        banana = new Banana();
        saladSauce = new SaladSauce();
        makeSalad(pear, banana, saladSauce);
    }

    private void makeSalad(Pear pear, Banana banana, SaladSauce saladSauce) {
        Log.e("TAG", "我在搅拌制作水果沙拉");
    }
}

ok，一盘水果沙拉做完了，使用了咱们最常使用的方法，看上去也没什么不妥的。

接下来我们把上面的代码，按照依赖注入的思想，使用Dagger2框架写一遍。

写完之后，你会觉得，使用Dagger2增加了很多代码，还不如用一般常规写法写呢，但是先不要急，这是因为这个例子展示的情形太简单了，还没有让你体会到依赖注入的强大，现在先不考虑这些，我们先学会怎么使用Dagger2，以后在慢慢体会他的好处。

好比是：乘法是加法的简便运算。如果我们把“1+1=2”用乘法优化为“1x2=2”，你是体验不到乘法的简便的。如果是10个1相加呢，你还用加法运算吗。
1. 首先是配置，引入Dagger2

project的build.gradle添加

dependencies {
     ... // 其他classpath
     classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8' //添加apt命令
 }

module的build.gradle添加

// 添加其他插件
apply plugin: 'com.neenbedankt.android-apt'//添加apt命令

dependencies {
    apt 'com.google.dagger:dagger-compiler:2.0.2' //指定注解处理器
    compile 'com.google.dagger:dagger:2.0.2'  //dagger公用api
    provided 'org.glassfish:javax.annotation:10.0-b28'  //添加android缺失的部分javax注解
}

2. 增加一个类SaladModule

首先声明：前面写的三个被依赖的类（Pear、Banana、 SaladSauce），不用做任何改动

下面要用到两个关键的注解@Module、@Provides
/**
 * Module管理所有的依赖
 * 就好比：你要做一个沙拉，需要（依赖）Pear/Banana/SaladSauce
 * 这里就把Pear/Banana/SaladSauce这三个被依赖的类管理起来
 * 方便你在之后获取Pear/Banana/SaladSauce的对象
 */
@Module
public class SaladModule {
    @Provides
    // 按照格式写就好了，
    // 返回值（被依赖的类类型）
    // 方法名（provideXxx必须以provide开头，后面随意）
    public Pear providePear(){
        return new Pear();//返回这个类的对象
    }
    @Provides
    public Banana provideBanana(){
        return new Banana();
    }
    @Provides
    public SaladSauce provideSaladSauce(){
        return new SaladSauce();
    }
}

3. 增加一个接口SaladComponent

下面要用到一个关键的注解@Component
@Component(modules = {SaladModule.class})//指明要在那些Module里寻找依赖
public interface SaladComponent {
    //注意：下面这三个方法，返回值必须是从上面指定的依赖库SaladModule.class中取得的对象
    //注意：而方法名不一致也行，但是方便阅读，建议一致，因为它主要是根据返回值类型来找依赖的
    //★注意：下面这三个方法也可以不写，但是如果要写，就按照这个格式来
    //但是当Component要被别的Component依赖时，
    //这里就必须写这个方法，不写代表不向别的Component暴露此依赖

    Pear providePear();

    Banana provideBanana();

    SaladSauce provideSaladSauce();


    //注意：下面的这个方法，表示要将以上的三个依赖注入到某个类中
    //这里我们把上面的三个依赖注入到Salad中
    //因为我们要做沙拉
    void inject(Salad salad);

}

4. 重写Salad类

下面要用到一个关键的注解@Inject
public class Salad {

    @Inject//表示注入这个依赖
    Pear pear;

    @Inject
    Banana banana;

    @Inject
    SaladSauce saladSauce;

    public Salad() {
        // DaggerSaladComponent编译时才会产生这个类，
        // 所以编译前这里报错不要着急(或者现在你先build一下)
        SaladComponent saladComponent = DaggerSaladComponent.create();
        saladComponent.inject(this);//将saladComponent所连接的SaladModule中管理的依赖注入本类中
        makeSalad(pear, banana, saladSauce);
    }

    private void makeSalad(Pear pear, Banana banana, SaladSauce saladSauce) {
        Log.e("TAG", "我在搅拌制作水果沙拉");
    }
}

5. 测试

08-31 15:53:46.117 24628-24628/com.demo.dagger2demo2 E/TAG: 我是一个梨
08-31 15:53:46.118 24628-24628/com.demo.dagger2demo2 E/TAG: 我是一个香蕉
08-31 15:53:46.118 24628-24628/com.demo.dagger2demo2 E/TAG: 我是沙拉酱
08-31 15:53:46.118 24628-24628/com.demo.dagger2demo2 E/TAG: 我在搅拌制作水果沙拉

4
到现在为止，Dagger2入门最简单的例子已经搞定了，简单总结一下：

使用@Module增加一个SaladModule类，用于管理所有依赖

a) 使用@Provides，为每一个依赖写一个provideXxx方法

 // 按照格式写就好了，
    // 返回值（被依赖的类类型）
    // 方法名（provideXxx必须以provide开头，后面随意）
    @Provides
    public Pear providePear(){
        return new Pear();//返回这个类的对象
    }

使用@Component增加一个SaladComponent接口

a) 指明要在那些Module里寻找依赖

@Component(modules = {SaladModule.class})//
1
1
b) 为所有的依赖添加一个方法

   //注意：下面这三个方法，返回值必须是从上面指定的依赖库SaladModule.class中取得的对象
    //注意：而方法名不一致也行，但是方便阅读，建议一致，因为它主要是根据返回值类型来找依赖的

    Pear providePear();

c) 提供一个供目标类使用的注入方法

  //注意：下面的这个方法，表示要将以上的三个依赖注入到某个类中
    //这里我们把上面的三个依赖注入到Salad中
    //因为我们要做沙拉
    void inject(Salad salad);

目标类使用依赖注入

a) 使用@Inject声明属性变量，表示注入这个依赖
b) 调用saladComponent的inject，注入依赖

 // DaggerSaladComponent编译时才会产生这个类，
        // 所以编译前这里报错不要着急(或者现在你先build一下)
        SaladComponent saladComponent = DaggerSaladComponent.create();
        saladComponent.inject(this);//将saladComponent所连接的SaladModule中管理的依赖注入本类中

4
或者调用下面方法，也可以

   DaggerSaladComponent.builder()
 .saladModule(new SaladModule())
 .build()
 .inject(this);

以上只是最最简单的一个例子，入门再说

接下来讲到：

被依赖的类，如果带有参数，怎么办
被依赖的类，如果有多个构造函数，Dagger2怎么区分（依赖迷失）
@Scope
@Singleton
参考文章

Android常用开源工具（1）-Dagger2入门（总结的相当到位）
Android：dagger2让你爱不释手-重点概念讲解、融合篇（解释概念绝对好文章）
解锁Dagger2使用姿势（结合例子，通俗易懂）