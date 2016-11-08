##Dagger2
###Daggger2是啥

>Dagger is a fully static, compile-time dependency injection framework for both Java and Android. It is an adaptation of an earlier versioncreated by Square and now maintained by Google. Dagger aims to address many of the development and performance issues that have plagued reflection-based solutions. More details can be found in this talk(slides) by +Gregory Kick


翻译:
Dagger是为Android和Java平台提供的一个完全静态的，在编译时进行依赖注入的框架，原来是由Square公司维护的然后现在把这堆东西扔给Google维护了。Dagger解决了基于反射带来的开发和性能上的问题（因为Dagger并没有用反射来做依赖注入）

###依赖注入

**就是目标类（目标类需要进行依赖初始化的类，下面都会用目标类一词来指代）中所依赖的其他的类的初始化过程，通过技术手段可以把其他的类的已经初始化好的实例自动注入到目标类中。**
![](http://i.imgur.com/9eeRhHb.png)

依赖注入是面向对象编程的一种设计模式，其目的是为了降低程序耦合，这个耦合就是类之间的依赖引起的。


举个例子：我们在写面向对象程序时，往往会用到组合，即在一个类中引用另一个类，从而可以调用引用的类的方法完成某些功能,就像下面这样.

    public class ClassA {
   
    ClassB b;
   
    public ClassA() {
    b = new ClassB();
    }
    
    public void do() {
   
    b.doSomething();
   
    }
    }
依赖注入的几种方式
* 通过接口注入

      interface ClassBInterface {
      void setB(ClassB b);
      }
    
      public class ClassA implements ClassBInterface {
      ClassB classB;
    
      @override
      void setB(ClassB b) {
      classB = b;
      }
      }
* 通过set方法注入

      public class ClassA {
      ClassB classB;
    
      public void setClassB(ClassB b) {
      classB = b;
      }
      }
* 通过构造方法注入

      public class ClassA {
      ClassB classB;
    
      public void ClassA(ClassB b) {
      classB = b;
      }
* 通过Java注解

      public class ClassA {
      //此时并不会完成注入，还需要依赖注入框架的支持，如RoboGuice,Dagger2
      @inject ClassB classB;
    
      ...
      public ClassA() {}
   在Dagger2中用的就是最后一种注入方式，通过注解的方式，将依赖注入到宿主类中
###注解
参考:
[深入理解Java：注解（Annotation）自定义注解入门](http://www.cnblogs.com/peida/archive/2013/04/24/3036689.html)
[Java 注解 Dependency injection](http://www.jianshu.com/p/9b7982eb063f)
「[深入Java」Annotation注解](http://www.jianshu.com/p/82093e5160ae)
###dagger2的使用

* 引入Dagger2

首先，我们需要将Dagger2的依赖写入我们的gradle中，具体配置如下：

    apply plugin: 'com.neenbedankt.android-apt'
    
    buildscript {
    repositories {
    jcenter()
    }
    dependencies {
    classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
    }
    }
    
    dependencies {
    apt 'com.google.dagger:dagger-compiler:2.0'
    compile 'com.google.dagger:dagger:2.0'
    }
    
把 apply plugin和dependencies放在app的gradle里，把buildscript放在项目的gradle里即可，之后我们就可以开始Dagger之旅了。

####注解的使用

看过Dagger2的人应该都知道，Dagger2是通过注解来实现依赖注入的，所以，在使用Dagger2之前，我们需要了解这些注解的含义，如果对注解是什么还不清楚的同学可以Google一下，在这就不细说了。Dagger2中主要有6种注解，我们把它拆为4+2，
前四种通俗易懂，后两种理解起来就有一定难度了。

#####四个基础

这里说的四个基础，指的是四种基础的注解，他们分别是： 

- @Inject Inject主要有两个作用，一个是使用在构造函数上，通过标记构造函数让Dagger2来使用（Dagger2通过Inject标记可以在需要这个类实例的时候来找到这个构造函数并把相关实例new出来）从而提供依赖，另一个作用就是标记在需要依赖的变量让Dagger2为其提供依赖。

- @Provide 用Provide来标注一个方法，该方法可以在需要提供依赖时被调用，从而把预先提供好的对象当做依赖给标注了@Injection的变量赋值。provide主要用于标注Module里的方法

- @Module 用Module标注的类是专门用来提供依赖的。有的人可能有些疑惑，看了上面的@Inject，需要在构造函数上标记才能提供依赖，那么如果我们需要提供的类构造函数无法修改怎么办，比如一些jar包里的类，我们无法修改源码。这时候就需要使用Module了。Module可以给不能修改源码的类提供依赖，当然，能用Inject标注的通过Module也可以提供依赖

- @Component Component一般用来标注接口，被标注了Component的接口在编译时会产生相应的类的实例来作为提供依赖方和需要依赖方之间的桥梁，把相关依赖注入到其中。

这些标注看起来可能比较抽像，为了方便各位理解，送图一张来说明这些标注的作用和之间的关系：

![](http://i.imgur.com/R7ZzbIE.jpg)

图片主要分为三部分，左边的是依赖提供者，比如我们用Module标注的类或者用Injection标注的构造函数，右边的是依赖的需求方，例如我们用inject标注的变量，而Component则是连接两者的桥梁，Component从依赖提供者提供依赖，并把这些依赖注入相关的类中，Dagge正如其名，就像把匕首让依赖能够非常犀利的注入到需要它的地方。

说了那么多前言，虽然这些注解都有各自独特的作用，单用起来其实很简单，接下来我们将进一步地讲解这些标注的作用，just show you code。在使用之前，只要大致明白这些标注的意义就行了，简单的依赖注入通过这几个标注就能完成。
#####例子
制作一盘美味的水果沙拉Salad。 
Salad需要什么原料呢？ 
需要梨（Pear）、香蕉（Banana）和沙拉酱 （SaladSauce） 
我们就说Salad依赖Pear、Banana、 SaladSauce
1. 三个依赖类
```
public class Pear {
    private static final String TAG = "Pear";
    public Pear() {
       // Log.d(TAG, "Pear() called");
        System.out.println("这是Pear");
    }
}
```
```
public class Banana {
    private String from;
    public Banana() {
       System.out.println("这是Banana");
    }
    public Banana(String from) {
        this.from = from;
        System.out.println("这是产自" + from + "的香蕉");
    }
}
```
```
public class SaladSacue {
  
    public SaladSacue() {
    }
    @Inject
    public SaladSacue(@Named("taste") String string) {
        System.out.println("这是" + string + "SaladSacue");

    }
}
```
2. SaladModule  
```
@Module
public class SaladModule {
    private String from;
    // 按照格式写就好了，
    // 返回值（被依赖的类类型）
    // 方法名（provideXxx必须以provide开头，后面随意）
    private String taste;
    @Provides
    public Pear providerPer() {
        return new Pear();
    }
    @Provides
    public String providerString() {
        return from;
    }

    @Named("taste")
    @Provides
    public String providerStringtaste() {
        return taste;
    }

    public SaladModule() {
    }

    public SaladModule(String from, String taste) {
        this.from = from;
        this.taste = taste;
    }

    @Provides
    public Banana providerBananaFrom(String from) {
        return new Banana(from);
    }
```
saladModule相当于工厂这里实例化了依赖类,Module管理所有的依赖就好比：你要做一个沙拉，需要（依赖）Pear/Banana/SaladSauce
这里就把Pear/Banana/SaladSauce这三个被依赖的类管理起来,方便你在之后获取Pear/Banana/SaladSauce的对象

3. 增加一个接口SaladComponent
```
    @Component(modules = {SaladModule.class})//指明要在那些Module里寻找依赖
    public interface  SaladComponent {
    
    //注意：下面这三个方法，返回值必须是从上面指定的依赖库SaladModule.class中取得的对象
    //注意：而方法名不一致也行，但是方便阅读，建议一致，因为它主要是根据返回值类型来找依赖的
    //★注意：下面这三个方法也可以不写，但是如果要写，就按照这个格式来
    //但是当Component要被别的Component依赖时，
    //这里就必须写这个方法，不写代表不向别的Component暴露此依赖
    //Pear providePear();
    //Banana ProvideBanana();
    //SaladSacue provideSaladSauce();
    //注意：下面的这个方法，表示要将以上的三个依赖注入到某个类中
    //这里我们把上面的三个依赖注入到Salad中
    //因为我们要做沙拉
    void inject(Salad salad);
    }
```
4. 目标类 salad
```
public class Salad {
    @Inject
    Pear pear;
    @Inject
    Banana banana;
    @Inject
    SaladSacue saladSacue;
    public Salad() {
        // DaggerSaladComponent.create().inject(this);
        DaggerSaladComponent.builder().saladModule(new SaladModule("菲律宾","苦的")).build().inject(this);
        makeSalad(banana, pear, saladSacue);
    }
    private void makeSalad(Banana banana, Pear pear, SaladSacue saladSacue) {
        System.out.println("制造完成");
    }
    public static void main(String[] args) {
        new Salad();
    }
}
```
> 注意: DaggerSaladComponent是编译后有gradle插件[apt](http://www.jianshu.com/p/2494825183c5)生成的类路径:app/build/generated/source/apt文件夹下

**查找过程**
- 步骤1：查找Module中是否存在创建该类的方法。 
- 步骤2：若存在创建类方法，查看该方法是否存在参数 
 - 步骤2.1：若存在参数，则按从步骤1开始依次初始化每个参数 
 - 步骤2.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束 
- 步骤3：若不存在创建类方法，则查找Inject注解的构造函数，看构造函数是否存在参数
 - 步骤3.1：若存在参数，则从步骤1开始依次初始化每个参数
 - 步骤3.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束
