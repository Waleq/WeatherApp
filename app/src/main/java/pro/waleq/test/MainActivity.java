package pro.waleq.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private final static String APIURL = "http://api.openweathermap.org/data/2.5/weather?q=Lodz,pl&appid=";

    private final static String APIKEY = "924bd4cc4333712a5cec7b4953fc510d";

    private TextView deegresView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deegresView = (TextView) findViewById(R.id.deegres);
        refresh();
    }

    protected void forceRefresh() {
        refresh();
    }

    private void refresh() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIURL + APIKEY)
                .build();
        Map<String, Object> service = retrofit.create(HashMap.class);
        if (service != null) {
            Map<String, Object> main = (HashMap<String, Object>) service.get("main");
            if (main != null) {
                Double temp = (Double) main.get("temp");
                if (temp != null) {
                    deegresView.setText(temp.toString());
                    deegresView.invalidate();
                }
            }
        }
    }

}
