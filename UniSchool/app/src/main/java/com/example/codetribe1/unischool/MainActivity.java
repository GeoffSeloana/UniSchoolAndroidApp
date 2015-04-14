package com.example.codetribe1.unischool;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codetribe1.unischool.dto.StudentDTO;
import com.example.codetribe1.unischool.providers.StudentsContentProviderUtil;
import com.example.codetribe1.unischool.util.Util;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    Button signin;
    Button register;
    Button existing;
    EditText txtFirstName;
    EditText txtMiddleName;
    EditText txtLastName;
    TextView txtEmail;
    TextView txtschoolID;
    TextView txtgradeID;
    TextView txtEMAIL;
    Button registerStudent;
    Context ctx;
    Activity activity;
    ImageView banner;
    String emailreal;
    String school;
    String grade;
    String email="";
    TextView login_username;
    TextView txtpassword1;
    TextView txtpassword2;
    EditText login_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting all the veiws needed to register student
        txtFirstName = (EditText) findViewById(R.id.reg_firstname);
        txtMiddleName = (EditText) findViewById(R.id.reg_middlename);
        txtLastName = (EditText) findViewById(R.id.reg_lastname);
        txtEmail = (TextView) findViewById(R.id.SI_txtEmail);
        txtschoolID = (TextView) findViewById(R.id.reg_schoolID);
        txtgradeID = (TextView) findViewById(R.id.reg_gradeID);
        registerStudent = (Button) findViewById(R.id.reg_BTN);
        txtEMAIL = (TextView) findViewById(R.id.SI_txtEmail);
        login_username = (TextView) findViewById(R.id.login_username);
        txtpassword1 = (TextView) findViewById(R.id.REG_password);
        txtpassword2 = (TextView) findViewById(R.id.REG_Conf_password);
        login_password = (EditText) findViewById(R.id.login_password);
        activity = this;
        ctx = getApplicationContext();
        getEmail();
        banner = (ImageView) findViewById(R.id.image);



        //--------------------------------register button onclick------------------------------------------------------
        registerStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //-----------------------------------------------registration for validation---------------------------------------
                if(txtFirstName.getText().toString().length()==0){
                    txtFirstName.setError("first name required");
                }else{txtFirstName.setError(null);}

                if(txtLastName.getText().toString().length()==0){
                    txtLastName.setError("last name required");
                }else{txtLastName.setError(null);}

                if(txtEMAIL.getText().toString().equals("Select Email") ){
                    txtEMAIL.setError("email required!");
                }else{txtEMAIL.setError(null);}

                if(txtpassword1.getText().toString().equals(txtpassword2.getText().toString()) ){
                    if(txtpassword1.getText().toString().equals("") && txtpassword2.getText().toString().equals("")){
                        txtpassword1.setError("Insert password");
                        txtpassword2.setError("Insert password");
                    }else{
                        txtpassword1.setError(null);
                        txtpassword2.setError(null);
                    }

                }else{txtpassword1.setError("passwords do not match!");
                    txtpassword2.setError("passwords do not match!");}

                if(txtschoolID.getText().toString().equals("Select School") ){
                    txtschoolID.setError("school required!");
                }else{txtschoolID.setError(null);}

                if(txtgradeID.getText().toString().equals("Select Grade") ){
                    txtgradeID.setError("grade required!");
                }else{txtgradeID.setError(null);}


                //-----------if all the fields are entered then this will execute-----------------------------------
                if(txtFirstName.getError() == null  &&
                        txtLastName.getError()==null &&
                        txtEMAIL.getError()==null &&
                        txtpassword1.getError()==null &&
                        txtpassword2.getError()==null &&
                        txtschoolID.getError()==null &&
                        txtgradeID.getError()==null){

                    Toast.makeText(getApplicationContext(),"Now go to the next page",Toast.LENGTH_LONG).show();
                    String firstName = txtFirstName.getText().toString();
                    String middleName = txtMiddleName.getText().toString();
                    String lastName = txtLastName.getText().toString();
                    email = txtEmail.getText().toString();
                    String school = txtschoolID.getText().toString();
                    String grade = txtgradeID.getText().toString();
                    String passwordOne = txtpassword1.getText().toString();

                    //instantial object
                    StudentDTO student = new StudentDTO(null,firstName,middleName,lastName,passwordOne,school,grade,email);
                    //add this to CP
                    StudentsContentProviderUtil.addStudent(getContentResolver(), student);

                    RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.register_form);
                    RelativeLayout login_form = (RelativeLayout) findViewById(R.id.login_form);

                    mainLayout.setVisibility(View.GONE);
                    login_form.setVisibility(View.VISIBLE);

                }
            }
        });

        //---------Logo Animation--------------Logo Animation------------------
        final View logo = findViewById(R.id.logo);
        Animation fadeIn = new AlphaAnimation(0f, 10f);
        fadeIn.setDuration(5000);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                logo.setVisibility(View.VISIBLE);
            }
        });

        logo.startAnimation(fadeIn);
        logo.setVisibility(View.VISIBLE);
        //--------***------***-----***-------***------***-------
        Button btnSignIn = (Button) findViewById(R.id.REG_btnExisting);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        existing = (Button) findViewById(R.id.REG_btnExisting);
        existing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main);
                RelativeLayout login_form = (RelativeLayout) findViewById(R.id.login_form);

                mainLayout.setVisibility(View.GONE);
                login_form.setVisibility(View.VISIBLE);

            }
        });

        register = (Button) findViewById(R.id.REG_btnNewGroup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main);
                RelativeLayout reg_form = (RelativeLayout) findViewById(R.id.register_form);

                mainLayout.setVisibility(View.GONE);
                reg_form.setVisibility(View.VISIBLE);

            }
        });

        //--------------------------------------------login screen signin button onclick-------------------------------------

        signin = (Button) findViewById(R.id.login_BTN);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //-----------------------------------------------login validation---------------------------------------
                if(login_username.getText().toString().equals("Select Email") ){
                    login_username.setError("email required!");
                }else{
                    login_username.setError(null);
                }
                if( login_password.getText().toString().length() == 0 ){
                    login_password.setError( "password required!" );
                }

                if(login_username.getError() !="email required!" && login_password.getError() !="password required!"){
                    startActivity(new Intent(getApplicationContext(), NewsActivity.class));
                }
            }
        });

        //-----------------------------------select email onclick listener------------------------------------
        txtEMAIL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Util.flashOnce(txtEMAIL, 100, new Util.UtilAnimationListener() {
                    @Override
                    public void onAnimationEnded() {
                        Util.showPopupBasicWithHeroImage(ctx, activity, tarList,
                                banner, ctx.getString(R.string.select_email),
                                new Util.UtilPopupListener() {
                                    @Override
                                    public void onItemSelected(int index) {
                                        //selected
                                        emailreal = tarList.get(index);
                                        txtEMAIL.setText(emailreal);
                                    }
                                });
                    }
                });

            }
        });
        //----------------------------------select email onclick listener--------------------------------------
        login_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Util.flashOnce(login_username, 100, new Util.UtilAnimationListener() {
                    @Override
                    public void onAnimationEnded() {
                        Util.showPopupBasicWithHeroImage(ctx, activity, tarList,
                                banner, ctx.getString(R.string.select_email),
                                new Util.UtilPopupListener() {
                                    @Override
                                    public void onItemSelected(int index) {
                                        //selected
                                        emailreal = tarList.get(index);
                                        login_username.setText(emailreal);
                                    }
                                });
                    }
                });

            }
        });
        //-------------------------------------select school onclick listener-----------------------------------

        final List<String> schoolList = new ArrayList<>();
        schoolList.add("Global Wisdom International High School");
        schoolList.add("Little Bells E.M High School");
        schoolList.add("Loyola Public High School");
        schoolList.add("Vukani Mawethu High School");
        schoolList.add("Nellmapius Secondary High School");


        txtschoolID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Util.flashOnce(txtschoolID, 100, new Util.UtilAnimationListener() {
                    @Override
                    public void onAnimationEnded() {
                        Util.showPopupBasicWithHeroImage(ctx, activity, schoolList,
                                banner, ctx.getString(R.string.select_school),
                                new Util.UtilPopupListener() {
                                    @Override
                                    public void onItemSelected(int index) {
                                        //selected
                                        school = schoolList.get(index);
                                        txtschoolID.setText(school);
                                    }
                                });
                    }
                });

            }
        });
        //----------------------------------------select grade onclick listener-------------------------------------
        final List<String> gradeList = new ArrayList<>();
        gradeList.add("One");
        gradeList.add("Two");
        gradeList.add("Three");
        gradeList.add("Four");
        gradeList.add("Five");
        gradeList.add("Six");
        gradeList.add("Seven");
        gradeList.add("Eight");
        gradeList.add("Nine");
        gradeList.add("Ten");
        gradeList.add("Eleven");
        gradeList.add("Twelve");

        txtgradeID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Util.flashOnce(txtgradeID, 100, new Util.UtilAnimationListener() {
                    @Override
                    public void onAnimationEnded() {
                        Util.showPopupBasicWithHeroImage(ctx, activity, gradeList,
                                banner, ctx.getString(R.string.select_grade),
                                new Util.UtilPopupListener() {
                                    @Override
                                    public void onItemSelected(int index) {
                                        //selected
                                        grade = gradeList.get(index);
                                        txtgradeID.setText(grade);
                                    }
                                });
                    }
                });

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getEmail() {
        AccountManager am = AccountManager.get(getApplicationContext());
        Account[] accts = am.getAccounts();
        if (accts.length == 0) {
            Toast.makeText(getApplicationContext(), "No Accounts found. Please create one and try again ", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        if (accts != null) {
            tarList.add("Please select account");
            for (int i = 0; i < accts.length; i++) {
                tarList.add(accts[i].name);
            }


        }

    }

    ArrayList<String> tarList = new ArrayList<String>();
}
