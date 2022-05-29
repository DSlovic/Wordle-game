package com.example.skocko;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class activity_igra extends AppCompatActivity {
    private static int pozicija = 0;
    private String viewPogadjanje = "imageView1";
    private String viewRezultat = "imageView2";
    private static int red = 0;
    private final int[] nizMogucihKaraktera = { 0, 1, 2, 3 };
    private int[] nizGenerisanihKaraktera = new int[4];
    private static int[] brojKaraktera = {0, 0, 0, 0};
    private int[] nizPostavljenihKaraktera = new int[4];
    private static int brojPokusaja = 0;
   // private String pr; //Ova promenljiva sluzi za varanje, izbrisite sve komentare i napritisku dugmeta herc mozete videti kombinaciju

    public void upozorenje(){
        TextView upoz = (TextView) findViewById(R.id.textView_info);
        upoz.setText("Kombinacija je puna!");
        if (upoz.getVisibility() == View.INVISIBLE){
            upoz.setVisibility(View.VISIBLE);
        }
    }
    public void upozorenje2(){
        TextView upoz = (TextView) findViewById(R.id.textView_info);
        upoz.setText("Niste uneli celu kombinaciju!");
        if (upoz.getVisibility() == View.INVISIBLE){
            upoz.setVisibility(View.VISIBLE);
        }
    }
    public void upozorenje3(){
        TextView upoz = (TextView) findViewById(R.id.textView_info);
        upoz.setText("Ovo je poslednji pokusaj!");
        if (upoz.getVisibility() == View.INVISIBLE){
            upoz.setVisibility(View.VISIBLE);
        }
    }
    public void cestitka(){
        TextView upoz = (TextView) findViewById(R.id.textView_info);
        upoz.setText("CESTITAMO! POBEDILI STE IGRU SKOCKO!");
        upoz.setTextColor(Color.GREEN);
        if (upoz.getVisibility() == View.INVISIBLE){
            upoz.setVisibility(View.VISIBLE);
        }
        ImageView kutia = (ImageView) findViewById(R.id.kutia);
        TextView cestitka = (TextView) findViewById(R.id.textView_kutia);
        Button da = (Button) findViewById(R.id.button_da);
        Button ne = (Button) findViewById(R.id.button_ne);

        kutia.setVisibility(View.VISIBLE);
        cestitka.setTextColor(Color.GREEN);
        cestitka.setText("POBEDILISTE! Zelite li da pokusate ponovo?");
        cestitka.setVisibility(View.VISIBLE);
        da.setVisibility(View.VISIBLE);
        ne.setVisibility(View.VISIBLE);

        da.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                generisiKaraktere();
                pocniPonovo();

                kutia.setVisibility(View.INVISIBLE);
                cestitka.setVisibility(View.INVISIBLE);
                da.setVisibility(View.INVISIBLE);
                ne.setVisibility(View.INVISIBLE);
            }
        });
        ne.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.runFinalizersOnExit(true);
            }
        });

        for(int j = 0; j < 4; j++){
            String novViewRez = "imageViewRez" + Integer.toString(j + 1);
            int novId = getResources().getIdentifier(novViewRez, "id", getPackageName());
            ImageView element = (ImageView) findViewById(novId);
            if(nizGenerisanihKaraktera[j] == 0){
                element.setImageDrawable(getResources().getDrawable(R.drawable.hearts));
            }else if(nizGenerisanihKaraktera[j] == 1){
                element.setImageDrawable(getResources().getDrawable(R.drawable.karo));
            }else if(nizGenerisanihKaraktera[j] == 2){
                element.setImageDrawable(getResources().getDrawable(R.drawable.pik));
            }else{
                element.setImageDrawable(getResources().getDrawable(R.drawable.tref));
            }
        }
    }
    public void obavstenje(){
        TextView upoz = (TextView) findViewById(R.id.textView_info);
        upoz.setText("Ostali ste bez pokusaja! Izgubili ste igru skocko!");
        if (upoz.getVisibility() == View.INVISIBLE){
            upoz.setVisibility(View.VISIBLE);
        }
        for(int j = 0; j < 4; j++){
            String novViewRez = "imageViewRez" + Integer.toString(j + 1);
            int novId = getResources().getIdentifier(novViewRez, "id", getPackageName());
            ImageView element = (ImageView) findViewById(novId);
            if(nizGenerisanihKaraktera[j] == 0){
                element.setImageDrawable(getResources().getDrawable(R.drawable.hearts));
            }else if(nizGenerisanihKaraktera[j] == 1){
                element.setImageDrawable(getResources().getDrawable(R.drawable.karo));
            }else if(nizGenerisanihKaraktera[j] == 2){
                element.setImageDrawable(getResources().getDrawable(R.drawable.pik));
            }else{
                element.setImageDrawable(getResources().getDrawable(R.drawable.tref));
            }
        }
        ImageView kutia = (ImageView) findViewById(R.id.kutia);
        TextView cestitka = (TextView) findViewById(R.id.textView_kutia);
        Button da = (Button) findViewById(R.id.button_da);
        Button ne = (Button) findViewById(R.id.button_ne);

        kutia.setVisibility(View.VISIBLE);
        cestitka.setTextColor(Color.RED);
        cestitka.setText("IZGUBILISTE! Zelite li da pokusate ponovo?");
        cestitka.setVisibility(View.VISIBLE);
        da.setVisibility(View.VISIBLE);
        ne.setVisibility(View.VISIBLE);

        da.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                generisiKaraktere();
                pocniPonovo();
            }
        });
        ne.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.runFinalizersOnExit(true);
            }
        });
    }
    public void pocniPonovo(){
        Button herc = (Button) findViewById(R.id.button_herc);
        Button karo = (Button) findViewById(R.id.button_karo);
        Button pik = (Button) findViewById(R.id.button_pik);
        Button tref = (Button) findViewById(R.id.button_tref);
        Button izbrisi = (Button) findViewById(R.id.button_resetuj);
        Button proveri = (Button) findViewById(R.id.button_potvrdi);

        herc.setClickable(false);
        karo.setClickable(false);
        pik.setClickable(false);
        tref.setClickable(false);
        izbrisi.setClickable(false);
        proveri.setClickable(false);

        red = 0;
        pozicija = 0;
        brojPokusaja = 0;
        for(int i = 1; i<29; i++){
            if (i>=10){
                viewPogadjanje = "imageView1";
            }else {
                viewPogadjanje = "imageView10";
            }
            String novViewPog = viewPogadjanje + Integer.toString(i);
            int novId = getResources().getIdentifier(novViewPog, "id", getPackageName());
            ImageView element = (ImageView) findViewById(novId);
            if(element.getVisibility() == View.VISIBLE){
                element.setVisibility(View.INVISIBLE);
            }
        }
        for(int i = 1; i<8; i++){
            for(int j = 1; j<5; j++){
                String novViewRez = viewRezultat + Integer.toString(i) + Integer.toString(j);
                int novId = getResources().getIdentifier(novViewRez, "id", getPackageName());
                ImageView element = (ImageView) findViewById(novId);
                element.setImageDrawable(getResources().getDrawable(R.drawable.border));
            }
        }
        for(int i = 1; i<5; i++){
            String novViewRez = "imageViewRez" + Integer.toString(i);
            int novId = getResources().getIdentifier(novViewRez, "id", getPackageName());
            ImageView element = (ImageView) findViewById(novId);
            element.setImageDrawable(getResources().getDrawable(R.drawable.border));
        }
        TextView upoz = (TextView) findViewById(R.id.textView_info);
        upoz.setTextColor(Color.RED);
        if (upoz.getVisibility() == View.VISIBLE){
            upoz.setVisibility(View.INVISIBLE);
        }
        herc.setClickable(true);
        karo.setClickable(true);
        pik.setClickable(true);
        tref.setClickable(true);
        izbrisi.setClickable(true);
        proveri.setClickable(true);
    }
    private void generisiKaraktere() {
        Random rand = new Random();
        for (int i = 0; i < nizGenerisanihKaraktera.length; i++) {
            int slucajanBroj = rand.nextInt(4);
            nizGenerisanihKaraktera[i] = nizMogucihKaraktera[slucajanBroj];
            if(slucajanBroj == 0){brojKaraktera[0]++;}
            else if (slucajanBroj == 1){brojKaraktera[1]++;}
            else if (slucajanBroj == 2){brojKaraktera[2]++;}
            else {brojKaraktera[3]++;}
        }
     //   pr = Integer.toString(nizGenerisanihKaraktera[0]) + Integer.toString(nizGenerisanihKaraktera[1]) + Integer.toString(nizGenerisanihKaraktera[2]) + Integer.toString(nizGenerisanihKaraktera[3] );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_igra);
        generisiKaraktere();

        Button button_herc = (Button) findViewById(R.id.button_herc);
        button_herc.setOnClickListener(new View.OnClickListener()

        {
            public void onClick (View v) {
                if(red < 4) {
                    pozicija++;
                    nizPostavljenihKaraktera[red] = 0;
                    red++;

                    if (pozicija < 10) {
                        viewPogadjanje = "imageView10";
                    } else {
                        viewPogadjanje = "imageView1";
                    }
                    String novViewPog = viewPogadjanje + Integer.toString(pozicija);

                    int novId = getResources().getIdentifier(novViewPog, "id", getPackageName());
                    ImageView element = (ImageView) findViewById(novId);;
                    element.setImageDrawable(getResources().getDrawable(R.drawable.hearts));
                    element.setVisibility(View.VISIBLE);

                 //   Toast toast = Toast.makeText(getApplicationContext(), pr, Toast.LENGTH_SHORT);
                 //   toast.show();
                }else{
                    upozorenje();
                }

            }
        });

        Button button_karo = (Button) findViewById(R.id.button_karo);
        button_karo.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                if(red < 4) {
                    pozicija++;
                    nizPostavljenihKaraktera[red] = 1;
                    red++;

                    if (pozicija < 10) {
                        viewPogadjanje = "imageView10";
                    } else {
                        viewPogadjanje = "imageView1";
                    }
                    String novViewPog = viewPogadjanje + Integer.toString(pozicija);

                    int novId = getResources().getIdentifier(novViewPog, "id", getPackageName());
                    ImageView element = (ImageView) findViewById(novId);
                    DrawableCompat.setTint(getResources().getDrawable(R.drawable.karo), Color. RED);
                    element.setImageDrawable(getResources().getDrawable(R.drawable.karo));
                    element.setVisibility(View.VISIBLE);

                }else{
                    upozorenje();
                }

        }
        });

        Button button_pik = (Button) findViewById(R.id.button_pik);
        button_pik.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                if(red < 4) {
                    pozicija++;
                    nizPostavljenihKaraktera[red] = 2;
                    red++;

                    if (pozicija < 10) {
                        viewPogadjanje = "imageView10";
                    } else {
                        viewPogadjanje = "imageView1";
                    }
                    String novViewPog = viewPogadjanje + Integer.toString(pozicija);

                    int novId = getResources().getIdentifier(novViewPog, "id", getPackageName());
                    ImageView element = (ImageView) findViewById(novId);;
                    element.setImageDrawable(getResources().getDrawable(R.drawable.pik));
                    element.setVisibility(View.VISIBLE);

                }else{
                    upozorenje();
                }

            }
        });

        Button button_tref = (Button) findViewById(R.id.button_tref);
        button_tref.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                if(red < 4) {
                    pozicija++;
                    nizPostavljenihKaraktera[red] = 3;
                    red++;

                    if (pozicija < 10) {
                        viewPogadjanje = "imageView10";
                    } else {
                        viewPogadjanje = "imageView1";
                    }
                    String novViewPog = viewPogadjanje + Integer.toString(pozicija);

                    int novId = getResources().getIdentifier(novViewPog, "id", getPackageName());
                    ImageView element = (ImageView) findViewById(novId);;
                    element.setImageDrawable(getResources().getDrawable(R.drawable.tref));
                    element.setVisibility(View.VISIBLE);

                }else{
                    upozorenje();
                }

            }
        });

        Button button_reset = (Button) findViewById(R.id.button_resetuj);
        button_reset.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                if (red != 0) {
                    for (int i = 1; i < 5; i++){
                        if (pozicija < 13) {
                            viewPogadjanje = "imageView10";
                        } else {
                            viewPogadjanje = "imageView1";
                        }
                        System.out.println(viewPogadjanje);
                        if(pozicija >= 9){
                            if(pozicija <=12){
                                if(i>= 2){
                                    viewPogadjanje = "imageView1";
                                }
                            }
                        }
                        String novViewPog = viewPogadjanje + Integer.toString((pozicija - red) + i);
                        int novId = getResources().getIdentifier(novViewPog, "id", getPackageName());
                        ImageView element = (ImageView) findViewById(novId);
                        if(element.getVisibility() == View.VISIBLE){
                            element.setVisibility(View.INVISIBLE);
                        }
                    }
                    pozicija = pozicija - red;
                    red = 0;
                }
            }
        });

        Button button_potvrdi = (Button) findViewById(R.id.button_potvrdi);
        button_potvrdi.setOnClickListener(new View.OnClickListener() {

            public void onClick (View v) {
                if (red == 4) {
                    int brojPogodjenihNaMestu = 0;
                    int brojPogodjenih = 0;
                    int univerzalniBrojac = 0;
                    int[] brojKaraktera2 = {0, 0, 0, 0};
                    brojPokusaja++;
                    for(int j =0; j<4; j++){
                        brojKaraktera2[j] = brojKaraktera[j];
                    }
                    for (int i = 0; i < 4; i++) {
                        if (nizGenerisanihKaraktera[i] == nizPostavljenihKaraktera[i]) {
                            brojPogodjenihNaMestu++;
                            brojKaraktera2[nizPostavljenihKaraktera[i]]--;
                        }
                    }
                    for (int i = 0; i < 4; i++) {
                        if(nizGenerisanihKaraktera[i] != nizPostavljenihKaraktera[i]) {
                            for(int x = 0; x<4; x++){
                                if(nizGenerisanihKaraktera[x] == nizPostavljenihKaraktera[i]){
                                    if(brojKaraktera2[nizPostavljenihKaraktera[i]] > 0){
                                        brojPogodjenih++;
                                        brojKaraktera2[nizPostavljenihKaraktera[i]]--;
                                    }
                                }
                            }
                        }
                    }

                    if(brojPogodjenihNaMestu != 0){
                        for(int j = 0; j < brojPogodjenihNaMestu; j++){
                            String novViewRez = viewRezultat + Integer.toString(brojPokusaja) + Integer.toString(univerzalniBrojac + 1);
                            int novId = getResources().getIdentifier(novViewRez, "id", getPackageName());
                            ImageView element = (ImageView) findViewById(novId);
                            element.setImageDrawable(getResources().getDrawable(R.drawable.pogodjen_u_mestu_foreground));
                            univerzalniBrojac++;
                        }
                    }
                    if(brojPogodjenih != 0){
                        for(int j = 0; j < brojPogodjenih; j++){
                            String novViewRez = viewRezultat + Integer.toString(brojPokusaja) + Integer.toString(univerzalniBrojac + 1);
                            int novId = getResources().getIdentifier(novViewRez, "id", getPackageName());
                            ImageView element = (ImageView) findViewById(novId);
                            element.setImageDrawable(getResources().getDrawable(R.drawable.pogodjen_u_foreground));
                            univerzalniBrojac++;
                        }
                    }
                    red = 0;
                    if (brojPokusaja == 6){
                        upozorenje3();
                    }else{
                        TextView upoz = (TextView) findViewById(R.id.textView_info);
                        if (upoz.getVisibility() == View.VISIBLE){
                            upoz.setVisibility(View.INVISIBLE);
                        }
                    }

                    if (brojPogodjenihNaMestu == 4){
                        cestitka();
                    }
                    if(brojPokusaja == 7){
                        if(brojPogodjenihNaMestu != 4){
                            obavstenje();
                        }
                    }
                }else{
                    upozorenje2();
                }
            }
        });


}
}