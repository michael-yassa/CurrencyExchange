package com.example.currencychange;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.currencychange.models.Contents.*;

public class MainActivity extends BaseActivity {
    EditText search;
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
        editSearch();
        showFavourInTab();
        editSearch();

    }

    private void initView() {
        tabLayout = findViewById(R.id.tap_layout);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.prg_bar);
        search = findViewById(R.id.search);

       // favour=findViewById(R.id.favour);
    }
    public void editSearch(){
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
              //  Toast.makeText(homeActivity, ""+currencyNamee, Toast.LENGTH_SHORT).show();
              filter(editable.toString());
            }
        });
    }


    public void filter( String text){


        for (int i = 0; i < currencyNamee.size(); i++) {
            String itemSearch = currencyNamee.get(i);
            if (itemSearch.toLowerCase().contains(text.toLowerCase())) {
                filtredNameList.add(itemSearch);
                 filtrePostionList.add(i);
            }
        }
      //  Toast.makeText(this, ""+ filtredNameList, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, ""+ filtrePostionList, Toast.LENGTH_SHORT).show();
        //int vv= filtrePostionList.get(3);
        //Toast.makeText(this, ""+vv, Toast.LENGTH_SHORT).show();

        adapter.changeData(filtredNameList);
        intRecyclerView(filtredNameList);

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
                            intRecyclerView(currencyNamee);





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
                 public void showFavourInTab( ){
                 if(favourName ==null)return;
                   for(int i = 0 ; i<favourName.size();i++){
                      TabLayout.Tab tab = tabLayout.newTab();
                      tab.setText(favourName.get(i));
                      tab.setTag(favourPostion.get(i));
                      tabLayout.addTab(tab);}
                   tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                       @Override
                       public void onTabSelected(TabLayout.Tab tab) {

                           Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                           intent.putExtra("name", tab.getText());
                           intent.putExtra("pos", (Integer) tab.getTag());

                           startActivity(intent);
                       }

                       @Override
                       public void onTabUnselected(TabLayout.Tab tab) {

                       }

                       @Override
                       public void onTabReselected(TabLayout.Tab tab) {

                       }
                   });
                 }


                 public void intRecyclerView(final List<String> adapterList){
         adapter= new CurrencyAdapter(adapterList);

                   layoutManager=new GridLayoutManager(this,2);
                   recyclerView.setLayoutManager(layoutManager);
                   recyclerView.setAdapter(adapter);

                   adapter.setOnClickListener(new CurrencyAdapter.OnClickListener() {

                       @Override
                       public void onItemClick(int pos, String name) {
                           if (adapterList == filtredNameList) {

                               int newpos = filtrePostionList.get(pos);
                               favourName.add(name);
                               favourPostion.add(newpos);


                               showFavourInTab();

                               Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                               intent.putExtra("name", name);
                               intent.putExtra("pos", newpos);

                               startActivity(intent);
                           }
                           else {
                           favourName.add(name);
                           favourPostion.add(pos);


                           showFavourInTab();

                           Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                           intent.putExtra("name", name);
                           intent.putExtra("pos", pos);

                           startActivity(intent);
                       }

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

                               }

                               catch (IllegalAccessException e) {
                                   e.printStackTrace();
                               } catch (InvocationTargetException e) {
                                   e.printStackTrace();
                               }




                       }
                   }


















