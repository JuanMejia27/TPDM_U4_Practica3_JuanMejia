package mx.edu.ittepic.tpdm_u4_practica3_juanmejia;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView L,T,msjl,msjt;
    int contador,contadorHilo;
    Button inicio;
    Boolean variable = true;
    CountDownTimer timer;
    Thread thread;
    Runnable runnable;
    int tortuga = 0;
    int liebre = 0;
    String mt,ml;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicio = findViewById(R.id.inicio);
        L = findViewById(R.id.liebre);
        T = findViewById(R.id.tortuga);
        msjl = findViewById(R.id.msjl);
        msjt = findViewById(R.id.msjt);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread = new Thread(){
                    public void run(){
                        while(variable) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            int r1 = (int) (Math.random()*100)+1;

                            if(r1 >= 0 && r1 <= 50){
                                tortuga+=3;
                                mt = "avance rapido";
                            }else{
                                if(r1 > 50 && r1 <=70){
                                    tortuga-=6;
                                    mt = "resbalo";
                                    if(tortuga<0){
                                        tortuga = 0;
                                    }
                                }else{
                                    tortuga+=1;
                                    mt = "avance lento";
                                }
                            }
                            int r2 = (int) (Math.random()*100)+1;

                            if(r2 >= 0 && r2<=20){
                                liebre = liebre;
                                ml = "duerme";
                            }
                            if (r2>20 && r2<=40){
                                liebre+=9;
                                ml = "gran salto";
                            }
                            if(r2 >40 && r2<=50){
                                liebre-=12;
                                ml = "resbalon grande";
                                if(liebre<0){
                                    liebre = 0;
                                }
                            }
                            if(r2 >50 && r2<=80){
                                liebre += 1;
                                ml = "pequeño salto";
                            }
                            if(r2 > 80){
                                liebre-=2;
                                ml = "resbalon pequeño";
                                if(liebre<0){
                                    liebre = 0;
                                }
                            }

                            if(liebre > 70){
                                liebre = 70;
                                variable = false;
                                ml = "Ganador";
                            }

                            if(tortuga > 70){
                                tortuga = 70;
                                variable = false;
                                mt = "Ganador";
                            }


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    L.setText(liebre+"");
                                    msjt.setText(mt);
                                    msjl.setText(ml);
                                    T.setText(tortuga+"");
                                }
                            });
                            //contador++;

                            try {
                                sleep(300);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                };
                thread.start();
            }
        });
    }//onCreate
}//class
