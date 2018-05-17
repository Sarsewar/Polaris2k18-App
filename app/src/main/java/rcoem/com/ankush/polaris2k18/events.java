package com.example.ankush.polaris2k18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class events extends AppCompatActivity {
    String type="2",title;
    String slot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collapse);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar12);
      //  setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }
        Intent intent = getIntent();
        ImageView im=(ImageView)findViewById(R.id.llogo);
        TextView text=(TextView)findViewById(R.id.info);
        TextView textm=(TextView)findViewById(R.id.title);

        title = intent.getStringExtra("title");
        slot = intent.getStringExtra("slot");
        int number=Integer.parseInt(slot);
        textm.setText(title);
        if(number==1)
        {
            im.setImageResource(R.drawable.coderelay);
            text.setText("Code-Relay is a coding competition event. It will be a one day competition. This event will have 3 rounds. This competition is a paired coding competition (Team of 2). A team of 2 is mandatory.  \n" +
                    "Round I- This round will be a code debugging round in which a code will be given with some exceptions and errors participants must find the bug (cause of error) and fix it in the given time. The code should satisfy all the test cases then the solution will be accepted. \n" +
                    "In this round we will try to eliminate about 20-30% of the crowd.\n" +
                    "About 10 – 15 codes will be given to a team. They must debug the codes in one hour.\n" +
                    "Selection criteria for next round will be based on number questions solved relatively in one hour. If there is a tie between teams, then number test cases passed by partially correct solutions will be the tie breaker.\n" +
                    "\n" +
                    "\n" +
                    "Round II-This round is a surprise round which will be revealed to participants on the day of competition. Time duration for this round will be about 1.5- 2 hrs. Number of question will be about 10 – 15. We will try to eliminate the 30-40% of the crowd.\n" +
                    "\nRound III- This round is a language independent coding round (teams can choose from C, C++ and java); the difficulty level of questions will be from moderate to high. Number of questions 6-8. Time Duration will be 2hrs.\n" +
                    "Either we will use offline compiler, or we’ll use hackerrank IDE for the same.\n" +
                    "The team which has solved more questions relatively will be declared as winner. \n" +
                    "•\tDate: 24 January 2018\n" +
                    "•\tTime: 12 pm\n" +
                    "•\tVenue: Lab 1 , CSE Department, RCOEM\n" +
                    "•\tEntry fees: Rs.150 per team.\n");
        }
        else if(number==2){
            im.setImageResource(R.drawable.crackjack);
            text.setText("A competition in which participants are asked to decrypt set of questions to reach a goal step by step and test the knowledge and logic of participants in a team. Each team consists of 4 members. Initially all teams will be divided into half, consisting of two members each.\n" +
                    "•\tTeam A (2 members): Each team will get a WinRaR compressed file that will be password protected. For each password a hint in form of image or text is provided to crack the password. Use of internet is allowed. It is mandatory to crack the password at each level to proceed further. The team can solve five questions in this way and will wait for the Team B to get the key of 6th question and proceed further. The one who decrypts all the levels is the winner.\n" +
                    "•\tTeam B (2 members): Each Team will get a set of questions which needs to be solved and there will be one encrypted question which will serve as the key for 6th question of Team A. The faster the team decrypts, sooner they can help their corresponding team. It will be pen-paper round.\n" +
                    "NOTE: Participants need to bring their own laptops and internet connection.\n" +
                    "•\tDate: 24th Jan 2018\n" +
                    "•\tVenue: Main Auditorium\n" +
                    "•\tTime:  12 pm\n" +
                    "•\tEntry Fees: 200/-  team of 4 members\n");
        }
        else if(number==3){
            im.setImageResource(R.drawable.nemesis);
            text.setText("It’s going to be a 2-round competition where, the controllability of your Remote-Controlled Robot will be tested. Rounds will be executed during the Polaris week, 2k18.There are no restrictions, whatsoever, on the technology used for the remote and the Robot. Each team consist of 4 or 5 participants. The rounds are as follows:\n" +
                    "Round 1 -\n" +
                    "This is going to be the simplest round of all where your bot needs to traverse the given arena having obstacles in the least time. This will test the speed as well as the navigational control of the bot. Top 4 teams will qualify for the final round.\n" +
                    "Date: 24 January 2018\n" +
                    "Time: 12 am onwards\n" +
                    "Venue:CS207\n"+
                    "Round 2 -\n" +
                    "This will be a ONE-ON-ONE Competition between all finalists. Teams will compete in knockout soccer competition with their bots.\n" +
                    "Date: 25 January 2017\n" +
                    "Venue:Robowars Base,CSE Department, RCOEM\n"+
                    "Time: 4:30 pm onwards\n" +
                    "Entry fees – 250/- per group.\n" +
                    "Expected entries - 20-25  groups ( max- 5 members/group).\n");
        }
        else if(number==4){
            im.setImageResource(R.drawable.quiz);
            text.setText(" An event which will test the participants on their general knowledge and knowledge about current happenings around the globe.\n" +
                    "Round 1:\n" +
                    "This round will consist of a pen and paper test consisting of 20 questions to test the participants on their general knowledge. The first 10 questions will be MCQs and will carry one mark each. The next nine questions will have one word answers having 2 marks each. The last question will be a tie-breaker in case two teams have the same marks. Top six teams will qualify for round 2.\n" +
                    "\n" +
                    "Round 2:\n" +
                    "In this round, each team will be asked questions one by one and will be awarded marks for every correct answer. There will be no negative marks in this round. The team with the lowest score will be eliminated.\n" +
                    "Round 3:\n" +
                    "The teams will have to choose a category from 3 given categories on which one question will be asked. If the team answers incorrectly, the question will be passed on to the next team. Points will be awarded to the team for every correct answer. The team with the lowest score will be eliminated.\n" +
                    "Round 4:\n" +
                    "Teams will be given 5 Options having varied difficulty levels to choose from. Question will be asked depending on the difficulty level chosen. Points will be awarded for every correct answer based on the difficulty level chosen, with 1 being the lowest and 5 being the highest. The team with the lowest score will be eliminated.\n" +
                    "Round 5:\n" +
                    "The top three teams will qualify for this round. This will be a buzzer round, where the team which buzzes first will get to answer the question. There will be negative marks in this round. The team having the highest cumulative marks at the end of this round will win the event.\n" +
                    "Date: 25 January 2018\n" +
                    "Time : 10 am onwards\n" +
                    "Venue: Class CS 308, CSE Department, RCOEM\n" +
                    "Entry Fees: 100/- (2 members)\n");
        }
        else if(number==5){
            im.setImageResource(R.drawable.hot_heads);
            text.setText(": A fun event which will challenge the mental and physical strength of participants. An event which has physical challenges and games to compete in a team at different stages. 3-4 rounds will be there. All rounds will test your physical and mental strength.\n" +
                    "•\tDate: 25th Jan 2018\n" +
                    "•\tTime: 9 am onwards\n" +
                    "•\tVenue: IT Parking\n" +
                    "•\tEntry Fees: 250/- (4-5 members)\n");
        }
        else if(number==6){
            im.setImageResource(R.drawable.lordofstage);
            text.setText("A literary event which will challenge the knowledge, speaking abilities and creativity of participants in multiple levels. This event will be a golden opportunity for participants with excellent skills to showcase their talent and a learning opportunities for beginners.\n" +
                    "Round 1:This round is an aptitude test which consist of total 30 questions of general knowledge, logical reasoning, grammar, literature and picture perception. This round will cause direct elimination if the score is less than a particular value. The qualified participants head to the second round.\n" +
                    "Round 2: This will be an extempore round where the contestants will have to pick chits for their topics and will get 2 minutes preparation time. \n" +
                    "Marking Scheme: Marks will be deducted if the time limit is exceeded or if ended before time.\n" +
                    "Vocabulary, Confidence and Content will be judged. \n" +
                    "Round 3: This is a formal debate with one side “for” and ”against”. Topic will be given to the participants with 10 minutes preparation time. Participants have to place their respective views on the topic for 2 minutes. At the end there will be a faceoff for 1 minutes between the sides.\n" +
                    "•\tDate: 25th Jan 2018\n" +
                    "•\tTime: 9-1 pm\n" +
                    "•\tVenue: Room no 208, CSE Department, RCOEM \n" +
                    "•\tEntry Fees: 50/-\n" +
                    "\n");
        }

        else if(number==7){
            im.setImageResource(R.drawable.angular);
            text.setText("A Workshop which will guide you through the basics of JavaScript.\n" +
                    "EVENT DETAILS AND STRUCTURE:\n" +
                    "1) 2 days workshop. \n" +
                    "2) Single Person workshop. \n" +
                    "3) The participants get the CSI certificate.\n" +
                    "DATE:\n" +
                    "24.01.2018 and 25.01.2018\n" +
                    "VENUE:\n" +
                    "CSE DEPARTMENT, SHRI RAMDEOBABA COLLEGE OF ENGINEERING AND MANAGEMENT\n" +
                    "ENTRY FEE:\n" +
                    "₹ 400 Only Per Person\n" +
                    "COORDINATORS:\n" +
                    "1)Jay Bawankar:" +
                    "8275867999\n" +
                    "2)Piyush Jain:" +
                    "9425022291\n" +
                    "\n");
        }
        else if(number==8){
            im.setImageResource(R.drawable.csgo);
            text.setText("Event Name: Counter – Strike Global Offensive\n" +
                    "Description: This is a LAN gaming event. It will be a one day competition. This event will have rounds depending on the number of teams. This competition is a team competition (Team of 5).  \n" +
                    "Round Details: \n" +
                    "\tThis event will be held in a knockout style tournament. Fixtures will be decided on the basis of number of teams and it will be held in a tournament fashion. Two teams will be pitted against each other in a match which lasts around 30 minutes and the winning team will advance to the next stage. At the end of each stage half of the teams will be eliminated. In case of a tie, there will be a rematch. \n" +
                    "\n" +
                    "Student Co-ordinator Details:\n" +
                    "Name: Ameya Yerpude\n" +
                    "Mobile no.: 9545942302\n" +
                    "Email-Id: yerpudear@rknec.edu\n" +
                    "Name: Siddharth Khandelwal\n" +
                    " Mobile no.: 9075123486\n" +
                    "Email-Id: khandelwals@rknec.edu\n\n" +

                    "Date:  27/01/2018\n" +
                    "Venue:   Accenture Lab (CSE department)\n" +
                    "Time:   9 a.m.  to  4 p.m.      \n \n" +
                    "Entry Fees:  250Rs (per team)\n"
                    );
        }
        else if(number==9){
            im.setImageResource(R.drawable.python);
            text.setText("Instruction\n" +
                    "Lunch is provided to each participant.\n" +
                    "Accommodation is provided (extra charges: 100/day).\n" +
                    "\n" +
                    "Date:  24-25 January 2018\n" +
                    "Timing: 9:00 am onwards\n" +
                    "Venue: Lab 1, lab 2 and Accenture lab, CSE Department, RCOEM\n" +
                    "Entry fees: Rs.400 per person\n" +
                    "\n" +
                    "\n" +
                    "\n");
        }
        else if(number==10){
            im.setImageResource(R.drawable.quiz);
            text.setText(" An event which will test the participants on their general knowledge and knowledge about current happenings around the globe.\n" +
                    "Round 1:\n" +
                    "This round will consist of a pen and paper test consisting of 20 questions to test the participants on their general knowledge. The first 10 questions will be MCQs and will carry one mark each. The next nine questions will have one word answers having 2 marks each. The last question will be a tie-breaker in case two teams have the same marks. Top six teams will qualify for round 2.\n" +
                    "\n" +
                    "Round 2:\n" +
                    "In this round, each team will be asked questions one by one and will be awarded marks for every correct answer. There will be no negative marks in this round. The team with the lowest score will be eliminated.\n" +
                    "Round 3:\n" +
                    "The teams will have to choose a category from 3 given categories on which one question will be asked. If the team answers incorrectly, the question will be passed on to the next team. Points will be awarded to the team for every correct answer. The team with the lowest score will be eliminated.\n" +
                    "Round 4:\n" +
                    "Teams will be given 5 Options having varied difficulty levels to choose from. Question will be asked depending on the difficulty level chosen. Points will be awarded for every correct answer based on the difficulty level chosen, with 1 being the lowest and 5 being the highest. The team with the lowest score will be eliminated.\n" +
                    "Round 5:\n" +
                    "The top three teams will qualify for this round. This will be a buzzer round, where the team which buzzes first will get to answer the question. There will be negative marks in this round. The team having the highest cumulative marks at the end of this round will win the event.\n" +
                    "Date: 25 January 2018\n" +
                    "Time : 10 am onwards\n" +
                    "Venue: Class CS308, CSE Department, RCOEM\n" +
                    "Entry Fees: 100/- (2 members)\n");
        }
        else if(number==11){
            im.setImageResource(R.drawable.project);
            text.setText("An Event providing you a platform to showcase your project ideas and working models for reviews by companies and college authorities." +
                    "EVENT DETAILS AND STRUCTURE\n" +
                    "1) Each team need to present their project in front of judges.\n" +
                    "2) The best will be chosen for cash prize\n" +
                    "DATE:" +
                    "24.01.2018\n" +
                    "ENTRY FEE\n" +
                    "Venue:CS208,CSE Dept\n" +
                    "₹ 500/- Per Team");
        }
        getSupportActionBar().setTitle(title);



        }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}

