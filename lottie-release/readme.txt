动画库  负责人 彭汉迎
参考 http://www.jianshu.com/p/cae606f45c0b

Lottie是Airbnb开源的一个支持 Android、iOS 以及 ReactNative，利用json文件的方式快速实现动画效果的库。这么看可能很难理解，接下来我将详细的讲解如何使用。

假设我们要做一个缓冲数据时的一个loading动画，不用Lottie之前你们公司的美工一般都会给一个gif动画效果和一些切好的一帧一帧的图片。现在不需要这么操作了，美工直接输出json资源
我们在代码中直接使用json资源即可

参考项目中已经实现的动画模块 ，app-chery-lion项目中实现的麦克风动画
资源文件：app-chery-lion\src\main\assets
代码：DureOsMicAnimImageView