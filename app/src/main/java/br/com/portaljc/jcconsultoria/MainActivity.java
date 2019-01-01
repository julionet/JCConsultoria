package br.com.portaljc.jcconsultoria;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.portaljc.jcconsultoria.fragments.EmpresaFragment;
import br.com.portaljc.jcconsultoria.fragments.PendenteFragment;
import br.com.portaljc.jcconsultoria.utils.Constants;
import br.com.portaljc.jcconsultoria.utils.Preferences;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view_menu);

        View headerView = navigationView.getHeaderView(0);
        TextView textViewUsuario = headerView.findViewById(R.id.text_view_usuario_drawer);
        textViewUsuario.setText(Preferences.getString(getBaseContext(), Constants.USER_LOGIN));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();

                switch (item.getItemId()) {
                    case R.id.navigation_executar:
                        Preferences.setString(getBaseContext(), Constants.FRAGMENT_CURRENT, "E");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EmpresaFragment(), "").commit();
                        break;
                    case R.id.navigation_sincronizar:
                        Preferences.setString(getBaseContext(), Constants.FRAGMENT_CURRENT, "S");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PendenteFragment(), "").commit();
                        break;
                    case R.id.navigation_finalizar:
                        Preferences.setString(getBaseContext(), Constants.FRAGMENT_CURRENT, "");
                        Preferences.setString(getBaseContext(), Constants.USER_LOGIN, "");
                        Preferences.setString(getBaseContext(), Constants.PASSWORD_LOGIN, "");
                        Preferences.setString(getBaseContext(), Constants.PASSWORD_IV, "");
                        finish();
                        break;
                }
                return true;
            }
        });

        updateFrament(Preferences.getString(getBaseContext(), Constants.FRAGMENT_CURRENT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateFrament(String fragment) {
        switch (fragment) {
            case "E":
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new EmpresaFragment(), "").commit();
                break;
            case "S":
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new PendenteFragment(), "").commit();
                break;
        }
    }
}
