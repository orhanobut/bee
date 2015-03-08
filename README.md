[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Bee-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1441)    [![API](https://img.shields.io/badge/API-10%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=10) [![Join the chat at https://gitter.im/orhanobut/bee](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/orhanobut/bee?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

#Bee
QA/Debug Tool <br><br>
<img src='https://github.com/orhanobut/bee/blob/master/images/bee_yellow.png' width='128' height='128'></img>

#### What is Bee?
Bee is a QA/Debug tool that works like a widget in any application. The idea is to add some features/options to developer and QA to configure the app, get some information. Bee works like an extra view when the user presses the bee icon. You can do the following operations :

- Configure the application on the fly
- Add button, spinner and checkbox options regarding to your needs
- Show the predefined information such as version number, android version, package name. Add some other custom information such as current end point
- Show some usefull information to remind the user such as username, passwords for test
- Show the log for the important events, ie: An event is triggered and you want to see whether it is triggered or not

#### What Bee is not?
- Bee is not an application
- Bee is not for the release apk

<img src='https://github.com/nr4bt/bee/blob/master/images/bee0.png' width='140' height='200'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee3.png' width='140' height='200'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee1.png' width='140' height='200'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee2.png' width='140' height='200'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee4.png' width='140' height='200'></img>

#### Gradle

```groovy
repositories {
    maven { url "https://oss.sonatype.org/content/repositories/snapshots/"}
}
dependencies {
    compile 'com.orhanobut:bee:1.1-SNAPSHOT@aar'
}
```

#### Usage
Extend BeeConfig class and add the functionalities as you needed.
```java
public class SampleBeeConfig extends BeeConfig {

    /**
     * Add extra information by using content object.
     */
    @Override
    public void onInfoContentCreated(Map<String, String> content) {
        content.put("Current End Point", "http://www.google.com");
    }

    /**
     * Add information to the clipboard by using content object.
     */
    @Override
    public void onClipboardContentCreated(Map<String, String> content) {
        content.put("User1", "324234234");
        content.put("Visa Expire Date", "2/16");
        content.put("Visa Code", "34");
    }

    /**
     * It is called when the save button is pressed
     */
    @Override
    public void onSave() {
        super.onSave();
    }

    /**
     * It is called when the close button is pressed 
     */
    @Override
    public void onClose() {
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
@Override
protected void onCreate(Bundle savedInstanceState) {
    ...

    Bee.inject(this, SampleBeeConfig.class);
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

#### You might also like
- [Hawk](https://github.com/orhanobut/hawk) Secure simple key-value storage
- [Wasp](https://github.com/orhanobut/wasp) All-in-one network solution
- [DialogPlus](https://github.com/orhanobut/dialogplus) Easy,simple dialog solution
- [SimpleListView](https://github.com/orhanobut/simplelistview) Simple basic listview implementation with linearlayout

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
