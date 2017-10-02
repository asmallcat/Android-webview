package com.example.cz.webview1;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.R.attr.category;
import static android.R.attr.name;
import static android.R.attr.value;
import static android.view.View.Y;
import static com.example.cz.webview1.R.id.webView;

public class MainActivity extends Activity {
    private WebView webview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView) findViewById(webView);
        WebSettings webSettings = webview.getSettings();
        //设置自适应屏幕，两者合用
//        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
//        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //加载需要显示的网页
        webview.loadUrl("file:///android_asset/gauge&&pie.html");
        //设置Web视图
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //在这里执行你想调用的js函数
                webview.post(new Runnable() {
                    String call = "javascript:showpie([{value: 50,name: '负面', selected:false},{value: 250,name: '正面'}])";
//                    String call = "javascript:showforce( [{category: 0, name: '宝马', value: 20}, {category: 1, name: '油耗', value: 20}, {category: 0, name: '奇瑞', value: 20}, {category: 1, name: '奇瑞汽车', value: 20}, {category: 0, name: '奥迪', value: 20}, {category: 1, name: '吉普', value: 20}, {category: 1, name: '空间', value: 20}, {category: 0, name: '吉普', value: 20}, {category: 0, name: '雪佛兰', value: 20}, {category: 1, name: '外观', value: 20}, {category: 1, name: '丰田', value: 20}, {category: 1, name: '卡罗拉', value: 20}, {category: 1, name: '雪佛兰', value: 20}, {category: 0, name: '外观', value: 20}, {category: 0, name: '空间', value: 20}, {category: 0, name: '丰田', value: 20}, {category: 0, name: '卡罗拉', value: 20}, {category: 0, name: '比亚迪', value: 20}, {category: 1, name: '玻璃', value: 20}, {category: 0, name: '别克', value: 20}, {category: 1, name: '车灯', value: 20}, {category: 1, name: '灯', value: 20}, {category: 1, name: '别克', value: 20}, {category: 1, name: '设计师', value: 20}, {category: 0, name: '车灯', value: 20}, {category: 1, name: '业务', value: 20}, {category: 1, name: '前景', value: 20}, {category: 0, name: '业务', value: 20}, {category: 0, name: 'byd', value: 20}, {category: 1, name: '策略', value: 20}, {category: 1, name: '问题', value: 20}, {category: 1, name: '车', value: 20}, {category: 0, name: '策略', value: 20}, {category: 0, name: '车', value: 20}]," +
//                            "[{source: '宝马', target: '油耗', weight: 1}, {source: '奇瑞', target: '奇瑞汽车', weight: 1}, {source: '奥迪', target: '吉普', weight: 1}, {source: '奥迪', target: '空间', weight: 8}, {source: '吉普', target: '空间', weight: 1}, {source: '雪佛兰', target: '外观', weight: 1}, {source: '雪佛兰', target: '空间', weight: 1}, {source: '雪佛兰', target: '丰田', weight: 1}, {source: '雪佛兰', target: '卡罗拉', weight: 1}, {source: '雪佛兰', target: '雪佛兰', weight: 1}, {source: '外观', target: '空间', weight: 1}, {source: '空间', target: '外观', weight: 1}, {source: '丰田', target: '雪佛兰', weight: 1}, {source: '丰田', target: '外观', weight: 1}, {source: '丰田', target: '空间', weight: 1}, {source: '丰田', target: '卡罗拉', weight: 1}, {source: '卡罗拉', target: '雪佛兰', weight: 1}, {source: '卡罗拉', target: '外观', weight: 1}, {source: '卡罗拉', target: '空间', weight: 1}, {source: '卡罗拉', target: '丰田', weight: 1}, {source: '比亚迪', target: '玻璃', weight: 1}, {source: '别克', target: '车灯', weight: 1}, {source: '别克', target: '灯', weight: 1}, {source: '别克', target: '别克', weight: 1}, {source: '别克', target: '设计师', weight: 1}, {source: '车灯', target: '灯', weight: 1}, {source: '比亚迪', target: '业务', weight: 1}, {source: '比亚迪', target: '前景', weight: 1}, {source: '业务', target: '前景', weight: 1}, {source: 'byd', target: '策略', weight: 1}, {source: 'byd', target: '问题', weight: 1}, {source: 'byd', target: '车', weight: 1}, {source: '策略', target: '问题', weight: 1}, {source: '车', target: '策略', weight: 1}, {source: '车', target: '问题', weight: 1}])";
                    public void run() {
                        webview.loadUrl(call);
                    }
                });
            }

        });
    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (webview != null) {
            webview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webview.clearHistory();

            ((ViewGroup) webview.getParent()).removeView(webview);
            webview.destroy();
            webview = null;
        }
        super.onDestroy();
    }
}