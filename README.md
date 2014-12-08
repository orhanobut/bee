##Introduction
Bee is a debug tool for developers and QA. You can do the following operations 
<ul>
<li>Configure the application while the app is running, ie: Changing endpoint</li>
<li>Show the predefined information such as version number, android version, package name. You can also add some other custom information such as current end point</li>
<li>You can show some usefull information to remind the user such as username,passwords for test</li>
<li>You can show the log for the important events, ie: An event is triggered and you want to see whether it is triggered or not</li>
</ul>

<img src='https://github.com/nr4bt/bee/blob/master/images/bee1.png' width='140' height='180'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee2.png' width='140' height='180'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee3.png' width='140' height='180'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee4.png' width='140' height='180'></img>
<img src='https://github.com/nr4bt/bee/blob/master/images/bee5.png' width='140' height='180'></img>

##Setup
####1. Add dependency

<pre>
dependencies {
    compile 'com.github.nr4bt:bee:1.0.0-SNAPSHOT@aar'
}
</pre>

####2. Extend BeeConfig class and customize it

<pre>
public class SampleBeeConfig extends BeeConfig {
}
</pre>

####3. initialize Bee
In order to activate Bee, you need to pass activity as context. You can either initialize it in base activity and show in all activities or you can just initialize it in specific activities. 

<pre>
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    new SampleBeeConfig().inject(this);
}
</pre>

####4. Use BeeLog in order to show the log in the bee

<pre>
BeeLog.d(TAG,"Some event triggered");
</pre>

####BeeConfig guide

Add configuration settings. Note: You can only add spinner and checkbox for now. Most important part is you need to put unique request code for each item. You can put as many item as you want. 

<pre>
public class SampleBeeConfig extends BeeConfig {
    private static final int ENDPOINT = 0;
    private static final int LOG = 1;
    
    @Override
    public void onMenuContentCreated(Bee.Builder builder) {
        super.onMenuContentCreated(builder);
        builder.addSpinner("End Point Url", new String[]{"http://www.google.com"}, ENDPOINT)
                .addCheckbox("Show logs", LOG);
    }
}
</pre>

Info tab will show some important information such as android version, app version vs. You can also add other information for the tester/developer to see when the application is running such as current end point or some other information.
    
<pre>
public class SampleBeeConfig extends BeeConfig {
    @Override
    public void onInfoContentCreated(Map<String, String> content) {
        content.put("Current End Point", "http://www.google.com");
    }
}
</pre>

This information will be shown in the application, this is usefull when you have some information and you can use them in everywhere and you don't want to check somewhere else but just see them from here, for instance: For testing, you may use some username and passwords for testing, you can put them here and use them whenever you want.
        
<pre>
public class SampleBeeConfig extends BeeConfig {
    @Override
    public void onClipboardContentCreated(Map<String, String> content) {
        content.put("User1", "324234234");
        content.put("Visa Expire Date", "2/16");
        content.put("Visa Code", "34");
    }
}
</pre>

Whenever a dropdown item is changed, this method will be called, you can simply take an action regarding to request code. requestCode is the identifier that you specified at the beginning.

<pre>
public class SampleBeeConfig extends BeeConfig {
    @Override
    public void onItemSelected(int requestCode, String data) {
        super.onItemSelected(requestCode, data);
    }
}
</pre>

Whenever a checkbox item is checked/unchecked, this method will be called, you can simply take an action regarding to request code. requestCode is the identifier that you specified at the beginning.

<pre>
public class SampleBeeConfig extends BeeConfig {
    @Override
    public void onCheckedChanged(int requestCode, CompoundButton buttonView, boolean isChecked) {
        super.onCheckedChanged(requestCode, buttonView, isChecked);
    }
}
</pre>

This method will be called whenever user touches the check/save button. You can take action regarding to your needs.

<pre>
public class SampleBeeConfig extends BeeConfig {
    @Override
    public void onSave() {
        super.onSave();
    }
}
</pre>

This method will be called whenever user touches the check/save button. You can take action regarding to your needs.

<pre>
public class SampleBeeConfig extends BeeConfig {
    @Override
    public void onClose() {
        super.onClose();
    }
}
</pre>
