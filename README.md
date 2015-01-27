#Bee
Debug/QA tool

- Configure the application on the fly
- Add button, spinner and checkbox options regarding to your needs
- Show the predefined information such as version number, android version, package name. Add some other custom information such as current end point
- Show some usefull information to remind the user such as username, passwords for test
- Show the log for the important events, ie: An event is triggered and you want to see whether it is triggered or not

<img src='https://github.com/nr4bt/bee/blob/master/images/bee0.png' width='120' height='180'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee1.png' width='120' height='180'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee2.png' width='120' height='180'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee3.png' width='120' height='180'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee4.png' width='120' height='180'></img>

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
     * Use Button annotation to add button to the settings menu.
     * Title is used for button text. 
     * Method should have no parameter.
     */
    @Title("Reset")
    @Button
    public void onResetClicked() {
        Log.d(TAG, "onResetClicked");
    }
    
    /**
     * Use CheckBox annotation to add checkbox to the settings menu.
     * Title is used for the label. 
     * Method should have a boolean parameter 
     */
    @Title("Show splash screen")
    @CheckBox
    public void onShowSplashChecked(boolean isChecked) {
        Log.d(TAG, "onShowSplashChecked");
    }

    /**
     * Use Spinner annotation to add spinner to the settings menu.
     * Spinner annotation gets the content of spinner, either String[] or just String
     * Title is used for the label.
     * Method should have String parameter
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

#### Use BeeLog in order to show the log in the bee

```java
BeeLog.d(TAG,"Some event triggered");
```

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
