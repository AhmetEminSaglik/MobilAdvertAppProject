package com.example.ilanvermemobilprojeodevi.activities.program.mainmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.ilanvermemobilprojeodevi.R;
import com.example.ilanvermemobilprojeodevi.activities.program.fragment.homepage.HomepageFragment;
import com.example.ilanvermemobilprojeodevi.activities.program.fragment.myadvertfragment.MyAdvertFragment;
import com.example.ilanvermemobilprojeodevi.activities.program.fragment.profil.ProfilFragment;
import com.example.ilanvermemobilprojeodevi.db.user.Customer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import java.util.Set;

public class MainMenuActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        // Kayitli id yi alma
        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        String kayitliId = prefs.getString("id", "null");
        String username = prefs.getString("username", "null");
        String phone = prefs.getString("phone", "null");
        String mail = prefs.getString("mail", "null");
        String password = prefs.getString("password", "null");
        String fullName = prefs.getString("fullName", "null");
//        Set<String> set = prefs.getStringSet("set", null);

        Customer customer = new Customer(kayitliId, fullName, username, password, phone, mail);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.itemHome:
                        fragment = new HomepageFragment(MainMenuActivity.this);
                        break;
                    case R.id.itemProfil:
                        fragment = new ProfilFragment(MainMenuActivity.this, customer);
                        break;
                    case R.id.itemMyAdvert:
                        fragment = new MyAdvertFragment(MainMenuActivity.this, customer);
                        break;
                    case R.id.itemExit:
                        Toast.makeText(getBaseContext(), "Loged out safely from application ", Toast.LENGTH_SHORT).show();
                        finish();
                        return false;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();

                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.itemHome);
    }
}