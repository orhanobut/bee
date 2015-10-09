[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Bee-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1441)    [![API](https://img.shields.io/badge/API-10%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=10) [![Join the chat at https://gitter.im/orhanobut/bee](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/orhanobut/bee?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![](https://img.shields.io/badge/AndroidWeekly-%23143-blue.svg)](http://androidweekly.net/issues/issue-143)

#Bee
QA/Debug Tool <br><br>
<img src='https://github.com/orhanobut/bee/blob/master/images/bee_yellow.png' width='128' height='128'></img>

#### What is Bee?
Bee is a QA/Debug tool that works like a widget in any application. The idea is to add some features/options to developer and QA to configure the app, get some information. Bee works like an extra view when the user presses the bee icon. You can do the following operations :

- Configure the application on the fly
- Add button, spinner and checkbox options regarding to your needs
- Show the predefined information such as version number, android version, package name. Add some other custom information such as current end point
- Show the log for the important events, ie: An event is triggered and you want to see whether it is triggered or not

#### What Bee is not?
- Bee is not an application
- Bee is not for the release apk

<img src='https://github.com/nr4bt/bee/blob/master/images/bee0.png' width='140' height='200'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee_settings.png' width='140' height='200'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee_info.png' width='140' height='200'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee_log.png' width='140' height='200'></img>

#### Dependency

https://jitpack.io/#orhanobut/bee/1.5
```groovy
repositories {
  // ...
  maven { url "https://jitpack.io" }
}

dependencies {
  compile 'com.github.orhanobut:bee:1.5'
}
```

#### Usage
Extend BeeConfig class and add the functionalities as you needed.
```java
public class SampleBeeConfig extends BeeConfig {

  /**
  * Add extra information by using content object.
  */
  @Override public void onInfoContentCreated(Map<String, String> content) {
    content.put("Current End Point", "http://www.google.com");
  }

  /**
  * It is called when the close button is pressed 
  */
  @Override public void onClose() {
    super.onClose();
  }
    
  /**
  * A sample button implementation
  */
  @Title("Reset")
  @Button
  public void onResetClicked() {
    Log.d(TAG, "onResetClicked");
  }
    
  /**
  * A sample checkbox implementation
  */
  @Title("Show splash screen")
  @CheckBox
  public void onShowSplashChecked(boolean isChecked) {
    Log.d(TAG, "onShowSplashChecked");
  }

  /**
  * A sample spinner implementation
  */
  @Title("End Point")
  @Spinner({"Staging", "Live", "Mock"})
  public void onEndPointSelected(String selectedValue) {
    Log.d(TAG, "onEndPointSelected");
  }
}
```

#### Start Bee
In order to activate Bee, you need to pass activity as context. You can either initialize it in base activity and show in all activities or you can just initialize it in specific activities. 

```java
@Override protected void onCreate(Bundle savedInstanceState) {
    ...

  Bee.init(this)
    .setBeeSize(100)                 //optional bee button size
    .setBeePosition(Gravity.CENTER)  //optional bee button position
    .setBeeMargin(0, 0, 0, 400)      //optional margin for the bee button
    .inject(SampleBeeConfig.class);  //required
}
```

#### Add buttons to the settings menu
- Use Button annotation to create a new button in the settings menu.
- Use Title annotation to put a text for button
- Use nullary method (no parameter should be added) signature.
- Add as many button as you need. All methods will be called individually.
```java
    @Title("Reset")
    @Button
    public void onResetClicked() {
        Log.d(TAG, "onResetClicked");
    }
```

#### Add checkbox to the settings menu
- Use CheckBox annotation to create a new checkbox
- Use Title annotation to put a label
- Use following method signature. Only one parameter should be added and it should be boolean
- Add as many checkbox as you need. All methods will be called individually.
```java
    @Title("Show splash screen")
    @CheckBox
    public void onShowSplashChecked(boolean isChecked) {
        Log.d(TAG, "onShowSplashChecked");
    }
```

#### Add dropdown(Spinner) to the settings menu
- Use Spinner annotation to create a new spinner
- Use Title annotation to put a label
- Use following method signature. Only one parameter should be added and it should be String
- Add as many dropdown as you need. All methods will be called individually and it will return the selected value
```java
    @Title("End Point")
    @Spinner({"Staging", "Live", "Mock"})
    public void onEndPointSelected(String selectedValue) {
        Log.d(TAG, "onEndPointSelected");
    }
```

#### Use BeeLog in order to show the log in the bee

```java
BeeLog.d(TAG,"Some event triggered");
```

#### Add more options to the settings.

- Just create a method and use annotation and provide the signature.
- You can add as many as options you want. All methods will be called individually.

#### More

- Long click to a list item will copy the value to the clipboard.

#### License 
<pre>
Copyright 2015 Orhan Obut

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>
