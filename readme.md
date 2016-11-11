# Dagger2


## Daggger2是啥

>Dagger is a fully static, compile-time dependency injection framework for both Java and Android. It is an adaptation of an earlier versioncreated by Square and now maintained by Google. Dagger aims to address many of the development and performance issues that have plagued reflection-based solutions. More details can be found in this talk(slides) by +Gregory Kick


翻译:
Dagger是为Android和Java平台提供的一个完全静态的，在编译时进行依赖注入的框架，原来是由Square公司维护的然后现在把这堆东西扔给Google维护了。Dagger解决了基于反射带来的开发和性能上的问题（因为Dagger并没有用反射来做依赖注入）

### 依赖注入

**就是==目标类==（目标类需要进行依赖初始化的类，下面都会用目标类一词来指代）中所依赖的其他的类的初始化过程，通过技术手段可以把其他的类的已经初始化好的实例自动注入到目标类中。**
![](http://i.imgur.com/9eeRhHb.png)

依赖注入是面向对象编程的一种设计模式，其目的是为了降低程序耦合，这个耦合就是类之间的依赖引起的。


>举个例子：

我们在写面向对象程序时，往往会用到组合，即在一个类中引用另一个类，从而可以调用引用的类的方法完成某些功能,就像下面这样.

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

#### 1. 通过接口注入
 
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
#### 2.  通过set方法注入
  
        public class ClassA {
                ClassB classB;
        
                public void setClassB(ClassB b) {
                    classB = b;
                }
            }
#### 3.  通过构造方法注入

         public class ClassA {
                ClassB classB;
        
                public void ClassA(ClassB b) {
                    classB = b;
                }

#### 4.  通过Java注解
  

    public class ClassA {
    //此时并不会完成注入，还需要依赖注入框架的支持，如Dagger2
          @inject
          ClassB classB;
        }
        
   在Dagger2中用的就是最后一种注入方式，通过注解的方式，将依赖注入到==宿主类==中
   
#### 注解
参考:

[深入理解Java：注解（Annotation）自定义注解入门](http://www.cnblogs.com/peida/archive/2013/04/24/3036689.html)


[Java 注解 Dependency injection](http://www.jianshu.com/p/9b7982eb063f)

[深入Java Annotation注解](http://www.jianshu.com/p/82093e5160ae)
	
## dagger2的使用

### 引入Dagger2

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
   compile 'com.google.dagger:dagger:2.4'
    apt 'com.google.dagger:dagger-compiler:2.4'
    }
    
把 apply plugin和dependencies放在app的gradle里，把buildscript放在项目的gradle里即可，之后我们就可以开始Dagger之旅了。

### 注解的使用
看过Dagger2的人应该都知道，Dagger2是通过注解来实现依赖注入的，所以，在使用Dagger2之前，我们需要了解这些注解的含义，如果对注解是什么还不清楚的同学可以Google一下，在这就不细说了。Dagger2中主要有6种注解，我们把它拆为4+2，
前四种通俗易懂，后两种理解起来就有一定难度了。

#### 四个基础

这里说的四个基础，指的是四种基础的注解，他们分别是： 

- @Inject Inject主要有两个作用，一个是使用在构造函数上，通过标记构造函数让Dagger2来使用（Dagger2通过Inject标记可以在需要这个类实例的时候来找到这个构造函数并把相关实例new出来）从而提供依赖，另一个作用就是标记在需要依赖的变量让Dagger2为其提供依赖。

- @Provide 用Provide来标注一个方法，该方法可以在需要提供依赖时被调用，从而把预先提供好的对象当做依赖给标注了@Injection的变量赋值。provide主要用于标注Module里的方法

- @Module 用Module标注的类是专门用来提供依赖的。有的人可能有些疑惑，看了上面的@Inject，需要在构造函数上标记才能提供依赖，那么如果我们需要提供的类构造函数无法修改怎么办，比如一些jar包里的类，我们无法修改源码。这时候就需要使用Module了。Module可以给不能修改源码的类提供依赖，当然，能用Inject标注的通过Module也可以提供依赖

- @Component Component一般用来标注接口，被标注了Component的接口在编译时会产生相应的类的实例来作为提供依赖方和需要依赖方之间的桥梁，把相关依赖注入到其中。

这些标注看起来可能比较抽像，为了方便各位理解，送图一张来说明这些标注的作用和之间的关系：

![](http://i.imgur.com/R7ZzbIE.jpg)

图片主要分为三部分，左边的是依赖提供者，比如我们用Module标注的类或者用Injection标注的构造函数，右边的是依赖的需求方，例如我们用inject标注的变量，而Component则是连接两者的桥梁，Component从依赖提供者提供依赖，并把这些依赖注入相关的类中，Dagge正如其名，就像把匕首让依赖能够非常犀利的注入到需要它的地方。

说了那么多前言，虽然这些注解都有各自独特的作用，单用起来其实很简单，接下来我们将进一步地讲解这些标注的作用，just show you code。在使用之前，只要大致明白这些标注的意义就行了，简单的依赖注入通过这几个标注就能完成。
##### 例子
组装一台电脑。 
电脑需要什么原料呢？ 
需要显示器(Display)、键盘(keyboard)、主机(Master),也就是依赖
1. 三个依赖类

        public class Display {
            private static final String TAG = "Display";
            public Display() {
               // Log.d(TAG, "Display() called");
                System.out.println("这是个显示器");
            }
        }
    
    
    
        public class keyboard {
            private static final String TAG = "keyboard";
        
            private String from;
        
            public keyboard() {
        
                System.out.println("这是键盘");
            }
        
            public keyboard(String from) {
                this.from = from;
                System.out.println("这是产自" + from + "的键盘");
            }
            
        }
        
        

        public class Master {
            public Master() {
            }
            @Inject
            public Master(@Named("taste") String string) {
                System.out.println("这是" + string + "Master");
            }
        }
2. ComputerModule 管理原料的module  

        @Module
        public class ComputerModule {
        
            private String from;
        
            private String taste;
        
            @Provides
            public Display providerPer() {
                return new Display();
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
        
            public ComputerModule() {
            }
        
            public ComputerModule(String from, String taste) {
                this.from = from;
                this.taste = taste;
            }
        
            @Provides
            public keyboard providerBananaFrom(String from) {
                return new keyboard(from);
            }
        }
        
        
ComputerdModule相当于工厂这里实例化了==依赖类==,Module管理所有的依赖就好比：你要组装一台电脑需要键盘,显示器,主机原料,module生产并管理这些原料,给其他对象使用


3. 增加一个接口ComputerComponent


         @Component(modules = {ComputerModule.class})//指明要在那些Module里寻找依赖
        public interface ComputerComponent {
            //注意：下面这三个方法，返回值必须是从上面指定的依赖库SaladModule.class中取得的对象
            //注意：而方法名不一致也行，但是方便阅读，建议一致，因为它主要是根据返回值类型来找依赖的
            //★注意：下面这三个方法也可以不写，但是如果要写，就按照这个格式来
            //但是当Component要被别的Component依赖时，
            //这里就必须写这个方法，不写代表不向别的Component暴露此依赖
            Display sprovideDisplay();
        
            keyboard ProvidekeyBoard();
        
            Master provideMaster();
            //注意：下面的这个方法，表示要将以上的三个依赖注入到某个类中
            //这里我们把上面的三个依赖注入到Computer中
            //因为我们要做沙拉
            void inject(Computer computer);
        }



ComputerComponent就像工厂管理员,高度目标类我有那些原料.并把这些原料运送给==目标类==.
4. 目标类 Computer


        public class Computer {
            @Inject
            Display display;
            @Inject
            keyboard keyboard;
            @Inject
            Master master;
            public Computer() {
                // DaggerComputerComponent.create().inject(this);
                DaggerComputerComponent.builder().computerModule(new ComputerModule("中国","联想")).build().inject(this);
                makeComputer(keyboard, display, master);
            }
            private void makeComputer(keyboard keyboard, Display display, Master master) {
                System.out.println("制造完成");
            }
            public static void main(String[] args) {
                new Computer();
            }
        }


目标类就是要成品电脑,在这组装完成.
> **注意: DaggerComputerComponent是编译后有gradle插件[apt](http://www.jianshu.com/p/2494825183c5)生成的类路径:app/build/generated/source/apt文件夹下,其中若coputerModule(new conputerModule()),如果没有参数的话可以直接DaggerComputerComponent.create().inject(this);**

#####  查找过程
- 步骤1：查找Module中是否存在创建该类的方法。 
- 步骤2：若存在创建类方法，查看该方法是否存在参数 
    - 步骤2.1：若存在参数，则按从步骤1开始依次初始化每个参数 
    - 步骤2.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束 
- 步骤3：若不存在创建类方法，则查找Inject注解的构造函数，看构造函数是否存在参数
    - 步骤3.1：若存在参数，则从步骤1开始依次初始化每个参数
    - 步骤3.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束

#####  原理分析
咱们从上面知道SaladComponent是个接口,具体实现是有apt生成的类DaggerSaladComponent来实现.
关键类: **DaggerSaladComponent**

**其中变量有哪些？**

    private Provider<Display> providerDisplayProvider;
    
      private Provider<String> providerStringProvider;
    
      private Provider<keyboard> providerkeyboardFromProvider;
    
      private Provider<String> providerStringtasteProvider;
    
      private Provider<Master> masterProvider;
    
      private MembersInjector<Computer> computerMembersInjector;//这个是注入类也就是inject(this)的时候调用的对象


**Provider<T>是什么呢**


public interface Provider<T> {

        /**
         * Provides a fully-constructed and injected instance of {@code T}.
         *
         * @throws RuntimeException if the injector encounters an error while
         *  providing an instance. For example, if an injectable member on
         *  {@code T} throws an exception, the injector may wrap the exception
         *  and throw it to the caller of {@code get()}. Callers should not try
         *  to handle such exceptions as the behavior may vary across injector
         *  implementations and even different configurations of the same injector.
         */
        T get();
    }


由上面可以看出Modul中注解了Provider的方法都会生成 Provider<T> 的变量对象,那它们是在哪里初始化的呢,还记得我们怎么获得DaggerComputerComponent吗?

***初始化***

DaggerComputerComponent.builder().computerModule(new ComputerModule("中国","联想")).build().inject(this); 

其中 DaggerSaladComponent.builder().saladModule(new SaladModule("菲律宾","苦的")).build()会调用以下


     @SuppressWarnings("unchecked")
      private void initialize(final Builder builder) {
    
        this.providerDisplayProvider =
            ComputerModule_ProviderDisplayFactory.create(builder.computerModule);
    
        this.providerStringProvider =
            ComputerModule_ProviderStringFactory.create(builder.computerModule);
    
        this.providerkeyboardFromProvider =
            ComputerModule_ProviderkeyboardFromFactory.create(
                builder.computerModule, providerStringProvider);
    
        this.providerStringtasteProvider =
            ComputerModule_ProviderStringtasteFactory.create(builder.computerModule);
    
        this.masterProvider = Master_Factory.create(providerStringtasteProvider);
    
        this.computerMembersInjector =
            Computer_MembersInjector.create(
                providerDisplayProvider, providerkeyboardFromProvider, masterProvider);
      }
  
此时我们发现每个provider都是有一个==Factory==,Create出来,我们拿 **ComputerModule_ProviderDisplayFactory**为例,看源码



        public final class ComputerModule_ProviderDisplayFactory implements Factory<Display> {
          private final ComputerModule module;
        
          public ComputerModule_ProviderDisplayFactory(ComputerModule module) {
            assert module != null;
            this.module = module;
          }
        
          @Override
          public Display get() {
            return Preconditions.checkNotNull(
                module.providerDisplay(), "Cannot return null from a non-@Nullable @Provides method");
          }
        
          public static Factory<Display> create(ComputerModule module) {
            return new ComputerModule_ProviderDisplayFactory(module);
          }
        }



此时我们发现该对象里面有个ComputerModule对象,我们又发现里面有个get方法是Factory<Display>的==父类方法==


        public interface Factory<T> extends Provider<T> {
        }
        public interface Provider<T> {
        
            /**
             * Provides a fully-constructed and injected instance of {@code T}.
             *
             * @throws RuntimeException if the injector encounters an error while
             *  providing an instance. For example, if an injectable member on
             *  {@code T} throws an exception, the injector may wrap the exception
             *  and throw it to the caller of {@code get()}. Callers should not try
             *  to handle such exceptions as the behavior may vary across injector
             *  implementations and even different configurations of the same injector.
             */
            T get();
        }


**inject**

而Factory又是继承Provider,我们发现通过这个Provider我们可以拿T也就是Provider<Display>的Dispaly对象.那么他是怎么注入到Computer中的呢?
我们再看看DaggerComputerComponent的inject(this)方法

        
         @Override
          public void inject(Computer computer) {
            computerMembersInjector.injectMembers(computer);
          }
  
  
  
computerMembersInjector也就是我们刚开始看到的那个变量,从上面初始化也就是


        this.computerMembersInjector =
                Computer_MembersInjector.create(
                    providerDisplayProvider, providerkeyboardFromProvider, masterProvider);
            
            
传入了三个providerDisplayProvider, providerkeyboardFromProvider, masterProvider,那么Computer_MembersInjector是什么呢?


        public final class Computer_MembersInjector implements MembersInjector<Computer> {
          private final Provider<Display> displayProvider;
        
          private final Provider<keyboard> keyboardProvider;
        
          private final Provider<Master> masterProvider;
        
          public Computer_MembersInjector(
              Provider<Display> displayProvider,
              Provider<keyboard> keyboardProvider,
              Provider<Master> masterProvider) {
            assert displayProvider != null;
            this.displayProvider = displayProvider;
            assert keyboardProvider != null;
            this.keyboardProvider = keyboardProvider;
            assert masterProvider != null;
            this.masterProvider = masterProvider;
          }
        
          public static MembersInjector<Computer> create(
              Provider<Display> displayProvider,
              Provider<keyboard> keyboardProvider,
              Provider<Master> masterProvider) {
            return new Computer_MembersInjector(displayProvider, keyboardProvider, masterProvider);
          }
        
          @Override
          public void injectMembers(Computer instance) {
            if (instance == null) {
              throw new NullPointerException("Cannot inject members into a null reference");
            }
            instance.display = displayProvider.get();
            instance.keyboard = keyboardProvider.get();
            instance.master = masterProvider.get();
          }
        
          public static void injectDisplay(Computer instance, Provider<Display> displayProvider) {
            instance.display = displayProvider.get();
          }
        
          public static void injectKeyboard(Computer instance, Provider<keyboard> keyboardProvider) {
            instance.keyboard = keyboardProvider.get();
          }
        
          public static void injectMaster(Computer instance, Provider<Master> masterProvider) {
            instance.master = masterProvider.get();
          }
        }



也就是Computer_MembersInjector中有了这三个Provider,在调用方法injectMembers的时候通过provider的get方法拿到了Dispaly对象,然后又赋值了给instance.display,instance是什么鬼,从传入的参数我们大喜,发现这就是那个inject(this),也就是==目标类==,也就是完成了computer中的Dispay的初始化,但要注意因为是对象直接辅助所有在目标类注入的对象都不能写成private.原理也就讲完了.

### 两个难点@Qulifier和@Scope
上面把四个简单的注解的用法都讲完了，但很多时候这几个注解并不能涵盖我们所有的场景，这时就需要@Scope和@Qulifier来帮忙了。

#### @Qulifier

- @Qulifier
有的同学可能在用Module的时候会有疑惑，为什么方法怎么命名都行，那时怎么区分它为谁提供依赖呢。答案是根据返回类型来确定的，当某个对象需要注入依赖时，Dagger2就会根据Module中标记了@Provide的方法的返回值来确定由谁为这个变量提供实例。那问题来了，如果有两个一样的返回类型，该用谁呢。我们把这种场景叫做依赖迷失，见名知意，Dagger这时候就不知道用谁来提供依赖，自然就迷失了。所以我们引入了@Qulifier这个东西，通过自定义Qulifier，可以告诉Dagger2去需找具体的依赖提供者。
	- @Qualifier注解


        @Qualifier
        @Documented
        @Retention(RetentionPolicy.RUNTIME)
        public @interface Type {
            String value() default "";
        }




- 一个依赖类
	

	
        public class Mouse {
            private String color;
        
            public Mouse() {
        
                System.out.println("nomal ");
            }
        
            public Mouse(String color) {
                this.color = color;
                System.out.println("color" + color);
            }
        }
        
        
- module


        @Module
        public class ComputerTestModule {
            @Singleton
            @Type("nomal")
            @Provides
            public Mouse providerNomalApple() {
        
                return new Mouse();
            }
            @Type("color")
            @Provides
            public Mouse providerColorApple(String color) {
        
                return new Mouse(color);
            }
            //    由于我们的Apple构造函数里使用了String,所以这里要管理这个String(★否则报错)
            //    int等基本数据类型是不需要这样做的
            @Provides
            public String providerString() {
                return new String("red");
            }
        }


- component


        @Singleton
        @Component(modules = ComputerTestModule.class)
        public interface ComputerTestComponent {
        //    @Type("nomal")
        //    Mouse providerNonmalApple();
        //
        //    @Type("color")
        //    Mouse providerColorApple();
           // String providerString();
            ////注意：下面的这个方法，表示要将以上的三个依赖注入到某个类中
        //这里我们把上面的三个依赖注入到Salad中
            void inject(ComputerTest computerTest);
        }
        
        
- 目标类
 

        public class ComputerTest {
            @Inject
            @Type("nomal")
            Mouse nomalMouse;
        
            @Type("nomal")
            @Inject
            Mouse nomalMouse2;
        
            @Type("color")
            @Inject
            Mouse colorMouse;
        
            public ComputerTest() {
                ComputerTestComponent computerTestComponent = DaggerComputerTestComponent.create();
                computerTestComponent.inject(this);
                System.out.println(nomalMouse.hashCode()+"_______"+ nomalMouse2.hashCode());
            }
        
            public static void main(String[] args) {
                new ComputerTest();
            }
        }
        
        
因为在moudle中进行查找的时候是根据返回值进行查找,所以通过type我们可以区分不同的方法,也可以通过@name进行判别也可以在参数中判别详见电脑组装的Master
@name的api其是有@Qualifier注解


        @Qualifier
        @Documented
        @Retention(RUNTIME)
        public @interface Named {
            /** The name. */
            String value() default "";
        }



#### @scope

现在有这样的一个场景,tom和jason住在同一个房子里面,该怎么做呢,这时候就需要自定义注解scope


        @Scope
        @Retention(RetentionPolicy.RUNTIME)
        public @ interface HouseScope {
        }


- 依赖类


        public class House {
            public House() {
                System.out.print("这是个房子");
            }
        }


- module


        @Module
        public class HouseModule {
        
            /**
             * 指定房子的使用范围
             *
             * @return
             */
            @HouseScope
            @Provides
            public House providerHouse() {
                return new House();
            }
        }


- component

	
        @HouseScope
        @Component(modules = {HouseModule.class})
        public interface HouseComponent {
            void inject(Tom tom);
        
            void inject(Jason jason);
        }


- 目标类



        public class Jason {
            @Inject
            House house;
            public Jason() {
                App.getHouseComponent().inject(this);
                System.out.println("Jason"+house.hashCode());
            }
        }



        public class Tom {
            @Inject
            House house;
            public Tom() {
                App.getHouseComponent().inject(this);
                System.out.println("Tom" + house.hashCode());
            }
        }
- Applicaiton
    

    public class App extends Application {
    
        private static HouseComponent houseComponent;
    
        @Override
        public void onCreate() {
    
            houseComponent = DaggerHouseComponent.builder().houseModule(new HouseModule()).build();
            super.onCreate();
        }
    
        public static HouseComponent getHouseComponent() {
            return houseComponent;
        }
    }

没什么要用Application,因为他是单例的,而Tom和Jason只能用一个DaggerHouseComponent,才可能找到同一个对象House,通过在module加注解制定了这个House的使用对象必须是加了@House的HouseComponent.
结果:

11-10 00:28:06.353 24783-24783/com.example.vwenjutian.learningtest I/System.out: 这是个房子Jason1128505112
11-10 00:28:06.353 24783-24783/com.example.vwenjutian.learningtest I/System.out: Tom1128505112
初始化一次说明是同一个对象 ,也就是他们住的是同一个房间

>参考 

[解锁Dagger2使用姿势（二） 之带你理解@Scope](http://blog.csdn.net/u012702547/article/details/52213706)


### component依赖
如果我们有一套做好的沙拉体系（一套齐全的依赖体系，Module、Component），另外一个类需要这套依赖体系的一个对象作为依赖，怎么办，还需要再为这个对象，建立一套新的Module和Component吗
**显然是不用的，Component之间是可以依赖的**


- 依赖类
```
public class Audio {

    public Audio() {
        System.out.println("这是个音响");
    }
}
```
- module
```
@Module
public class AudioModule {
    @Provides
    public Audio providerTomato() {
        return new Audio();
    }
}
```
- component
```
@Component(modules = AudioModule.class, dependencies = {ComputerComponent.class})
public interface AudioComponent {
    /**
     * 此处的方法可以不写.写了是为了暴露对象 给子依赖
     *
     * @return
     */
    public Audio providerTomato();

    /**
     * 是否想注入那个对象中,如果不想注入的话可以不写
     * @param computer2
     */
      void inject(Computer2 computer2);
}
```
- 目标类
```
public class Computer2 {

    @Inject
    Audio audio;
    @Inject
    keyboard keyboard;
    @Inject
    Display display;
    @Inject
    Master master;

    public Computer2() {
        ComputerComponent component =  DaggerComputerComponent.builder().computerModule(new ComputerModule("中国","台达")).build();
        DaggerAudioComponent.builder().computerComponent(component).audioModule(new AudioModule()).build().inject(this);

    }

    public static void main(String[] args) {
        new Computer2();
    }
}

```
源码传送 https://github.com/tianwenju/LearningTest