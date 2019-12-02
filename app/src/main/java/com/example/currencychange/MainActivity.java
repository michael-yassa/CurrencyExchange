package com.example.currencychange;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencychange.Base.BaseActivity;
import com.example.currencychange.adapter.CurrencyAdapter;
import com.example.currencychange.apis.ApiManger;
import com.example.currencychange.models.Contents;
import com.example.currencychange.models.CurrencyResponse;
import com.example.currencychange.models.Rates;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.currencychange.models.Contents.*;

public class MainActivity extends BaseActivity {
    TabLayout tabLayout;
    RecyclerView recyclerView;
    CurrencyAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;

    HomeActivity homeActivity;

    TextView favour;



    public static final String  access_key = "4e898a12f0b10a97d22192e01bfb5f86";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        getSourses();
    }

    private void initView() {
        tabLayout = findViewById(R.id.tap_layout);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.prg_bar);

        favour=findViewById(R.id.favour);
    }

    CurrencyResponse currencyResponse;
        Rates ratesourses;


    private void getSourses() {
        ApiManger.getApi().
                getCurrencySourses(access_key,"EUR")
                .enqueue(new Callback<CurrencyResponse>() {
                    @Override
                    public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                        if(true ==(response.body().getSuccess()
                        )){

                            ratesourses = response.body().getRates();
                           // Toast.makeText(MainActivity.this, "inAPIIIII", Toast.LENGTH_SHORT).show();
                            fillCurrencyList();
                            intRecyclerView();

                          progressBar.clearFocus();

                          //  showResponseInTab();
                        }
                        else{
                            showMessage("erooooorrrr",getString(R.string.ok));
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                            showMessage(t.getLocalizedMessage(),getString(R.string.ok));
                    }
                });
    }
                 private void showResponseInTab( ){
                    // Toast.makeText(this, ""+rt.size(), Toast.LENGTH_SHORT).show();
                  //for (int i=0; i<rt.size();i++){
                   //   Rates ratess = rt.get(i);
                      TabLayout.Tab tab = tabLayout.newTab();
                      tab.setText(ratesourses.toString());
                      tab.setTag(ratesourses);
                      tabLayout.addTab(tab);
                 // }

              //  for( Rates rates : ratesourses){


                // tab.setText(ratesourses.getALL().toString());

             //    }



                 }

               public void intRecyclerView(){
         adapter= new CurrencyAdapter(currencyNamee);

                   layoutManager=new GridLayoutManager(this,2);
                   recyclerView.setLayoutManager(layoutManager);
                   recyclerView.setAdapter(adapter);

                   adapter.setOnClickListener(new CurrencyAdapter.OnClickListener() {
                       @Override
                       public void onItemClick(int pos, String name) {


                          favour.setText(name);

                           Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                           intent.putExtra("name", name);
                           intent.putExtra("pos", pos);

                           startActivity(intent);

                       }
                   });
               }


               public void fillCurrencyList() {

                               try {

                                   Field[] fields = ratesourses.getClass().getDeclaredFields();
                                   for(Field field :fields){


                                       currencyNamee.add(field.getName().substring(1));

                                   }

                                   Method[] methods = ratesourses.getClass().getMethods();
                                   for(Method method : methods){
                                   if(method.getName().startsWith("get")){
                                  currencyValue.add( method.invoke(ratesourses));



                               }}


                                  //   Toast.makeText(this, ""+ Contents.currencyNamee, Toast.LENGTH_LONG).show();


                               }

                               catch (IllegalAccessException e) {
                                   e.printStackTrace();
                               } catch (InvocationTargetException e) {
                                   e.printStackTrace();
                               }




                       }
                   }


















