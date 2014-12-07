##Introduction
Bee is a debug tool for developers to configure the application and see the log on the fly.

##Setup
Extend BeeConfig class and add content for the following

<pre>
public class AppBeeConfig extends BeeConfig {

    @Override
    public void onMenuContentCreated(Bee.Builder builder) {
        super.onMenuContentCreated(builder);
        builder.addSpinner("test", new String[]{"asdf"}, 0)
                .addCheckbox("tesasdf", 1);
    }

    @Override
    public void onInfoContentCreated(Map<String, String> content) {
        content.put("Current End Point", "http://www.google.com");
    }

    @Override
    public void onClipboardContentCreated(Map<String, String> content) {
        content.put("User1", "324234234");
        content.put("Visa Expire Date", "2/16");
        content.put("Visa Code", "34");
    }

    @Override
    public void onItemSelected(int requestCode, String data) {
        super.onItemSelected(requestCode, data);
    }

    @Override
    public void onCheckedChanged(int requestCode, CompoundButton buttonView, boolean isChecked) {
        super.onCheckedChanged(requestCode, buttonView, isChecked);
    }

    @Override
    public void onSave() {
        super.onSave();
    }

    @Override
    public void onClose() {
        super.onClose();
    }
}
</pre>
###