##Introduction
Bee is a debug tool for developers and QA to configure the application and see the configured logs, information about the application, clipboard to put some info and see them all the time and settings for the configuration.

##Setup
####1. Add dependency

<pre>
dependencies {
    compile 'com.github.nr4bt:bee:1.0.0-SNAPSHOT@aar'
}
</pre>

####2. Extend BeeConfig class and add content for the following

<pre>
public class SampleBeeConfig extends BeeConfig {
    private static final int ENDPOINT = 0;
    private static final int LOG = 1;
    
    @Override
    public void onMenuContentCreated(Bee.Builder builder) {
        super.onMenuContentCreated(builder);
        builder.addSpinner("End Point Url", new String[]{"http://www.google.com"}, ENDPOINT)
                .addCheckbox("Show logs", LOG);
                
        //This is the settings content that you want to configure on the fly. You can put spinner(dropdown) 
        // or checkbox in order to use it when the application is running. Most important part is you need 
        // to put unique request code for each item. You can put as many item as you want. 
    }

    @Override
    public void onInfoContentCreated(Map<String, String> content) {
       // Info tab will show some important information such as android version, app version vs. 
       // You can also add other information for the tester/developer to see when the application 
       // is running such as current end point or some other information.
    
        content.put("Current End Point", "http://www.google.com");
    }

    @Override
    public void onClipboardContentCreated(Map<String, String> content) {
        // This information will be shown in the application, this is usefull when you have some 
        // information and you can use them in everywhere and you don't want to check somewhere else 
        // but just see them from here, for instance: For testing, you may use some username and 
        // passwords for testing, you can put them here and use them whenever you want.
        
        content.put("User1", "324234234");
        content.put("Visa Expire Date", "2/16");
        content.put("Visa Code", "34");
    }

    @Override
    public void onItemSelected(int requestCode, String data) {
        super.onItemSelected(requestCode, data);
        
        //Whenever a dropdown item is changed, this method will be called, you can simply take an 
        //action regarding to request code. requestCode is the identifier that you specified at the beginning.
    }

    @Override
    public void onCheckedChanged(int requestCode, CompoundButton buttonView, boolean isChecked) {
        super.onCheckedChanged(requestCode, buttonView, isChecked);
        
        //Whenever a checkbox item is checked/unchecked, this method will be called, you can simply take 
        //an action regarding to request code. requestCode is the identifier that you specified at the beginning.
    }

    @Override
    public void onSave() {
        super.onSave();
        
        //This method will be called whenever user touches the check/save button. You can take action 
        //regarding to your needs.
    }

    @Override
    public void onClose() {
        super.onClose();
        
        //This method will be called whenever user touches the close button. You can take action 
        //regarding to your needs.
    }
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

####4. Use BeeLog in order to show log in the bee

<pre>
BeeLog.d(TAG,"Some event triggered");
</pre>
