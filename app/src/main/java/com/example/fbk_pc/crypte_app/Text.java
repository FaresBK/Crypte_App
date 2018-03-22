package com.example.fbk_pc.crypte_app;

/**
 * Created by TM161 on 19-05-2017.
 */

public class Text {
    String [] Text;

    public Text(String[] text) {
       this.Text = text;
    }
   public String getText(int i){
       return Text[i];
   }
    public void setText(String[] Text) {
        this.Text = Text;
    }
}
