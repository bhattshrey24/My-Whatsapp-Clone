package com.example.mywhatsappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mywhatsappclone.Adapters.TabLayoutFragAdapter;
import com.example.mywhatsappclone.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

//Todo
// Add Recycler list in calls fragment and chats fragment
// phone authentication using firebase phone authentication
// Add Logic For retrieving ChatListItems from Firestore and after that dont forget to put intent filter back to signing page
// Create a splash screen with just whatsapp image in between and in that check if user is signed in or not if yes then send to main activity else send to phonenumberinputpage activity
// Add another screen where you take personal details of the user so that you can create it in users
// Fix that everytime we come to chats fragment it fetches users again which is not needed
// Sort the chat list recycler based on 'CREATED AT' attribut which should be in both  oneToOne and Rooms
// Add Room and mvvm
// Instead of just doing fetch put a firestore listener in chat fragment recycler too like we used in ChatScreen so that it doesnt update everytime and only when new connection is made or new message came (i.e last message changed maybe)
// VERY IMPORTANT- never forget to do .trim() if u are reading or writing on Firestore like if you are reading from firestore like db.collection(id.trim())
// MAKE SURE YOU DONT KEEP ANY ACTIVE LISTNER WHEN ITS NOT NEEDED OTHER WISE IT WILL DRAIN BATTERY SO ALWAYS DISABLE IT WHEN NOT IN USE
// Right now i have done jugad for color of names in group chat but later save the color generated randomly in the sql and not on firebase cause color is specific to a user only
// I'll have to change use recycle view for friends chat screen instead of just using xml and here also i have to use different layouts for different items like i did in chat

//Todo Today
// Group Functionality (Done)
// color feature of name in groups (Done)
// new activity open when someone touches username in chat screen
// add actual menu instead of dummy
// add feature or linking contact and sending message ang basically creating node
public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.viewPager.setAdapter(new TabLayoutFragAdapter(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

    }
}