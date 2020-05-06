
package kr.vhd.webview.daoupay;

import android.app.Dialog;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNWebviewDaoupayModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNWebviewDaoupayModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNWebviewDaoupay";
    }

    @ReactMethod
    public void test(String text) {
        Dialog dialog = new Dialog(reactContext);
        dialog.setTitle("test():text:" + text);
        dialog.show();
    }
}