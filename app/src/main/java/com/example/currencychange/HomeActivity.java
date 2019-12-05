package com.example.currencychange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.currencychange.models.Contents;
import com.example.currencychange.models.Rates;

import static com.example.currencychange.R.color.cardview_shadow_end_color;
import static com.example.currencychange.R.color.colorPrimaryDark;
import static com.example.currencychange.R.color.color_blue;
import static com.example.currencychange.R.color.color_cyan;

public class HomeActivity extends AppCompatActivity {
    MainActivity mainActivity;
    Rates rates;
    TextView title3;
     TextView view1,view2,view3;
     TextView textInput,textOut,textOut3;
     String valueInput ="1" ;
     String nameIn  ;
         String  nameOut  , nameOut3;
     int postionIn,postionOut,postionOut3;
     LinearLayoutCompat div3;
     ConstraintLayout div4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      initView();
       // Toast.makeText(this, ""+Contents.favourName, Toast.LENGTH_SHORT).show();
           view2.setText("AED");
        getDataFromMain();
        setDataToTV();
        if(nameOut3 !=null){onNumberClick(postionIn,postionOut3,textOut3);}
        onNumberClick(postionIn,postionOut,textOut);
        Contents.filtredNameList.clear();
        Contents.filtrePostionList.clear();

        }
       public void initView(){
           textInput = findViewById(R.id.input1);
           textOut = findViewById(R.id.textout);
           textOut3 = findViewById(R.id.textout3);
           view1=findViewById(R.id.view1);
           view2=findViewById(R.id.view2);
           view3=findViewById(R.id.view3);
           div3 = findViewById(R.id.div3);
           div4 = findViewById(R.id.div4);
           title3 = findViewById(R.id.titelin3);
       }

    private void setDataToTV() {
        view1.setText(nameIn);
        view2.setText(nameOut);
        view3.setText(nameOut3);
    }

    private void getDataFromMain() {

        if(Contents.select==1) {
            nameOut=Contents.nameOut;
            postionOut=Contents.posOut;
            nameOut3=Contents.nameOut3;
            postionOut3=Contents.posOut3;
            nameIn = getIntent().getStringExtra("name");
            Contents.nameIn = getIntent().getStringExtra("name");
            postionIn = getIntent().getIntExtra("pos", -1);
            Contents.posIn=getIntent().getIntExtra("pos", -1);
        }
        else if (Contents.select == 2){
            nameIn=Contents.nameIn;
            postionIn= Contents.posIn;
            nameOut3=Contents.nameOut3;
            postionOut3=Contents.posOut3;
            nameOut = getIntent().getStringExtra("name");
            Contents.nameOut = getIntent().getStringExtra("name");
            postionOut = getIntent().getIntExtra("pos", -1);
            Contents.posOut = getIntent().getIntExtra("pos", -1);
        }
        else {
            nameIn = Contents.nameIn;
            postionIn = Contents.posIn;
            nameOut = Contents.nameOut;
            postionOut = Contents.posOut;

            nameOut3 = getIntent().getStringExtra("name");
              Contents.nameOut3=getIntent().getStringExtra("name");
            postionOut3 = getIntent().getIntExtra("pos", -1);
           Contents.posOut3 = getIntent().getIntExtra("pos", -1);



        }

    }

    public void onDigitClick(View view){

         Button  button = ((Button) view);
         String btn = button.getText().toString();
         textInput.append(btn);
         valueInput = textInput.getText().toString();
         //   Toast.makeText(this, ""+valueInput, Toast.LENGTH_SHORT).show();
        if(nameOut3 !=null){onNumberClick(postionIn,postionOut3,textOut3);}
        onNumberClick(postionIn,postionOut,textOut);


        }
        public void OnDelClick(View view){
          Button del = ((Button) view);
          textInput.setText("");
          textOut.setText("");
            textOut3.setText("");


        }


        public void onRevClick(View view){
        Button rev = ((Button) view);
        if(Contents.reverceCase == 1) {
            nameIn = Contents.nameOut;
            postionIn = Contents.posOut;

            nameOut = Contents.nameOut3;
            postionOut = Contents.posOut3;


            nameOut3 = Contents.nameIn;
            postionOut3 = Contents.posIn;

            onNumberClick(postionIn,postionOut3,textOut3);
            onNumberClick(postionIn,postionOut,textOut);
        }
        else{
            Toast.makeText(this, "caseeeee"+222222, Toast.LENGTH_SHORT).show();
            nameIn = Contents.nameOut;
            postionIn = Contents.posOut;

            nameOut = Contents.nameIn;
            postionOut = Contents.posIn;
            onNumberClick(postionIn,postionOut,textOut);
        }

            setDataToTV();


            Contents.nameIn=nameIn;
            Contents.posIn=postionIn;
            Contents.nameOut=nameOut;
            Contents.posOut=postionOut;
            Contents.nameOut3=nameOut3;
            Contents.posOut3=postionOut3;

        }





        public void onImageClick1(View view){

      ;
         startActivity(new Intent(HomeActivity.this,MainActivity.class));
            Contents.select =1;
        }
    public void onImageClick2(View view){
        startActivity(new Intent(HomeActivity.this,MainActivity.class));
        Contents.select =2;
    }
    public void addCurrency(View view){
        Button add = ((Button) view);
        Contents.reverceCase = 1;

        startActivity(new Intent(HomeActivity.this,MainActivity.class));
        Contents.select =3;
    }
       public void ClearCurrency(View view){

           Contents.reverceCase = 2;
           title3.setText(null);
           textOut3.setText(null);
           view3.setText(null);
           div3.setBackgroundColor(R.color.color_white);

       }


        public void onNumberClick(int postionIn , int postionOut,TextView out){
        if(Contents.currencyValue.size()==0 || Contents.currencyNamee.size() == 0)return;

         //   if(postionIn !=null || postionOut = null)return;
          if(postionIn ==39 || postionOut == 39)return;
             if(postionIn < 39 && postionOut<39){
                 calculteCuurencyValue(postionIn,postionOut,out);
              //   Toast.makeText(this, "inner loww", Toast.LENGTH_SHORT).show();
             }
             else if(postionIn >39 && postionOut>39){
                 calculteCuurencyValue(postionIn+1,postionOut+1,out);
               //  Toast.makeText(this, "inner hightttttt", Toast.LENGTH_SHORT).show();
             }
            else if(postionIn <39 &&postionOut >39){
                 calculteCuurencyValue(postionIn , postionOut+1,out);
               //  Toast.makeText(this, "lowwwww hiiight", Toast.LENGTH_SHORT).show();
             }
            else{
                calculteCuurencyValue(postionIn+1,postionOut,out);

             }



        }
        public void calculteCuurencyValue(int posIn,int posOut,TextView out){
            String valueIn= Contents.currencyValue.get(posIn).toString();
            Double vInDouble = Double.parseDouble(valueIn);
            String valueOut= Contents.currencyValue.get(posOut).toString();
          //  Toast.makeText(this, ""+valueOut+""+posOut, Toast.LENGTH_SHORT).show();
            Double  vOutDouble = Double.parseDouble(valueOut);
            Double multyValue = Double.parseDouble(valueInput);
           // Toast.makeText(this, ""+nameIn+vInDouble+"  " +posIn+nameOut+vOutDouble, Toast.LENGTH_SHORT).show();
            Double result = vOutDouble/vInDouble *multyValue;
          //  Toast.makeText(this, ""+valueOut+valueIn, Toast.LENGTH_SHORT).show();
            String resultString= ( result).toString();
            out.setText(resultString);
            if(out == textOut3){

                div3.setBackgroundColor(cardview_shadow_end_color);
                title3.setText("TO");


            }
        }


}
