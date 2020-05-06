
package kr.vhd.webview.daoupay;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.net.URISyntaxException;

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
        Toast.makeText(reactContext.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    private boolean chkAppInstalled(String packagePath){
        boolean appInstalled = false;
        try {
            reactContext.getPackageManager().getPackageInfo(packagePath, PackageManager.GET_ACTIVITIES);
            appInstalled = true;
        } catch(PackageManager.NameNotFoundException e){
            appInstalled = false;
        }
        return appInstalled;
    }

    private boolean intentSchemeParser(String url) {
        try {
            Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
            if(reactContext.getPackageManager().resolveActivity(intent , 0) == null){
                String pakagename = intent.getPackage();
                if(pakagename != null) {
                    Uri uri = Uri.parse("market://details?id="+pakagename);
                    intent = new Intent(Intent.ACTION_VIEW , uri);
                    reactContext.startActivity(intent);
                    return true;
                }
            }
            Uri uri = Uri.parse(intent.getDataString());
            intent = new Intent(Intent.ACTION_VIEW , uri);
            reactContext.startActivity(intent);
            return true;

        } catch (URISyntaxException e) {
        }
        return false;
    }

    private boolean customSchemeParser(String url) {
        String packageName = null;
        if(url.startsWith("shinhan-sr-ansimclick://")) {        //���� ��ī��
            packageName = "com.shcard.smartpay";
        }else if(url.startsWith("mpocket.online.ansimclick://")) {  //�Ｚ��ī��
            packageName = "kr.co.samsungcard.mpocket";
        } else if(url.startsWith("hdcardappcardansimclick://")) {       //����Ƚɰ���
            packageName = "com.hyundaicard.appcard";
        } else if(url.startsWith("droidxantivirusweb:")){               //droidx ���
            packageName = "net.nshc.droidxantivirus";
        } else if(url.startsWith("vguardstart://") || url.startsWith("vguardend://")){  //vguard���
            packageName = "kr.co.shiftworks.vguardweb";
        } else if(url.startsWith("hanaansim")){         //�ϳ���ȯ��ī��
            packageName = "com.ilk.visa3d";
        } else if(url.startsWith("nhappcardansimclick://")) { //������ī��
            packageName = "nh.smart.mobilecard";
        } else if(url.startsWith("ahnlabv3mobileplus")){
            packageName = "com.ahnlab.v3mobileplus";
        } else if(url.startsWith("smartxpay-transfer://")){
            packageName = "kr.co.uplus.ecredit";
        }
        else {
            return false;
        }

        Intent intent = null;
        //�ϵ��ڵ��� ��Ű�������� �� ��ġ���θ� �Ǵ��Ͽ� �ش� �� ���� �Ǵ� ���� �̵�
        if(chkAppInstalled(packageName)){

            try {
                intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                Uri uri = Uri.parse(intent.getDataString());
                intent = new Intent(Intent.ACTION_VIEW , uri);
                reactContext.startActivity(intent);
                return true;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Uri uri = Uri.parse("market://details?id="+packageName);
            intent = new Intent(Intent.ACTION_VIEW , uri);
            reactContext.startActivity(intent);
            return true;
        }

        return false;
    }

    private boolean checkAppInstalled(String url , String type){
        if(type.equals("intent")){
            return intentSchemeParser(url);
        } else if(type.equals("customLink")){
            return customSchemeParser(url);
        }
        return false;
    }

    @ReactMethod
    public boolean shouldOverrideUrlLoading(String url) {
        if (url.startsWith("intent")){
            return checkAppInstalled(url, "intent");
        } else if (url.startsWith("market://")) {
            try {
                Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                if (intent != null) {
                    reactContext.startActivity(intent);
                }
                return true;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else if(url.startsWith("http://") || url.startsWith("https://")) {
            return true;
        }
        else {
            return checkAppInstalled(url , "customLink");
        }
        return true;
    }
}