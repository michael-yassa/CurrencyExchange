package com.example.currencychange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.currencychange.models.Contents;
import com.example.currencychange.models.Rates;

public class HomeActivity extends AppCompatActivity {
     TextView view1,view2;
     TextView textInput,textOut;
     String valueInput  ;
     String nameIn,nameOut;
     int postionIn,postionOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textInput = findViewById(R.id.input1);
        textOut = findViewById(R.id.textout);
        view1=findViewById(R.id.view1);
        view2=findViewById(R.id.view2);
        getDataFromMain();
        setDataToTV();


        }

    private void setDataToTV() {
        view1.setText(nameIn);
        view2.setText(nameOut);
    }

    private void getDataFromMain() {
        if(Contents.select==1) {
            nameOut=Contents.nameOut;
            postionOut=Contents.posOut;
            nameIn = getIntent().getStringExtra("name");
            Contents.nameIn = getIntent().getStringExtra("name");
            postionIn = getIntent().getIntExtra("pos", -1);
            Contents.posIn=getIntent().getIntExtra("pos", -1);
        }
        else{
            nameIn=Contents.nameIn;
            postionIn= Contents.posIn;
            nameOut = getIntent().getStringExtra("name");
            Contents.nameOut = getIntent().getStringExtra("name");
            postionOut = getIntent().getIntExtra("pos", -1);
            Contents.posOut = getIntent().getIntExtra("pos", -1);
        }
    }

    public void onDigitClick(View view){

         Button  button = ((Button) view);
         String btn = button.getText().toString();
         textInput.append(btn);
         valueInput = textInput.getText().toString();
         //   Toast.makeText(this, ""+valueInput, Toast.LENGTH_SHORT).show();
        onNumberClick();


        }
        public void OnDelClick(View view){
          Button del = ((Button) view);
          textInput.setText("");
          textOut.setText("");
         // valueInput = "";

        }
        public void onImageClick1(View view){
         startActivity(new Intent(HomeActivity.this,MainActivity.class));
            Contents.select =1;
        }
    public void onImageClick2(View view){
        startActivity(new Intent(HomeActivity.this,MainActivity.class));
        Contents.select =2;
    }


        public void onNumberClick(){
        if(Contents.currencyValue.size()==0 || Contents.currencyNamee.size() == 0)return;

         //   if(postionIn !=null || postionOut = null)return;
          if(postionIn ==39 || postionOut == 39)return;
             if(postionIn < 39 && postionOut<39){
                 calculteCuurencyValue(postionIn,postionOut);
                 Toast.makeText(this, "inner loww", Toast.LENGTH_SHORT).show();
             }
             else if(postionIn >39 && postionOut>39){
                 calculteCuurencyValue(postionIn+1,postionOut+1);
                 Toast.makeText(this, "inner hightttttt", Toast.LENGTH_SHORT).show();
             }
            else if(postionIn <39 &&postionOut >39){
                 calculteCuurencyValue(postionIn , postionOut+1);
                 Toast.makeText(this, "lowwwww hiiight", Toast.LENGTH_SHORT).show();
             }
            else{
                calculteCuurencyValue(postionIn+1,postionOut);
                 Toast.makeText(this, "hight lowwwwww", Toast.LENGTH_SHORT).show();
             }



        }
        public void calculteCuurencyValue(int posIn,int posOut){
            String valueIn= Contents.currencyValue.get(posIn).toString();
            Double vInDouble = Double.parseDouble(valueIn);
            String valueOut= Contents.currencyValue.get(posOut).toString();
            Double  vOutDouble = Double.parseDouble(valueOut);
            Double multyValue = Double.parseDouble(valueInput);
            Toast.makeText(this, ""+nameIn+vInDouble+"  " +posIn+nameOut+vOutDouble, Toast.LENGTH_SHORT).show();
            Double result = vOutDouble/vInDouble *multyValue;
            String resultString= ( result).toString();
            textOut.setText(resultString);
        }


}
