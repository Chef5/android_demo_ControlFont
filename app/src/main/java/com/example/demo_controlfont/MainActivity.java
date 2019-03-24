package com.example.demo_controlfont;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView demo_text;
    private TextView show_bar_text;
    private RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取被操作对象
        demo_text = (TextView)findViewById(R.id.main_test_word);

        //单选改变颜色
        group = findViewById(R.id.radio_color);
        RadioListener rd_listener = new RadioListener();
        group.setOnCheckedChangeListener(rd_listener);

        //复选改变样式
        CheckBoxListener ck_listener = new CheckBoxListener();
        CheckBox bold = (CheckBox)findViewById(R.id.checkbox_style_bold);
        CheckBox italic = (CheckBox)findViewById(R.id.checkbox_style_italic);
        CheckBox reverse = (CheckBox)findViewById(R.id.checkbox_style_reverse);
        CheckBox vertical = (CheckBox)findViewById(R.id.checkbox_style_vertical);
        bold.setOnCheckedChangeListener(ck_listener);
        italic.setOnCheckedChangeListener(ck_listener);
        reverse.setOnCheckedChangeListener(ck_listener);
        vertical.setOnCheckedChangeListener(ck_listener);

        //拖动改变字体大小
        show_bar_text = (TextView)findViewById(R.id.show_bar_text);
        SeekbarListener sb_listener = new SeekbarListener();
        SeekBar size = (SeekBar)findViewById(R.id.bar_size);
        size.setOnSeekBarChangeListener(sb_listener);
    }

    class RadioListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.radio_color_black:{
                    demo_text.setTextColor(Color.parseColor("#000000"));
                    break;
                }
                case R.id.radio_color_red:{
                    demo_text.setTextColor(Color.parseColor("#ff0000"));
                    break;
                }
                case R.id.radio_color_blue:{
                    demo_text.setTextColor(Color.parseColor("#0000ff"));
                    break;
                }
                case R.id.radio_color_green:{
                    demo_text.setTextColor(Color.parseColor("#00ff00"));
                    break;
                }
            }
        }
    }

    class CheckBoxListener implements CheckBox.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()){
                case R.id.checkbox_style_bold:{
                    demo_text.setText(demo_text.getText()); //不知道为什么，没有对文字的改变，下面的就语句就不生效
                    if(isChecked)demo_text.getPaint().setFakeBoldText(true);
                    else demo_text.getPaint().setFakeBoldText(false);
                    break;
                }
                case R.id.checkbox_style_italic:{
                    demo_text.setText(demo_text.getText()); //不知道为什么，没有对文字的改变，下面的就语句就不生效
                    if(isChecked)demo_text.getPaint().setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                    else demo_text.getPaint().setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    break;
                }
                case R.id.checkbox_style_reverse:{
                    StringBuffer str = new StringBuffer(demo_text.getText().toString());
                    demo_text.setText(str.reverse());
                    break;
                }
                case R.id.checkbox_style_vertical:{
                    if(isChecked)demo_text.setEms(1);
                    else demo_text.setEms(demo_text.getText().toString().length());
                    break;
                }
            }
        }
    }

    class SeekbarListener implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(seekBar.getId()==R.id.bar_size){
                show_bar_text.setText("当前"+progress);
                demo_text.setTextSize(progress);
            }
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}

