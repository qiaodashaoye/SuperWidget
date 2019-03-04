# SuperWidget

包含注解式检查是否联网、注解式检查登陆、注解式按钮防抖和一系列常用控件的封装

- 项目地址：[https://github.com/qiaodashaoye/SuperWidget](https://github.com/qiaodashaoye/SuperWidget)

- 项目依赖：`implementation 'com.qpg.widget:superwidget:1.0.0'`


### 用法实例：

第一步需要在application中进行全局初始化以及添加全局相关配置，具体使用如下：
> 简单初始化
```
SuperWidget.getInstance().init(this);
        
```
> 如果使用本库中的登陆检测注解，需用以下配置
```
  SuperWidget.getInstance().init(this, new ILogin() {
              @Override
              public void login(Context context, int actionDefine) {
                  switch (actionDefine) {
                      case 0:
                      //    context.startActivity(new Intent(context,LoginActivity.class));
                          break;
                      case 1:
                          ToastUtil.showCustomToast("请登录后操作");
                          break;
                  }
              }
  
              @Override
              public boolean isLogin(Context context) {
                  if(false){
                      return true;
                  }
                  return false;
              }
          });

```
#### 网络判断注解

在需要网络检测的方法上添加@CheckNetTrace即可
```
  @CheckNetTrace
  public void getUserInfo(){
  
  }
```

#### 按钮防抖注解

在按钮点击所要执行的方法上添加@SingleClickTrace，默认防抖时间是500毫秒，若要自定义时间应@SingleClickTrace(1000)
或@SingleClickTrace(value=1000)
```
  @SingleClickTrace
  public void register(){
  
  }
```

#### 登陆检测注解

在按钮点击所要执行的方法上添加@CheckLoginTrace，登陆检测注解可以自定义事件，具体用法可看demo或添加群进行交流

```
  @CheckLoginTrace
  public void jumpToLike(){
  
  }
```


## 交流方式
 * QQ: 1451449315
 * QQ群: 122619758
 
 ## Licenses
 ```
  Copyright 2017 qpg
 
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 ```
